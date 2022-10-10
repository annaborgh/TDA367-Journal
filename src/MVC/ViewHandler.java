package src.MVC;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewHandler {
    private final Stage stage;
    private Scene scene;
    FXMLLoader fxmlLoader;

    public ViewHandler(Stage stage){
        this.stage = stage;
        stage.setResizable(false);
    }

    public void start(){
        stage.setTitle("Diary");
        showScene("MVC/scenebuilder");
    }

    // loads scene and displays it as the root-scene
    public void showScene(String sceneToShow){
        stage.setScene(scene);
        stage.show();
    }
}