package bsu.edu.cs;

import org.json.JSONArray;
import org.json.JSONObject;

public class FormatData {

    public String processJson(String jsonData) {
        JSONObject root = new JSONObject(jsonData);
        HandleExceptions exception = new HandleExceptions();
        StringBuilder sb = new StringBuilder();

        exception.checkForArticle(root);

        sb.append(formatRedirect(root));
        sb.append(formatTitle(root));
        sb.append(formatRevisions(root));

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

    public String formatRevisions(JSONObject root) {
        StringBuilder sb = new StringBuilder();
        JSONObject query = root.getJSONObject("query");
        JSONArray pages = query.getJSONArray("pages");

        for (int i = 0; i < pages.length(); i++) {
            JSONObject page = pages.getJSONObject(i);
            if (!page.has("revisions")) continue;

            JSONArray revisions = page.getJSONArray("revisions");
            for (int j = 0; j < revisions.length(); j++) {
                JSONObject revision = revisions.getJSONObject(j);
                String user = revision.getString("user");
                String time =  revision.getString("timestamp");
                sb.append(j + 1).append(" ").append(time).append("  ").append(user).append("\n");
            }//end for
        }//end for
        return sb.toString();
    }//end formatRevisions

}//end class