package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteTCP {

    private Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;

    public static void main(String[] args) {
        new ClienteTCP().start();
    }

    public void start() {
        try {
            this.openConnection();
            this.writeMessage(String.format("[Cliente %s]: Olá", clientSocket.getLocalPort()));
            this.readServerMessages();
//            this.closeConnection();
        } catch (IOException ex) {
            System.out.println("Não foi possível conectar ao servidor!");
            System.out.println(ex.getMessage());
        }
    }

    private void openConnection() throws IOException {
        clientSocket = new Socket("localhost", 8888);
        in = new DataInputStream(clientSocket.getInputStream());
        out = new DataOutputStream(clientSocket.getOutputStream());
        String cliente = String.format("[Cliente %s]: ", clientSocket.getLocalPort());
        System.out.println(cliente + "Conectando ao servidor...");
    }

    private void closeConnection() throws IOException {
        String cliente = String.format("[Cliente %s]: ", clientSocket.getLocalPort());
        System.out.println(cliente + "Saindo do servidor...");
        in.close();
        out.close();
        clientSocket.close();
    }

    private void readServerMessages() throws IOException {
        String msgServidor = in.readUTF();

        String cliente = String.format("[Cliente %s]: ", clientSocket.getLocalPort());
        System.out.println(cliente + "Mensagem do Servidor: " + msgServidor);
    }

    private void writeMessage(String message) throws IOException {
        out.writeUTF(message);
    }


}
