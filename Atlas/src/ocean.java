import java.awt.Color;
import java.awt.Point;


public class ocean extends Tile{
	private Color tileColor = new Color (0, 0, 200, 160);
	
	//Constructor
		public ocean(Point gridPoint, Point screenPoint){
			super(gridPoint, screenPoint);
			setTileGridVisible(false);
			
			//Sets the color of ocean tiles
			setTileColor(tileColor);
		}
}
