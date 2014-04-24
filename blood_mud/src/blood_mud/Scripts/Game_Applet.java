package blood_mud.Scripts;

import blood_mud.Scripts.Assests.Tree.*;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
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
		Thread.currentThread().setPriority(Thread.MIN_PRIORITY);
		Object temptree=new Tree();
		Instantiate(structureList,temptree);
		while(true){
			repaint();					//end of run method
			Thread.yield();
			}
	}
	public void keyPressed(KeyEvent key){}
	public void keyReleased(KeyEvent key){
		
	}
	public void stop(){}
	public void paint(Graphics paint){
		for(int a=0;a<structureList.size();a++){
			int x1=structureList.get(a).x;
			int y1=structureList.get(a).y;
			Image img=structureList.get(a).defaultImage;
			
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
