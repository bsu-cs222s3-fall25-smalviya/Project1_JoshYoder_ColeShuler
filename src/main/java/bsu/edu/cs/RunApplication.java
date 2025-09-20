package bsu.edu.cs;

import java.io.IOException;
import java.util.Scanner;

public class RunApplication {

    public static void main(String[] args){
        RunApplication run = new RunApplication();
        System.out.print("Starting Application...");
        try {
            run.runApplication();
        } catch (Exception e) {
            System.out.println(e);
        }//end try/catch
        System.out.println("Application Terminated.");
    }//end main method


    public void runApplication(){
        String articleName = getArticleName();
        checkForArticleName(articleName);

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
    }//end checkForArticleName
}//end class
