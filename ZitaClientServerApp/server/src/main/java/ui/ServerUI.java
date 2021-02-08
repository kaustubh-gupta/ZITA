package ui;

import server.Server;
import utils.HashSHA512;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ServerUI extends ServerFrame implements Server.ServerResponse, Server.ServerResponse2 {

    private final Server server;

    public ServerUI(Server server) {
        super();
        initListeners();
        this.server = server;
        server.setServerResponse(this);
        server.setServerResponse2(this);
    }

    private void initListeners() {
        btn_connect.addActionListener(this::connect);
        btn_gsk.addActionListener(this::generate_session_key);
        btnKey1.addActionListener(this::newKey1);
        btnKey2.addActionListener(this::newKey2);
    }

    private void connect(ActionEvent e) {
        try {
            server.setPort(Integer.parseInt(txt_port.getText()));
            new Thread(server).start();
        } catch (Exception ex) {
            error(null, ex.getMessage());
            txt_port.setText("");
        }
    }

    private void generate_session_key(ActionEvent e) {

        server.sendMessage();
    }

    private void newKey1(ActionEvent e) {
        server.generateNewKey1();
        notification("src/main/resources/key_dialog.png", "Server is exchanging the keys...");
    }

    private void newKey2(ActionEvent e) {
        server.generateNewKey2();
        notification("src/main/resources/key_dialog.png", "Server is exchanging the keys...");
    }

    @Override
    public void notifyConnectionOpen() {
        getTxt_status().setText("Waiting clients D and S");
        getTxt_status().setForeground(Color.WHITE);
        getTxt_status().setBackground(Color.decode("#003366"));
    }

    @Override
    public void notifyConnectionOpen2() {
        getTxt_status().setText("Waiting clients D and S");
        getTxt_status().setForeground(Color.WHITE);
        getTxt_status().setBackground(Color.decode("#003366"));
    }


    @Override
    public void notifyMessageReceived() {
        notification("src/main/resources/android_logo.png", "You have just received a message from D");
    }

    @Override
    public void notifyMessageReceived2() {
        notification("src/main/resources/android_logo.png", "You have just received a message from S");
    }


    String traceX = "";
    String traceY = "";

    @Override
    public void showMessageReceived(String s) {
        traceX = s;

        if (traceX.length() > 0 && traceY.length() > 0) {
            setFinalCS(traceX, traceY);
        } else {
            txt_messageReceived.setText(s);
        }
    }

    @Override
    public void showMessageReceived2(String s) {
        traceY = s;

        if (traceX.length() > 0 && traceY.length() > 0) {
            setFinalCS(traceX, traceY);
        } else {
            txt_messageReceived2.setText(s);
        }
    }

    public void setFinalCS(String CS1, String CS2) {
        String CSKeyX = keyValX().getText();
        String CSKeyY = keyValY().getText();
        String FinalX = CS1 + CS2 + CSKeyX;
        String FinalY = CS1 + CS2 + CSKeyY;

        String FinalHashX = HashSHA512.encryptThisString(FinalX);
        String FinalHashY = HashSHA512.encryptThisString(FinalY);

        txt_messageReceived.setText(FinalHashX);
        txt_messageReceived2.setText(FinalHashY);
    }

    @Override
    public void showPrivateKey(String s) {
        txt_key1.setText(s);
    }

    @Override
    public void showPrivateKey2(String s) {
        txt_key2.setText(s);
    }

    @Override
    public void showErrorMessage(String s) {
        error("Error", s);
    }

    @Override
    public void showErrorMessage2(String s) {
        error("Error", s);
    }

    @Override
    public void notifyConnectionEstablished() {
        txt_status.setText("Server connected");
        txt_status.setForeground(Color.BLACK);
        txt_status.setBackground(Color.green);
    }

    @Override
    public void notifyConnectionEstablished2() {
        txt_status.setText("Server connected");
        txt_status.setForeground(Color.BLACK);
        txt_status.setBackground(Color.green);
    }
}

