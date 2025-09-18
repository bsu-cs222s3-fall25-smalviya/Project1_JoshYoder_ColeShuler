package bsu.edu.cs;

import org.json.JSONArray;
import org.json.JSONObject;

public class FormatData {
    public static void main(String[] args) {
        // Example JSON string (this one has a page, change pageid to -1 to test missing)
        String jsonString = """
        {
          "continue": {
            "rvcontinue": "20250712175843|1300173523",
            "continue": "||"
          },
          "query": {
            "redirects": [
              {"from": "Skyrim", "to": "The Elder Scrolls V: Skyrim"}
            ],
            "pages": {
              "30014712": {
                "pageid": 30014712,
                "ns": 0,
                "title": "The Elder Scrolls V: Skyrim",
                "revisions": [
                  {"user": "JJMC89 bot III", "timestamp": "2025-09-18T13:43:36Z"},
                  {"user": "2600:4040:5A6F:3700:E0ED:6CA1:C93A:9409", "anon": "", "timestamp": "2025-09-04T20:00:46Z"},
                  {"user": "GreenC bot", "timestamp": "2025-08-25T21:55:57Z"},
                  {"user": "Dabmasterars", "timestamp": "2025-08-14T15:54:22Z"}
                ]
              }
            }
          }
        }
        """;

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
}
