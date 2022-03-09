import java.io.*;
import java.util.Stack;

public class Zadaca6 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream("C:\\Users\\danil\\FINKI\\OperativniSistemi\\2022\\Lab\\Lab1\\Files\\izvor.txt")));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("C:\\Users\\danil\\FINKI\\OperativniSistemi\\2022\\Lab\\Lab1\\Files\\destinacija.txt")));
        Stack<Character> bytes = new Stack<>();
        int readChar = -1;
        while ((readChar= br.read())!= -1){
            bytes.push((char) readChar);
        }
        while (!bytes.isEmpty()){
            bw.write(bytes.pop());
        }
        if(br!=null) br.close();
        if(bw!=null) bw.close();

    }
}
