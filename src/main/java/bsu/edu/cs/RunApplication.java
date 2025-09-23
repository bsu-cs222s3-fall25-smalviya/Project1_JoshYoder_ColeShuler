package bsu.edu.cs;

import java.io.IOException;

public class RunApplication {

    public static void main(String[] args){
        RunApplication run = new RunApplication();
        try {
            String results = run.runApplication();
            System.out.print("\n" + results);
        } catch (Exception e) {
            handleConnectionError(e.getMessage());
        }//end try/catch
    }//end main method



    public String runApplication(){
        FormatData fd = new FormatData();
        String jsonData = getRevisionData();
        return fd.processJson(jsonData);
    }//end runApplication



    public String getRevisionData(){
        try {
            RetrieveArticleAPI retriever = new RetrieveArticleAPI();
            return retriever.getData();
        }//end try
        catch (IOException e) {
            return ("System Error: " + e.getMessage());
        }//end catch
    }//end getRevisionData



    public static void handleConnectionError(String message){
        System.err.println("System Error: " + message);
        System.exit(0);
    }//end handleConnectionError

}//end class
