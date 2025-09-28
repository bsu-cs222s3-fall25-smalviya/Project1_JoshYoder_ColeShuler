package bsu.edu.cs;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.Format;

public class runGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }//end main

    public void start(Stage primaryStage){
        runGUI run =  new runGUI();

        TextField textField = new TextField();
        textField.setPromptText("Enter article name...");
        Button enter = new Button("Enter");
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(textField, enter);

        Scene scene = new Scene(hBox, 300, 200);

        primaryStage.setTitle("Wikipedia Article Revision Generator");
        primaryStage.setScene(scene);
        primaryStage.show();



        enter.setOnAction(e -> {
            String input =  textField.getText();


            Stage resultStage = new Stage();
            resultStage.setTitle(input);

            Label label = null;
            try {
                label = new Label(run.getData(input));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }//end try

            Scene resultScene = new Scene(label, 300, 275);
            resultStage.setScene(resultScene);

            resultStage.show();
        });

    }//end start

    public String getData(String input) throws IOException {
        RetrieveArticleAPI retriever = new RetrieveArticleAPI();
        FormatData fd = new FormatData();
        return fd.processJson(retriever.retrieveArticleDataFromAPI(input));
    }//end getData

}//end class