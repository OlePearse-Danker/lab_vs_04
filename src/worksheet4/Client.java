// Autoren: Ole Pearse-Danker, Kateryna Bodrova
// Datum: 10.01.2024

package worksheet4;

import java.net.*;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1250;

    public static void main(String[] args) {
        try {
            // Set up client socket
            DatagramSocket clientSocket = new DatagramSocket();

            // Increment counter n times
            int n = 10;  // Number of times to increment
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < n; i++) {
                // Send request to server
                String request = "increment";
                byte[] sendData = request.getBytes();
                InetAddress serverAddress = InetAddress.getByName(SERVER_ADDRESS);
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
                clientSocket.send(sendPacket);

                // Receive response from server
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String response = new String(receivePacket.getData()).trim();

                System.out.println("Received response from server: " + response);
            }

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            double averageTime = (double) totalTime / n;

            System.out.println("Total time taken: " + totalTime + " ms");
            System.out.println("Average time per increment: " + averageTime + " ms");

            // Close the socket
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

