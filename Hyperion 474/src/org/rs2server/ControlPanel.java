package org.rs2server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;

import org.rs2server.rs2.net.ActionSender;
import org.rs2server.rs2.model.Player;
import org.rs2server.util.XMLController;


public class ControlPanel extends JFrame implements ActionListener
{

    private JButton ban, kick, mute;
    private JPanel panel;
    private JLabel value;
    private JTextField player;
    public Player player2;

    public ControlPanel()
    {
        //The components added to the control panel
        panel = new JPanel();
        ban = new JButton("Ban");
        kick = new JButton("Kick");
        mute = new JButton("Mute");
        value = new JLabel("Player");
        player = new JTextField(10);

        //Changes the font to Times New Roman
        Font f = new Font("Times New Roman", Font.PLAIN, 14);

        //Adds the Action Listener to the buttons
        ban.addActionListener(this);
        kick.addActionListener(this);
        mute.addActionListener(this);

        //Adds the components to the panel
        panel.add(value);
        panel.add(player);
        panel.add(ban);
        panel.add(kick);
        panel.add(mute);

       //Sets the font of the components
        value.setFont(null);
        player.setFont(null);
        ban.setFont(null);
        kick.setFont(null);
        mute.setFont(null);

        //Sets the color of the components
        panel.setBackground(Color.WHITE);
        panel.setForeground(Color.RED);


       //Sets the title, size, font, and visibility of the frame. Then adds the panel to the frame.
       this.setTitle("Server Control Panel");
       this.setFont(f);
       this.setSize(300, 300);
       this.setVisible(true);
       this.add(panel);

    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == ban)
        {
			try {
			File file = new File("data/bannedUsers.xml");
				List<String> bannedUsers = XMLController.readXML(file);
				bannedUsers.add(player.getText());
				XMLController.writeXML(bannedUsers, file);
				System.out.println("Control Panel just banned: "+player.getText()+" (Control Panel)");
			} catch (IOException ioban){
			System.out.println("Encountered an error: Trying to ban "+player.getText()+" (Control Panel)");
			}
		}
        
        if(e.getSource() == mute)
        {
		try {
			File file = new File("data/mutedUsers.xml");
				List<String> bannedUsers = XMLController.readXML(file);
				bannedUsers.add(player.getText());
				XMLController.writeXML(bannedUsers, file);
				System.out.println("Control Panel just muted: "+player.getText()+" (Control Panel)");
			} catch (IOException ioban){
			System.out.println("Encountered an error: Trying to mute "+player.getText()+" (Control Panel)");
			}
		
        }
        if(e.getSource() == kick)
        {
		//ActionSender.sendMessage("");
		//	player.getText().ActionSender.sendLogout();
         //  ActionSender.sendLogout() = player.getText();
        }

    }
}