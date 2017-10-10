package ce3005;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.SocketException;

public class Rfc865UdpServer {

    public static void main(String[] argv) {
        //
        // 1. Open UDP socket at well-known port
        //
        DatagramSocket socket = null;
        int port = 1234;
        int bufferLength = 512;
        byte[] receiveBuf = new byte[bufferLength];
        byte[] sendBuf = "Hello!".getBytes();
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                //
                // 2. Listen for UDP request from client
                //
                System.out.println("Waiting for request from client");
                DatagramPacket request = new DatagramPacket(receiveBuf, bufferLength);
                socket.receive(request);
                String s = new String(receiveBuf);
                System.out.println("Received message from client: " + s);
                //
                // 3. Send UDP reply to client
                //
                DatagramPacket reply = new DatagramPacket(sendBuf, sendBuf.length, request.getAddress(), request.getPort());
                System.out.println("IP: " + request.getAddress().toString());
                socket.send(reply);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}