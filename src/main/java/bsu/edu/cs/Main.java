package bsu.edu.cs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException, URISyntaxException {
        Main instance = new Main();
        RetrieveArticle wiki = new RetrieveArticle();
        String jsonData = wiki.run(instance.getArticleName());
        System.out.println(jsonData);
    }//end main method

    public String getArticleName(){
        System.out.print("Search Article Name: ");
        String articleName = input.nextLine();
        System.out.print("\nGenerating Results... \n");
        return articleName;
    }//end getArticleName

}//end class
