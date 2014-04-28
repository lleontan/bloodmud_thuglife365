package blood_mud.Scripts;

import blood_mud.Scripts.Assests.Tree.*;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
public class Game_Applet extends Applet implements Runnable,KeyListener,MouseListener{
	int windowsizex=1000;
	int windowsizey=600;
	int playerlives=10;
	public ArrayList cosmeticList=new ArrayList();			//this is our arraylist of cosmetic battlefield damage(blown up trees)
	public ArrayList tempCosemeticList=new ArrayList();		//arraylist of temporary battle damage(bullet holes, explosions)
	public ArrayList unitList=new ArrayList();				//arraylist of all units on the battlefield, player and ai
	public ArrayList structureList=new ArrayList();			//arraylist of all structures ai and player(trees, rocks, trenches)
	
	
	
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
	int testint=0;//te(sting int, delete later
	
	/*public void update(Graphics paint){
		paint(paint);
	}*/
	Image offscreen;
	public void paint(Graphics paint){	
		Graphics2D g=(Graphics2D)paint;
		int tempSize=structureList.size();			//temp variable for preformance reasons
		
		offscreen=createImage(windowsizex,windowsizey);
		Graphics2D off=(Graphics2D) offscreen.getGraphics();
		
		System.out.println("asdf "+testint);
		testint++;
		for(int a=0;a<tempSize;a++){
			cosmeticSprite cos=(cosmeticSprite) structureList.get(a);
			int x1=(int)cos.x;
			int y1=(int)cos.y;
			Image img=cos.defaultImage;
			off.drawImage(img,x1, y1, this);
			
			//we're going to use graphics 2d to do all our painting instead of just graphics
			/*AffineTransform rotation;
			
			g.drawImage(img, xform, this);*/
		}
	paint.drawImage(offscreen, 0, 0, this);
	}
	/*public void update(){
		//something to do with buffering
	}*/
	public void start(){

		Thread th=new Thread(this);
		th.start();
	}
	public int RandomNumber(int range,int min){						//returns a random integer
		int returnnum = (int)(Math.random()*range+min);
		return returnnum;
	}
	public void Instantiate(ArrayList List,Object obj){
		List.add(obj);
	}
	public void rotatePrefab(float degrees){
		
	}
	public void deletePrefab(ArrayList List){
		
	}
	public void keyTyped(KeyEvent arg0) {
		
	}
	public void mouseClicked(MouseEvent mouse) {
		//clicked and released
		int buttonPressed=mouse.getButton();
		int x=mouse.getX();
		int y=mouse.getY();
		
		Collisions col=new Collisions();
		col.checkPosition(x,y,unitList);
	}
	public void mouseEntered(MouseEvent mouse) {
		
		int x=mouse.getX();
		int y=mouse.getY();
		
		
	}
	public void mouseExited(MouseEvent arg0) {
		
	}
	public void mousePressed(MouseEvent arg0) {
		//mouse held
	}
	public void mouseReleased(MouseEvent arg0) {
		//mouse released
		
	}
}
