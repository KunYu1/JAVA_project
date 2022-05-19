
import java.io.*;
public class MapMaker{
    public MapMaker(){
        String fileName = "Map.txt";
        String encoding = "UTF-8";
        try{
            PrintWriter writer = new PrintWriter(fileName, encoding);
            for(int k = 2;k<=10;k++){
                for(int i=-k;i<=k;i+=1)
                    for(int j=-k;j<=k;j+=1)
                        writer.println("3"+" "+i+" "+k+" "+j);
            }
            for(int i=-100;i<=100;i+=1)
                for(int j=-100;j<=100;j+=1)
                    writer.println("1"+" "+i+" "+"11"+" "+j);
            // for(int i=0;i<=0;i+=1)
            //     for(int j=-6;j<=-5;j+=1)
            //         writer.println("1"+" "+i+" "+"1"+" "+j);
            // for(int i=5;i<=6;i+=1)
            //     for(int j=0;j<=0;j+=1)
            //         writer.println("1"+" "+i+" "+"1"+" "+j);
            // for(int i=-6;i<=-5;i+=1)
            //     for(int j=0;j<=0;j+=1)
            //         writer.println("1"+" "+i+" "+"1"+" "+j);                        
            writer.close();
        } catch (IOException e){
              System.out.println("An error occurred.");
              e.printStackTrace();
        }
    }
}