package com.demon.virtualwork;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author DeMon
 * Date 2021/5/27.
 * E-mail idemon_liu@qq.com
 * Desc:
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("VirtualWork");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(event -> {
            MainController controller = fxmlLoader.getController();
            if (controller != null) {
                controller.onEnd(null);
            }
            Platform.exit();
        });
    }

    public static void main(String[] args) {
        launch();
    }
}