package com.mycompany.QLVT;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.image.Image;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        //scene = new Scene(loadFXML("login"));
        stage.setMinWidth(400);
        stage.setMinHeight(450);
        stage.setMaxHeight(450);
        stage.setMaxWidth(750);
//        stage.getIcons().add(new Image(getClass().getResourceAsStream("../../../img/icons8_states_20px")));
        scene = new Scene(loadFXML("login"));
        stage.setScene(scene);

//        stage.setTitle("Wow!! Stackoverflow Icon");
        stage.show();

    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
//        System.out.println("../../../fxml/" + fxml + ".fxml");
//          System.out.println("../../fxml/" + fxml + ".fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("../../../fxml/" + fxml + ".fxml"));
        return fxmlLoader.load();//return Root node(Parent node)
    }

    public static void main(String[] args) {

        launch();
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            int n = 0;

            @Override
            public void run() {
                System.out.println(n);
                if (++n == 5) {
                    timer.cancel();
                }
            }
        }, 1000, 1000);
    }

    /*FXMLLoader.load()
        create java object tha represent View
        create Controller ( fire Contructor)
        connect View nodes with Controller fileds
        Fire controller initialize() method
        return root node
     */
}
