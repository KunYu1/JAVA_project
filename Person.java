import javafx.scene.Node;
import javafx.scene.shape.Box;
public class Person {
    Box box;
    Box box_visible;
    double old_x;
    double old_y;
    double old_z;
    Person(){
        box_visible = new Box(1, 1, 1);
        box = new Box(10, 10, 10);
    }
    void set_visible(double x,double y, double z){
        box_visible.setTranslateX(x+box.getTranslateX());
        box_visible.setTranslateY(y+box.getTranslateY());
        box_visible.setTranslateZ(z+box.getTranslateZ());
    }

}
