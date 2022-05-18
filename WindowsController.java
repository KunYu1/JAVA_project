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
    private PerspectiveCamera perspectiveCamera;
	private ArrayList<MapBox> mapbox_array;
	private Node now_loc;
	private MapMaker mapmaker;
	private Vec version_vec;
    private Map map;
	private PointLight pointLight;
	private final DoubleProperty angleZ = new SimpleDoubleProperty(0);
	private Rotate zRotate;
	public WindowsController()throws Exception{
		//init map
		mapmaker = new MapMaker();
		version_vec = new Vec(0, 1, 0);
		map = new Map();
		mapbox_array = map.get_cube();
		now_loc= new Node(0, 0, 0);
	}
	@FXML
	public void initialize(){
		//load picture
		PhongMaterial material_1 = new PhongMaterial();
		material_1.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/1.jpg")));
		PhongMaterial material_3 = new PhongMaterial();
		material_3.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/3.jpg")));
		
		zRotate = new Rotate(0, Rotate.Z_AXIS);
		zRotate.angleProperty().bind(angleZ);
		pointLight = new PointLight();
        pointLight.setColor(Color.WHITE);
        pointLight.getTransforms().add(new Translate(now_loc.getx(),-500,now_loc.getz()));
        group.getChildren().add(pointLight);
		group.getTransforms().addAll(zRotate);
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
	}
	
	@FXML
	private void keyPressed(KeyEvent keyEvent){
		switch (keyEvent.getCode()) {
			case W:
				break;
			case S:
				break;
			case D:
				break;
			case A:
				break;
		}
	}
	public void set_pointLight(double x, double y,double z){
		pointLight.setTranslateX(x);
		pointLight.setTranslateY(y-100);
		pointLight.setTranslateZ(z);
	}	

}