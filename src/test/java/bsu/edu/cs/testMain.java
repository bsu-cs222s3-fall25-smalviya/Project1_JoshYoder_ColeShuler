package bsu.edu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class testMain {

    //getArticleNameTests

    @Test
    public void notNull(){
        Main test  = new Main();
        test.getArticleName();
        Assertions.assertNull(test.getArticleName());
    }//end notNull test

}//end testMain
