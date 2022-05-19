import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.input.KeyCode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import java.security.SecureRandom;
import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.stage.Stage;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.PointLight;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.*;

public class WindowsController {
	String Key;
	double x=0,y=0;
	int flag_time=0,flag_jump=0;
	double z=0;
    double velX=5,velY=5;
	double velZ=-40;
	double g=4;
	double t0=0,t=0;
	@FXML
    private Circle Position;
	@FXML
	private Group group;

	private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);
	private Scene scene;
	private double anchorX, anchorY;
    private double anchorAngleXZ = 0;
    private double anchorAngleY = 0;
    private double velocity = 20;	
    private PerspectiveCamera perspectiveCamera;
	private ArrayList<MapBox> mapbox_array;
	private Node now_loc;
	private MapMaker mapmaker;
	private Vec version_vec;
    private Map map;
	private PointLight pointLight;
	private Scene scene_j;
    private Windows main;
	Camera camera ;
	Rotate xRotate;
	Rotate yRotate;
	Rotate zRotate;
	int jump_flag = 0;

	public void setSelfScene(Scene scene){
		this.scene = scene;
		scene.setCamera(camera);
		scene.setOnMousePressed(event -> {
            //System.out.println(1);
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
            //System.out.println(camera.getTransforms());
        });
	}
    public void setMain(Windows main){
        this.main = main;
    }
    public void setScene(Scene scene){
        this.scene_j = scene;
    }
    // this method is called by clicking the button
    @FXML
    public void switchScene(){main.setScene(scene_j);}

	public WindowsController()throws Exception{
		//init map
		mapmaker = new MapMaker();
		version_vec = new Vec(0, 1, 0);
		map = new Map();
		mapbox_array = map.get_cube();
		now_loc= new Node(0, 0, 0);
    	camera = new PerspectiveCamera(true);
        camera.setNearClip(1);
        camera.setFarClip(30000);  
        camera.getTransforms().addAll(
            yRotate = new Rotate(0, Rotate.Y_AXIS),                      
            xRotate = new Rotate(0, Rotate.X_AXIS),
            new Translate(0, 0, 0)
        );
	}
	@FXML
	public void initialize(){
		//load picture
		PhongMaterial material_1 = new PhongMaterial();
		material_1.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/2.jpg")));
		//material_1.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/earth-d.jpg")));
		PhongMaterial material_3 = new PhongMaterial();
		material_3.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/3.jpg")));
		
		pointLight = new PointLight();
        pointLight.setColor(Color.WHITE);
        pointLight.getTransforms().add(new Translate(now_loc.getx(),-500,now_loc.getz()));
        group.getChildren().add(pointLight);
		for(int i=0;i<mapbox_array.size();i++){
			switch(mapbox_array.get(i).type.get_item_num()){
				case 1:
					mapbox_array.get(i).box.setMaterial(material_1);
					break;
				case 3:
					mapbox_array.get(i).box.setMaterial(material_3);
					break;
			}
			group.getChildren().add(mapbox_array.get(i).box);
		}
		xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);
	}
	
	@FXML
	private void keyPressed(KeyEvent keyEvent){
		switch (keyEvent.getCode()) {
			case W:
				camera.translateZProperty().set(camera.getTranslateZ() + velocity*Math.cos(yRotate.getAngle()*(Math.PI)/180));
				camera.translateXProperty().set(camera.getTranslateX() + velocity*Math.sin(yRotate.getAngle()*(Math.PI)/180));
				break;
			case S:
				camera.translateZProperty().set(camera.getTranslateZ() - velocity*Math.cos(yRotate.getAngle()*(Math.PI)/180));
				camera.translateXProperty().set(camera.getTranslateX() - velocity*Math.sin(yRotate.getAngle()*(Math.PI)/180));
				break;
			case D:
				camera.translateZProperty().set(camera.getTranslateZ() - velocity*Math.sin(yRotate.getAngle()*(Math.PI)/180));
				camera.translateXProperty().set(camera.getTranslateX() + velocity*Math.cos(yRotate.getAngle()*(Math.PI)/180));
				break;
			case A:
				camera.translateZProperty().set(camera.getTranslateZ() + velocity*Math.sin(yRotate.getAngle()*(Math.PI)/180));
				camera.translateXProperty().set(camera.getTranslateX() - velocity*Math.cos(yRotate.getAngle()*(Math.PI)/180));
				break;
			case E:
				camera.translateYProperty().set(camera.getTranslateY() - 200);
				break;
			case C:
				camera.translateYProperty().set(camera.getTranslateY() + 200);
				break;
			case SPACE:
				System.out.println("junp");
				if (jump_flag==0)
				{
					double tmp = camera.getTranslateY();	
					jump_flag=1;
					AnimationTimer timer = new AnimationTimer() {
						double dt=0;
						@Override
						public void handle(long now) {
							if(dt<20){
								dt+=1;
								camera.translateYProperty().set(camera.getTranslateY()+(2.4*dt-24));	
							}
							System.out.println(camera.getTranslateY());
							if(dt>=20){
								camera.translateYProperty().set(tmp);
								System.out.println("jump_flag=0");
								jump_flag=0;
								stop();
								//System.out.println("=0");
							}
						}
					};
					//System.out.println(hand.getTranslateY());
					timer.start();
					break;
				}
				else if(jump_flag==1){
					break;
				}
				break;
		}
		this.set_pointLight(camera.getTranslateX(),camera.getTranslateY(),camera.getTranslateZ());
		System.out.println(camera.getTranslateX()+" "+camera.getTranslateY()+" "+camera.getTranslateZ());
	}
	public void set_pointLight(double x, double y,double z){
		pointLight.setTranslateX(x);
		pointLight.setTranslateY(y-100);
		pointLight.setTranslateZ(z);
	}	

}