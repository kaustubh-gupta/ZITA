package server;

import dh.KeyExchange;
import utils.HashSHA512;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static utils.ZitaChatUtils.ENCRYPTED_MESSAGE;
import static utils.ZitaChatUtils.PROTOCOL_SEPARATOR;

public class Server implements Runnable {

    private ServerResponse serverResponse;
    private ServerResponse2 serverResponse2;

    private ClientHandler clientHandler;
    private ClientHandler2 clientHandler2;

    private int port;
    private ServerSocket serverSocket;
    private Socket socket;
    private Socket socket2;
    private KeyExchange keyExchange;
    private KeyExchange keyExchange2;

    public void openConnection(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
        serverResponse.notifyConnectionOpen();
        serverResponse2.notifyConnectionOpen2();
    }


    public void setPort(int port) {
        this.port = port;
    }

    public void setServerResponse(ServerResponse serverResponse) {
        this.serverResponse = serverResponse;
    }

    public void setServerResponse2(ServerResponse2 serverResponse2) {
        this.serverResponse2 = serverResponse2;
    }

    public void handleClient() {
        initKeyExchange();

        clientHandler = new ClientHandler(socket, serverResponse);
        clientHandler.setKeyExchange(keyExchange);
        clientHandler.start();

        clientHandler2 = new ClientHandler2(socket2, serverResponse2);
        clientHandler2.setKeyExchange(keyExchange2);
        clientHandler2.start();
    }

    public void generateNewKey1() {
        initKeyExchange();
        clientHandler.requestNewKey();
    }

    public void generateNewKey2() {
        initKeyExchange();
        clientHandler2.requestNewKey();
    }

    @Override
    public void run() {

        try {
            openConnection(port);
            acceptConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        serverResponse.notifyConnectionEstablished();
        serverResponse2.notifyConnectionEstablished2();
        handleClient();

    }

    private void acceptConnection() throws IOException {
        this.socket = serverSocket.accept();
        this.socket2 = serverSocket.accept();
    }


    public void sendMessage() {

        String Random = HashSHA512.Random;

        clientHandler.sendMessage(ENCRYPTED_MESSAGE, PROTOCOL_SEPARATOR, Random);
        clientHandler2.sendMessage(ENCRYPTED_MESSAGE, PROTOCOL_SEPARATOR, Random);
    }

    public void initKeyExchange() {
        this.keyExchange = new KeyExchange();
        keyExchange.init();

        this.keyExchange2 = new KeyExchange();
        keyExchange2.init();

    }

    public interface ServerResponse {
        void notifyConnectionOpen();

        void notifyMessageReceived();

        void showMessageReceived(String s);

        void showPrivateKey(String s);

        void showErrorMessage(String s);

        void notifyConnectionEstablished();
    }

    public interface ServerResponse2 {
        void notifyConnectionOpen2();

        void notifyMessageReceived2();

        void showMessageReceived2(String s);

        void showPrivateKey2(String s);

        void showErrorMessage2(String s);

        void notifyConnectionEstablished2();
    }


}
