import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.input.KeyCode;
public class WindowsController {
	String Key;
	int x=300,y=200;
    @FXML
    private Circle Position;
    @FXML
    private Button btn;

    @FXML
    void roleOnKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.A){
			x-=5;
			System.out.println("A");
			Position.setCenterX(x);
		}
		if(event.getCode() == KeyCode.D){
			x+=5;
			System.out.println("D");
			Position.setCenterX(x);
		}	
	    if(event.getCode() == KeyCode.W){
			y-=5;
			System.out.println("W");
			Position.setCenterY(y);
		}
		if(event.getCode() == KeyCode.S){
			y+=5;
			System.out.println("S");
			Position.setCenterY(y);
		}
		if(event.getCode() == KeyCode.ENTER){
			System.out.println("Enter pressed");
		}	
    }

}
