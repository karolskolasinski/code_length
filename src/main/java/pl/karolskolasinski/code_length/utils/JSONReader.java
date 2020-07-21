package pl.karolskolasinski.code_length.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class JSONReader {

    /**
     * Reads the page content and appends to the builder.
     *
     * @param sb:         new instance of StringBuilder.
     * @param URLAddress: URL for read by InputStreamReader.
     */
    void readJSONFromURLByStringBuilder(StringBuilder sb, String URLAddress, String token) throws IOException {
        URL url = new URL(URLAddress);

        if (token != null) {
            connectWithToken(sb, token, url);
            return;
        }

        appendLinesToStringBuilder(sb, url.openStream());
    }


    private void connectWithToken(StringBuilder sb, String token, URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Authorization", "token " + token);
        appendLinesToStringBuilder(sb, httpURLConnection.getInputStream());
    }


    private void appendLinesToStringBuilder(StringBuilder sb, InputStream inputStream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        reader.lines().forEach(sb::append);
    }

}
