package Tree;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.print.DocFlavor.URL;
public class Leaf1 extends Scripts.Prefab{
	public Leaf1(int x,int y,int width,int height) throws IOException{
		//constructor
	//this.assetPath="C:\\Users\\Janet\\git\\blood and mud\\blood_mud\\src\\blood_mud\\Scripts\\Assests\\treetopdown.jpg";
	this.assetPath="C:\\Users\\Leon Tan\\git\\blood\\bloodmud_thuglife365\\Tree\\leafOne.jpg";
	this.x=x;
	this.y=y;

	this.height=height;
	this.width=width;
	this.defaultImage=get_image(assetPath);
		}
	}
