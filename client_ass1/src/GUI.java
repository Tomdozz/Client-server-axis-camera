import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    JFrame frame = new JFrame("Client-ass1");
    JButton connect, settings;
    JLabel resolution, ipadress, port, fps;
    JTextField resInput, upInput, prtInput, fpsInput;
    JPanel  mainPanel;

     void prepareGUI(){
        this.setSize(1000,1500);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();

        int xPos = (dim.width / 2) - (this.getWidth() / 2);
        int yPos = (dim.height / 2) - (this.getHeight() / 2);
        this.setLocation(xPos, yPos);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Client-ass1");

        mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        //set theseon a panel instead this is fucked up,.......
        connect = new JButton("Connect");
        connect.setBounds(10,10,1, 1);
        connect.addActionListener(this);

        settings = new JButton("Settings");
        settings.setBounds(10,50,1, 1);
        settings.addActionListener(this);

        resInput = new JTextField(20);
        resInput.setPreferredSize(new Dimension(200,24));
        //resInput.setBounds(10,50,1, 1);
        mainPanel.add(resInput);
        mainPanel.add(settings);
        mainPanel.add(connect);
         //this.add(connect);
        //this.add(settings);
        this.add(mainPanel);
        this.setVisible(true);
    }

    public GUI(){
        prepareGUI();
    }
    public static void main(String[] args){
        GUI gui =new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
