package pl.karolskolasinski.code_length.model;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

import java.util.List;

@Getter
public class SingleRepo {

    @SerializedName("sha")
    private String sha;


    @SerializedName("url")
    private String URL;


    @SerializedName("tree")
    private List<Tree> tree;


    @SerializedName("truncated")
    private boolean truncated;

}
