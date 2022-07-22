package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClienteTCP {

    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("localhost", 8888)) {

            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            out.writeUTF("Cliente 1");

            String msgServidor = in.readUTF();

            System.out.println("Mensagem do Servidor: " + msgServidor);
            System.out.println("IP do Servidor: " + clientSocket.getInetAddress().getHostAddress());
            System.out.println("Porta do Servidor: " + clientSocket.getPort());
            System.out.println("IP do Cliente: " + clientSocket.getLocalAddress().getHostAddress());
            System.out.println("Porta do Cliente: " + clientSocket.getLocalPort());

            in.close();
            out.close();
        } catch (IOException ex) {
            System.out.println("Não foi possível montar a conexão no cliente.");
            ex.printStackTrace();
        }
    }

}
