import java.security.cert.PKIXCertPathValidatorResult;

public class Node{
    private int x;
    private int y;
    private int z;
    public Node(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }
    public int getx(){
        return x;
    }
    public int gety(){
        return y;
    }
    public int getz(){
        return z;
    }
    public void setpos(int x,int y,int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
}