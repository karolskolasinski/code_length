package pl.karolskolasinski.code_length.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

class JSONReader {

    /**
     *
     */
    void readJSONFromURLByStringBuilder(StringBuilder sb, String URLAddress) throws IOException {
        URL url = new URL(URLAddress);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        reader.lines().forEach(sb::append);
    }
}
