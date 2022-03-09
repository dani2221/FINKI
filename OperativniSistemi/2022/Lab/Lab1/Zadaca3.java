import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Zadaca3 {
    public static File biggestFile(File f){
        File maxFile = null;
        for(File file : f.listFiles()){
            File temp = file;
            if(file.isDirectory()) temp = biggestFile(file);
            if(maxFile==null) maxFile = temp;
            if(maxFile.length()<temp.length()) maxFile = temp;
        }
        return maxFile;
    }
    public static void main(String[] args) throws FileNotFoundException {
        String path = ".";
        File f = new File(path);
        if(!f.exists() || !f.isDirectory()){
            throw new FileNotFoundException();
        }
        File maxFile = biggestFile(f);
        System.out.println(maxFile.getName());
    }
}
