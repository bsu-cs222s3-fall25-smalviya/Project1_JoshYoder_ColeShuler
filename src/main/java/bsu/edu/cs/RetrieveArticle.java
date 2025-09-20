package bsu.edu.cs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class RetrieveArticle {

    public static void main(String[] args) throws IOException, URISyntaxException {
        URLConnection connection = connectToWikipedia("Einstein");
        String jsonData = readJsonAsStringFrom(connection);
        printRawJson(jsonData);
    }//end main


    public String run(String articleName) throws IOException, URISyntaxException {
        URLConnection connection = connectToWikipedia(articleName);
        return readJsonAsStringFrom(connection);
    }//end run


    private static URLConnection connectToWikipedia(String articleName) throws IOException, URISyntaxException {
        String encodedUrlString = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" +
                URLEncoder.encode(articleName, Charset.defaultCharset()) +
                "&rvprop=timestamp" + URLEncoder.encode("|",Charset.defaultCharset()) + "user&rvlimit=15&redirects";
        URI uri = new URI(encodedUrlString);
        URLConnection connection = uri.toURL().openConnection();
        connection.setRequestProperty("User-Agent",
                "FirstProject/0.1 (academic use; https://example.com)");
        connection.connect();
        return connection;
    }//end connectToWikipedia


    private static String readJsonAsStringFrom(URLConnection connection) throws IOException {
        return new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());
    }//end readJsonAsStringFrom


    private static void printRawJson(String jsonData) {
        System.out.println(jsonData);
    }

}//end class
