package Form;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
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
	private InputStream in;

	String hostName;
	int portNumber;

	public GUI(final JFrame frame) {
		connectButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				hostName = ipText.getText();
				portNumber = Integer.parseInt(portText.getText());

				try {
					socket = new Socket(hostName, portNumber);
					out = new PrintWriter(socket.getOutputStream(), true);
					in = socket.getInputStream();

					if (in.read() == 1) {
						connectionStatusLabel.setForeground(new Color(58, 187, 35));
						connectionStatusLabel.setText("Connected!");
					}

					int width = Integer.parseInt(widthText.getText());
					int height = Integer.parseInt(heightText.getText());
					int frequency = Integer.parseInt(frequencyText.getText());
					String msg = "w" + width + "h" + height + "f" + frequency + "\n";
					System.out.print(msg);
					out.println(msg);
					out.flush();

					// TODO while true som uppdaterar bilden, GUIn kommer frysa men bilden blir bra?
					// Alt. repaint kan behövas
					// Om det inte går med repaint så bryt ut det till en annan tråd.
					
					(new Thread(new Runnable(){
						
						 public void run() {
							 while(true)
							 {
							 try{
								byte buffer[] = new byte[4];
								int a = in.read(buffer);
								System.out.print(a + "\n");

								ByteBuffer wrapped = ByteBuffer.wrap(buffer);
								int imgSize = wrapped.getInt();

								System.out.print(imgSize + "\n");

								buffer = new byte[imgSize];

								for (int i = 0; i < imgSize;) {
									i += in.read(buffer, i, imgSize - i);
								}

								BufferedImage img = ImageIO.read(new ByteArrayInputStream(buffer));
								imageLabel.setIcon(new ImageIcon(img));
								frame.pack();

								resolutionStatusLabel.setText("Resolution: " + widthText.getText() + "x" + heightText.getText());
								frequencyStatusLabel.setText("Frequency: " + frequencyText.getText());
							 }
							 catch(Exception e)
							 {
							 }
							 }
							 
						    }
						
						
					})).start();
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
	}
	
	//160x90, 1280x960

	public static void main(String[] args) {
		JFrame frame = new JFrame("GUI");

		GUI gui = new GUI(frame);

		frame.setContentPane(gui.panelMain);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	{
		$$$setupUI$$$();
	}
	
	private void $$$setupUI$$$() {
		panelMain = new JPanel();
		panelMain.setLayout(
				new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(15, 15, 15, 15), -1, -1));
		imagePanel = new JPanel();
		imagePanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
		panelMain.add(imagePanel,
				new com.intellij.uiDesigner.core.GridConstraints(0, 0, 2, 1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						null, null, null, 0, false));
		imageLabel = new JLabel();
		imageLabel.setText("");
		imagePanel.add(imageLabel,
				new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		buttonPanel = new JPanel();
		buttonPanel
				.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(9, 1, new Insets(0, 0, 100, 0), -1, -1));
		panelMain.add(buttonPanel,
				new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_SOUTH,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						null, null, null, 0, false));
		widthText = new JTextField();
		widthText.setText("160");
		buttonPanel.add(widthText,
				new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1),
						null, 0, false));
		heightText = new JTextField();
		heightText.setText("90");
		buttonPanel.add(heightText,
				new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1),
						null, 0, false));
		widthLabel = new JLabel();
		widthLabel.setText("Width (160/1280)");
		buttonPanel.add(widthLabel,
				new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		heightLabel = new JLabel();
		heightLabel.setText("Height (90/960)");
		buttonPanel.add(heightLabel,
				new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		frequencyText = new JTextField();
		frequencyText.setText("1");
		buttonPanel.add(frequencyText,
				new com.intellij.uiDesigner.core.GridConstraints(7, 0, 1, 1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1),
						null, 0, false));
		frequencyLabel = new JLabel();
		frequencyLabel.setText("Frequency (25/30)");
		buttonPanel.add(frequencyLabel,
				new com.intellij.uiDesigner.core.GridConstraints(6, 0, 1, 1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		// sendButton = new JButton();
		// sendButton.setText("Send settings to server");
		// buttonPanel.add(sendButton,
		// new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1,
		// com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
		// com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
		// com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
		// | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
		// com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null,
		// null, 0, false));
		frequencyStatusLabel = new JLabel();
		frequencyStatusLabel.setEnabled(true);
		frequencyStatusLabel.setForeground(new Color(-4483004));
		frequencyStatusLabel.setText("Frequency: ---");
		buttonPanel.add(frequencyStatusLabel,
				new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		resolutionStatusLabel = new JLabel();
		resolutionStatusLabel.setEnabled(true);
		resolutionStatusLabel.setForeground(new Color(-4483004));
		resolutionStatusLabel.setText("Resolution: ---x---");
		buttonPanel.add(resolutionStatusLabel,
				new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		connectionPanel = new JPanel();
		connectionPanel
				.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
		panelMain.add(connectionPanel,
				new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_NORTH,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						null, null, null, 0, false));
		connectionStatusLabel = new JLabel();
		connectionStatusLabel.setForeground(new Color(-4507830));
		connectionStatusLabel.setText("Not connected!");
		connectionPanel.add(connectionStatusLabel,
				new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		ipLabel = new JLabel();
		ipLabel.setText("IP");
		connectionPanel.add(ipLabel,
				new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		portLabel = new JLabel();
		portLabel.setText("Port");
		connectionPanel.add(portLabel,
				new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_NONE,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
		ipText = new JTextField();
		ipText.setText("192.168.20.247");
		connectionPanel.add(ipText,
				new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1),
						null, 0, false));
		portText = new JTextField();
		portText.setText("9009");
		connectionPanel.add(portText,
				new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1),
						null, 0, false));
		connectButton = new JButton();
		connectButton.setText("Connect to server");
		// connectionPanel.add(connectButton,
		// new com.intellij.uiDesigner.core.GridConstraints(5, 0, 1, 1,
		// com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
		// com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
		// com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
		// | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
		// com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null,
		// null, 0, false));
		buttonPanel.add(connectButton,
				new com.intellij.uiDesigner.core.GridConstraints(8, 0, 1, 1,
						com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER,
						com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK
								| com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW,
						com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
	}

	public JComponent $$$getRootComponent$$$() {
		return panelMain;
	}
}
