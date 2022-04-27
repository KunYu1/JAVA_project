
import java.io.File;
import java.util.*;
public class Map{
    private ArrayList<Cube> cube_array;
    public Map()throws Exception{
        cube_array = new ArrayList<Cube>();
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
            cube_array.add(new Cube(type, x, y, z));            
        }
        sc.close();
    }
    public ArrayList<Cube> get_cube(){
        return cube_array;
    }
}