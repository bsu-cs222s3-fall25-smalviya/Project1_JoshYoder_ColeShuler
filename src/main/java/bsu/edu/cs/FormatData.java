package bsu.edu.cs;

import org.json.JSONObject;

public class FormatData {

    // Public entry point: calls all steps in order
    public static void processJson(String jsonString) {
        JSONObject root = new JSONObject(jsonString);
        JSONObject query = root.getJSONObject("query");
        JSONObject pages = query.getJSONObject("pages");

        String pageKey = getFirstPageKey(pages);
        JSONObject page = pages.getJSONObject(pageKey);

        if (isMissingPage(pageKey, page)) {
            System.out.println("Article not found.");
            return;
        }//end if

        printTitle(page);
        printRedirects(query);
        printRevisions(page);
    }//end processJson

    public static String getFirstPageKey(JSONObject pages) {
        return pages.keys().next();
    }

    public static boolean isMissingPage(String pageKey, JSONObject page) {
        return pageKey.equals("-1") || page.has("missing");
    }

    public static void printTitle(JSONObject page) {
        String title = page.getString("title");
        System.out.println("Title: " + title);
    }

    public static void printRedirects(JSONObject query) {
        if (query.has("redirects")) {
            var redirects = query.getJSONArray("redirects");
            for (int i = 0; i < redirects.length(); i++) {
                var r = redirects.getJSONObject(i);
                System.out.println("Redirect: " + r.getString("from") +
                        " → " + r.getString("to"));
            }
        }
    }

    public static void printRevisions(JSONObject page) {
        System.out.println("\nRevisions:");
        if (page.has("revisions")) {
            var revisions = page.getJSONArray("revisions");
            for (int i = 0; i < revisions.length(); i++) {
                printRevision(revisions.getJSONObject(i));
            }
        }
    }

    public static void printRevision(JSONObject rev) {
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