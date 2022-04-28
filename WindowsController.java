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
    private Button btn;
	@FXML
	private AnchorPane map_pane;
	private ArrayList<Cube> cube_array;
	private Node now_loc;
	private MapMaker mapmaker;
	private Vec version_vec;
    private Map map;
	public WindowsController()throws Exception{
		//init map
		mapmaker = new MapMaker();
		version_vec = new Vec(0, 1, 0);
		map = new Map();
		cube_array = map.get_cube();
		now_loc= new Node(0, -2, 0);
	}
	@FXML
	public void initialize(){
		//test
		Image mud = new Image("other.png");
		ImagePattern pattern = new ImagePattern(mud, 20, 20, 40, 40, false);
		Image grass = new Image("top.png");
		ImagePattern pattern_2 = new ImagePattern(grass, 20, 20, 40, 40, false);

		double width = map_pane.getPrefWidth();
		double height = map_pane.getPrefHeight();
		System.out.println(width+" "+height);
		for(int i=0;i<cube_array.size();i++){
			Node cube = cube_array.get(i).get_loc();
			Vec lookvec = new Vec(cube, now_loc);
			//System.out.println(cube);
			//System.out.println(now_loc);
			//System.out.println(lookvec);
			double points[] = new double[8];
			Polygon polygon;
			RotationCube rot = new RotationCube(cube_array.get(i));
			System.out.println("x: "+lookvec.xytheta(new Vec(now_loc)));
			System.out.println("y: "+lookvec.xyztheta(new Vec(now_loc)));
			System.out.println(lookvec.r(new Vec(now_loc)));
			for(int j=0;j<8;j++){
				System.out.println(rot.get_node(j));
			}
			System.out.println("\n");
			rot.rotatez(-lookvec.xytheta(new Vec(now_loc)));
			for(int j=0;j<8;j++){
				System.out.println(rot.get_node(j));
			}
			rot.rotatex(lookvec.xyztheta(new Vec(now_loc)));
			// rot.rotatey(version_vec.xztheta(lookvec));
			//System.out.println(lookvec.dot(cube_array.get(i).front));
			for(int j=0;j<8;j++){
				System.out.println(rot.get_node(j));
			}
			if(lookvec.dot(rot.front)>0){
				points[0]=(double)rot.get_nodex(0)+width/2;
				points[1]=(double)rot.get_nodez(0)+height/2;
				points[2]=(double)rot.get_nodex(1)+width/2;
				points[3]=(double)rot.get_nodez(1)+height/2;
				points[4]=(double)rot.get_nodex(5)+width/2;
				points[5]=(double)rot.get_nodez(5)+height/2;
				points[6]=(double)rot.get_nodex(4)+width/2;
				points[7]=(double)rot.get_nodez(4)+height/2;
				System.out.println("front");
				for(int j=0;j<8;j++)
					System.out.println(points[j]);
				polygon = new Polygon(points);
				//polygon.setFill(Color.rgb(194,255,194));
				polygon.setFill(pattern);
				polygon.setStroke(Color.BLACK);
				map_pane.getChildren().add(polygon);
				//System.out.println();
			}
			if(lookvec.dot(rot.behind)>0){
				points[0]=(double)rot.get_nodex(3)+width/2;
				points[1]=(double)rot.get_nodez(3)+height/2;
				points[2]=(double)rot.get_nodex(2)+width/2;
				points[3]=(double)rot.get_nodez(2)+height/2;
				points[4]=(double)rot.get_nodex(6)+width/2;
				points[5]=(double)rot.get_nodez(6)+height/2;
				points[6]=(double)rot.get_nodex(7)+width/2;
				points[7]=(double)rot.get_nodez(7)+height/2;
				System.out.println("behind");
				for(int j=0;j<8;j++)
					System.out.println(points[j]);
				polygon = new Polygon(points);
				//polygon.setFill(Color.rgb(255,194,194));
				polygon.setFill(pattern);
				polygon.setStroke(Color.BLACK);
				map_pane.getChildren().add(polygon);
				//System.out.println(1234);
			}
			if(lookvec.dot(rot.right)<0){
				points[0]=(double)rot.get_nodex(1)+width/2;
				points[1]=(double)rot.get_nodez(1)+height/2;
				points[2]=(double)rot.get_nodex(2)+width/2;
				points[3]=(double)rot.get_nodez(2)+height/2;
				points[4]=(double)rot.get_nodex(6)+width/2;
				points[5]=(double)rot.get_nodez(6)+height/2;
				points[6]=(double)rot.get_nodex(5)+width/2;
				points[7]=(double)rot.get_nodez(5)+height/2;
				System.out.println("right");
				for(int j=0;j<8;j++)
					System.out.println(points[j]);
				polygon = new Polygon(points);
				//polygon.setFill(Color.rgb(194,255,255));
				polygon.setFill(pattern);
				polygon.setStroke(Color.BLACK);
				map_pane.getChildren().add(polygon);
				//System.out.println(1234);
			}
			if(lookvec.dot(rot.left)<0){
				points[0]=(double)rot.get_nodex(0)+width/2;
				points[1]=(double)rot.get_nodez(0)+height/2;
				points[2]=(double)rot.get_nodex(3)+width/2;
				points[3]=(double)rot.get_nodez(3)+height/2;
				points[4]=(double)rot.get_nodex(7)+width/2;
				points[5]=(double)rot.get_nodez(7)+height/2;
				points[6]=(double)rot.get_nodex(4)+width/2;
				points[7]=(double)rot.get_nodez(4)+height/2;
				System.out.println("left");
				for(int j=0;j<8;j++)
					System.out.println(points[j]);
				polygon = new Polygon(points);
				//polygon.setFill(Color.rgb(255,194,255));
				polygon.setFill(pattern);
				polygon.setStroke(Color.BLACK);
				map_pane.getChildren().add(polygon);
				//System.out.println(1234);
			}
			if(lookvec.dot(rot.top)>0){
				points[0]=(double)rot.get_nodex(4)+width/2;
				points[1]=(double)rot.get_nodez(4)+height/2;
				points[2]=(double)rot.get_nodex(5)+width/2;
				points[3]=(double)rot.get_nodez(5)+height/2;
				points[4]=(double)rot.get_nodex(6)+width/2;
				points[5]=(double)rot.get_nodez(6)+height/2;
				points[6]=(double)rot.get_nodex(7)+width/2;
				points[7]=(double)rot.get_nodez(7)+height/2;
				System.out.println("top");
				for(int j=0;j<8;j++)
					System.out.println(points[j]);
				polygon = new Polygon(points);
				//polygon.setFill(Color.rgb(255,255,194));
				polygon.setFill(pattern);
				polygon.setStroke(Color.BLACK);
				map_pane.getChildren().add(polygon);
				//System.out.println(1234);
			}
			if(lookvec.dot(rot.bottom)>0){
				points[0]=(double)rot.get_nodex(0)+width/2;
				points[1]=(double)rot.get_nodez(0)+height/2;
				points[2]=(double)rot.get_nodex(1)+width/2;
				points[3]=(double)rot.get_nodez(1)+height/2;
				points[4]=(double)rot.get_nodex(2)+width/2;
				points[5]=(double)rot.get_nodez(2)+height/2;
				points[6]=(double)rot.get_nodex(3)+width/2;
				points[7]=(double)rot.get_nodez(3)+height/2;
				System.out.println("bottom");
				for(int j=0;j<8;j++)
					System.out.println(points[j]);
				polygon = new Polygon(points);
				polygon.setFill(pattern_2);
				//polygon.setFill(Color.rgb(194,194,255));
				polygon.setStroke(Color.BLACK);
				map_pane.getChildren().add(polygon);
				//System.out.println(1234);
			}
		}
		
	}
	

    @FXML
    void roleOnKeyPressed(KeyEvent event) {

		if (event.getCode() == KeyCode.A){
			x-=velX;
			System.out.println("A");
			Position.setCenterX(x);
			Position.setCenterY(y);
		}
		if(event.getCode() == KeyCode.D){
			x+=velX;
			System.out.println("D");
			Position.setCenterX(x);
			Position.setCenterY(y);
		}	
	    if(event.getCode() == KeyCode.W){
			y-=velY;
			System.out.println("W");
			Position.setCenterX(x);
			Position.setCenterY(y);
		}
		if(event.getCode() == KeyCode.S){
			y+=velY;
			System.out.println("S");
			Position.setCenterX(x);
			Position.setCenterY(y);
		}
		if(flag_jump==0){
			if(event.getCode() == KeyCode.SPACE){
				t0=t;
				flag_jump=1;
				System.out.println("Space pressed");
				Timeline timelineAnimation = new Timeline(
					new KeyFrame(Duration.millis(100), 
					new EventHandler<ActionEvent>() {
								
						@Override
						public void handle(final ActionEvent e) {
							t+=1;
							System.out.println(t);
							if((t-t0)<=20){
								z=velZ*(t-t0)+(0.5)*g*(t-t0)*(t-t0);
							}
							if((t-t0)==20){
								flag_jump=0;
							}							
							Position.setCenterY(z);
						}
					}
					)	
				);
				if(flag_time==0){	
					timelineAnimation.setCycleCount(Timeline.INDEFINITE);
					timelineAnimation.play();
					flag_time=1;
				}
			}
		}		
		
    }
	

}