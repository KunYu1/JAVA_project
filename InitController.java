import javafx.scene.Scene;
import javafx.stage.Window;
import javafx.application.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class InitController {

    private Scene scene;
    private Windows main;

    public void setMain(Windows main){
        this.main = main;
    }
    public void setScene(Scene scene){
        this.scene = scene;
    }
    // this method is called by clicking the button
    @FXML
    public void switchScene(){main.setScene(scene);}
    @FXML
    public void exit_scene(){
        System.exit(0);
    }
}