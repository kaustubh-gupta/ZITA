package com.zita.client.asynctask;

import android.os.AsyncTask;

import com.zita.client.ClientApplication;
import com.zita.client.ui.activity.ChatResponseActivity;
import com.zita.client.utils.ZitaChatUtils;

import java.io.ObjectInputStream;

public class ReceiveMessage extends AsyncTask<Void, String, String> {

    private ClientApplication clientApplication;
    private boolean isConnected;
    private ObjectInputStream ois;
    private ReceiveMessageResponse response;

    public ReceiveMessage(ClientApplication clientApplication) {
        this.clientApplication = clientApplication;
    }

    @Override
    protected String doInBackground(Void... voids) {
        while (isConnected) {
            String data;
            ZitaChatUtils.delay(100);

            data = readMessage();

            if (data != null) {
                int command = ZitaChatUtils.retrieveCommand(data);
                String message = ZitaChatUtils.retrieveMessage(data);
                performOperation(command, message);
            }
        }

        return null;
    }

    private void performOperation(int c, String message) {
        if (c == ZitaChatUtils.REQUEST_PUBLIC_KEY) {
            byte[] decodedPublicKey = ZitaChatUtils.decodeBase64(message);
            clientApplication.executeKeyExchange(decodedPublicKey);
        } else if (c == ZitaChatUtils.ENCRYPTED_MESSAGE) {
            publishProgress(message);
        } else if (c == ZitaChatUtils.REQUEST_NEW_KEY) {
            byte[] decodedPublicKey = ZitaChatUtils.decodeBase64(message);
            clientApplication.executeKeyExchange(decodedPublicKey);
        }
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        String message = values[0];
        response.notifyUserMessageReceived();
        setInMessage(message);
        ChatResponseActivity.btn_send.performClick();

    }

    static String FinalInMessage = "";

    public static void setInMessage(String message) {
        FinalInMessage = message;
    }

    public static String getInMessage() {
        return FinalInMessage;
    }

    private String readMessage() {
        String message = null;

        try {
            message = ois.readUTF();
            System.err.println(message);
        } catch (Exception e) {
            response.showError(e.getLocalizedMessage());
        }

        return message;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }

    public void setIsConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public void setResponse(ReceiveMessageResponse response) {
        this.response = response;
    }

    public interface ReceiveMessageResponse {
        void notifyUserMessageReceived();

        void showError(String error);
    }
}