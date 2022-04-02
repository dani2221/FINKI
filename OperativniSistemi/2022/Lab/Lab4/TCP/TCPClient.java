package TCP;

import java.io.*;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("194.149.135.49",9753);
        Reader reader = new Reader(socket);
        Writer writer = new Writer(socket);
        reader.start();
        writer.start();
    }
}

class Reader extends Thread{
    BufferedReader reader;
    Socket socket;

    public Reader(Socket socc) throws IOException {
        reader = new BufferedReader(new InputStreamReader(socc.getInputStream()));
        socket = socc;
    }

    @Override
    public void run() {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if(line.contains("bad greeting")){
                    reader.close();
                    socket.close();
                }
            }
        }catch (IOException ex){
            System.out.println("SOCKET CLOSED");
        }
    }
}

class Writer extends Thread{
    PrintWriter writer;

    public Writer(Socket socc) throws IOException {
        writer = new PrintWriter(new OutputStreamWriter(socc.getOutputStream()));
    }

    @Override
    public void run() {
        writer.write("login:206033\n");
        writer.flush();
        writer.write("hello:206033\n");
        writer.flush();
        writer.write("206033:test poraka\n");
        writer.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            Communicate(br);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void Communicate(BufferedReader br) throws InterruptedException, IOException {
        while (true){
            Thread.sleep(1000);
            System.out.println("Enter destination:");
            String dest = br.readLine();
            System.out.println("Enter message:");
            String message = br.readLine();
            writer.write(dest+":"+message+'\n');
            writer.flush();
        }
    }
}
