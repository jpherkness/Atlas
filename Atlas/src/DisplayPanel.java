import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class DisplayPanel extends JPanel{
	
	public ArrayList<Player> playerList = new ArrayList<>();	
	public Iterator<Player> playerIterator;
	public static Player currentPlayer;
	public Dimension displayPanelSize = new Dimension(300, Game.GAMESCREENSIZE.height -35 - Game.BORDERSIZE - 80);
	
	//Buttons
	public JScrollPane scrollPane;
	public JPanel playerDisplayPanel;
	public DisplayPanel(){
		this.setPreferredSize(new Dimension(300, 100));
		this.setBackground(Game.BACKGROUNDCOLOR.darker());
		this.setBorder(new CompoundBorder(BorderFactory.createLoweredBevelBorder(),new EmptyBorder(10, 10, 10, 10)));
		
		
		playerDisplayPanel = new JPanel();
		playerDisplayPanel.setBackground(Game.BACKGROUNDCOLOR.darker());
		playerDisplayPanel.setLayout(new BoxLayout(playerDisplayPanel,
				BoxLayout.Y_AXIS));
		
		this.add(playerDisplayPanel, BorderLayout.CENTER);
	}
	
	//Deletes a specific player
	public void deletePlayer(Player p){
		playerList.remove(p);
		refreshPlayerList();
	}
	
	//Deletes all players
	public void deleteAllPlayers(){
		playerList.clear();
		refreshPlayerList();
	}
	//Refreshes the visible player list
	public void refreshPlayerList(){
		playerDisplayPanel.removeAll();
		playerIterator = playerList.iterator();
		while(playerIterator.hasNext()){
			playerDisplayPanel.add(playerIterator.next());
			playerDisplayPanel.add(Box.createRigidArea(new Dimension(0,5)));
		}
		validate();
	}
	//Cycles through current players
	public void cyclePlayers(){
		if(playerList.size() != 0){
			Player storedPlayer = playerList.get(0);
			for (int i = 1; i < playerList.size(); i ++){
				playerList.set(i - 1, playerList.get(i));
			}
			playerList.set(playerList.size() - 1, storedPlayer);
			refreshPlayerList();
		}
	}
	
	
}
