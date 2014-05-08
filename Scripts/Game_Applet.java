package Scripts;

import generic_soldier.Tank;
import generic_soldier.generic_soldier;

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

import Tree.Leaf1;
import Tree.Tree;
import Tree.pauseButton;
public class Game_Applet extends Applet implements Runnable,KeyListener,MouseListener{
	int windowsizex=700;					//window sizes
	public static int windowsizey=600;
	int playerlives=10;
	public playerSoldier selectedUnit;
	gameController controller=new gameController();
	
	
	
	
	Font font1 = new Font("consolas", Font.PLAIN, 24);			//font
	public Game_Applet(){
		
	}	
	public void init(){
			setSize(windowsizex, windowsizey);
			setBackground(Color.WHITE);
			
			addKeyListener(this);					//add listeners here
			addMouseListener(this);
	}
	public void run() {
		try{
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);	//threading stuff
		cosmeticSprite temptree;							//tree and soldier are for testing purposes
		//temptree = new Tree(20,20,30,30);
		
		/*
		int Leafnum=(int) (Math.random()*10+5);
		for(int a=0;a<Leafnum;a++){
			Leaf1 l=new Leaf1((int)(Math.random()*300+100),(int) (Math.random()*300+100),20,20);
			Instantiate(controller.cosmeticList,l);
		}*/
		pauseButton pause=new pauseButton(30,30,45,45);
		
		
		generic_soldier soldier1=new generic_soldier(200,this.windowsizey,45,45);
		generic_soldier soldier2=new generic_soldier(500,this.windowsizey,45,45);
		generic_soldier soldier3=new generic_soldier(400,this.windowsizey,45,45);
		generic_soldier soldier4=new generic_soldier(300,this.windowsizey,45,45);

		soldier1.setMoveOrders(soldier1.x, soldier1.y-100);
		soldier2.setMoveOrders(soldier2.x, soldier2.y-120);
		soldier3.setMoveOrders(soldier3.x, soldier3.y-120);
		soldier4.setMoveOrders(soldier4.x, soldier4.y-100);
		//Soldier tempSol = new Tank(50, -70, 90, 90);
		//tempSol.setMoveOrders(100, 100);
		
		//Instantiate(controller.AIUnitlist,tempSol );
		Instantiate(controller.structureList,pause);
		
		Instantiate(controller.playerUnitlist,soldier2);
		Instantiate(controller.playerUnitlist,soldier3);
		Instantiate(controller.playerUnitlist,soldier4);
		
		Instantiate(controller.playerUnitlist,soldier1);		//to create a unit call instantiate with
		//Instantiate(controller.structureList,temptree);		//the list and the object you want to create
		while(true){
			//update loop
			excecuteListAI(controller.playerUnitlist);		//excecute actions for unit AIs, make another one for structure effects
			excecuteListAI(controller.AIUnitlist);
			
			
			
			//run gameController here
			controller.excecuteController();
			
			
			repaint();					//end of run method
			Thread.sleep(65);			
			Thread.yield();
			}
		}
		catch (MalformedURLException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		catch (InterruptedException e) {e.printStackTrace();}
	}
	public void excecuteListAI(ArrayList list) throws IOException{
		//excecutes the AI state machines of all units in the given arraylist
		
		int size=list.size();
		for(int a=0;a<size;a++){
			//try{
			size=list.size();
			Soldier sol;
			if(a>size){
				break;
			}
			sol=(Soldier)list.get(a);//some sort of error here

			
		
			System.out.println(a+" list index, unit name is "+sol.targetname);
			System.out.println(sol.y+" "+sol.x+" soldier x and y");
			sol.checkIfAlive();
			if(sol.isDead==false){
				sol.soldierState();			//calling the soldier decision making
				sol.doMove();				//telling the soldier to move
				list.set(a, sol);
				//list.set(a, sol);
			}
			}
			/*catch(IndexOutOfBoundsException e){
				
				System.out.println("shit went wrong at excecuting list AI");
			}*/
			
		}
		
	
	public float findDistance(ArrayList list,ArrayList targetList,int shooterName,int targetName){
		//finds the distance between 2 objects
		
		Soldier shooter=(Soldier)list.get(shooterName);
		Soldier targetSol=(Soldier)list.get(targetName);
		
			
			//gabe check my distance code
			float distance=(float) (Math.sqrt(Math.pow(shooter.x-targetSol.x,2)+Math.pow(shooter.y-targetSol.y,2)));
			
		
		return distance;
	}
	public void keyPressed(KeyEvent key){}
	public void keyReleased(KeyEvent key){
		
	}
	public void stop(){}
	Image offscreen;		//offscreen is for buffering stuff
	public void paint(Graphics paint){
		//when painting paint background objects first
		Graphics2D g=(Graphics2D)paint;
		int tempSize=controller.structureList.size();			//temp variable for preformance reasons
		
		offscreen=createImage(windowsizex,windowsizey);
		Graphics2D off=(Graphics2D) offscreen.getGraphics();
		
		drawList(off,controller.cosmeticList);
		drawList(off,controller.structureList);
		drawList(off,controller.AIUnitlist);
		drawList(off,controller.playerUnitlist);
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
			if(cos.invisible_to_player==false&&cos.isDead==false){//some enemies may be invis to player
			int x1=(int)cos.x;		//temp variables
			int y1=(int)cos.y;
			int height=cos.height;
			int width=cos.width;
			
			Image img=cos.defaultImage;	//getting image
			
			System.out.println();
			
			int imgh=img.getHeight(this);
			int imgw=img.getWidth(this);	//getting the size of image itself to be able to resize
			
			float scalex=(float) ((width+.0)/(imgw+.0));	//scaling stuff
			float scaley=(float) ((height+.0)/(imgh+.0));
			
			AffineTransform newform=new AffineTransform();
			//use this for rotating, scaling, transforming ect
			
			//newform.setToRotation(cos.rotation);
			//newform.rotate(cos.rotation);
			//newform.rotate(cos.rotation, cos.x+.5*cos.width, cos.y+.5*cos.height);
			newform.setToRotation(cos.rotation, cos.x+.5*cos.width, cos.y+.5*cos.height);
			cos.rotation=0;
			
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
			String tempname=tempSol.displayName;
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
	public static void Instantiate(ArrayList List,cosmeticSprite obj){
		//USE THIS TO CREATE OBJECTS
		cosmeticSprite spri=(cosmeticSprite)obj;
		
		spri.targetname=gameController.getNewName();
		List.add(spri);
	}
	public static void InstantiateEnemy(Object obj){
		//USE THIS TO CREATE OBJECTS
		ArrayList List=gameController.AIUnitlist;
		cosmeticSprite spri=(cosmeticSprite)obj;

		spri.targetname=gameController.getNewName();
		spri=(Soldier)spri;
		List.add(spri);
	}
	public static void InstantiatePlayerSoldier(Object obj){
		//USE THIS TO CREATE OBJECTS
		ArrayList List=gameController.playerUnitlist;
		cosmeticSprite spri=(cosmeticSprite)obj;
		
		spri.targetname=gameController.getNewName();
		spri=(Soldier)spri;
		List.add(spri);
	}
	public void rotatePrefab(float degrees){
		//
	}
	public static void deletePrefab(ArrayList list,int unitname){
		int size=list.size();
		for(int a=0;a<size;a++){
			cosmeticSprite spri=(cosmeticSprite) list.get(a);
			if (spri.targetname==unitname){
				System.out.println("Unit Deleted");
				list.remove(a);
				break;
			}
		}
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
			int index=col.checkPosition(x,y,controller.playerUnitlist);
			if(index==-1){
				System.out.println("not finding anything");
				selectedUnit=null;
			}
			else{
				//there may be a possible issue with our selected unit.
				//we kinda have to use the selected unit instead of an index
				//we may need to have a reserved spot in the arraylist for the selected unit
			selectedUnit=(playerSoldier) controller.playerUnitlist.get(index);
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
