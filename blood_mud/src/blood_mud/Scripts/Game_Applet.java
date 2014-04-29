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

import javax.imageio.ImageIO;
public class Game_Applet extends Applet implements Runnable,KeyListener,MouseListener{
	int windowsizex=1000;
	int windowsizey=600;
	int playerlives=10;
	public ArrayList cosmeticList=new ArrayList();			//this is our arraylist of cosmetic battlefield damage(blown up trees)
	public ArrayList tempCosemeticList=new ArrayList();		//arraylist of temporary battle damage(bullet holes, explosions)
	public ArrayList AIUnitlist=new ArrayList();				//arraylists of all units on the battlefield, player and ai
	public ArrayList playerUnitlist=new ArrayList();
	public ArrayList structureList=new ArrayList();			//arraylist of all structures, ai and player(trees, rocks, trenches)
	
	public playerSoldier selectedUnit;
	
	Font font1 = new Font("consolas", Font.PLAIN, 24);			//font
		
		public void init(){
			setSize(windowsizex, windowsizey);
			
			
			/*setBackground(new Color((int) (Math.random() * 255),		
					(int) (Math.random() * 255),
					(int) (Math.random() * 255)));*/
			setBackground(Color.GREEN);
			addKeyListener(this);
			addMouseListener(this);
	}
	public void run() {
		try{
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		Object temptree;
			temptree = new Tree(20,20,30,30);
		
		generic_soldier soldier1=new generic_soldier(200,200,40,40);
		Instantiate(playerUnitlist,soldier1);
		
		Instantiate(structureList,temptree);
		while(true){
			excecuteListAI(playerUnitlist);
			excecuteListAI(AIUnitlist);
			
			
			
			repaint();					//end of run method
			Thread.sleep(5);
			Thread.yield();
			}
		}
		catch (MalformedURLException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
		catch (InterruptedException e) {e.printStackTrace();}
	}
	public void excecuteListAI(ArrayList list){
		int size=list.size();
		for(int a=0;a<size;a++){
			Soldier sol=(Soldier)list.get(a);
			sol.soldierState();
			sol.doMove();
		}
	}
	public Image get_image(String url) throws IOException{
		File file=new File(url);
		
		
		java.net.URL f=new File(url).toURI().toURL();
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
		
		drawList(off,cosmeticList);
		drawList(off,structureList);
		drawList(off,AIUnitlist);
		drawList(off,playerUnitlist);
		//we're going to use graphics 2d to do all our painting instead of just graphics
		/*AffineTransform rotation;
			
		g.drawImage(img, xform, this);*/
		
		paint.drawImage(offscreen, 0, 0, this);
	}
	/*public void update(){
		//something to do with buffering
	}*/
	public void drawList(Graphics2D off,ArrayList list){
		int tempSize=list.size();
		for(int a=0;a<tempSize;a++){
			cosmeticSprite cos=(cosmeticSprite) list.get(a);
			if(cos.invisible_to_player==false){
			int x1=(int)cos.x;
			int y1=(int)cos.y;
			int height=cos.height;
			int width=cos.width;
			
			Image img=cos.defaultImage;
			int imgh=img.getHeight(this);
			int imgw=img.getWidth(this);
			
			float scalex=(float) ((width+.0)/(imgw+.0));
			float scaley=(float) ((height+.0)/(imgh+.0));
			
			AffineTransform newform=new AffineTransform();
			
			newform.translate(x1, y1);				//we should change this so that the affine transforms are held by the
													//cosmetic sprite object
			newform.scale(scalex, scaley);					//size rescaling
			off.drawImage(img, newform, this);
			//off.drawImage(img,x1, y1, this);
			}
		}
	}
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
	public void keyTyped(KeyEvent arg0) {}
	public void mouseClicked(MouseEvent mouse) {
		//clicked and released
		int buttonPressed=mouse.getButton();
		int x=mouse.getX();
		int y=mouse.getY();
		
		Collisions col=new Collisions();
		
		if (buttonPressed==1){
			System.out.println("asdf");
			int index=col.checkPosition(x,y,playerUnitlist);
			if(index==-1){
				System.out.println("not finding anything");
				selectedUnit=null;
			}
			else{
			selectedUnit=(playerSoldier) playerUnitlist.get(index);
			selectedUnit.setMoveOrders(x, y);
			System.out.println(selectedUnit.getClass().getName());
			}
		}
		if(buttonPressed==3){
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
	}
	public void mouseReleased(MouseEvent arg0) {
		//mouse released
		
	}
}
