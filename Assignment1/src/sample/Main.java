package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.image.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.scene.control.cell.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;




/*
 * Christien Soosaipillai
 * 100557844
 * Assignment #1
 * March/10/2016
 */

public class Main extends Application {
    private Stage window;
    private BorderPane layout;
    private TableView<TestFile> table;
    private TextField lnameField, gpaField;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Spam Master 3000");






        /* create the table (for the center of the user interface) */

        table = new TableView<TestFile>();
       //table.setItems(DataSource.getAllNames(folder));

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("."));
        File mainDirectory = directoryChooser.showDialog(primaryStage);
        table.setItems(DataSource.getAllNames(mainDirectory));

        //if(mainDirectory == "train")


        /* create the table's columns */
        TableColumn<TestFile,Double> SpamProbability;
        SpamProbability = new TableColumn<>("Spam Probability");
        SpamProbability.setMinWidth(355);
        SpamProbability.setCellValueFactory(new PropertyValueFactory<>("spamProbability"));

        TableColumn<TestFile,String> actualClass;
        actualClass = new TableColumn<>("Actual Class");
        actualClass.setMinWidth(120);
        actualClass.setCellValueFactory(new PropertyValueFactory<>("actualClass"));


        TableColumn<TestFile,String> filename;
        filename = new TableColumn<>("File");
        filename.setMinWidth(355);
        filename.setCellValueFactory(new PropertyValueFactory<>("filename"));



        table.getColumns().add(filename);
        table.getColumns().add(actualClass);
        table.getColumns().add(SpamProbability);






        /* create an edit form (for the bottom of the user interface) */
        GridPane editArea = new GridPane();
        editArea.setPadding(new Insets(10, 10, 10, 10));
        editArea.setVgap(10);
        editArea.setHgap(10);

        Label sidLabel = new Label("Accuracy");
        editArea.add(sidLabel, 0, 0);
        TextField sidField = new TextField();
        sidField.setPromptText("Accuracy");
        editArea.add(sidField, 1, 0);

        Label fnameLabel = new Label("Precision:");
        editArea.add(fnameLabel, 0, 1);
        TextField fnameField = new TextField();
        fnameField.setPromptText("Precision");
        editArea.add(fnameField, 1, 1);


        /* arrange all components in the main user interface */
        layout = new BorderPane();

        layout.setCenter(table);
        layout.setBottom(editArea);

        Scene scene = new Scene(layout, 830, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ImageView imageFile(String filename) {
        return new ImageView(new Image("file:" + filename));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
