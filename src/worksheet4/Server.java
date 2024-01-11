// Autoren: Ole Pearse-Danker, Kateryna Bodrova
// Datum: 10.01.2024

package worksheet4;

import java.net.*;

public class Server {
    private static int counter = 0;

    public static void main(String[] args) {
        try {
            // Set up server socket
            DatagramSocket serverSocket = new DatagramSocket(1250);

            System.out.println("worksheet4.Server is running...");

            while (true) {
                // Receive request from client
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String request = new String(receivePacket.getData()).trim();

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                System.out.println("Received request from client: " + request);

                // Process request
                if (request.equalsIgnoreCase("increment")) {
                    counter++;
                    System.out.println("Counter incremented to: " + counter);
                } else if (request.equalsIgnoreCase("reset")) {
                    counter = 0;
                    System.out.println("Counter reset to: " + counter);
                }

                // Send response to client
                byte[] sendData = String.valueOf(counter).getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
