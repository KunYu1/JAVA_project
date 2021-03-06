import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.input.KeyCode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.geometry.Point3D;
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
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.stage.Stage;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.PointLight;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.binding.BooleanBinding;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.robot.Robot;
import java.awt.AWTException;
import javafx.scene.shape.Rectangle;
import javafx.scene.SubScene;
import javafx.scene.SceneAntialiasing;
import java.util.*;
import javafx.scene.input.MouseButton;
import javafx.scene.Cursor; 
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
	private Group group;
    @FXML
    private Rectangle Rect;
	@FXML
    private AnchorPane pane;
    @FXML
    private Box box;

	
	private BooleanProperty forwardPressed = new SimpleBooleanProperty(false);
	private BooleanProperty backPressed = new SimpleBooleanProperty(false);
	private BooleanProperty rightPressed = new SimpleBooleanProperty(false);
	private BooleanProperty leftPressed = new SimpleBooleanProperty(false);
	private BooleanProperty spacePressed = new SimpleBooleanProperty(false);
	private BooleanProperty jumping = new SimpleBooleanProperty(false);
	private BooleanProperty mouse = new SimpleBooleanProperty(false);
	private BooleanProperty drop = new SimpleBooleanProperty(false);

	private BooleanProperty num_1 = new SimpleBooleanProperty(false);
	private BooleanProperty num_2 = new SimpleBooleanProperty(false);
	private BooleanProperty num_3 = new SimpleBooleanProperty(false);
	private BooleanProperty num_4 = new SimpleBooleanProperty(false);
	private BooleanProperty num_5 = new SimpleBooleanProperty(false);
	private BooleanProperty num_6 = new SimpleBooleanProperty(false);
	private BooleanProperty num_7 = new SimpleBooleanProperty(false);
	private BooleanProperty num_8 = new SimpleBooleanProperty(false);
	private BooleanProperty num_9 = new SimpleBooleanProperty(false);
	private BooleanBinding anyPressed = num_9.or(num_8.or(num_7.or(num_6.or(num_5.or(num_4.or(num_3.or(num_2.or(num_1.or
				(forwardPressed.or(rightPressed.or(leftPressed.or(backPressed).or(spacePressed.or(jumping.or(mouse.or(drop))))))	)))))))));

	private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);
	private Scene scene;
	private double anchorX, anchorY;
    private double anchorAngleXZ = 0;
    private double anchorAngleY = 0;
    private double velocity = 20;	
    private PerspectiveCamera perspectiveCamera;
	private ArrayList<MapBox> mapbox_array;
	//private Node now_loc;
	private Person now_loc;
	private MapMaker mapmaker;
	//private Vec version_vec;
    private Map map;
	private PointLight pointLight;
	private Scene scene_j;
    private Windows main;
	private PhongMaterial material_1;
	private PhongMaterial material_2;
	private PhongMaterial material_3;
	private PhongMaterial now_meterial;
	SubScene subscene;
	int material_type = 1;
	Camera camera ;
	Rotate xRotate;
	Rotate yRotate;
	Rotate zRotate;
	double an_x = 0;
	double an_y = 0;
	int jump_flag = 0;
	int flag ;
	Robot robot;

	public void setSelfScene(Scene scene){
		this.scene = scene;
		subscene.setCamera(camera);
		pane.getChildren().add(subscene);
		subscene.toBack();
		flag = 0;
		scene.setOnMouseMoved(event -> {
			if(flag == 0){
				anchorX = event.getSceneX();
				anchorY = event.getSceneY();
				flag = 1;
				return;
			}
			if(flag == 2){
				anchorX = event.getSceneX();
				anchorY = event.getSceneY();
				flag = 0;
				return;
			}
			an_x += (anchorX - event.getSceneX())*0.1;
			an_y += (anchorY - event.getSceneY())*0.1;
			//System.out.println(an_x+" "+an_y);
			if(angleX.get()+an_y<-90){
				an_y = 0;        
            } else if(angleX.get()+an_y>90){
                an_y = 0;            
            }
			if(an_x!=0||an_y!=0)
				mouse.set(true);
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
			scene.setCursor(Cursor.NONE);
			subscene.setCursor(Cursor.NONE);
        });
		scene.setOnMouseExited(event -> {
			robot.mouseMove(960,540);
			flag =2;
		});
		scene.setOnMousePressed(event -> {
			if(event.getButton() == MouseButton.PRIMARY){
				System.out.println("explosion!!!!!!");
				//System.out.println(angleX.get()+" "+angleY.get());
				double v_x = 100*Math.cos(angleX.get()*(Math.PI)/180)*Math.sin(angleY.get()*(Math.PI)/180);
				double v_y = 100*Math.sin(angleX.get()*(Math.PI)/180);
				double v_z = 100*Math.cos(angleX.get()*(Math.PI)/180)*Math.cos(angleY.get()*(Math.PI)/180);
				//System.out.println(v_x+" "+v_y+" "+v_z);
				map.break_map(group, camera.getTranslateX(),camera.getTranslateY(),camera.getTranslateZ(),v_x,v_y,v_z);
			} else {
				System.out.println("construction!!!!!!");
				//System.out.println(angleX.get()+" "+angleY.get());
				double v_x = 100*Math.cos(angleX.get()*(Math.PI)/180)*Math.sin(angleY.get()*(Math.PI)/180);
				double v_y = 100*Math.sin(angleX.get()*(Math.PI)/180);
				double v_z = 100*Math.cos(angleX.get()*(Math.PI)/180)*Math.cos(angleY.get()*(Math.PI)/180);
				//System.out.println(v_x+" "+v_y+" "+v_z);
				map.construct_map(group, camera.getTranslateX(),camera.getTranslateY(),camera.getTranslateZ(),v_x,v_y,v_z,now_meterial,material_type);
			}

		});
	}
    public void setMain(Windows main){
        this.main = main;
    }
    public void setScene(Scene scene){
        this.scene_j = scene;
    }

    public void switchScene(){main.setScene(scene_j);}

	public WindowsController()throws Exception{
		//init map
		group = new Group();
		subscene = new SubScene(group, 800, 600, true,SceneAntialiasing.BALANCED);
		//mapmaker = new MapMaker();
		map = new Map();
		mapbox_array = map.get_cube();
		now_loc= new Person();
		now_loc.box_visible.getTransforms().addAll(
            yRotate = new Rotate(0, Rotate.Y_AXIS),                      
            xRotate = new Rotate(0, Rotate.X_AXIS),
            new Translate(0, 0, 100)
		);
		//System.out.println( now_loc.box_visible.getTranslateX()+" "+now_loc.box_visible.getTranslateY()+" "+now_loc.box_visible.getTranslateZ());
		now_loc.box.getTransforms().addAll(
            yRotate,                      
            xRotate,
            new Translate(0, 0, 0)
		);
    	camera = new PerspectiveCamera(true);
        camera.setNearClip(1);
        camera.setFarClip(30000);  
        camera.getTransforms().addAll(
            yRotate,                      
            xRotate,
            new Translate(0, 0, 0)
        );
		robot = new Robot();
	}

	@FXML
	public void initialize(){
		//System.out.println(anyPressed.get());
		pane.requestFocus();
		rightPressed.set(false);
		//System.out.println(anyPressed.get());
		//load picture
		material_1 = new PhongMaterial();
		material_1.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/1.jpg")));
		material_3 = new PhongMaterial();
		material_3.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/3.jpg")));
		
		material_2 = new PhongMaterial();
		material_2.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/2.jpg")));	
		PhongMaterial material_4 = new PhongMaterial();
		material_4.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/10.jpg")));	
		PhongMaterial material_5 = new PhongMaterial();
		material_5.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/5.jpg")));	
		PhongMaterial material_6 = new PhongMaterial();
		material_6.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/6.jpg")));
		PhongMaterial material_7 = new PhongMaterial();
		material_7.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/7.jpg")));	
		PhongMaterial material_8 = new PhongMaterial();
		material_8.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/8.jpg")));	
		PhongMaterial material_9 = new PhongMaterial();
		material_9.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/9.jpg")));
		
		pointLight = new PointLight();
        pointLight.setColor(Color.WHITE);
        pointLight.getTransforms().add(new Translate(now_loc.box.getTranslateX(),now_loc.box.getTranslateY(),now_loc.box.getTranslateY()));
		group.getChildren().add(now_loc.box_visible);
        group.getChildren().add(pointLight);
		now_meterial = material_1;
		for(int i=0;i<mapbox_array.size();i++){
			switch(mapbox_array.get(i).type.get_item_num()){
				case 1:
					mapbox_array.get(i).box.setMaterial(material_1);
					break;
				case 2:
					mapbox_array.get(i).box.setMaterial(material_2);
					break;
				case 3:
					mapbox_array.get(i).box.setMaterial(material_3);
					break;
				case 4:
					mapbox_array.get(i).box.setMaterial(material_4);
					break;
				case 5:
					mapbox_array.get(i).box.setMaterial(material_5);
					break;
				case 6:
					mapbox_array.get(i).box.setMaterial(material_6);
					break;
				case 7:
					mapbox_array.get(i).box.setMaterial(material_7);
					break;
				case 8:
					mapbox_array.get(i).box.setMaterial(material_8);
					break;
				case 9:
					mapbox_array.get(i).box.setMaterial(material_9);
					break;
			}
			group.getChildren().add(mapbox_array.get(i).box);
		}
		xRotate.angleProperty().bind(angleX);
        yRotate.angleProperty().bind(angleY);
		box.setMaterial(material_1);
		AnimationTimer timer = new AnimationTimer() {
			double tmp = camera.getTranslateY();	
			double dt=0;
			double ds=0;
			@Override
			public void handle(long timestamp) {
				double rotation = (angleY.get() - an_x);
            	angleY.set(rotation);
            	double tmp_angle = (angleX.get() + an_y);
				angleX.set(tmp_angle);
				an_x = 0;
				an_y = 0;
				//System.out.println(timestamp);
				int k_x = (int)Math.ceil((camera.getTranslateX()-100)/200);
				int k_y = (int)Math.ceil((camera.getTranslateY()-100)/200);
				int k_z = (int)Math.ceil((camera.getTranslateZ()-100)/200);
				if(map.find_map(k_x*200, k_y*200, k_z*200)==1)
					System.out.println(k_x+" "+k_y+" "+k_z);
				if(num_1.get()){
					material_type = 1;
					now_meterial = material_1;
					box.setMaterial(material_1);
					Rect.setLayoutX(5);
				}
				if(num_2.get()){
					material_type = 2;
					now_meterial = material_2;
					box.setMaterial(material_2);
					Rect.setLayoutX(56);
				}
				if(num_3.get()){
					material_type = 3;
					now_meterial = material_3;
					box.setMaterial(material_3);
					Rect.setLayoutX(107);
					
				}
				if(num_4.get()){
					material_type = 4;
					now_meterial = material_4;
					box.setMaterial(material_4);
					Rect.setLayoutX(158);
				}
				if(num_5.get()){
					material_type =5;
					now_meterial = material_5;
					box.setMaterial(material_5);
					Rect.setLayoutX(209);
				}
				if(num_6.get()){
					material_type = 6;
					now_meterial = material_6;
					box.setMaterial(material_6);
					Rect.setLayoutX(260);
				}
				if(num_7.get()){
					material_type =7;
					now_meterial = material_7;
					box.setMaterial(material_7);
					Rect.setLayoutX(311);
				}
				if(num_8.get()){
					material_type =8;
					now_meterial = material_8;
					box.setMaterial(material_8);
					Rect.setLayoutX(362);
				}
				if(num_9.get()){
					material_type =9;
					now_meterial = material_9;
					box.setMaterial(material_9);
					Rect.setLayoutX(413);
				}
				if (forwardPressed.get()) {
					k_x = (int)Math.ceil((camera.getTranslateX() + velocity*Math.sin(yRotate.getAngle()*(Math.PI)/180)-100)/200);
					k_y = (int)Math.ceil((camera.getTranslateY()-100)/200);
					k_z = (int)Math.ceil((camera.getTranslateZ() + velocity*Math.cos(yRotate.getAngle()*(Math.PI)/180)-100)/200);
					if(map.find_map(k_x*200, k_y*200, k_z*200)==0&&map.find_map(k_x*200, k_y*200+200, k_z*200)==0){
						camera.translateZProperty().set(camera.getTranslateZ() + velocity*Math.cos(yRotate.getAngle()*(Math.PI)/180));
						camera.translateXProperty().set(camera.getTranslateX() + velocity*Math.sin(yRotate.getAngle()*(Math.PI)/180));
					}
						// System.out.println(k_x+" "+k_y+" "+k_z);
				}
				if (rightPressed.get()) {
					k_x = (int)Math.ceil((camera.getTranslateX() + velocity*Math.cos(yRotate.getAngle()*(Math.PI)/180)-100)/200);
					k_y = (int)Math.ceil((camera.getTranslateY()-100)/200);
					k_z = (int)Math.ceil((camera.getTranslateZ() - velocity*Math.sin(yRotate.getAngle()*(Math.PI)/180)-100)/200);
					if(map.find_map(k_x*200, k_y*200, k_z*200)==0&&map.find_map(k_x*200, k_y*200+200, k_z*200)==0){
						//System.out.println(k_x+" "+k_y+" "+k_z);
						camera.translateZProperty().set(camera.getTranslateZ() - velocity*Math.sin(yRotate.getAngle()*(Math.PI)/180));
						camera.translateXProperty().set(camera.getTranslateX() + velocity*Math.cos(yRotate.getAngle()*(Math.PI)/180));
					}
				}
				if (leftPressed.get()) {
					k_x = (int)Math.ceil((camera.getTranslateX() - velocity*Math.cos(yRotate.getAngle()*(Math.PI)/180)-100)/200);
					k_y = (int)Math.ceil((camera.getTranslateY()-100)/200);
					k_z = (int)Math.ceil((camera.getTranslateZ() + velocity*Math.sin(yRotate.getAngle()*(Math.PI)/180)-100)/200);
					if(map.find_map(k_x*200, k_y*200, k_z*200)==0&&map.find_map(k_x*200, k_y*200+200, k_z*200)==0){
						//System.out.println(k_x+" "+k_y+" "+k_z);
						camera.translateZProperty().set(camera.getTranslateZ() + velocity*Math.sin(yRotate.getAngle()*(Math.PI)/180));
						camera.translateXProperty().set(camera.getTranslateX() - velocity*Math.cos(yRotate.getAngle()*(Math.PI)/180));
					}
				}
				if (backPressed.get()) {
					k_x = (int)Math.ceil((camera.getTranslateX() - velocity*Math.sin(yRotate.getAngle()*(Math.PI)/180)-100)/200);
					k_y = (int)Math.ceil((camera.getTranslateY()-100)/200);
					k_z = (int)Math.ceil((camera.getTranslateZ() - velocity*Math.cos(yRotate.getAngle()*(Math.PI)/180)-100)/200);
					if(map.find_map(k_x*200, k_y*200, k_z*200)==0&&map.find_map(k_x*200, k_y*200+200, k_z*200)==0){
						//System.out.println(k_x+" "+k_y+" "+k_z);
						camera.translateZProperty().set(camera.getTranslateZ() - velocity*Math.cos(yRotate.getAngle()*(Math.PI)/180));
						camera.translateXProperty().set(camera.getTranslateX() - velocity*Math.sin(yRotate.getAngle()*(Math.PI)/180));
					}
				}
				k_x = (int)Math.ceil((camera.getTranslateX()-100)/200);
				k_y = (int)Math.ceil((camera.getTranslateY()-100)/200);
				k_z = (int)Math.ceil((camera.getTranslateZ()-100)/200);
				if(map.find_map(k_x*200, k_y*200+400, k_z*200)==0&&!jumping.get()){
					dt = 10;
					jumping.set(true);
				}
				

				if (!jumping.get()&&spacePressed.get())	{
					tmp = camera.getTranslateY();	
					jumping.set(true);
					dt=0;
				} 
				if(jumping.get()){
					//if(dt<20){
						dt+=1;
						k_x = (int)Math.ceil((camera.getTranslateX()-100)/200);
						k_y = (int)Math.ceil((camera.getTranslateY()+(5*dt-50)-100)/200);
						k_z = (int)Math.ceil((camera.getTranslateZ()-100)/200);
						//System.out.println(k_x+" "+k_y+" "+k_z);
						if(map.find_map(k_x*200, k_y*200+200, k_z*200)==0){
							camera.translateYProperty().set(camera.getTranslateY()+(5*dt-50));	
						} else{
							//System.out.println(k_x+" "+k_y+" "+k_z);
							camera.translateYProperty().set(k_y*200-200);
							jumping.set(false);
							//dt=20;
							dt=0;
						}
					//}
					//System.out.println(camera.getTranslateY());
					// if(dt>=20){
					// 	camera.translateYProperty().set(tmp);
					// 	//System.out.println("jump_flag=0");
						
					// }
				}

				//System.out.println(camera.getTranslateX()+" "+camera.getTranslateY()+" "+camera.getTranslateZ());
				now_loc.set_visible(camera.getTranslateX(),camera.getTranslateY(),camera.getTranslateZ());
				set_pointLight(camera.getTranslateX(),camera.getTranslateY(),camera.getTranslateZ());
				mouse.set(false);
				//System.out.println(camera.getTranslateX()+" "+camera.getTranslateY()+" "+camera.getTranslateZ());
			}
		};
		anyPressed.addListener((obs, wasPressed, isNowPressed) ->{
			if(isNowPressed){
				timer.start();
			} else{
				timer.stop();
			}
		});
		
	}
	
	@FXML
	private void keyPressed(KeyEvent keyEvent){
		switch (keyEvent.getCode()) {
			case W:
				//System.out.print("w");
				forwardPressed.set(true);
				break;
			case S:
				backPressed.set(true);
				break;
			case D:
				rightPressed.set(true);
				break;
			case A:
				leftPressed.set(true);
				break;
			case E:
				camera.translateYProperty().set(camera.getTranslateY() - 200);
				now_loc.set_visible(camera.getTranslateX(),camera.getTranslateY(),camera.getTranslateZ());
				break;
			case C:
				camera.translateYProperty().set(camera.getTranslateY() + 200);
				now_loc.set_visible(camera.getTranslateX(),camera.getTranslateY(),camera.getTranslateZ());
				break;
			case SPACE:
				spacePressed.set(true);
				break;
			case L:
				switchScene();
			case DIGIT1:
				num_1.set(true);
				break;

			case DIGIT2:
				num_2.set(true);
				break;
			case DIGIT3:
				num_3.set(true);
				break;	
			case DIGIT4:
				num_4.set(true);
				break;
			case DIGIT5:
				num_5.set(true);
				break;
			case DIGIT6:
				num_6.set(true);
				break;
			case DIGIT7:
				num_7.set(true);
				break;
			case DIGIT8:
				num_8.set(true);
				break;
			case DIGIT9:
				num_9.set(true);
				break;
			}
		}
		@FXML
		private void keyReleased(KeyEvent keyEvent){
			switch (keyEvent.getCode()) {
				case W:
					forwardPressed.set(false);
					break;
				case S:
					backPressed.set(false);
					break;
				case D:
					rightPressed.set(false);
					break;
				case A:
					leftPressed.set(false);
					break;
				case SPACE:
					spacePressed.set(false);
					break;
				case DIGIT1:
					num_1.set(false);
					break;
				case DIGIT2:
					num_2.set(false);
					break;
				case DIGIT3:
					num_3.set(false);
					break;	
				case DIGIT4:
					num_4.set(false);
					break;
				case DIGIT5:
					num_5.set(false);
					break;
				case DIGIT6:
					num_6.set(false);
					break;
				case DIGIT7:
					num_7.set(false);
					break;
				case DIGIT8:
					num_8.set(false);
					break;
				case DIGIT9:
					num_9.set(false);
					break;
			}
		}
	public void set_pointLight(double x, double y,double z){
		pointLight.setTranslateX(x);
		pointLight.setTranslateY(y);
		pointLight.setTranslateZ(z);
	}	

}