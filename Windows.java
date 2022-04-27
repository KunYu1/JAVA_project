import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Label;
public class Windows extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // draw window    
        Parent root = FXMLLoader.load(getClass().getResource("Windows.fxml"));
        Scene scene = new Scene(root); 
        stage.setTitle("JAVACRAFT");
        stage.setScene(scene); 
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}