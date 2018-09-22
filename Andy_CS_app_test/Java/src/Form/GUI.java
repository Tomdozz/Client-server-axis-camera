package Form;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class GUI {
    private JPanel panelMain;
    private JPanel imagePanel;
    private JLabel imageLabel;
    private JLabel frequencyStatusLabel;
    private JPanel buttonPanel;
    private JTextField widthText;
    private JTextField heightText;
    private JLabel widthLabel;
    private JLabel heightLabel;
    private JButton sendButton;
    private JTextField frequencyText;
    private JLabel frequencyLabel;
    private JLabel resolutionStatusLabel;
    private JLabel connectionStatusLabel;
    private JPanel connectionPanel;
    private JLabel ipLabel;
    private JLabel portLabel;
    private JTextField ipText;
    private JTextField portText;
    private JButton connectButton;


    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;


    public GUI() {
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String hostName = ipText.getText();
                int portNumber = Integer.parseInt(portText.getText());

                try{
                    Socket clientSocket = new Socket(hostName, portNumber);
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    if (in.readLine().equals("You have reached the server!")){
                        connectionStatusLabel.setForeground(new Color(58, 187, 35));
                        connectionStatusLabel.setText("Connected!");
                    }

                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });


        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("/home/andy/code/Client-server-axis-camera/Andy_CS_app_test/mallard.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageLabel.setIcon(new ImageIcon(img));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");

        GUI gui = new GUI();

        frame.setContentPane(gui.panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
