// Autoren: Ole Pearse-Danker, Kateryna Bodrova
// Datum: 10.01.2024

package worksheet4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPSocket {
    private DatagramSocket socket;
    private InetAddress address;
    private int port;

    public UDPSocket(int port) throws SocketException {
        this.socket = new DatagramSocket(port);
    }

    public void send(String s, InetAddress addr, int port) throws IOException {
        byte[] buffer = s.getBytes();
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, addr, port);
        socket.send(packet);
    }

    public String receive(int maxBytes) throws IOException {
        byte[] buffer = new byte[maxBytes];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        address = packet.getAddress();
        this.port = packet.getPort();
        return new String(packet.getData(), 0, packet.getLength());
    }

    public void reply(String s) throws IOException {
        if (address == null) {
            throw new IOException("No address to reply to");
        }
        send(s, address, port);
    }

    public InetAddress getSenderAddress() {
        return address;
    }

    public int getSenderPort() {
        return port;
    }
}
