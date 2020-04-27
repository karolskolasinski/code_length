package pl.karolskolasinski.code_length;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.karolskolasinski.code_length.controller.IndexController;
import pl.karolskolasinski.code_length.model.UserRepos;
import pl.karolskolasinski.code_length.model.dto.ObjectToDisplay;
import pl.karolskolasinski.code_length.model.dto.UserCodeLength;
import pl.karolskolasinski.code_length.repository.UserCodeLengthRepository;
import pl.karolskolasinski.code_length.service.UserCodeLengthService;
import pl.karolskolasinski.code_length.utils.NumberOfReposUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("IndexController Test")
class IndexControllerTest {

    @Mock
    private UserCodeLengthService uclService;

    @Mock
    private NumberOfReposUtil numberOfReposUtil;

    @Mock
    private UserCodeLengthRepository userCodeLengthRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        IndexController indexController = new IndexController(uclService, numberOfReposUtil);
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
    }

    @ParameterizedTest
    @MethodSource("expectedTop10List")
    @DisplayName("should return index page with status OK")
    void index_shouldReturnIndexPageWithStatusOk(List<UserCodeLength> top10) throws Exception {
        //when
        when(uclService.top10()).thenReturn(top10);

        //then
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("top10"))
                .andExpect(model().attribute("top10", top10));
    }

    private static Stream<Arguments> expectedTop10List() {
        List<UserCodeLength> emptyList = new ArrayList<>();
        List<UserCodeLength> oneElementList = new ArrayList<>();
        oneElementList.add(new UserCodeLength("username", 3, 10.0, "PHP"));

        return Stream.of(
                Arguments.of(emptyList),
                Arguments.of(oneElementList)
        );
    }

    @ParameterizedTest
    @MethodSource("incorrectNames")
    @DisplayName("should return an error message when the username is incorrect")
    void index_shouldReturnAnErrorMessageWhenTheUsernameIsIncorrect(String name) throws Exception {
        //given
        UserCodeLengthService userCodeLengthService = new UserCodeLengthService(userCodeLengthRepository);

        //when
        when(uclService.incorrectUsername(name)).thenReturn(userCodeLengthService.incorrectUsername(name));

        //then
        mockMvc.perform(post("/get")
                .param("username", name)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("username", name))
                .andExpect(status().isOk())
                .andExpect(model().attribute("errorMessage", "You need to enter a username."))
                .andExpect(view().name("index"));
    }


    private static Stream<Arguments> incorrectNames() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of(" "),
                Arguments.of("aaa bbb"),
                Arguments.of("aaa%"),
                Arguments.of("#aaa"),
                Arguments.of("aaa@aaa"),
                Arguments.of("aaa@aaa.aa"),
                Arguments.of("aaa.aa"),
                Arguments.of("aaa "),
                Arguments.of("aaa #")
        );
    }

    @Test
    @DisplayName("should return an error message when the user is not found")
    void index_shouldReturnAnErrorMessageWhenTheUserHasNoPublicRepositories() throws Exception {
        //given
        String username = "givenUser";
        ObjectToDisplay userToTest = new ObjectToDisplay(username, -2, 10.0, "PHP", new ArrayList<>(), new ArrayList<>());

        //when
        when(uclService.getUserDetails(username)).thenReturn(userToTest);
        when(numberOfReposUtil.getNumberOfPublicRepos(username)).thenReturn(userToTest.getNumberOfPublicRepos());

        //then
        mockMvc.perform(post("/get")
                .param("username", username)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("username", username))
                .andExpect(status().isOk())
                .andExpect(model().attribute("errorMessage", "User not found."))
                .andExpect(view().name("index"));
    }

    @Test
    @DisplayName("should return ObjectToDisplay when providing username")
    void index_shouldReturnObjectToDisplayWhenProvidingUsername() throws Exception {
        //given
        String username = "givenUser";
        int numberOfPublicRepos = 4;
        double length = 10.0;
        String language = "PHP";
        List<String> reposNames = new ArrayList<>();
        reposNames.add("code_length");
        Collection<UserRepos> userRepos = new ArrayList<>();
        ObjectToDisplay userToTest = new ObjectToDisplay(username, numberOfPublicRepos, length, language, reposNames, userRepos);

        //when
        when(uclService.getUserDetails(username)).thenReturn(userToTest);

        //then
        mockMvc.perform(post("/get")
                .param("username", username)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("username", username))
                .andExpect(status().isOk())
                .andExpect(model().attribute("numberOfPublicRepos", numberOfPublicRepos))
                .andExpect(model().attribute("length", length))
                .andExpect(model().attribute("language", language))
                .andExpect(model().attribute("repos", reposNames))
                .andExpect(view().name("result"));
    }
}
