package blood_mud.Scripts;
import java.awt.*;

import javax.print.DocFlavor.URL;

public class cosmeticSprite {
	//public Game_Applet app=new Game_Applet();//use this to call any methods from the applet
	public int x,y,height,width;
	
	public String assetPath;//the path to the directory with the art
	public Image defaultImage;
	public boolean invisible_to_player=false;
	

	int targetname;
	public cosmeticSprite(){
		targetname=0;
	}
}
