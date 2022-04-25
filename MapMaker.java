
import java.io.*;
public class MapMaker{
    public MapMaker(){
        String fileName = "Map.txt";
        String encoding = "UTF-8";
        try{
            PrintWriter writer = new PrintWriter(fileName, encoding);
            for(int i=-4;i<4;i++)
                for(int j=-4;j<4;j++)
                    writer.println("1"+" "+i+" "+j+" "+"0");
            writer.close();
        } catch (IOException e){
              System.out.println("An error occurred.");
              e.printStackTrace();
        }
    }
}