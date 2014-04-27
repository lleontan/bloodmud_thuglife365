package blood_mud.Scripts;

import blood_mud.Scripts.Assests.Tree.*;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
public class Game_Applet extends Applet implements Runnable,KeyListener{
	int windowsizex=1000;
	int windowsizey=600;
	int playerlives=10;
	public ArrayList cosmeticList=new ArrayList();			//this is our arraylist of cosmetic battlefield damage(blown up trees)
	public ArrayList tempCosemeticList=new ArrayList();		//arraylist of temporary battle damage(bullet holes, explosions)
	public ArrayList unitList=new ArrayList();				//arraylist of all units on the battlefield, player and ai
	public ArrayList structureList=new ArrayList();			//arraylist of all structures ai and player(trees, rocks, trenches)
	
	
	
	Thread th=new Thread(this);
	Font font1 = new Font("consolas", Font.PLAIN, 24);			//font
		
		public void init(){
			setSize(windowsizex, windowsizey);
			
			
			/*setBackground(new Color((int) (Math.random() * 255),		
					(int) (Math.random() * 255),
					(int) (Math.random() * 255)));*/
			setBackground(Color.GREEN);
			addKeyListener(this);
	}
	public void run() {
		try{
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		Object temptree;
			temptree = new Tree(20,20);
		

		Instantiate(structureList,temptree);
		while(true){
			
			repaint();					//end of run method
			Thread.yield();
			}
		}
		catch (MalformedURLException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
	}
	public Image get_image(String url) throws IOException{
		File file=new File(url);
		
		
		java.net.URL f=new File(url).toURI().toURL();
		System.out.println(f.getPath());
		Image returnImage;
		
		returnImage=ImageIO.read(file);
		//returnImage=getImage(f,name);
		//returnImage=getImage(f);
		
		return returnImage;
	}
	public void keyPressed(KeyEvent key){}
	public void keyReleased(KeyEvent key){
		
	}
	public void stop(){}
	public void paint(Graphics paint){
		for(int a=0;a<structureList.size();a++){
			cosmeticSprite cos=(cosmeticSprite) structureList.get(a);
			int x1=(int)cos.x;
			int y1=(int)cos.y;
			Image img=cos.defaultImage;
			paint.drawImage(img,x1, y1, this);
			
		}
	}
	public void update(){
		//something to do with buffering
	}
	public void start(){
		th.start();
	}
	public int RandomNumber(int range,int min){						//returns a random integer
		int returnnum = (int)(Math.random()*range+min);
		return returnnum;
	}
	public void Instantiate(ArrayList List,Object obj){
		List.add(obj);
	}
	public void rotatePrefab(float degrees){}
	public void deletePrefab(ArrayList List){
		
	}
	public void keyTyped(KeyEvent arg0) {
		
	}
}
