package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class AppFX extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppFX.class.getResource("/ba.unsa.etf.rpr.fxml/home.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 540);
        stage.setTitle("PC Builder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException, IOException {
        launch();
    }
}