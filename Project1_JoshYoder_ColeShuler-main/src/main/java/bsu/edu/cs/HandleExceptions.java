package bsu.edu.cs;

import org.json.JSONArray;
import org.json.JSONObject;

public class HandleExceptions {

    public void checkForArticleName(String articleName) {
        if(articleName.isEmpty()){
            System.err.print("\nSystem error: No Article Name entered. \nTerminating Application...");
            System.exit(0);
        }//end if
    }//end checkForArticleName

    public void checkForArticle(JSONObject root) {
        JSONObject query = root.getJSONObject("query");
        JSONArray pages = query.getJSONArray("pages");
        JSONObject page = pages.getJSONObject(0);
        if(page.has("missing") && page.getBoolean("missing")) {
            System.err.print("Page not found\n");
            System.exit(0);
        }//end if
    }//end checkForArticle

}//end class