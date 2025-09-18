package bsu.edu.cs;

import org.json.JSONArray;
import org.json.JSONObject;

public class FormatData {

    protected String jsonString = null;

    public static void main(String[] args) {

    }//end main

    public void run(){

        JSONObject root = new JSONObject(jsonString);
        JSONObject query = root.getJSONObject("query");
        JSONObject pages = query.getJSONObject("pages");

        // Get first page key
        String pageKey = pages.keys().next();
        JSONObject page = pages.getJSONObject(pageKey);

        // If page is missing (Wikipedia API returns "missing": true or pageid = -1)
        if (pageKey.equals("-1") || page.has("missing")) {
            System.out.println("⚠️ Article not found.");
            return;
        }

        // Title
        String title = page.getString("title");
        System.out.println("Title: " + title);

        // Redirects
        if (query.has("redirects")) {
            JSONArray redirects = query.getJSONArray("redirects");
            for (int i = 0; i < redirects.length(); i++) {
                JSONObject r = redirects.getJSONObject(i);
                System.out.println("Redirect: " + r.getString("from") +
                        " → " + r.getString("to"));
            }
        }

        // Revisions
        System.out.println("\nRevisions:");
        if (page.has("revisions")) {
            JSONArray revisions = page.getJSONArray("revisions");
            for (int i = 0; i < revisions.length(); i++) {
                JSONObject rev = revisions.getJSONObject(i);
                String user = rev.getString("user");
                String timestamp = rev.getString("timestamp");
                boolean anon = rev.has("anon");
                if (anon) {
                    System.out.println("- " + timestamp + ": " + user + " (anonymous)");
                } else {
                    System.out.println("- " + timestamp + ": " + user);
                }
            }
        }
    }


    public void setJsonString(String tempString) {
        jsonString = tempString;
    }//end setJsonString

}//end class