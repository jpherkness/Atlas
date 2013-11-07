import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;


public class Resources {
	public static final int ResourceSize = 6;
	public String name;
	public Color color;
	public Point resourceLocation = new Point(30, 30);
	
	public static Resources oil = new Resources("Oil", Color.gray.darker().darker().darker());	
	public static Resources food = new Resources("Food", Color.YELLOW.darker());	
	public static Resources water = new Resources("Water", Color.CYAN.darker().darker());
	public static Resources minerals = new Resources("Minerals",Color.LIGHT_GRAY.darker());
	public static Resources wood = new Resources("Wood", Color.GREEN.darker().darker());
	
	public static ArrayList<Resources> resourceRegistry = new ArrayList<Resources>();
	
	public Resources(String n, Color c){
		name = n;
		color = c;
	}
	
	public static ArrayList<Resources> generateResources(ArrayList<Resources> resources){
		int resourceNum = (int)(4 * Math.random() + 1); // Generates a number of resources from 1 to 4
		for (int num = 1; num <= resourceNum; num ++){
                    Resources reffResource = getRandomResource();
                    Resources newResource = new Resources(reffResource.name, reffResource.color);
                    if (num == 1){
                        newResource.resourceLocation = new Point((MapDisplayPanel.tileSize / 4) - ResourceSize / 2, (MapDisplayPanel.tileSize / 4) - ResourceSize / 2);
                    }else if (num == 2){
                        newResource.resourceLocation = new Point((MapDisplayPanel.tileSize - MapDisplayPanel.tileSize / 4) - ResourceSize / 2, (MapDisplayPanel.tileSize / 4) - ResourceSize / 2);
                    }else if (num == 3){
                        newResource.resourceLocation = new Point((MapDisplayPanel.tileSize / 4) - ResourceSize / 2,( MapDisplayPanel.tileSize - MapDisplayPanel.tileSize / 4) - ResourceSize / 2);
                    }else if (num == 4){
                        newResource.resourceLocation = new Point((MapDisplayPanel.tileSize - MapDisplayPanel.tileSize / 4) - ResourceSize / 2, (MapDisplayPanel.tileSize - MapDisplayPanel.tileSize / 4) - ResourceSize / 2);
                    }
                    resources.add(newResource);
		}
		return resources;
	}
	
	public static Resources getRandomResource(){
		int resource = (int)(resourceRegistry.size() * Math.random() + 1) - 1;
		return resourceRegistry.get(resource);
	}
	
	public static void registerResources(){
		resourceRegistry.add(oil);
		resourceRegistry.add(food);
		resourceRegistry.add(water);
		resourceRegistry.add(minerals);
		resourceRegistry.add(wood);
	}
	
}
