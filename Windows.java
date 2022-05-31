
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Camera;
import javafx.scene.PointLight;
import javafx.scene.PerspectiveCamera;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.transform.Translate;
import javafx.scene.Cursor; 
import javafx.scene.transform.Rotate;
import javafx.scene.SceneAntialiasing;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.shape.Sphere;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;

public class Windows extends Application {
    // private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    // private final DoubleProperty angleY = new SimpleDoubleProperty(0);
    Stage now_stage;
    Scene scene, scene_init;
    static WindowsController windowscon;
    static InitController initcon;
    // private double anchorX, anchorY;
    // private double anchorAngleXZ = 0;
    // private double anchorAngleY = 0;
    // private double velocity = 20;
    @Override
    public void start(Stage stage) throws Exception {
        
        now_stage = stage;
        // draw window   

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Windows.fxml"));
        FXMLLoader loader_init = new FXMLLoader(getClass().getResource("Windows_UI_1.fxml"));
        Parent root_init = loader_init.load();
        Parent root = loader.load();

        ImageCursor Mouse=new ImageCursor(new Image(getClass().getResourceAsStream("texture/cursor.png")),800,600);
         

        scene_init = new Scene(root_init,800,600); 
        scene = new Scene(root,800,600,true, SceneAntialiasing.BALANCED); 
        scene.setCursor(Mouse);

        windowscon = (WindowsController)loader.getController();
        initcon = (InitController)loader_init.getController();
        initcon.setScene(scene);
        initcon.setMain(this);
        windowscon.setScene(scene_init);
        windowscon.setSelfScene(scene);
        windowscon.setMain(this);

        scene.setFill(Color.SILVER);
        now_stage.setTitle("JAVACRAFT");
        now_stage.setScene(scene_init); 
        now_stage.show();
    }
    public void setScene(Scene scene){
        now_stage.setScene(scene);
    }
    public static void main(String[] args) {
        launch(args);
    }
}