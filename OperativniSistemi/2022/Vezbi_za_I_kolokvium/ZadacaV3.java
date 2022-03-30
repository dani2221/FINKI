import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Random;

public class ZadacaV3 {
    public static void main(String[] args) throws InterruptedException {
        ServerR srv = new ServerR();
        srv.start();
        HashSet<ClientR> clients = new HashSet<>();
        for(int i=0;i<10;i++){
            clients.add(new ClientR());
        }
        for(ClientR cl : clients){
            cl.start();
        }

    }
}
class ClientR extends Thread{

    @Override
    public void run(){
        Socket socket = null;
        try {
            socket = new Socket(InetAddress.getLocalHost(),9753);
            PrintWriter bw = new PrintWriter(socket.getOutputStream());
            bw.write("hello\n");
            bw.flush();
            bw.write("ide gas?\n");
            bw.flush();
            bw.write("bye\n");
            bw.flush();
            bw.write("lol ne\n");
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class ServerR extends Thread{

    @Override
    public void run() {
        try {
            ServerSocket socket = new ServerSocket(9753);
            System.out.println("SERVER STARTED");
            while (true){
                Socket conn = socket.accept();
                System.out.println("NEW CONNECTION - "+conn.getPort());
                ServerWorker worker = new ServerWorker(conn);
                worker.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ServerWorker extends Thread{
    Socket conn;
    PrintWriter writer;
    BufferedReader reader;

    public ServerWorker(Socket conn) throws IOException {
        this.conn = conn;
        this.writer = new PrintWriter(new OutputStreamWriter(conn.getOutputStream()));
        this.reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    }

    @Override
    public void run() {

        String line;
        File folder = new File("./Chats/"+conn.getPort());
        if(!folder.mkdirs()) System.out.println("oof rip");
        int counter = 0;
        File newFile = new File("./Chats/"+conn.getPort()+"/comm"+(counter++)+".txt");
        PrintWriter wtFile = null;
        try {
            wtFile = new PrintWriter(new OutputStreamWriter(new FileOutputStream(newFile)));
            while ((line=reader.readLine())!=null){
                wtFile.println(line);
                wtFile.flush();
                if(line.equals("bye")){
                    wtFile.close();
                    newFile = new File("./Chats/"+conn.getPort()+"/comm"+(counter++)+".txt");
                    wtFile = new PrintWriter(new OutputStreamWriter(new FileOutputStream(newFile)));
                }
            }
            System.out.println("CONNECTION CLOSED - "+conn.getPort());
            wtFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

