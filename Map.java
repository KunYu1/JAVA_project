
import java.io.File;
import java.util.*;

import javafx.scene.Group;
public class Map{
    private ArrayList<MapBox> mapbox_array;
    public Map()throws Exception{
        mapbox_array = new ArrayList<MapBox>();
        int type;
        int x;
        int y;
        int z;
        File file = new File("Map.txt");
        Scanner sc = new Scanner(file);
        while(sc.hasNextInt()){
            type = sc.nextInt();
            x = sc.nextInt();
            y = sc.nextInt();
            z = sc.nextInt();
            mapbox_array.add(new MapBox(type, x, y, z));            
        }
        sc.close();
    }
    public ArrayList<MapBox> get_cube(){
        return mapbox_array;
    }
    public void break_map(Group group, double now_x,double now_y,double now_z, double v_x,double v_y,double v_z){
        int break_idx = -1;
        double length = 100000;
        double l;
        double x;
        double y;
        double z;
        double d_x;
        double d_y;
        double d_z;
        for(int i=0;i<mapbox_array.size();i++){
            x = mapbox_array.get(i).box.getTranslateX();
            y = mapbox_array.get(i).box.getTranslateY();
            z = mapbox_array.get(i).box.getTranslateZ();
            d_x = x-now_x;
            d_y = y-now_y;
            d_z = z-now_z;
            l = Math.sqrt(d_x*d_x+d_y*d_y+d_z*d_z);
            //System.out.println(l+" "+v_x*d_x+v_y*d_y+v_z*d_z);
            if(Math.abs(d_x)<600&&Math.abs(d_y)<600&&Math.abs(d_z)<600){
                System.out.println(d_x+" "+d_y+" "+d_z+" "+(v_x*d_x+v_y*d_y+v_z*d_z));
                if((v_x*d_x+v_y*d_y+v_z*d_z)/(100*l)>0.5&&l<length){
                    break_idx = i;
                }             
            }
        }
        System.out.println(break_idx);
        if(break_idx >=0){
            group.getChildren().remove(mapbox_array.get(break_idx).box);
            mapbox_array.remove(break_idx);
        }

    }
}