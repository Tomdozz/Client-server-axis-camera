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

            public void actionPerformed(ActionEvent e) {

                String hostName = ipText.getText();
                int portNumber = Integer.parseInt(portText.getText());

                try{
                    socket = new Socket(hostName, portNumber);
                    out = new PrintWriter(socket.getOutputStream(), true);
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

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

        sendButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int width = Integer.parseInt(widthText.getText());
                int height = Integer.parseInt(heightText.getText());
                int frequency = Integer.parseInt(frequencyText.getText());
                String msg = "w" + width + "h" + height + "f" + frequency + "\n";
                System.out.print(msg);
                out.println(msg);
                out.flush();
            }
        });


        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("../mallard.jpg"));
            //System.out.print((new File("mallard.jpg").getAbsolutePath()));
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
