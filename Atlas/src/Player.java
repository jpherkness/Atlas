import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class Player extends JPanel{
	public ArrayList<Tile> tilesOwned = new ArrayList<>();
	JPanel buttonPanel;
	JPanel resourcePanel;
	JButton colorButton;
	JButton nameButton;
	JButton deleteButton;
	JLabel playerNamelabel;
	public String playerName = "";
	public Color playerColor;
	
	public Player(DisplayPanel dP){
		//Sets the player name
		playerName = newName().toUpperCase();
		if (playerName == null || playerName == ""){
			playerName = "PLAYER";
		}else if (playerName.contains("PENIS") || playerName.contains("GAY") || playerName.contains("PEN1S") || playerName.contains("PEN15") || playerName.contains("PENI5")){
			playerName = "SHAME";
		}
		//Sets the player color
		playerColor = newColor(Color.BLUE);
		if (playerColor == null){
			playerColor = Color.GRAY;
		}
		this.setLayout(new BoxLayout(this,
				BoxLayout.Y_AXIS));
		this.setBackground(new Color(playerColor.getRed(), playerColor.getGreen(), playerColor.getBlue(), 50));
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
	    this.setPreferredSize(new Dimension(280, 200));
	    
	     //Player label
	    playerNamelabel = new JLabel(playerName);
	    //Arial Unicode MS
	    playerNamelabel.setFont( new Font(Game.gameFont, Font.BOLD, 20));
	    playerNamelabel.setForeground(playerColor);
	    
	    //ResourcePanel
	    resourcePanel = new JPanel();
	    resourcePanel.setBackground(new Color (0,0,0,75));
	    
	    this.add(playerNamelabel,  BorderLayout.NORTH);
	    this.add(resourcePanel, BorderLayout.CENTER);
	}


	public Color newColor(Color c){
		return JColorChooser.showDialog(null, "Select a player color", c);
	}
	public String newName(){
		return JOptionPane.showInputDialog(null, "Enter your player name : ","New Player", 1);
	}
}
