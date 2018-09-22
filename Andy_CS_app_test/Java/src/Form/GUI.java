package Form;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;
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


    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");

        GUI gui = new GUI();

        String hostName = "127.0.0.1";
        int portNumber = 9009;

        try{
         //   Socket clientSocket = new Socket(hostName, portNumber);
         //   PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
         //   BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

         //   gui.label.setText(in.readLine());

            BufferedImage img = null;

            img = ImageIO.read(new File("/home/andy/code/Client-server-axis-camera/Andy_CS_app_test/mallard.jpg"));

            gui.imageLabel.setIcon(new ImageIcon(img));




        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        frame.setContentPane(gui.panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
