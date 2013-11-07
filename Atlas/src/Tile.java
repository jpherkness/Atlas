import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.ArrayList;

public class Tile {
		protected MapDisplayPanel mapDisplayPanel;
		protected ArrayList<Resources> resources = new ArrayList<Resources>();
		private Point tileScreenLocation, tileGridLocation;
		private Color tileColor;
		private boolean tileGridVisible, selectable, selected;
		public Graphics2D tileGraphic;
		private Player tileOwner;
		public int landID = 1, oceanID = 2;
		
		//Constructor
		public Tile(int ID, Point gridPoint, Point screenPoint, MapDisplayPanel mapDisplayPanel){
			this.mapDisplayPanel = mapDisplayPanel;
			if(ID == landID){
				this.tileColor = new Color(0, (int)(20 * Math.random() + 90), 0, 180);
				this.tileGridVisible = true;
				this.tileScreenLocation = screenPoint;
				this.tileGridLocation = gridPoint;
				this.selectable = true;
				resources = Resources.generateResources(resources);
			}else if(ID == oceanID){
				this.tileColor = new Color(0, 0, 200, 180);
				this.tileGridVisible = false;
				this.tileScreenLocation = screenPoint;
				this.tileGridLocation = gridPoint;
				this.selectable = false;
			}
		}
		public Player getTileOwner(){
			return tileOwner;
		}
		
		//Draw method for all tiles
		public void drawTile(Graphics2D g) {
			tileGraphic = g;
			tileGraphic.setColor(this.tileColor);
			tileGraphic.clearRect(tileScreenLocation.x, tileScreenLocation.y, MapDisplayPanel.tileSize, MapDisplayPanel.tileSize);
			tileGraphic.fillRect(tileScreenLocation.x, tileScreenLocation.y, MapDisplayPanel.tileSize, MapDisplayPanel.tileSize);
			
			if(this.tileGridVisible == true ){
				tileGraphic.setColor(new Color(0, 0, 0, 40));
				tileGraphic.drawRect(tileScreenLocation.x, tileScreenLocation.y, MapDisplayPanel.tileSize, MapDisplayPanel.tileSize);
			}
			drawTileOverlay();
		}
		
		//Draws the overlay for a tile
		public void drawTileOverlay(){
			
			if (tileOwner != null){
				tileGraphic.setColor(tileOwner.playerColor);
				Stroke defaultStroke = tileGraphic.getStroke();
				tileGraphic.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL, 0, new float[] {4}, 0));
				
				//Draws the dotted border around owned tiles
				if(this.tileOwner != mapDisplayPanel.getTileNorth(this).getTileOwner()){
					tileGraphic.drawLine(tileScreenLocation.x + 1, tileScreenLocation.y + 1, tileScreenLocation.x + MapDisplayPanel.tileSize + 1, tileScreenLocation.y + 1);
				}
				if(this.tileOwner != mapDisplayPanel.getTileSouth(this).getTileOwner()){
					tileGraphic.drawLine(tileScreenLocation.x + 1, tileScreenLocation.y + MapDisplayPanel.tileSize - 1, tileScreenLocation.x + MapDisplayPanel.tileSize - 1, tileScreenLocation.y + MapDisplayPanel.tileSize - 1);
				}
				if(this.tileOwner != mapDisplayPanel.getTileEast(this).getTileOwner()){
					tileGraphic.drawLine(tileScreenLocation.x + MapDisplayPanel.tileSize - 1, tileScreenLocation.y + 1, tileScreenLocation.x + MapDisplayPanel.tileSize - 1, tileScreenLocation.y + MapDisplayPanel.tileSize - 1);
				}
				if(this.tileOwner != mapDisplayPanel.getTileWest(this).getTileOwner()){
					tileGraphic.drawLine(tileScreenLocation.x + 1, tileScreenLocation.y + 1, tileScreenLocation.x + 1, tileScreenLocation.y + MapDisplayPanel.tileSize - 1);
				}
				
				//fills the tile with a slightly transparent color of the owner
				tileGraphic.setColor(new Color(tileOwner.playerColor.getRed(), tileOwner.playerColor.getGreen(), tileOwner.playerColor.getBlue(), 100));
				tileGraphic.fillRect(tileScreenLocation.x , tileScreenLocation.y, MapDisplayPanel.tileSize, MapDisplayPanel.tileSize);
				tileGraphic.setStroke(defaultStroke);
			}
			for (int resource = 0; resource <= resources.size() - 1; resource ++){
				tileGraphic.setColor(this.resources.get(resource).color);
				tileGraphic.fillRect(this.resources.get(resource).resourceLocation.x + tileScreenLocation.x, this.resources.get(resource).resourceLocation.y + tileScreenLocation.y, Resources.ResourceSize, Resources.ResourceSize);
				tileGraphic.setColor(this.resources.get(resource).color.darker().darker());
				tileGraphic.drawRect(this.resources.get(resource).resourceLocation.x + tileScreenLocation.x, this.resources.get(resource).resourceLocation.y + tileScreenLocation.y, Resources.ResourceSize, Resources.ResourceSize);
				
			}
		}
		
		public ArrayList<Resources> getResources() {
			return resources;
		}
		public void setResources(ArrayList<Resources> resources) {
			this.resources = resources;
		}
		public Point getTileScreenLocation() {
			return tileScreenLocation;
		}
		public void setTileScreenLocation(Point tileScreenLocation) {
			this.tileScreenLocation = tileScreenLocation;
		}
		public Point getTileGridLocation() {
			return tileGridLocation;
		}
		public void setTileGridLocation(Point tileGridLocation) {
			this.tileGridLocation = tileGridLocation;
		}
		public Color getTileColor() {
			return tileColor;
		}
		public void setTileColor(Color tileColor) {
			this.tileColor = tileColor;
		}
		public boolean isTileGridVisible() {
			return tileGridVisible;
		}
		public void setTileGridVisible(boolean tileGridVisible) {
			this.tileGridVisible = tileGridVisible;
		}
		public boolean isSelectable() {
			return selectable;
		}
		public void setSelectable(boolean selectable) {
			this.selectable = selectable;
		}
		public boolean isSelected() {
			return selected;
		}
		public void setSelected(boolean selected) {
			this.selected = selected;
		}
		public void setTileOwner(Player tileOwner) {
			this.tileOwner = tileOwner;
		}
}
