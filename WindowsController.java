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
    void roleOnKeyPressed(KeyEvent event) {

		if (event.getCode() == KeyCode.A){
			x-=velX;
			System.out.println("A");
			Position.setCenterX(x);
			//Position.setCenterY(y);
		}
		if(event.getCode() == KeyCode.D){
			x+=velX;
			System.out.println("D");
			Position.setCenterX(x);
			//Position.setCenterY(y);
		}	
	    if(event.getCode() == KeyCode.W){
			y-=velY;
			System.out.println("W");
			//Position.setCenterX(x);
			Position.setCenterY(y);
		}
		if(event.getCode() == KeyCode.S){
			y+=velY;
			System.out.println("S");
			//Position.setCenterX(x);
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