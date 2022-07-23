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
            this.writeMessage("Cliente 1 entrou!");
            this.readServerMessages();
            this.closeConnection();
        } catch (IOException ex) {
            System.out.println("Não foi possível conectar ao servidor!");
            System.out.println(ex.getMessage());
        }
    }

    private void openConnection() throws IOException {
        System.out.println("Conectando ao servidor...");
        clientSocket = new Socket("localhost", 8888);
        in = new DataInputStream(clientSocket.getInputStream());
        out = new DataOutputStream(clientSocket.getOutputStream());
    }

    private void closeConnection() throws IOException {
        System.out.println("Saindo do servidor...");
        in.close();
        out.close();
        clientSocket.close();
    }

    private void readServerMessages() throws IOException {
        String msgServidor = in.readUTF();

        System.out.println("Mensagem do Servidor: " + msgServidor);
    }

    private void writeMessage(String message) throws IOException {
        out.writeUTF(message);
    }


}
