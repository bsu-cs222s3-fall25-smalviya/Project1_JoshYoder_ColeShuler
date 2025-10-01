package bsu.edu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;


public class FormatDataTest {

    private String sampleJson = """
{"continue":{"rvcontinue":"20250813205253|1305739046","continue":"||"},"query":{"normalized":[{"fromencoded":false,"from":"sinatra","to":"Sinatra"}],"redirects":[{"from":"Sinatra","to":"Frank Sinatra"}],"pages":[{"pageid":11181,"ns":0,"title":"Frank Sinatra","revisions":[{"user":"BRONX BUILT","timestamp":"2025-09-20T20:22:38Z"}]}]}}
""";
    private String fullOutput = "Redirect: Sinatra to Frank Sinatra\nTitle: Frank Sinatra\nRevision: BRONX BUILT 2025-09-20T20:22:38Z\nRevision: BRONX BUILT 2025-09-20T20:19:43Z\nRevision: BRONX BUILT 2025-09-20T20:18:44Z\nRevision: GreenC bot 2025-09-16T09:31:46Z\nRevision: GreenC bot 2025-09-11T22:47:51Z\nRevision: 2600:8800:311B:4100:19D7:D5EA:B4C4:8E87 2025-09-11T14:14:25Z\nRevision: 2600:8800:311B:4100:19D7:D5EA:B4C4:8E87 2025-09-11T14:05:53Z\nRevision: 2601:380:8386:310:69D5:8F87:C46C:AF70 2025-09-10T11:27:34Z\nRevision: 193.82.228.240 2025-09-09T09:20:57Z\nRevision: Merakl? bir zat 2025-09-03T22:26:47Z\nRevision: 2404:4404:412E:6B00:55A2:F36C:AC6D:4DE3 2025-09-01T08:23:58Z\nRevision: Urueu 2025-08-29T12:53:34Z\nRevision: Cinemaniac86 2025-08-21T23:08:27Z\nRevision: Stillgotgame 2025-08-21T19:44:22Z\nRevision: 86.47.90.196 2025-08-19T12:28:56Z";

    @Test
    public void formatTitle(){
        FormatData fd = new FormatData();
        JSONObject root = new JSONObject(sampleJson);
        Assertions.assertEquals("Title: Frank Sinatra\n", fd.formatTitle(root));
    }//end formatUserAndTimeTest

    @Test
    public void formatRedirect(){
        FormatData fd = new FormatData();
        JSONObject root = new JSONObject(sampleJson);
        Assertions.assertEquals("Redirect: Sinatra to Frank Sinatra\n", fd.formatRedirect(root));
    }//end formatUserAndTimeTest

    @Test
    public void formatRevisions(){
        FormatData fd = new FormatData();
        JSONObject root = new JSONObject(sampleJson);
        Assertions.assertEquals("Revision: BRONX BUILT 2025-09-20T20:22:38Z\n",fd.formatRevisions(root));
    }//end formatRevisions
}//end FormatDataTEst
