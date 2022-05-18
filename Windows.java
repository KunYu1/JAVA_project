
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
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);
    private final DoubleProperty angleZ = new SimpleDoubleProperty(0); 
    static WindowsController windowscon;
    private double anchorX, anchorY,anchorZ;
    private double anchorAngleXZ = 0;
    private double anchorAngleY = 0;
    private double mod = 0;
    //private double anchorAngleZ = 0;
    @Override
    public void start(Stage stage) throws Exception {
        
        // draw window   
        Rotate xRotate;
        Rotate yRotate;
        Rotate zRotate;
        Group group;
        Camera camera = new PerspectiveCamera(true);
        camera.setNearClip(1);
        camera.setFarClip(50000);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Windows.fxml"));
        Parent root = loader.load();
        windowscon = (WindowsController)loader.getController();
        Scene scene = new Scene(root, 800, 600, true, SceneAntialiasing.BALANCED); 
        scene.setCamera(camera);
        scene.setFill(Color.SILVER);
        stage.setTitle("JAVACRAFT");
        camera.getTransforms().addAll(
            yRotate = new Rotate(0, Rotate.Y_AXIS),            
            zRotate = new Rotate(0, Rotate.Z_AXIS),            
            xRotate = new Rotate(0, Rotate.X_AXIS),


            new Translate(0, 0, 0)
        );

        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()) {
                case W:
                    camera.translateZProperty().set(camera.getTranslateZ() + 100*Math.cos(yRotate.getAngle()*(Math.PI)/180));
                    camera.translateXProperty().set(camera.getTranslateX() + 100*Math.sin(yRotate.getAngle()*(Math.PI)/180));
                    break;
                case S:
                    camera.translateZProperty().set(camera.getTranslateZ() - 100*Math.cos(yRotate.getAngle()*(Math.PI)/180));
                    camera.translateXProperty().set(camera.getTranslateX() - 100*Math.sin(yRotate.getAngle()*(Math.PI)/180));
                    break;
                case D:
                    camera.translateZProperty().set(camera.getTranslateZ() - 100*Math.sin(yRotate.getAngle()*(Math.PI)/180));
                    camera.translateXProperty().set(camera.getTranslateX() + 100*Math.cos(yRotate.getAngle()*(Math.PI)/180));
                    break;
                case A:
                    camera.translateZProperty().set(camera.getTranslateZ() + 100*Math.sin(yRotate.getAngle()*(Math.PI)/180));
                    camera.translateXProperty().set(camera.getTranslateX() - 100*Math.cos(yRotate.getAngle()*(Math.PI)/180));
                    break;
                case E:
                    camera.translateYProperty().set(camera.getTranslateY() - 200);
                    break;
                case C:
                    camera.translateYProperty().set(camera.getTranslateY() + 200);
                    break;
            }
            windowscon.set_pointLight(camera.getTranslateX(),camera.getTranslateY(),camera.getTranslateZ());
        });


        xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);
        zRotate.angleProperty().bind(angleZ);
        scene.setOnMousePressed(event -> {
            System.out.println(1);
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleY = angleY.get();
        });
        scene.setOnMouseDragged(event -> {
            anchorAngleY = angleY.get();
            double rotation = (anchorAngleY - (anchorX - event.getSceneX())*0.1);
            angleY.set(rotation);
            double tmp_angle = (anchorAngleXZ + (anchorY - event.getSceneY())*0.1);
            if(tmp_angle<=-80){
                angleX.set(-80); 
                anchorAngleXZ = -80;          
            } else if(tmp_angle>=80){
                angleX.set(80);  
                anchorAngleXZ = 80;              
            } else{
                angleX.set(tmp_angle);
                anchorAngleXZ = tmp_angle; 
            }
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            System.out.println(camera.getTransforms());
        });
        stage.setScene(scene); 
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}