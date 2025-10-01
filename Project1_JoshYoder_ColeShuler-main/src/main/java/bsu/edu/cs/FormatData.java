package bsu.edu.cs;

import org.json.JSONArray;
import org.json.JSONObject;

public class FormatData {

    public String processJson(String jsonData) {
        JSONObject root = new JSONObject(jsonData);
        HandleExceptions exception = new HandleExceptions();
        StringBuilder sb = new StringBuilder();
        SortRevisions sort  = new SortRevisions();

        exception.checkForArticle(root);

        sb.append(formatRedirect(root));
        //sb.append(formatTitle(root));
        sb.append(sort.sortRevisions(jsonData));

        return sb.toString();
    }//end processJson

    public String formatRedirect(JSONObject root) {
        StringBuilder sb = new StringBuilder();
        JSONObject query = root.getJSONObject("query");

        if (!query.has("redirects")) return "";

        JSONArray redirects = query.getJSONArray("redirects");

        for (int i = 0; i < redirects.length(); i++) {
            JSONObject redirect = redirects.getJSONObject(i);
            String from = redirect.getString("from");
            String to = redirect.getString("to");
            sb.append("From: ").append(from).append(" To: ").append(to).append("\n");
        }//end for
        return sb.toString();
    }//end formatRedirect

    public String formatTitle(JSONObject root) {
        StringBuilder sb = new StringBuilder();
        JSONObject query = root.getJSONObject("query");
        JSONArray pages = query.getJSONArray("pages");
        JSONObject page = pages.getJSONObject(0);

        String title = page.getString("title");
        sb.append("Title: ").append(title).append("\n");

        return sb.toString();
    }//end formatTitle

}//end class