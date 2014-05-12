package Scripts;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;

public class cosmeticSprite {
	//public Game_Applet app=new Game_Applet();//use this to call any methods from the applet
	public int x,y,height,width;
	
	public String assetPath;//the path to the directory with the art
	public Image defaultImage;
	public boolean invisible_to_player=false;
	
	public float naturalRotation=0;
	boolean isDead=false;
	public float rotation=0;
	gameController controller;
	int targetname, enemytargetname;
	public String displayname="asdf";
	public cosmeticSprite(){
		//targetname=0;
	}
	

	public Image get_image(String url) throws IOException{
		//gets an image using a given url, can take from internet or file directory
		File file=new File(url);
		java.net.URL f=new File(url).toURI().toURL();
		Image returnImage=ImageIO.read(file);
		return returnImage;
	}
}
