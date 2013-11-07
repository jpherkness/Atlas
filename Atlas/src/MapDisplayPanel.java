import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MapDisplayPanel extends JPanel implements MouseListener {
	private DisplayPanel display;
	public Tile[][] tileGrid;
	public Iterator<Tile> tileIterator;
	public Dimension mapDisplaySize;
	public Dimension tileGridDimensions;
	public Tile selectedTile;
	public static int tileSize;
	public boolean mapExists = false;
	public final int MAPBORDERSIZE = 0;

	// Constructor
	public MapDisplayPanel(DisplayPanel displ) {
		display = displ;
		mapDisplaySize = new Dimension(Game.GAMESCREENSIZE.width
				- display.displayPanelSize.width - 3 * (Game.BORDERSIZE),
				display.displayPanelSize.height);
		this.setBackground(Game.BACKGROUNDCOLOR.darker());
		this.setBorder(BorderFactory.createLoweredBevelBorder());
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (doesMapExist() && Game.gameRunning) {
					for (int x = 0; x < tileGridDimensions.width; x++) {
						for (int y = 0; y < tileGridDimensions.height; y++) {
							Tile currTile = tileGrid[x][y];
							if (e.getPoint().x >= currTile.getTileScreenLocation().x
									&& e.getPoint().x <= currTile.getTileScreenLocation().x
											+ tileSize
									&& e.getPoint().y >= currTile.getTileScreenLocation().y
									&& e.getPoint().y <= currTile.getTileScreenLocation().y
											+ tileSize
									&& currTile.isSelectable() == true) {
								selectedTile = currTile;
							} else {
								currTile.setSelected(false);
							}
						}
					}
					if (selectedTile != null) {
						selectedTile.setSelected(true);
					}

					if (validClickableTile()) {
						selectedTile.setTileOwner(display.playerList.get(0));
						display.playerList.get(0).tilesOwned.add(selectedTile);
						display.cyclePlayers();
					}
					repaint();
				}

			}
			

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
	}
	
	//check to see if the tile passed is valid for the current player
	public boolean validClickableTile(){
		if(!display.playerList.isEmpty()){
			//Not owned by another player
			if(selectedTile.getTileOwner() != null && selectedTile.getTileOwner() != display.playerList.get(0)){
				return false;
			}
			//Not owned by you
			if(selectedTile.getTileOwner() == display.playerList.get(0)){
				return false;
			}
			//You have no other tiles (usefull for initial tile placement)
			if(display.playerList.get(0).tilesOwned.isEmpty()){
				return true;
			}else{
				if (getTileNorth(selectedTile).getTileOwner() == display.playerList.get(0) || 
						getTileSouth(selectedTile).getTileOwner() == display.playerList.get(0) || 
						getTileEast(selectedTile).getTileOwner() == display.playerList.get(0) ||
						getTileWest(selectedTile).getTileOwner() == display.playerList.get(0) ||
						getTileNorthEast(selectedTile).getTileOwner() == display.playerList.get(0) ||
						getTileSouthEast(selectedTile).getTileOwner() == display.playerList.get(0) ||
						getTileNorthWest(selectedTile).getTileOwner() == display.playerList.get(0) ||
						getTileSouthWest(selectedTile).getTileOwner() == display.playerList.get(0)){
					return true;
				}else{
					return false;
				}
			}
		}
		return false;
	}
	//Returns Tile North
	public Tile getTileNorth(Tile t){
		if (t.getTileGridLocation().y - 1 >= 0){
			return tileGrid[t.getTileGridLocation().x][t.getTileGridLocation().y - 1];
		}else{
			return null;
		}
	}
	//Returns Tile South
	public Tile getTileSouth(Tile t){
		if (t.getTileGridLocation().y + 1 <= tileGridDimensions.height){
			return tileGrid[t.getTileGridLocation().x][t.getTileGridLocation().y + 1];
		}else{
			return null;
		}
	}
	//Returns Tile East
	public Tile getTileEast(Tile t){
		if (t.getTileGridLocation().x + 1 <= tileGridDimensions.width){
			return tileGrid[t.getTileGridLocation().x  + 1][t.getTileGridLocation().y];
		}else{
			return null;
		}
	}
	//Returns Tile West
	public Tile getTileWest(Tile t){
		if (t.getTileGridLocation().x - 1 >= 0){
			return tileGrid[t.getTileGridLocation().x - 1][t.getTileGridLocation().y];
		}else{
			return null;
		}
	}
	//Returns Tile North-East
	public Tile getTileNorthEast(Tile t){
		if (t.getTileGridLocation().y - 1 >= 0 && t.getTileGridLocation().x + 1 <= tileGridDimensions.width){
			return tileGrid[t.getTileGridLocation().x + 1][t.getTileGridLocation().y - 1];
		}else{
			return null;
		}
	}
	//Returns Tile South-East
	public Tile getTileSouthEast(Tile t){
		if (t.getTileGridLocation().y + 1 <= tileGridDimensions.height && t.getTileGridLocation().x + 1 <= tileGridDimensions.width){
			return tileGrid[t.getTileGridLocation().x + 1][t.getTileGridLocation().y + 1];
		}else{
			return null;
		}
	}
	//Returns Tile South-West
	public Tile getTileSouthWest(Tile t){
		if (t.getTileGridLocation().x - 1 >= 0 && t.getTileGridLocation().y + 1 <= tileGridDimensions.height){
			return tileGrid[t.getTileGridLocation().x - 1][t.getTileGridLocation().y + 1];
		}else{
			return null;
		}
	}
	//Returns Tile North-West
	public Tile getTileNorthWest(Tile t){
		if (t.getTileGridLocation().x - 1 >= 0 && t.getTileGridLocation().y - 1 >= 0){
			return tileGrid[t.getTileGridLocation().x - 1][t.getTileGridLocation().y - 1];
		}else{
			return null;
		}
	}

	// creates a tile array for the world
	public void generateTileArray(int tileSize) {

		// gets the width and height of the Map Display window
		int width = this.getWidth() - MAPBORDERSIZE;
		int height = this.getHeight() - MAPBORDERSIZE;

		// sets the current tile size for the map
		MapDisplayPanel.tileSize = tileSize;

		// gets the point where the tiles should start on the map display window
		Point tileStart = new Point((width % tileSize) / 2,(height % tileSize) / 2);

		// sets up the tile array
		tileGridDimensions = new Dimension((width / tileSize),(height / tileSize));
		tileGrid = new Tile[tileGridDimensions.width][tileGridDimensions.height];

		// generates an array of tiles / world generation
		for (int x = 0; x < tileGridDimensions.width; x++) {
			for (int y = 0; y < tileGridDimensions.height; y++) {
				for (int border = 0; border < (int) (tileGridDimensions.height / 2); border++) {
					if (x >= border && y >= border
							&& x <= tileGridDimensions.width - (border + 1)
							&& y <= tileGridDimensions.height - (border + 1)) {
						int Probability = (int) (100 / Math.pow(1.8, border));
						int randomNum = (int) (Math.random() * (100) + 1);
						Point tileScreenPoint = new Point(x * tileSize
								+ tileStart.x + (MAPBORDERSIZE / 2), y
								* tileSize + tileStart.y + (MAPBORDERSIZE / 2));
						Point tileGridPoint = new Point(x, y);
						if (randomNum <= Probability) {
							tileGrid[x][y] = new Tile(2, tileGridPoint,
									tileScreenPoint, this);
						} else {
							tileGrid[x][y] = new Tile(1, tileGridPoint,
									tileScreenPoint, this);
						}
					}
				}
			}
		}
		setMapExists(true);
		repaint();
	}
	
	public void refreshDisplay(){
		repaint();
	}

	public void paintComponent(Graphics g) {
		Graphics2D graphics = (Graphics2D) g;
		// Draws the 1 Layer of the map
		graphics.setColor(Game.BACKGROUNDCOLOR.darker());
		graphics.fillRect(1, 1, this.getWidth(), this.getHeight());

		if (doesMapExist()) {
			// Draws the 1 Layer of the map
			graphics.clearRect(1, 1, this.getWidth(), this.getHeight());

			// Draws the 1 Layer of the map
			graphics.setColor(new Color(0, 0, 200, 180));
			graphics.fillRect(1, 1, this.getWidth(), this.getHeight());

			// Draws the second layer
			for (int x = 0; x < tileGridDimensions.width; x++) {
				for (int y = 0; y < tileGridDimensions.height; y++) {
					Tile tile = tileGrid[x][y];
					tile.drawTile(graphics);
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean doesMapExist() {
		return mapExists;
	}

	public void setMapExists(boolean mapExists) {
		this.mapExists = mapExists;
	}
}
