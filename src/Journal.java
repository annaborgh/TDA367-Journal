package src;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.MVC.*;

/**
 * This is the class for main.
 *
 * @author Adam Wikström
 * @author Anna Borgh
 * @author Julia Ekeblad
 * @author Tarek Chorfi
 * @author Wilma Nordlund
 */
public class Journal extends Application {
    Model model;
    Controller controller;
    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        model = new Model();
        controller = new Controller();

        FXMLLoader fxmlLoader = new FXMLLoader(Journal.class.getResource("MVC/scenebuilder.fxml"));
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load(), 900, 635);

        controller.init(model);

        stage.setTitle("Min Dagbok");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
    @Override
    public void stop(){
        controller.shutdown();
    }
}
