/*
 * Name: Jessica Tjong
 * Group: BCE3
 * IP Address: -
 */
package ce3005client;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Rfc865UdpClient {
    public static void main(String[] argv) {
        //
        // 1. Open UDP socket
        //
        DatagramSocket socket = null;
        int port = 1234;
        int bufferLength = 512;
        byte[] receiveBuf = new byte[bufferLength];
        String message = "Jessica Tjong, BCE3, -";
        byte[] sendBuf = message.getBytes();
        
        try {
            socket = new DatagramSocket();
            InetAddress server = InetAddress.getByName("localhost");
            socket.connect(server, port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try {
            //
            // 2. Send UDP request to server
            //
            DatagramPacket request = new DatagramPacket(sendBuf, sendBuf.length);
            socket.send(request);
            System.out.println("Sent message to server: " + message);
            //
            // 3. Receive UDP reply from server
            //
            DatagramPacket reply = new DatagramPacket(receiveBuf, receiveBuf.length);
            socket.receive(reply);
            String s = new String(receiveBuf);
            System.out.println("Received message from server: " + s);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}