package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader1 = new FXMLLoader();
        loader1.setLocation(getClass().getResource("sample.fxml"));
        Parent root = loader1.load();
        primaryStage.setTitle("Kanban");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
        Controller controller = loader1.getController();
        Controller2 controller2 = controller.getController();
        controller2.setController(controller);


    }

    public static void main(String[] args) {
        launch(args);
    }
}
