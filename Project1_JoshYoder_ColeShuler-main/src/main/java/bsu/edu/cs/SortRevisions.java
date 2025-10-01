package bsu.edu.cs;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.Instant;
import java.util.ArrayList;

public class SortRevisions {

    private final ArrayList<revision> revisions = new ArrayList<>();


    public static class revision{
        String user;
        String time;

        public String getString(){
            return time + "  " + user;
        }//end getString
    }//end static class revision

    public String sortRevisions(String jsonData){
        StringBuilder sb = new StringBuilder();

        getRevisionList(jsonData);

        bubbleSort();

        int counter = 1;
        for (revision revision : revisions) {
            sb.append(counter).append("  ");
            sb.append(revision.getString()).append("\n");
            counter += 1;
        }//end for
        return sb.toString();
    }//end sortRevisions

    public void getRevisionList(String jsonString){

        JSONObject root = new JSONObject(jsonString);
        JSONObject query = root.getJSONObject("query");
        JSONArray pages = query.getJSONArray("pages");
        JSONObject page = pages.getJSONObject(0);
        JSONArray rev = page.getJSONArray("revisions");

        for(int i = 0; i < rev.length(); i++){
            JSONObject revision = rev.getJSONObject(i);
            revision tempRevision = new revision();
            tempRevision.user = revision.getString("user");
            tempRevision.time = revision.getString("timestamp");
            revisions.add(tempRevision);
        }//end for
    }//end getRevisionList

    public void bubbleSort() {
        for (int i = 0; i < revisions.size() - 1; i++) {
            for (int j = 0; j < revisions.size() - i - 1; j++) {
                Instant time1 = Instant.parse(revisions.get(j).time);
                Instant time2 = Instant.parse(revisions.get(j + 1).time);
                if (time1.isBefore(time2)) {
                    revision temp = revisions.get(j);
                    revisions.set(j, revisions.get(j + 1));
                    revisions.set(j + 1, temp);
                }//end if
            }//end for
        }//end for
    }//end BubbleSort



}//end SortRevisions