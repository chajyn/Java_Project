package practice2;

public enum TileType {
	
	Dirt("testTile", true), Tree("tree", true);
	
	String textureName;
	boolean buildable;
	
	TileType(String textureName, boolean buildable){
		this.textureName = textureName;
		this.buildable = buildable;
	}
}
