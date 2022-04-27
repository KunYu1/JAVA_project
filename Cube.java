
public class Cube extends Item{
    private Node cube_loc;
    final double length = 50;
    private Node node[];
    Vec top;
    Vec front;
    Vec behind;
    Vec bottom;
    Vec left;
    Vec right;
    public Cube(int item, double x,double y,double z){
        super(item);
        this.cube_loc = new Node(x, y, z);
        node = new Node[8];
        node[0] = new Node(x*length-length/2, y*length-length/2, z*length-length/2);
        node[1] = new Node(x*length+length/2, y*length-length/2, z*length-length/2);
        node[2] = new Node(x*length+length/2, y*length+length/2, z*length-length/2);
        node[3] = new Node(x*length-length/2, y*length+length/2, z*length-length/2);
        node[4] = new Node(x*length-length/2, y*length-length/2, z*length+length/2);
        node[5] = new Node(x*length+length/2, y*length-length/2, z*length+length/2);
        node[6] = new Node(x*length+length/2, y*length+length/2, z*length+length/2);
        node[7] = new Node(x*length-length/2, y*length+length/2, z*length+length/2);
        top = new Vec(0, 0, -1);
        bottom = new Vec(0, 0, 1);
        front = new Vec(0, -1, 0);
        behind = new Vec(0, 1, 0);
        right = new Vec(1, 0, 0);
        left = new Vec(-1, 0, 0);        
    }
    public Node get_loc(){
        return cube_loc;
    }
    public Node get_true_loc(){
        return new Node(cube_loc.getx()*length, cube_loc.gety()*length, cube_loc.getz()*length);
    }
    public int get_item(){
        return super.get_item_num();
    }
    public double get_nodex(int n){
        return node[n].getx();
    }
    public double get_nodey(int n){
        return node[n].gety();
    }
    public double get_nodez(int n){
        return node[n].getz();
    }
    public Node get_node(int n){
        return node[n];
    }
    public void set_loc(int n,Node in_node){
        node[n].setpos(in_node.getx(), in_node.gety(), in_node.getz());
    }
}