import java.rmi.server.RemoteRef;
import java.security.cert.PKIXCertPathValidatorResult;

public class Node{
    private double x;
    private double y;
    private double z;
    public Node(double x,double y,double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
	public Node(Node b){
        this.x = b.x;
        this.y = b.y;
        this.z = b.z;
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
	public double addX(double dx){
		this.x += dx;
		return this.x;
	}
	public double addY(double dy){
		this.y += dy;
		return this.y;
	}
	public double addZ(double dz){
		this.z += dz;
		return this.z;
	}
    public void setpos(double x,double y,double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
	public Node add_node(Node b){
		return new Node(this.x+b.x, this.y+b.y, this.z+b.z);
	}
    public String toString(){
        return x+" "+y+" "+z;
    }
    public void rotate_x(double t){
		double xx;
		double yy;
		double zz;
		xx = x;
		yy = Math.cos((t*2*Math.PI)/360.0)*y-Math.sin((t*2*Math.PI)/360.0)*z; 
		zz = Math.sin((t*2*Math.PI)/360.0)*y+Math.cos((t*2*Math.PI)/360.0)*z; 
		this.x=(int)xx;
		this.y=(int)yy;
		this.z=(int)zz;
	}
	// public void rotate_y(double t){
	// 	double xx;
	// 	double yy;
	// 	double zz;
	// 	xx = Math.cos((t*2*Math.PI)/360.0)*(double)x+Math.sin((t*2*Math.PI)/360.0)*(double)z;
	// 	yy = y; 
	// 	zz = -Math.sin((t*2*Math.PI)/360.0)*(double)x+Math.cos((t*2*Math.PI)/360.0)*(double)z; 
	// 	this.x=(int)xx;
	// 	this.y=(int)yy;
	// 	this.z=(int)zz;
	// }
	public void rotate_z(double t){
		double xx=0;
		double yy=0;
		double zz=0;
		xx = Math.cos((t*2*Math.PI)/360.0)*x-Math.sin((t*2*Math.PI)/360.0)*y;
		yy = Math.sin((t*2*Math.PI)/360.0)*x+Math.cos((t*2*Math.PI)/360.0)*y; 
		zz = z; 
		//System.out.println("123: "+x+" "+y);
		this.x=xx;
		this.y=yy;
		this.z=zz;
	}
}