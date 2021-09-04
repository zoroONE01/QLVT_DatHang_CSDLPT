package com.mycompany.mavenproject2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
            System.out.println(App.class.getResource("../../../fxml/FXML" + ".fxml"));
       System.out.println(App.class.getResource("./DemoController/demoFXML" + ".fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
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