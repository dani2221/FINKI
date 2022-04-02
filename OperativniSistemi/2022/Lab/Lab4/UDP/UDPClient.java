package UDP;

import java.io.IOException;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        String address = "194.149.135.49";
        int port = 9753;
        byte[] ref = "206033".getBytes();
        DatagramPacket packet = new DatagramPacket(ref,ref.length, InetAddress.getByName(address),port);
        socket.send(packet);
        packet = new DatagramPacket(ref, ref.length);
        socket.receive(packet);
        System.out.println(new String(packet.getData(),0,packet.getLength()));
        socket.close();
    }
}
