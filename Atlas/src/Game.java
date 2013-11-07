import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


@SuppressWarnings("serial")
public class Game extends JFrame{
	
	//Game variable
	public static boolean gameRunning = false;
	public final static String GAMENAME = "Atlas";
	public static Dimension GAMESCREENSIZE = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	public final static int BORDERSIZE = 20;
	public final static Color BACKGROUNDCOLOR = new Color(176, 196, 222, 255).darker();
	public final static String gameFont = "Bookman Old Style";
	//Constructor
	public Game(){
		super();
		
		DisplayPanel display = new DisplayPanel();
		MapDisplayPanel mapDisplay = new MapDisplayPanel(display);
		ControlPanel controlPanel = new ControlPanel(mapDisplay ,display);
		
		//Loads the window and populates it
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(30, 30));
		panel.setBackground(BACKGROUNDCOLOR);
		panel.setBorder(new EmptyBorder(BORDERSIZE, BORDERSIZE, BORDERSIZE, BORDERSIZE));
		panel.add(controlPanel, BorderLayout.NORTH);
		panel.add(display, BorderLayout.WEST);
		panel.add(mapDisplay, BorderLayout.CENTER);
		
		//Container
	    Container c = this.getContentPane();
	    c.setLayout(new BorderLayout());
	    c.setBackground(BACKGROUNDCOLOR);
	    c.add(panel, BorderLayout.CENTER);
	    
	    //Registry
	    Resources.registerResources();
	}

	//Main method
	public static void main(String[] args){
		Game game = new Game();
	    game.setDefaultCloseOperation(EXIT_ON_CLOSE);
		game.setSize(GAMESCREENSIZE);
		game.setVisible(true);
		game.setTitle(GAMENAME);
		game.setResizable(true);
	}
	
	
}
