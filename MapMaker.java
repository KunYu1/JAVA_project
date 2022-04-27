
import java.io.*;
public class MapMaker{
    public MapMaker(){
        String fileName = "Map.txt";
        String encoding = "UTF-8";
        try{
            PrintWriter writer = new PrintWriter(fileName, encoding);
            for(int i=-4;i<5;i+=2)
                for(int j=-3;j<4;j+=2)
                    writer.println("1"+" "+i+" "+"1"+" "+j);
            writer.close();
        } catch (IOException e){
              System.out.println("An error occurred.");
              e.printStackTrace();
        }
    }
}