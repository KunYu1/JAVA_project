
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
        //Scene scene = new Scene(root, 800, 600, true, SceneAntialiasing.BALANCED); 
        scene_init = new Scene(root_init,800,600); 
        scene = new Scene(root,800,600,true, SceneAntialiasing.BALANCED); 

		// Rotate xRotate;
        // Rotate yRotate;
        // Rotate zRotate;
        // Camera camera = new PerspectiveCamera(true);
        // camera.setNearClip(1);
        // camera.setFarClip(30000);

        windowscon = (WindowsController)loader.getController();
        initcon = (InitController)loader_init.getController();
        initcon.setScene(scene);
        initcon.setMain(this);
        windowscon.setScene(scene_init);
        windowscon.setSelfScene(scene);
        windowscon.setMain(this);

        scene.setFill(Color.SILVER);
        now_stage.setTitle("JAVACRAFT");
        // scene.setCamera(camera);  
        // camera.getTransforms().addAll(
        //     yRotate = new Rotate(0, Rotate.Y_AXIS),                      
        //     xRotate = new Rotate(0, Rotate.X_AXIS),
        //     new Translate(0, 0, 0)
        // );

        // stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
        //     switch (event.getCode()) {
        //         case W:
        //             camera.translateZProperty().set(camera.getTranslateZ() + velocity*Math.cos(yRotate.getAngle()*(Math.PI)/180));
        //             camera.translateXProperty().set(camera.getTranslateX() + velocity*Math.sin(yRotate.getAngle()*(Math.PI)/180));
        //             break;
        //         case S:
        //             camera.translateZProperty().set(camera.getTranslateZ() - velocity*Math.cos(yRotate.getAngle()*(Math.PI)/180));
        //             camera.translateXProperty().set(camera.getTranslateX() - velocity*Math.sin(yRotate.getAngle()*(Math.PI)/180));
        //             break;
        //         case D:
        //             camera.translateZProperty().set(camera.getTranslateZ() - velocity*Math.sin(yRotate.getAngle()*(Math.PI)/180));
        //             camera.translateXProperty().set(camera.getTranslateX() + velocity*Math.cos(yRotate.getAngle()*(Math.PI)/180));
        //             break;
        //         case A:
        //             camera.translateZProperty().set(camera.getTranslateZ() + velocity*Math.sin(yRotate.getAngle()*(Math.PI)/180));
        //             camera.translateXProperty().set(camera.getTranslateX() - velocity*Math.cos(yRotate.getAngle()*(Math.PI)/180));
        //             break;
        //         case E:
        //             camera.translateYProperty().set(camera.getTranslateY() - 200);
        //             break;
        //         case C:
        //             camera.translateYProperty().set(camera.getTranslateY() + 200);
        //             break;
        //     }
        //     windowscon.set_pointLight(camera.getTranslateX(),camera.getTranslateY(),camera.getTranslateZ());
        //     System.out.println(camera.getTranslateX()+" "+camera.getTranslateY()+" "+camera.getTranslateZ());
        // });


        // xRotate.angleProperty().bind(angleX);
        // yRotate.angleProperty().bind(angleY);
        // scene.setOnMousePressed(event -> {
        //     //System.out.println(1);
        //     anchorX = event.getSceneX();
        //     anchorY = event.getSceneY();
        //     anchorAngleY = angleY.get();
        // });
        // scene.setOnMouseDragged(event -> {
        //     anchorAngleY = angleY.get();
        //     double rotation = (anchorAngleY - (anchorX - event.getSceneX())*0.1);
        //     angleY.set(rotation);
        //     double tmp_angle = (anchorAngleXZ + (anchorY - event.getSceneY())*0.1);
        //     if(tmp_angle<=-80){
        //         angleX.set(-80); 
        //         anchorAngleXZ = -80;          
        //     } else if(tmp_angle>=80){
        //         angleX.set(80);  
        //         anchorAngleXZ = 80;              
        //     } else{
        //         angleX.set(tmp_angle);
        //         anchorAngleXZ = tmp_angle; 
        //     }
        //     anchorX = event.getSceneX();
        //     anchorY = event.getSceneY();
        //     //System.out.println(camera.getTransforms());
        // });
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