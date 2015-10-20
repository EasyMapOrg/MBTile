package com.tile.map;
import processing.core.PApplet;
import processing.core.PImage;
public class MBTileTestApp  extends PApplet {

	public static final String JDBC_CONN_STRING_MAC = "jdbc:sqlite:E:/Workspaces/MBTileApp/WebRoot/MBData/googlejd.mbtiles";
	PImage tile;
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public void setup() {
		size(600, 600);
		
		tile = MBTilesLoaderUtils.getMBTile(15, 10, 4, JDBC_CONN_STRING_MAC);
	}
	
	public void draw() {
		background(240);
		
		image(tile, 0, 0);
		
	}
}
