package bsu.edu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class RetrieveArticleAPITest {

    @Test
    public void testCreateURL() throws IOException {
        RetrieveArticleAPI retriever = new RetrieveArticleAPI();
        String result = String.valueOf(retriever.createURL("Zappa"));
        Assertions.assertEquals("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=Zappa" +
                "&redirects=1&formatversion=2&rvprop=timestamp%7Cuser&rvlimit=15", result);
    }//end test
    @Test
    public void testRetrieveRevisionsFromAPI() throws IOException {
        RetrieveArticleAPI retriever = new RetrieveArticleAPI();
        boolean testFail = true;
        String search = "Zappa";
        String data = retriever.retrieveRevisionsFromAPI(search);
        if (!data.isEmpty()) {
            testFail = false;
        }//end if
        Assertions.assertFalse(testFail);
    }//end testRetrieveRevisionsFromAPI

    //No Internet Connection Needed For test
     @Test
    public void testConnectionFailure(){
        RetrieveArticleAPI retriever = new RetrieveArticleAPI();
        String search = "Zappa";
        Assertions.assertThrows(IOException.class, () -> retriever.retrieveRevisionsFromAPI(search));
    }//end testConnectionFailure

}//end RetrieveArticleAPITest
