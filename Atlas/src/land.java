import java.awt.Color;
import java.awt.Point;


public class land extends Tile{
	private Color tileColor = new Color(0, 100, 0, 150);
	
	//Constructor
	public land(Point gridPoint, Point screenPoint){
		super(gridPoint, screenPoint);
		setTileGridVisible(true);
		
		//Sets the color of land tiles
		tileColor = new Color(0, (int)(10 * Math.random() + 100), 0, (int)(10 * Math.random() + 150));
		setTileColor(tileColor);
		
		//Gives the tile a set of resources
		resources = Resources.generateResources(resources);
	}
}
