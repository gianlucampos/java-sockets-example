package tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8888)) {
            Socket socket = serverSocket.accept();


            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String msgCliente = in.readUTF();
            System.out.println("Nome do cliente: ".concat(msgCliente));
            System.out.println("IP do cliente: ".concat(socket.getInetAddress().getHostAddress()));
            System.out.println("Porta do cliente: ".concat(String.valueOf(socket.getPort())));

            String msgServidor = "Olá cliente ".concat(msgCliente);
            out.writeUTF(msgServidor);

            in.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("Não foi possível montar a conexão no servidor.");
        }

    }

}
