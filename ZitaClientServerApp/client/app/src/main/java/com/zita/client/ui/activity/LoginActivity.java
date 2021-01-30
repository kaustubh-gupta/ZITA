package com.zita.client.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zita.client.ClientApplication;
import com.zita.client.R;
import com.zita.client.ui.fragments.ConnectionFragment;

public class LoginActivity extends AppCompatActivity implements ClientApplication.LoginResponse {

    Button btnConnect;
    TextView txtIP;
    TextView txtPort;
    TextView txtServerName;

    String port;
    String ip;
    String serverName;

    DialogFragment loadConnection;

    ClientApplication clientApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        clientApplication = ((ClientApplication) getApplication());
        clientApplication.setLoginResponse(this);

        btnConnect = findViewById(R.id.btnConnect);
        txtIP = findViewById(R.id.txt_ip);
        txtPort = findViewById(R.id.txtPort);
        txtServerName = findViewById(R.id.serverName);

        loadConnection = ConnectionFragment.getInstance();

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ip = txtIP.getText().toString();
                port = txtPort.getText().toString();
                serverName = txtServerName.getText().toString();
                setPort(port);
                String name = "Device " + serverName;
                setDeviceName(name);
                setServerName(serverName);

                try {
                    clientApplication.connect(ip, port);
                } catch (Exception e) {
                    e.printStackTrace();
                    showErrorMessage("Couldn't connect, check ip and port");
                }
            }
        });
    }

    static String newPort;

    public static void setPort(String port) {
        newPort = port;
    }

    public static String getPort() {
        return newPort;
    }

    static String newServerName;

    public static void setServerName(String serverName) {
        newServerName = serverName;
    }

    public static String getServerName() {
        return newServerName;
    }

    static String newDeviceName;

    public static void setDeviceName(String deviceName) {
        newDeviceName = deviceName;
    }

    public static String getDeviceName() {
        return newDeviceName;
    }

    @Override
    public void openChat() {
        Intent intent = new Intent(LoginActivity.this, ChatResponseActivity.class);
        startActivity(intent);
    }

    @Override
    public void openDialogConnection() {
        loadConnection.show(getSupportFragmentManager(), "Loading key");
    }

    @Override
    public void closeDialogConnection() {
        loadConnection.dismiss();
    }

    @Override
    public void showErrorMessage(final String message) {
        new Thread() {
            public void run() {
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();
    }
}
