package com.mycompany.QLVT;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

//        scene = new Scene(loadFXML("login"));


        stage.setMinWidth(400);
        stage.setMinHeight(650);
        scene = new Scene(loadFXML("main"));
        stage.setScene(scene);
        stage.show();
        
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
//        System.out.println("../../../fxml/" + fxml + ".fxml");
//          System.out.println("../../fxml/" + fxml + ".fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("../../../fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();//return Root node(Parent node)
    }

    public static void main(String[] args) {
        launch();
    }

    /*FXMLLoader.load()
        create java object tha represent View
        create Controller ( fire Contructor)
        connect View nodes with Controller fileds
        Fire controller initialize() method
        return root node
     */
}
