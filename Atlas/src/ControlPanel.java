import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ControlPanel extends JPanel implements ActionListener {

	private MapDisplayPanel mapDisplayPanel;
	private DisplayPanel displayPanel;
	public int mapTileSize, smallTile = 90, mediumTile = 75, largeTile = 60;
	public JButton newPlayer;
	public JButton skipTurn;
	public JButton genMap;
	public JButton startGame;
	public JButton stopGame;

	// Constructor
	public ControlPanel(MapDisplayPanel mapDisplayPanel,
			DisplayPanel displayPanel) {
		this.setBackground(Game.BACKGROUNDCOLOR);
		this.mapDisplayPanel = mapDisplayPanel;
		this.displayPanel = displayPanel;

		// New Player Button
		newPlayer = new JButton("Create New Player");
		newPlayer.setFont( new Font(Game.gameFont, Font.BOLD, 12));
		newPlayer.setBackground(Game.BACKGROUNDCOLOR.darker());
		newPlayer.setForeground(Game.BACKGROUNDCOLOR.darker().darker().darker());
		newPlayer.setPreferredSize(new Dimension(150, 30));
		newPlayer.setFocusPainted(false);

		// New Player Button
		skipTurn = new JButton("Skip Turn");
		skipTurn.setFont( new Font(Game.gameFont, Font.BOLD, 12));
		skipTurn.setBackground(Game.BACKGROUNDCOLOR.darker());
		skipTurn.setForeground(Game.BACKGROUNDCOLOR.darker().darker().darker());
		skipTurn.setPreferredSize(new Dimension(150, 30));
		skipTurn.setFocusPainted(false);

		// New Map button
		genMap = new JButton("Generate Map");
		genMap.setFont( new Font(Game.gameFont, Font.BOLD, 12));
		genMap.setBackground(Game.BACKGROUNDCOLOR.darker());
		genMap.setForeground(Game.BACKGROUNDCOLOR.darker().darker().darker());
		genMap.setPreferredSize(new Dimension(150, 30));
		genMap.setFocusPainted(false);
		genMap.setToolTipText("Keeps Players / Creates a New Map");

		// Start Game button
		startGame = new JButton("Start Game");
		startGame.setFont( new Font(Game.gameFont, Font.BOLD, 12));
		startGame.setBackground(Color.green.darker());
		startGame.setForeground(Color.green.darker().darker().darker());
		startGame.setPreferredSize(new Dimension(150, 30));
		startGame.setFocusPainted(false);
		startGame.setToolTipText("Keeps Players / Creates a New Map");
		
		// Stop Game button
		stopGame = new JButton("Stop Game");
		stopGame.setFont( new Font(Game.gameFont, Font.BOLD, 12));
		stopGame.setVisible(false);
		stopGame.setBackground(Color.RED.darker());
		stopGame.setForeground(Color.RED.darker().darker().darker());
		stopGame.setPreferredSize(new Dimension(150, 30));
		stopGame.setFocusPainted(false);
		stopGame.setToolTipText("Keeps Players / Creates a New Map");

		// Add the objects to the action listener
		newPlayer.addActionListener(this);
		skipTurn.addActionListener(this);
		genMap.addActionListener(this);
		startGame.addActionListener(this);
		stopGame.addActionListener(this);
		
		// Adds the objects to the Control Panel
		this.add(newPlayer);
		this.add(skipTurn);
		this.add(genMap);
		this.add(Box.createRigidArea(new Dimension(100,0)));
		this.add(startGame);
		this.add(stopGame);

	}
	//returns true if the string contains any letters
	public boolean containsIllegals(String toExamine) {
		String illegalCharacters = "~#@*+%{}<>[]|\"_^abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		 for(int i = 0; i < toExamine.length(); i++) {
			    char c = toExamine.charAt(i);
			    for(int m = 0; m < illegalCharacters.length(); m++){
			    	if(c == illegalCharacters.charAt(m)){
			    		return true;
			    	}
			    }
			 }
	    return false;
	}

	public void actionPerformed(ActionEvent event) {
		// generates a new map
		if (event.getSource() == genMap) {
			String tileSize = JOptionPane.showInputDialog(null, "Enter The Tile Size (60 : large - 90 : small) : ","TileSize Selector", 1);
			if(tileSize != null && !containsIllegals(tileSize)){
				mapTileSize =  Integer.parseInt(tileSize);
				mapDisplayPanel.generateTileArray(mapTileSize);
			}
		} else if (event.getSource() == newPlayer) {
			displayPanel.playerList.add(new Player(displayPanel));
			displayPanel.refreshPlayerList();
		} else if (event.getSource() == skipTurn) {
			displayPanel.cyclePlayers();
		} else if (event.getSource() == startGame) {
			if(mapDisplayPanel.doesMapExist()){
				//game on state
				Game.gameRunning = true;
				startGame.setVisible(false);
				stopGame.setVisible(true);
				genMap.setVisible(false);
				newPlayer.setVisible(false);
			}
		} else if (event.getSource() == stopGame) {
				
				//game off state
				Game.gameRunning = false;
				newPlayer.setVisible(true);
				startGame.setVisible(true);
				stopGame.setVisible(false);
				genMap.setVisible(true);
				
				//clears all data
				mapDisplayPanel.setMapExists(false);
				mapDisplayPanel.repaint();
				displayPanel.deleteAllPlayers();
		}
	}
}
