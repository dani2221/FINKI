import java.io.*;

public class Zadaca1 {
    public static void moveWritableTxtFiles(String from, String to){
        File fromFile = new File(from);
        if(!fromFile.exists()){
            System.out.println("Does not exist");
            return;
        }
        File[] filesToMove = fromFile.listFiles(f -> f.getName().endsWith(".txt") && f.canWrite());
        for(File newFile : filesToMove){
            newFile.renameTo(new File(to + "/" + newFile.getName()));
        }
    }
    public static void main(String[] args) {
        moveWritableTxtFiles(".", "test");
    }
}
