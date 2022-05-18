
import java.io.File;
import java.util.*;
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
}