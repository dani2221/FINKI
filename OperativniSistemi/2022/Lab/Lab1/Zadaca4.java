import java.io.*;

public class Zadaca4 {
    public static void filterFiles(File f){
        for(File file : f.listFiles()){
            if(file.isDirectory()) filterFiles(file);
            else{
                if(file.getName().endsWith(".txt") || file.getName().endsWith(".out")){
                    if(file.length()>1000 && file.length()<100000){
                        System.out.println(file.getAbsolutePath());
                    }
                }
            }
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        String filePath = args[0];
        File f = new File(filePath);
        if(!f.exists()) throw new FileNotFoundException();
        filterFiles(f);
    }
}
