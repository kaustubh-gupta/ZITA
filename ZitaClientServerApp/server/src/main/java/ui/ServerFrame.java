package ui;

import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

import javax.swing.*;
import java.awt.*;

public class ServerFrame extends JFrame {


    public ServerFrame() {
        this.setVisible(true);
        initComponents();

        putIcon();

        txt_port.setText("5000");

        txt_status.setText("Server Disconnected");
        txt_status.setForeground(Color.BLACK);
        txt_status.setBackground(Color.red);

        txt_messageReceived.setText("");
        txt_key1.setText("");
        txt_key2.setText("");
    }


    public void showCustomMessage(JFrame parent, String text, String title, int messageType, String pathImg) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Century Gothic", Font.BOLD, 15));
        ImageIcon icon = new ImageIcon(pathImg);

        new Thread(new Runnable() {
            @Override
            public void run() {
                JOptionPane.showMessageDialog(parent, label, title, messageType, icon);
            }
        }).start();

    }

    public void notification(String img, String text) {

        showCustomMessage(this, text, "Notification", JOptionPane.UNDEFINED_CONDITION, img);

    }

    public void error(String img, String text) {

        showCustomMessage(this, text, "Notification", JOptionPane.ERROR_MESSAGE, img);

    }

    private void putIcon() {
        ImageIcon icon = new ImageIcon("src/main/resources/lock_icon.png");
        setIconImage(icon.getImage());
    }

    protected void initComponents() {
        jPanel2 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jSeparator1 = new JSeparator();
        jLabel3 = new JLabel();
        btn_connect = new JButton();
        txt_port = new JTextField();
        txt_status = new JTextField();
        jSeparator3 = new JSeparator();
        jLabel8 = new JLabel();
        jSeparator4 = new JSeparator();
        jPanel3 = new JPanel();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        jLabel7 = new JLabel();
        jSeparator5 = new JSeparator();
        txt_messageReceived = new JTextField();
        txt_messageReceived2 = new JTextField();
        jSeparator7 = new JSeparator();
        jPanel1 = new JPanel();
        btn_gsk = new JButton();
        btnKey1 = new JButton();
        btnKey2 = new JButton();
        txt_key1 = new JTextField();
        txt_key2 = new JTextField();
        jLabel6 = new JLabel();
        jSeparator2 = new JSeparator();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("ZITA");
        setBackground(new Color(255, 0, 0));
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new AbsoluteLayout());

        jPanel2.setBackground(new Color(0, 51, 102));
        jPanel2.setLayout(new AbsoluteLayout());

        jLabel1.setIcon(new ImageIcon("src/main/resources/ZITA.png"));
        jPanel2.add(jLabel1, new AbsoluteConstraints(0, 50, 190, 140));

        jLabel2.setFont(new Font("Century Gothic", 1, 14));
        jLabel2.setForeground(new Color(255, 255, 255));
        jLabel2.setText("PORT");
        jPanel2.add(jLabel2, new AbsoluteConstraints(20, 230, 50, 20));

        //port
        jSeparator1.setFont(new Font("sansserif", 1, 12));
        jPanel2.add(jSeparator1, new AbsoluteConstraints(80, 250, 70, 10));

        btn_connect.setBackground(new Color(45, 92, 107));
        btn_connect.setFont(new Font("Century Gothic", 1, 14));
        btn_connect.setForeground(new Color(255, 255, 255));
        btn_connect.setIcon(new ImageIcon("src/main/resources/server_button.png"));
        btn_connect.setText("Run Server");
        btn_connect.setToolTipText("");
        btn_connect.setActionCommand("btn_connect");
        btn_connect.setBorder(null);
        btn_connect.setBorderPainted(false);

        jPanel2.add(btn_connect, new AbsoluteConstraints(10, 310, 170, 40));

        txt_port.setBackground(new Color(0, 51, 102));
        txt_port.setFont(new Font("Century Gothic", 1, 16));
        txt_port.setForeground(new Color(255, 255, 255));
        txt_port.setHorizontalAlignment(JTextField.CENTER);
        txt_port.setBorder(null);
        jPanel2.add(txt_port, new AbsoluteConstraints(90, 230, 50, -1));

        txt_status.setBackground(new Color(0, 51, 102));
        txt_status.setFont(new Font("Century Gothic", 1, 14));
        txt_status.setHorizontalAlignment(JTextField.CENTER);
        txt_status.setBorder(null);
        jPanel2.add(txt_status, new AbsoluteConstraints(0, 370, 190, 30));

        //server status
        jSeparator3.setForeground(new Color(255, 255, 255));
        jSeparator3.setFont(new Font("sansserif", 1, 12));
        jPanel2.add(jSeparator3, new AbsoluteConstraints(0, 400, 190, 20));

        getContentPane().add(jPanel2, new AbsoluteConstraints(0, 0, 190, 470));

        //message
        jPanel3.setBackground(new Color(0, 79, 153));
        jPanel3.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 51), 3));
        jPanel3.setLayout(new AbsoluteLayout());

        jScrollPane1.setBackground(new Color(0, 122, 153));
        jScrollPane1.setBorder(null);

        jTextArea1.setBackground(new Color(0, 114, 150));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel3.add(jScrollPane1, new AbsoluteConstraints(470, 310, 240, -1));

        jLabel7.setFont(new Font("Century Gothic", 1, 14));
        jLabel7.setForeground(new Color(255, 255, 255));
        jLabel7.setText("Updated Session Key D");
        jPanel3.add(jLabel7, new AbsoluteConstraints(20, 10, -1, 20));

        //message received
        jSeparator5.setForeground(new Color(255, 255, 255));
        jSeparator5.setFont(new Font("sansserif", 1, 12));
        jPanel3.add(jSeparator5, new AbsoluteConstraints(20, 60, 580, 20));

        txt_messageReceived.setFont(new Font("Century Gothic", 1, 17));
        txt_messageReceived.setForeground(new Color(255, 153, 51));
        txt_messageReceived.setBackground(new Color(0, 79, 153));
        txt_messageReceived.setText("");
        txt_messageReceived.setHorizontalAlignment(JTextField.CENTER);
        txt_messageReceived.setEditable(false);
        txt_messageReceived.setBorder(null);
        jPanel3.add(txt_messageReceived, new AbsoluteConstraints(20, 31, 580, 40));

        jLabel8.setFont(new Font("Century Gothic", 1, 14));
        jLabel8.setForeground(new Color(255, 255, 255));
        jLabel8.setText("Updated Session Key S");
        jPanel3.add(jLabel8, new AbsoluteConstraints(20, 70, -1, 20));

        //message received
        jSeparator7.setForeground(new Color(255, 255, 255));
        jSeparator7.setFont(new Font("sansserif", 1, 12));
        jPanel3.add(jSeparator7, new AbsoluteConstraints(20, 120, 580, 20));

        txt_messageReceived2.setFont(new Font("Century Gothic", 1, 17));
        txt_messageReceived2.setForeground(new Color(255, 153, 51));
        txt_messageReceived2.setBackground(new Color(0, 79, 153));
        txt_messageReceived2.setText("");
        txt_messageReceived2.setHorizontalAlignment(JTextField.CENTER);
        txt_messageReceived2.setEditable(false);
        txt_messageReceived2.setBorder(null);
        jPanel3.add(txt_messageReceived2, new AbsoluteConstraints(20, 91, 580, 40));

        getContentPane().add(jPanel3, new AbsoluteConstraints(190, 330, 800, 140));

        jPanel1.setBackground(new Color(32, 32, 95));
        jPanel1.setLayout(new AbsoluteLayout());

        btn_gsk.setBackground(new Color(45, 92, 107));
        btn_gsk.setFont(new Font("Century Gothic", 1, 14));
        btn_gsk.setForeground(new Color(255, 255, 255));
        btn_gsk.setText("Second-Factor AKA");
        btn_gsk.setToolTipText("");
        btn_gsk.setActionCommand("btn_connect");
        btn_gsk.setBorder(null);
        btn_gsk.setBorderPainted(false);


        jPanel1.add(btn_gsk, new AbsoluteConstraints(150, 260, 350, 40));

        btnKey1.setBackground(new Color(45, 92, 107));
        btnKey1.setFont(new Font("Century Gothic", 1, 14)); // NOI18N
        btnKey1.setForeground(new Color(255, 255, 255));
        btnKey1.setIcon(new ImageIcon("src/main/resources/key_icon.png"));
        btnKey1.setText("First-Factor AKA");
        btnKey1.setToolTipText("");
        btnKey1.setActionCommand("btn_connect");
        btnKey1.setBorder(null);
        btnKey1.setBorderPainted(false);
        jPanel1.add(btnKey1, new AbsoluteConstraints(470, 90, 220, 40));

        btnKey2.setBackground(new Color(45, 92, 107));
        btnKey2.setFont(new Font("Century Gothic", 1, 14)); // NOI18N
        btnKey2.setForeground(new Color(255, 255, 255));
        btnKey2.setIcon(new ImageIcon("src/main/resources/key_icon.png"));
        btnKey2.setText("First-Factor AKA");
        btnKey2.setToolTipText("");
        btnKey2.setActionCommand("btn_connect");
        btnKey2.setBorder(null);
        btnKey2.setBorderPainted(false);
        jPanel1.add(btnKey2, new AbsoluteConstraints(470, 170, 220, 40));

        txt_key1.setBackground(new Color(32, 32, 95));
        txt_key1.setFont(new Font("Century Gothic", 1, 14));
        txt_key1.setForeground(new Color(255, 153, 51));
        txt_key1.setHorizontalAlignment(JTextField.CENTER);
        txt_key1.setBorder(null);
        txt_key1.setEditable(false);
        jPanel1.add(txt_key1, new AbsoluteConstraints(250, 90, 200, 30));

        txt_key2.setBackground(new Color(32, 32, 95));
        txt_key2.setFont(new Font("Century Gothic", 1, 14));
        txt_key2.setForeground(new Color(255, 153, 51));
        txt_key2.setHorizontalAlignment(JTextField.CENTER);
        txt_key2.setBorder(null);
        txt_key2.setEditable(false);
        jPanel1.add(txt_key2, new AbsoluteConstraints(250, 170, 200, 30));

        jLabel6.setFont(new Font("Century Gothic", 1, 18));
        jLabel6.setForeground(new Color(255, 255, 255));
        jLabel6.setText("Session Key D");
        jPanel1.add(jLabel6, new AbsoluteConstraints(80, 100, -1, 20));

        jLabel3.setFont(new Font("Century Gothic", 1, 18));
        jLabel3.setForeground(new Color(255, 255, 255));
        jLabel3.setText("Session Key S");
        jPanel1.add(jLabel3, new AbsoluteConstraints(80, 180, -1, 20));

        //key
        jSeparator2.setForeground(new Color(255, 255, 255));
        jSeparator2.setFont(new Font("sansserif", 1, 12)); // NOI18N
        jPanel1.add(jSeparator2, new AbsoluteConstraints(255, 120, 170, 20));

        jSeparator4.setForeground(new Color(255, 255, 255));
        jSeparator4.setFont(new Font("sansserif", 1, 12)); // NOI18N
        jPanel1.add(jSeparator4, new AbsoluteConstraints(255, 200, 170, 20));

        getContentPane().add(jPanel1, new AbsoluteConstraints(190, 0, 800, 330));

        setResizable(false);

        pack();
    }

    protected JButton btnKey1;
    protected JButton btnKey2;
    protected JButton btn_connect;
    protected JButton btn_gsk;
    protected JLabel jLabel1;
    protected JLabel jLabel2;
    protected JLabel jLabel3;
    protected JLabel jLabel6;
    protected JLabel jLabel7;
    protected JLabel jLabel8;
    protected JPanel jPanel1;
    protected JPanel jPanel2;
    protected JPanel jPanel3;
    protected JScrollPane jScrollPane1;
    protected JSeparator jSeparator1;
    protected JSeparator jSeparator2;
    protected JSeparator jSeparator3;
    protected JSeparator jSeparator4;
    protected JSeparator jSeparator5;
    protected JSeparator jSeparator7;
    protected JTextArea jTextArea1;
    protected JTextField txt_key1;
    protected JTextField txt_key2;
    protected JTextField txt_messageReceived;
    protected JTextField txt_messageReceived2;
    protected JTextField txt_port;
    protected JTextField txt_status;

    public JTextField getTxt_status() {
        return txt_status;
    }

    public JTextField keyValX() {
        return txt_key1;
    }

    public JTextField keyValY() {
        return txt_key2;
    }
}

