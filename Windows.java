import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class Windows extends Application {
    private MapMaker mapmaker;
    private Map map;

    @Override
    public void start(Stage stage) throws Exception {
        //init map
        mapmaker = new MapMaker();
        map = new Map();

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