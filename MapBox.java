
import java.io.File;
import java.util.*;
import javafx.scene.shape.Box;
public class MapBox{
    Box box;
    Item type;
    int x;
    int y;
    int z;
    public MapBox(int type,int x,int y,int z){
        this.type = new Item(type);
        this.box = new Box(200,200,200);
        this.box.setTranslateX(200*x);
        this.box.setTranslateY(200*y);
        this.box.setTranslateZ(200*z);
        this.x =x;
        this.y =y;
        this.z =z;
    }
}