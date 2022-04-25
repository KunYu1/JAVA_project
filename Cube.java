
public class Cube extends Item{
    private Node cube_loc;
    final int length = 100;
    public Cube(int item, int x,int y,int z){
        super(item);
        this.cube_loc = new Node(x, y, z);
    }
}