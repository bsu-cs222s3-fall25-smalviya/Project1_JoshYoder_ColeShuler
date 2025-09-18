package bsu.edu.cs;

import java.util.Scanner;

public class Main {
    Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        RetrieveArticle retrieveArticle = new RetrieveArticle();
    }//end main method

    public String getArticleName(){
        return input.nextLine();
    }//end getArticleName

}//end class
