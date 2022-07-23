package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {

    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public static void main(String[] args) {
        ServidorTCP server = new ServidorTCP();
        Thread t1 = new Thread(server::start);
        t1.start();

        Thread t2 = new Thread(() -> new ClienteTCP().start());
        t2.start();

    }

    private void start() {
        try {
            this.openConnection();
            this.writeMessage("Seja bem vindo ao Servidor!");
            this.writeMessage("Divirta-se!");
            this.readClientMessages();
            this.closeConnection();
        } catch (IOException ex) {
            System.out.println("Não foi possível montar a conexão no servidor.");
            System.out.println(ex.getMessage());
        }
    }

    private void openConnection() throws IOException {
        System.out.println("Iniciando servidor...");
        serverSocket = new ServerSocket(8888);
        socket = serverSocket.accept();
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }

    private void closeConnection() throws IOException {
        System.out.println("Finalizando servidor...");
        in.close();
        out.close();
        socket.close();
        serverSocket.close();
    }

    private void readClientMessages() throws IOException {
        String msgCliente = in.readUTF();
        System.out.println(msgCliente);
    }

    private void writeMessage(String message) throws IOException {
        out.writeUTF(message);
    }

}
