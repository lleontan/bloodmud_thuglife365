package Scripts;

import java.awt.Image;
import java.util.ArrayList;

public abstract class Soldier extends SoldierAI{
	//this is a soldier class, all soldiers inherit from this class
	
	//use abstract classes if you want a soldier to give its own definition,
	//almost everything a soldier does should have some degree of randomness
	//example shooting,
	public Soldier(){}
	
	public Image altSprites[];
	
	public ArrayList friendList;
	public ArrayList enemyList;
	
	public float maxMovespeed=2;				//maximum movespeed
	public float actualMovespeed=maxMovespeed;
	public float xSpeed=0;			//x and y movespeeds
	public float ySpeed=0;
	
	public int morale=15;		//moral affects a bunch of stuff
	public int health=100;
	public int rifle=15;
	
	public int movex=60;					//call moveTo with these variables
	public int movey=60;
	
	public int side=(int) 2;				//determines the faction, 0 for other, 1 for player, 2 for ai, 3 for possibly a 2nd ai
	
	public int state=-1;			//soldier state
	public int shootingState=-1;	//soldier shooting statemachine
	public Weapon weapon;//Soldiers Weapon
	
	String name="Soldier1";	//we need name generating code
	String displayName="asdf";//name displayed
	
	int arrayIndex;	//used for finding in an arrayList
	/*
	public long aquireTimer[]=new long[4];//0 is timer, 1 is timer end, 2 is current duration, 3 is base duration
	public long baseFireTimer[]=new long[4];//in between shoots delay
	public long reloadTimer[]=new long[4];//reloading timer
	*/

	public long aquireTimer[]={(long) .1,(long) .2,(long) .3};//0 is timer,1 is current duration, 2 is base duration
	public long baseFireTimer[]=new long[4];//in between shoots delay
	public long reloadTimer[]=new long[4];//reloading timer
	
	public void generateName(){
		//seth populate this array
		String nameArray[]={"s","d"};
		//int usedNameReference[]=new int[nameArray.length];
		
		
		name=nameArray[(int)(Math.random()*nameArray.length)];
	}
	public void moveTo(int newX,int newY){
		//sets x and y movespeeds to get to coords, no messing with rotations
		
		System.out.println(newX+" "+newY);
		//serious issue with my math, gabe you fix
		float distance=(float) (Math.sqrt(Math.pow(newX-x,2)+Math.pow(newY-y,2)));
		if(distance>.1){
			float xDistance=newX-x;
			float yDistance=newY-y;
			
			xSpeed = (float) (xDistance / (distance / actualMovespeed));
			ySpeed = (float) (yDistance / (distance / actualMovespeed));
//			float tan=(float) Math.tan(yDistance/xDistance);
//			float theta=(float)Math.atan(tan);				//angle measure
//			xSpeed=(float) (Math.cos(theta)*actualMovespeed);
//			ySpeed=(float)(Math.sin(theta)*actualMovespeed);
		}
		else{
			xSpeed=0;
			ySpeed=0;
		}
	}
		//aquires and rotates to target, checks fire type of current weapon, executes weapon fire algorithem. instantiates shell on ground
		//soldier.shootat.method/variable to use
		public void tryfireAt(ArrayList list,int targetname){
			//fix
			 baseFireTimer[0]-=System.currentTimeMillis();
			 if(baseFireTimer[0]<0){
				 baseFireTimer=controller.resetTimer(baseFireTimer,(float) .2);
				 weapon.shoot();//Executes shoot method
			 }
		}
		public void damageAlgorythem(){
			//calculates the amount of damage the shot will do
		}
		public void executeShoot(){
			//soldier shooting state machine, call this
			ArrayList currentUnitList=controller.AIUnitlist;
			ArrayList targetUnitList=controller.playerUnitlist;
			System.out.println("Shooting at "+targetname);
			switch(shootingState){
			case 1:
				//aim state, rotates, waits for aimtimer
				//goes to back to idle or goes to excecute shoot
				aquireTimer[0]-=System.currentTimeMillis();
				if(aquireTimer[0]<0){
					//end of aiming
					controller.resetTimer(aquireTimer,0);
					shootingState=2;
				}
				break;
			case 2:
				//excecute shoot, calls the child classes shooting method inherited from weapon shooting class
				//excecutes until either a state change or clip runs out
				
				if(side==1){
					targetUnitList=controller.AIUnitlist;
				}
				else if(side==2){
					targetUnitList=controller.playerUnitlist;
				}
				tryfireAt(targetUnitList,targetname);
				break;
			case 3://reloading timer, goes to idle when done
					//if inturupted then just assume reloading is done
				break;
			case 4:
				System.out.println("no shoot");
				//indefinte no shoot, stays in this until told it can shoot again
				break;
			default:
				//idle state, checks for targets,goes into aim state from here
				System.out.println(currentUnitList.get(0)+"   "+targetUnitList.get(0));
				
				if(side==1){
					currentUnitList= controller.playerUnitlist;
					targetUnitList=controller.AIUnitlist;
				}
				else if(side==2){
					currentUnitList=controller.AIUnitlist;
					targetUnitList=controller.playerUnitlist;
				}
				
				targetname=controller.findListDistance(currentUnitList, targetUnitList, targetname);
				if(targetname==-1){
					
				}
				else{
					state=1;
				}
				break;
			}
		}
	
	
	
	
	
	public void damage(int ammount){
		health=health-ammount;
		if(health<-30){
			//excecute gib command and kill command
		}
	}
	public void close_quarters_combat_instance(){
		//if a unit comes into contact with an enemy that is not a
		//vehicle the two should fight hand to hand
	}
	public void setMoveOrders(int x,int y){
		//sets where the unit is going to move to next
		movex=x;
		movey=y;
	}
	public void doMove(){
		//excecutes the movement
		x=(int) (x+xSpeed);
		y=(int) (y+ySpeed);
	}
	public void setLists(ArrayList a,ArrayList b){
		friendList=a;
		enemyList=b;
	}
	}
