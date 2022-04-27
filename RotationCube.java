
public class RotationCube extends Cube{
    private Node n[];
    private double x_d;
    private double z_d;
    public RotationCube(Cube cube){
        super(cube.get_item(), cube.get_loc().getx(), cube.get_loc().gety(), cube.get_loc().getz());
        n = new Node[8];
        x_d =0;
        z_d = 0;
        double length = cube.length;
        n[0] = new Node(-length/2, -length/2, -length/2);
        n[1] = new Node(length/2, -length/2, -length/2);
        n[2] = new Node(length/2, length/2, -length/2);
        n[3] = new Node(-length/2, length/2, -length/2);
        n[4] = new Node(-length/2, -length/2, length/2);
        n[5] = new Node(length/2, -length/2, length/2);
        n[6] = new Node(length/2, length/2, length/2);
        n[7] = new Node(-length/2, length/2, length/2);
    }
    public void rotatez(double z){
        z_d = z -z_d;        
        for(int i=0;i<8;i++){
            //Node node = new Node(n[i]);
            n[i].rotate_z(z_d);
            //System.out.println("i:"+node);
            this.set_loc(i, this.get_true_loc().add_node(n[i]));
            //System.out.println("k:"+get_loc());
            //System.out.println(z);
        }
    }
    public void rotatex(double x){
        x_d = x -x_d;
        for(int i=0;i<8;i++){
            //Node node = new Node(n[i]);
            n[i].rotate_x(x_d);
            //System.out.println("i:"+node);
            this.set_loc(i, this.get_true_loc().add_node(n[i]));
            //System.out.println("k:"+get_loc());
            //System.out.println(z);
        }
    }
}