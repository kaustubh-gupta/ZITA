package com.zita.client.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.zita.client.ClientApplication;
import com.zita.client.R;
import com.zita.client.asynctask.ReceiveMessage;
import com.zita.client.asynctask.SendMessage;
import com.zita.client.ui.fragments.GenerateKeyFragment;
import com.zita.client.ui.fragments.LoadingKeyFragment;
import com.zita.client.utils.HashSHA512;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class ChatResponseActivity extends AppCompatActivity implements
        ReceiveMessage.ReceiveMessageResponse,
        SendMessage.SendMessageResponse,
        ClientApplication.ChatResponse {

    ClientApplication clientApplication;

    TextView txt_key;
    TextView txt_encrypted;
    TextView deviceNameText;

    public static ImageButton btn_send;

    DialogFragment generatePublicKeyDialog;
    DialogFragment generateKeyPairDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        clientApplication = ((ClientApplication) getApplication());

        txt_key = findViewById(R.id.txt_key);
        txt_encrypted = findViewById(R.id.txt_encrypted);
        btn_send = findViewById(R.id.btnSend);
        deviceNameText = findViewById(R.id.deviceNameText);

        generatePublicKeyDialog = LoadingKeyFragment.getInstance();
        generateKeyPairDialog = GenerateKeyFragment.getInstance();

        String deviceName = LoginActivity.getDeviceName();
        deviceNameText.setText(deviceName);


        clientApplication.setReceiveMessageResponse(this);
        clientApplication.setSendMessageResponse(this);
        clientApplication.setChatClient(this);
        clientApplication.executeReceive();


        try {
            clientApplication.ready();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // X ~ D
    // Y ~ S
    public void send(View v) {
        String m = ReceiveMessage.getInMessage();
        int size = 128;

        String[] tokens = m.split("(?<=\\G.{" + size + "})");

        String filePath = "/sdcard/Android/data/com.example.android.bluetoothchat/files/BluetoothChat/Keys.txt";
        String all = "";
        String finalText = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                all = all + strLine;
            }
            finalText = all;
        } catch (IOException e) {
            Log.e("notes_err", e.getLocalizedMessage());
        }

        String SessionKeyX = finalText + tokens[0];
        String SessionKeyY = finalText + tokens[1];

        String SessionKeyX_Hash = HashSHA512.encryptThisString(SessionKeyX);
        String SessionKeyY_Hash = HashSHA512.encryptThisString(SessionKeyY);

        String SendingHashValue = "";
        String NonSendingHashValue = "";
        String NewHashValue = "";

        String serverName = LoginActivity.getServerName();

        if (serverName.equals("D")) {
            System.out.println("Client is D");
            SendingHashValue = SessionKeyX_Hash;

            NonSendingHashValue = SessionKeyX_Hash + SessionKeyY_Hash + txt_key.getText();
            NewHashValue = HashSHA512.encryptThisString(NonSendingHashValue);
        } else if (serverName.equals("S")) {
            System.out.println("Client is S");
            SendingHashValue = SessionKeyY_Hash;

            NonSendingHashValue = SessionKeyX_Hash + SessionKeyY_Hash + txt_key.getText();
            NewHashValue = HashSHA512.encryptThisString(NonSendingHashValue);
        } else {
            System.out.println("Server name should be D or S");
        }

        try {
            txt_encrypted.setText(NewHashValue);
            clientApplication.sendMessage(SendingHashValue);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void showPrivateKey(String s) {
        txt_key.setText(s);
    }

    @Override
    public void openDialogKeyPair() {
        generateKeyPairDialog.show(getSupportFragmentManager(), "Loading key");
    }

    @Override
    public void closeDialogKeyPair() {
        generateKeyPairDialog.dismiss();
    }

    @Override
    public void openDialogPublicKey() {
        generatePublicKeyDialog.show(getSupportFragmentManager(), "Loading key");
    }

    @Override
    public void closeDialogPublicKey() {
        generatePublicKeyDialog.dismiss();
    }

    @Override
    public void showMessageSentConfirmation() {
        showToast(this, "Message sent!");
    }

    @Override
    public void showErrorSendingMessage() {
        showError("Couldn't send the message");
    }

    @Override
    public void notifyUserMessageReceived() {
        showToast(this, "You have just received a messsage!");
    }

    @Override
    public void showError(final String error) {
        showToast(this, error);
    }


    public void showToast(final Context context, final String message) {
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }.start();
    }

}
