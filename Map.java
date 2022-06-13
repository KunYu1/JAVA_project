
import java.io.File;
import java.util.*;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.image.Image;
import javafx.scene.Group;
public class Map{
    PhongMaterial material_1 = new PhongMaterial();
    PhongMaterial material_3 = new PhongMaterial();
    PhongMaterial material_2 = new PhongMaterial();
    PhongMaterial material_4 = new PhongMaterial();
    PhongMaterial material_5 = new PhongMaterial();	
    PhongMaterial material_6 = new PhongMaterial();
    PhongMaterial material_7 = new PhongMaterial();
    PhongMaterial material_8 = new PhongMaterial();
    PhongMaterial material_9 = new PhongMaterial();
    private ArrayList<MapBox> mapbox_array;
    public Map()throws Exception{
        material_1.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/1.jpg")));
        material_2.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/2.jpg")));
        material_3.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/3.jpg")));
        material_4.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/4.jpg")));
        material_5.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/5.jpg")));
        material_6.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/6.jpg")));
        material_7.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/7.jpg")));
        material_8.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/8.jpg")));
        material_9.setDiffuseMap(new Image(getClass().getResourceAsStream("texture/9.jpg")));
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
    public void construct_map(Group group, double now_x,double now_y,double now_z, double v_x,double v_y,double v_z){
        MapBox con_box;
        int con_idx = -1;
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
            d_y = now_y-y;
            d_z = z-now_z;
            l = Math.sqrt(d_x*d_x+d_y*d_y+d_z*d_z);
            //System.out.println(l+" "+v_x*d_x+v_y*d_y+v_z*d_z);
            if(Math.abs(d_x)<1000&&Math.abs(d_y)<1000&&Math.abs(d_z)<1000){
                //System.out.println(i+" "+d_x+" "+d_y+" "+d_z+" "+(v_x*d_x+v_y*d_y+v_z*d_z)/(100*l)+" "+l);
                if((v_x*d_x+v_y*d_y+v_z*d_z)/(100*l)>0.98&&l<length){
                    con_idx = i;
                    length =l;
                } else if((v_x*d_x+v_y*d_y+v_z*d_z)/(100*l)>0.95&&l<480){
                    con_idx = i;
                    length =l;  
                }
            }
        }
        double len;
        //System.out.println(break_idx);
        double the=0;
        if(con_idx >=0){
            double con_x = mapbox_array.get(con_idx).box.getTranslateX();
            double con_y = mapbox_array.get(con_idx).box.getTranslateY();
            double con_z = mapbox_array.get(con_idx).box.getTranslateZ();
            //System.out.println(con_x+" "+ con_y+" "+con_z);
            double build_x = 0;
            double build_y = 0;
            double build_z = 0;
            double leng;
            if(this.find_map(con_x,con_y,con_z+200)==0){
                leng = line_length(con_x,con_y,con_z+200,now_x,now_y,now_z);
                //System.out.println("f:"+((v_x*(con_x-now_x)+v_y*(now_y-con_y)+v_z*(con_z+200-now_z)))/(leng*100));
                if(leng<length&&((v_x*(con_x-now_x)+v_y*(now_y-con_y)+v_z*(con_z+200-now_z)))/(leng*100)>the){
                    build_x= con_x;
                    build_y= con_y;
                    build_z= con_z+200;
                    the = ((v_x*(con_x-now_x)+v_y*(now_y-con_y)+v_z*(con_z+200-now_z)))/(leng*100);
                }
                //System.out.println("123");
            }
            if(this.find_map(con_x,con_y,con_z-200)==0){
                //System.out.println("123");
                
                leng = line_length(con_x,con_y,con_z-200,now_x,now_y,now_z);
                //System.out.println("b:"+((v_x*(con_x-now_x)+v_y*(now_y-con_y)+v_z*(con_z+200-now_z)))/(leng*100));
                if(leng<length&&((v_x*(con_x-now_x)+v_y*(now_y-con_y)+v_z*(con_z-200-now_z)))/(leng*100)>the){
                    build_x= con_x;
                    build_y= con_y;
                    build_z= con_z-200;
                    the = ((v_x*(con_x-now_x)+v_y*(now_y-con_y)+v_z*(con_z-200-now_z)))/(leng*100);
                }
            }
            if(this.find_map(con_x,con_y+200,con_z)==0){
                //System.out.println("123");
                
                leng = line_length(con_x,con_y+200,con_z,now_x,now_y,now_z);
                //System.out.println("bottom:"+((v_x*(con_x-now_x)+v_y*(now_y-con_y)+v_z*(con_z+200-now_z)))/(leng*100));
                if(leng<length&&((v_x*(con_x-now_x)+v_y*(now_y-con_y-200)+v_z*(con_z-now_z)))/(leng*100)>the){
                    build_x= con_x;
                    build_y= con_y+200;
                    build_z= con_z;
                    the = ((v_x*(con_x-now_x)+v_y*(now_y-con_y-200)+v_z*(con_z-now_z)))/(leng*100);
                }
            }
            if(this.find_map(con_x,con_y-200,con_z)==0){
                leng = line_length(con_x,con_y-200,con_z,now_x,now_y,now_z);
                //System.out.println("t:"+((v_x*(con_x-now_x)+v_y*(now_y-con_y)+v_z*(con_z+200-now_z)))/(leng*100));
                //System.out.println("123");
                if(leng<length&&((v_x*(con_x-now_x)+v_y*(now_y-con_y+200)+v_z*(con_z-now_z)))/(leng*100)>the){
                    build_x= con_x;
                    build_y= con_y-200;
                    build_z= con_z;
                    the = ((v_x*(con_x-now_x)+v_y*(now_y-con_y+200)+v_z*(con_z-now_z)))/(leng*100);
                }
            }
            if(this.find_map(con_x+200,con_y,con_z)==0){
                leng = line_length(con_x+200,con_y,con_z,now_x,now_y,now_z);
                //System.out.println("r:"+((v_x*(con_x-now_x)+v_y*(now_y-con_y)+v_z*(con_z+200-now_z)))/(leng*100));
                //System.out.println("123");
                if(leng<length&&((v_x*(con_x-now_x+200)+v_y*(now_y-con_y)+v_z*(con_z-now_z)))/(leng*100)>the){
                    build_x= con_x+200;
                    build_y= con_y;
                    build_z= con_z;
                    the = ((v_x*(con_x-now_x+200)+v_y*(now_y-con_y)+v_z*(con_z-now_z)))/(leng*100);
                }
            }
            if(this.find_map(con_x-200,con_y,con_z)==0){
                leng = line_length(con_x-200,con_y,con_z,now_x,now_y,now_z);
                //System.out.println("l:"+((v_x*(con_x-now_x)+v_y*(now_y-con_y)+v_z*(con_z+200-now_z)))/(leng*100));
                //System.out.println("123");
                if(leng<length&&((v_x*(con_x-now_x-200)+v_y*(now_y-con_y)+v_z*(con_z-now_z)))/(leng*100)>the){
                    build_x= con_x-200;
                    build_y= con_y;
                    build_z= con_z;
                    the = ((v_x*(con_x-now_x-200)+v_y*(now_y-con_y)+v_z*(con_z-now_z)))/(leng*100);
                }
            }
            mapbox_array.add(new MapBox(1, (int)build_x/200, (int)build_y/200, (int)build_z/200));
            mapbox_array.get(mapbox_array.size()-1).box.setMaterial(material_1);  
            group.getChildren().add(mapbox_array.get(mapbox_array.size()-1).box);
        }

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
            d_y = now_y-y;
            d_z = z-now_z;
            l = Math.sqrt(d_x*d_x+d_y*d_y+d_z*d_z);
            //System.out.println(l+" "+v_x*d_x+v_y*d_y+v_z*d_z);
            if(Math.abs(d_x)<800&&Math.abs(d_y)<800&&Math.abs(d_z)<800){
                //System.out.println(i+" "+d_x+" "+d_y+" "+d_z+" "+(v_x*d_x+v_y*d_y+v_z*d_z)/(100*l)+" "+l);
                if((v_x*d_x+v_y*d_y+v_z*d_z)/(100*l)>0.98&&l<length){
                    break_idx = i;
                    length =l;
                } else if((v_x*d_x+v_y*d_y+v_z*d_z)/(100*l)>0.96&&l<480){
                    break_idx = i;
                    length =l;  
                }
            }
        }
        //System.out.println(break_idx);
        if(break_idx >=0){
            group.getChildren().remove(mapbox_array.get(break_idx).box);
            mapbox_array.remove(break_idx);
        }

    }
    public int find_map(double x,double y,double z){
        int flag = 0;
        for(int i=0;i<mapbox_array.size();i++){
            if(mapbox_array.get(i).box.getTranslateX()==x&&mapbox_array.get(i).box.getTranslateY()==y&&mapbox_array.get(i).box.getTranslateZ()==z){
                flag = 1;
                break;
            }
        }
        return flag;
    }
    public double line_length(double x,double y,double z,double dx,double dy,double dz){
        return Math.sqrt((x-dx)*(x-dx)+(y-dy)*(y-dy)+(z-dz)*(z-dz));
    }
}