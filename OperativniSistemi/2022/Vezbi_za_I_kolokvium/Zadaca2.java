import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Zadaca2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        // Client cl = new Client(9876,"COPY ./img1.png ./gas.png");
        // Client cl = new Client(9876,"LIST .");
        Client cl = new Client(9876,"DELETE ./Lab");
        Server srv = new Server(9876);
        srv.start();
        Thread.sleep(1000);
        cl.start();
    }
}
class Client extends Thread{
    private int port;
    private String command;

    public Client(int port, String command) {
        this.port = port;
        this.command = command;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(),port);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.write(command);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class Server extends Thread{
    ServerSocket socket;

    public Server(int port) throws IOException {
        this.socket = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (true){
            try {
                Socket conn = socket.accept();
                BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = bf.readLine();
                String[] parts = line.split("\\s++");
                if (parts[0].equals("COPY")){
                    try {
                        copyFile(parts[1],parts[2]);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if(parts[0].equals("LIST")){
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
                    listFiles(new File(parts[1]),bw);
                    bw.flush();
                }
                if(parts[0].equals("DELETE")){
                    deleteFile(new File(parts[1]));
                }
            } catch (IOException e) {
                System.out.println("err");
            }
        }
    }

    private void deleteFile(File file) {
        file.delete();
    }

    private void copyFile(String fr, String t) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fr)));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(t)));
        try {
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
            }
        }catch (IOException ex){
            ex.printStackTrace();
        }

        try {
            bw.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listFiles(File daddy, BufferedWriter out){
        if(daddy.isDirectory()){
            for(File child : daddy.listFiles()){
                listFiles(child,out);
            }
        }
        if(daddy.getName().endsWith(".txt")){
            try {
                out.write(String.format("Name: %s, Size: %d, CreatedAt:%s",
                        daddy.getName(),
                        daddy.length(),
                        new Date(daddy.lastModified()).toString()
                ));
                out.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}