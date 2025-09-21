package bsu.edu.cs;

import java.io.IOException;
import java.util.Scanner;

public class RunApplication {

    public static void main(String[] args){
        RunApplication run = new RunApplication();
        System.out.print("Starting Application...");
        try {
            String articleName = run.getArticleName();
            String results = run.runApplication(articleName);

        } catch (Exception e) {
            handleConnectionError(e.getMessage());
        }//end try/catch
        System.out.println("\nApplication Terminated.\n");
    }//end main method


    public String runApplication(String articleName){
        checkForArticleName(articleName);
        String jsonData = getRevisionData(articleName);
        return jsonData;
    }//end runApplication

    private String getArticleName() {
        Scanner input = new Scanner(System.in);
        System.out.print("\nPlease enter the article name: ");
        return input.nextLine();
    }//end getArticleName

    private void checkForArticleName(String articleName){
        if(articleName.isEmpty()){
            System.err.print("\nSystem error: No Article Name entered. \nTerminating Application...");
            System.exit(0);
        }//end if
        else{
            System.out.print("Article Name: True");
        }//end else
    }//end checkForArticleName

    public String getRevisionData(String articleName){
        try {
            RetrieveArticleAPI retriever = new RetrieveArticleAPI();
            return retriever.retrieveRevisionsFromAPI(articleName);
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
