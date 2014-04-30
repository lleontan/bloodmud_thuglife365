package blood_mud.Scripts;

import blood_mud.Scripts.Assests.Tree.*;
import blood_mud.Scripts.Assests.generic_soldier.generic_soldier;

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
import java.util.Arrays;

import javax.imageio.ImageIO;
public class Game_Applet extends Applet implements Runnable,KeyListener,MouseListener{
	int windowsizex=1000;					//window sizes
	int windowsizey=600;
	int playerlives=10;							//player lives
	public ArrayList cosmeticList=new ArrayList();			//this is our arraylist of cosmetic battlefield damage(blown up trees)
	public ArrayList tempCosemeticList=new ArrayList();		//arraylist of temporary battle damage(bullet holes, explosions)
	public ArrayList AIUnitlist=new ArrayList();				//arraylists of all units on the battlefield, player and ai
	public ArrayList playerUnitlist=new ArrayList();
	public ArrayList structureList=new ArrayList();			//arraylist of all structures, ai and player(trees, rocks, trenches)
	
	public playerSoldier selectedUnit;
	gameController controller;
	
	Font font1 = new Font("consolas", Font.PLAIN, 24);			//font
		
		public void init(){
			setSize(windowsizex, windowsizey);
			setBackground(Color.GREEN);
			addKeyListener(this);					//add listeners here
			addMouseListener(this);
	}
	public void run() {
		try{
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);	//threading stuff
		Object temptree;							//tree and soldier are for testing purposes
			temptree = new Tree(20,20,30,30);
		
		generic_soldier soldier1=new generic_soldier(200,200,70,70);
		
		Instantiate(playerUnitlist,soldier1);		//to create a unit call instantiate with
		Instantiate(structureList,temptree);		//the list and the object you want to create
		while(true){
			//update loop
			excecuteListAI(playerUnitlist);		//excecute actions for unit AIs, make another one for structure effects
			excecuteListAI(AIUnitlist);
			
			//run gameController here
			controller.excecuteController();
			
			
			repaint();					//end of run method
			Thread.sleep(30);			
			Thread.yield();
			}
		}
		catch (MalformedURLException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		catch (InterruptedException e) {e.printStackTrace();}
	}
	public void excecuteListAI(ArrayList list){
		//excecutes the AI state machines of all units in the given arraylist
		int size=list.size();
		for(int a=0;a<size;a++){
			Soldier sol=(Soldier)list.get(a);
			sol.soldierState();			//calling the soldier decision making
			sol.doMove();				//telling the soldier to move
		}
	}
	public int findListDistance(ArrayList list,ArrayList targetList,String unitName){
		//finds the closest thing in the arraylist
		//returns the arrayList index
		
		//-1 for nothing
		int unitIndex=findname(list,unitName);
		int size=targetList.size();
		Soldier sol=(Soldier)list.get(unitIndex);
		
		float lowestDistance=-1;
		String lowestDistanceReference="nothing found";
		
		for(int a=0;a<size;a++){
			Soldier targetSol=(Soldier)targetList.get(a);
			
			//gabe check my distance code
			float distance=(float) (Math.sqrt(Math.pow(sol.x-targetSol.x,2)+Math.pow(sol.y-targetSol.y,2)));
			if(a==0){
				lowestDistance=distance;
				lowestDistanceReference=targetSol.name;
			}
			else if(distance<lowestDistance){
				lowestDistance=distance;
				lowestDistanceReference=targetSol.name;
			}
		}
		return lowestDistanceReference;
	}
	public Image get_image(String url) throws IOException{
		//gets an image using a given url, can take from internet or file directory
		File file=new File(url);
		java.net.URL f=new File(url).toURI().toURL();
		Image returnImage=ImageIO.read(file);
		return returnImage;
	}
	public void keyPressed(KeyEvent key){}
	public void keyReleased(KeyEvent key){
		
	}
	public void stop(){}
	Image offscreen;		//offscreen is for buffering stuff
	public void paint(Graphics paint){
		//when painting paint background objects first
		Graphics2D g=(Graphics2D)paint;
		int tempSize=structureList.size();			//temp variable for preformance reasons
		
		offscreen=createImage(windowsizex,windowsizey);
		Graphics2D off=(Graphics2D) offscreen.getGraphics();
		
		drawList(off,cosmeticList);
		drawList(off,structureList);
		drawList(off,AIUnitlist);
		drawList(off,playerUnitlist);
		//we're going to use graphics 2d to do all our painting instead of just graphics
		/*AffineTransform for rotation;
			
		g.drawImage(img, xform, this);*/
		
		paint.drawImage(offscreen, 0, 0, this);
	}
	/*public void update(){
		//something to do with buffering
	}*/
	public void drawList(Graphics2D off,ArrayList list){
		int tempSize=list.size();		//we're doing forloops with temp variables for preformance
		for(int a=0;a<tempSize;a++){
			cosmeticSprite cos=(cosmeticSprite) list.get(a);//getting the transform and sprite
			if(cos.invisible_to_player==false){//some enemies may be invis to player
			int x1=(int)cos.x;		//temp variables
			int y1=(int)cos.y;
			int height=cos.height;
			int width=cos.width;
			
			Image img=cos.defaultImage;	//getting image
			int imgh=img.getHeight(this);
			int imgw=img.getWidth(this);	//getting the size of image itself to be able to resize
			
			float scalex=(float) ((width+.0)/(imgw+.0));	//scaling stuff
			float scaley=(float) ((height+.0)/(imgh+.0));
			
			AffineTransform newform=new AffineTransform();
			//use this for rotating, scaling, transforming ect
			
			newform.translate(x1, y1);	//we should change this so that the affine transforms are held by the
										//cosmetic sprite object, do it later when we finish everything else
			newform.scale(scalex, scaley);				//size rescaling
			off.drawImage(img, newform, this);
			//off.drawImage(img,x1, y1, this);
			}
		}
	}
	public int findname(ArrayList list,String findString){
		//scans through the list for something named the string and returns the address
		//-1 for nothing
		int returnAddress = -1;
		
		int tempSize=list.size();
		for(int a=0;a<tempSize;a++){
			Soldier tempSol=(Soldier)list.get(a);
			String tempname=tempSol.name;
			if(tempname.equals(findString)){
				returnAddress=a;
			}
		}
		return returnAddress;
	}
	public void start(){

		Thread th=new Thread(this);
		th.start();
	}
	public int RandomNumber(int range,int min){			//returns a random integer
		int returnnum = (int)(Math.random()*range+min);
		return returnnum;
	}
	public void Instantiate(ArrayList List,Object obj){
		//USE THIS TO CREATE OBJECTS
		List.add(obj);
	}
	public void rotatePrefab(float degrees){
		//
	}
	public void deletePrefab(ArrayList List){
		
	}
	public void keyTyped(KeyEvent arg0) {}
	public void mouseClicked(MouseEvent mouse) {
		//clicked and released
		int buttonPressed=mouse.getButton();
		int x=mouse.getX();
		int y=mouse.getY();
		
		Collisions col=new Collisions();
		//collisions object, checks positions
		
		//in collisions create another method that returns all
		//things at the position instead of just the first one
		
		if (buttonPressed==1){
			//left mouse button
			int index=col.checkPosition(x,y,playerUnitlist);
			if(index==-1){
				System.out.println("not finding anything");
				selectedUnit=null;
			}
			else{
				//there may be a possible issue with our selected unit.
				//we kinda have to use the selected unit instead of an index
				//we may need to have a reserved spot in the arraylist for the selected unit
			selectedUnit=(playerSoldier) playerUnitlist.get(index);
			selectedUnit.setMoveOrders(x, y);
			System.out.println(selectedUnit.getClass().getName());
			}
		}
		if(buttonPressed==3){
			//right mouse button
			if(selectedUnit!=null){
				//fix selected
				selectedUnit.setMoveOrders(x, y);
			}
		}
	}
	public void mouseEntered(MouseEvent mouse) {
		//when the mouse enters the applet window	
	}
	public void mouseExited(MouseEvent arg0) {
		
	}
	public void mousePressed(MouseEvent arg0) {
		//mouse held
		//implement click and drag selection
	}
	public void mouseReleased(MouseEvent arg0) {
		//mouse released
	}
}
