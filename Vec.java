public class Vec{
    private double x;
    private double y;
    private double z;
    public Vec(double x,double y,double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Vec(Node a,Node b){
        this.x = a.getx()-b.getx();
        this.y = a.gety()-b.gety();
        this.z = a.getz()-b.getz();
    }
    public Vec(Vec a,Vec b){
        this.x = a.getx()-b.getx();
        this.y = a.gety()-b.gety();
        this.z = a.getz()-b.getz();
    }
    public Vec(Node a){
        this.x = a.getx();
        this.y = a.gety();
        this.z = a.getz();
    }
    public double getx(){
        return x;
    }
    public double gety(){
        return y;
    }
    public double getz(){
        return z;
    }
    public void setvec(double x,double y,double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public double dot(Vec b){
        return (this.x*b.x)+(this.y*b.y)+(this.z*b.z);
    }
    public Vec product(Vec b){
        double x = this.y*b.z-this.z*b.y;
        double y = this.z*b.x-this.x*b.z;
        double z = this.x*b.y-this.y*b.x;
        return new Vec(x, y, z);
    }
    public String toString(){
        return x+" "+y+" "+z;
    }
    public double xyztheta(Vec b){
        Vec c = new Vec(this, b);
        double r = Math.sqrt(c.x*c.x+c.y*c.y+c.z*c.z);
        double the = Math.acos(z/r);
        return 90-the*(180/Math.PI);
    }
    public double xytheta(Vec b){
        Vec c = new Vec(this, b);
        double r = Math.sqrt(c.x*c.x+c.y*c.y+c.z*c.z);
        double the = Math.acos(z/r);
        return 90-Math.acos(c.x/(r*Math.sin(the)))*(180/Math.PI);
    }
    public double r(Vec b){
        Vec c = new Vec(this, b);
        double r = Math.sqrt(c.x*c.x+c.y*c.y+c.z*c.z);
        double the = Math.acos(z/r);
        return r;
    }
}