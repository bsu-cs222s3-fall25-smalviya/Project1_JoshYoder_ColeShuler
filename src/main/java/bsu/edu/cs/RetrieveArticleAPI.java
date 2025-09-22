package bsu.edu.cs;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class RetrieveArticleAPI {

    public String retrieveArticleDataFromAPI(String article) throws IOException {
        URL url = createURL(article);
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Revision Reporter/0.2 josh/yoder@bsu.edu");
            InputStream inputStream = connection.getInputStream();
            System.out.print("\nArticle Successfully Retrieved:\n");
            return new String(inputStream.readAllBytes());
        }//end try

        catch (IOException e) {
            throw new IOException("Error: Connection Failure", e);
        }//end catch

    }//end retrieveRevisionsFormAPI

    public URL createURL(String articleSearch) throws IOException {
        String encodedSearch = URLEncoder.encode(articleSearch, StandardCharsets.UTF_8);
        String apiSearchURL = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" +
                encodedSearch + "&redirects=1&formatversion=2&rvprop=timestamp%7Cuser&rvlimit=15";
        return URI.create(apiSearchURL).toURL();
    }//end createURL

}//end class
