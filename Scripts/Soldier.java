package Scripts;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import Tree.bloodSprite;

public abstract class Soldier extends SoldierAI{
	//this is a soldier class, all soldiers inherit from this class
	
	//use abstract classes if you want a soldier to give its own definition,
	//almost everything a soldier does should have some degree of randomness
	//example shooting,
	public Soldier(){
		
	}
	int currentClip=0;
	
	int shotAtCount=0;//number of times shot at in the last 5 seconds
	
	public Image altSprites[]=new Image[4];
	public String altSpritesPath[]=new String[4];
	
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
	
	String displayName="asdf";//name displayed
	
	public boolean isSuppressed=false;
	int arrayIndex;	//used for finding in an arrayList
	/*
	public long aquireTimer[]=new long[4];//0 is timer, 1 is timer end, 2 is current duration, 3 is base duration
	public long baseFireTimer[]=new long[4];//in between shoots delay
	public long reloadTimer[]=new long[4];//reloading timer
	*/

	public long aquireTimer[]={(long) 500,(long) 500,(long) 500,-1};//0 is timer,1 is current duration, 2 is base duration, 4 is the lock
	public long baseFireTimer[]={(long) 500,(long) 500,(long) 500,-1};//in between shoots delay
	public long reloadTimer[]={(long) 500,(long) 500,(long) 500,-1};//reloading timer
	
	public void generateName(){
		//seth populate this array
		String nameArray[]={"s","d"};
		//int usedNameReference[]=new int[nameArray.length];
		
		
		displayName=nameArray[(int)(Math.random()*nameArray.length)];
	}
	public void moveTo(int newX,int newY){
		//sets x and y movespeeds to get to coords, no messing with rotations
		
		System.out.println(newX+" "+newY);
		//serious issue with my math, gabe you fix
		float distance=(float) (Math.sqrt(Math.pow(newX-x,2)+Math.pow(newY-y,2)));
		if(distance>3){
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
		switch(shootingState){
		//case 1:
		case 2:
		case 3:
		//case 4:
			this.xSpeed=0;
			this.ySpeed=0;
			break;
		default:
			break;
		}
	}
		//aquires and rotates to target, checks fire type of current weapon, executes weapon fire algorithem. instantiates shell on ground
		//soldier.shootat.method/variable to use
		public void tryfireAt(ArrayList list,int targetname1, ArrayList targetUnitList, int enemytargetname1) throws IOException{
			//fix
			 baseFireTimer[0]=System.currentTimeMillis();
			 if(baseFireTimer[0]>baseFireTimer[1]){
				 //baseFireTimer=controller.resetTimer(baseFireTimer,500,(float) .2);
				 baseFireTimer[1]=System.currentTimeMillis()+500;
				 weapon.shoot(list,targetname,targetUnitList,enemytargetname1);//Executes shoot method
				 this.currentClip--;
			 }
		}
		public void resetShotCount(){
		}
		boolean killTimerLock=false;
		long killTimer[]=new long[4];
		public void checkIfAlive(){
			
			System.out.println("checking if alive");
			if(this.health<1){
				System.out.println("is dead");
				this.defaultImage=this.altSprites[2];
				if(killTimerLock==false){
					killTimerLock=true;
					killTimer[0]=System.currentTimeMillis();
					killTimer[1]=System.currentTimeMillis()+4000;
				}
				if(killTimerLock==true){
					killTimer[0]=System.currentTimeMillis();
					if(killTimer[0]>killTimer[1]){
						ArrayList list=new ArrayList();
						if(side==1){
							list=controller.playerUnitlist;
						}
						else{
							list=controller.AIUnitlist;
						}
						isDead=true;
						Game_Applet.deletePrefab(list,this.targetname);
					}
				}
			}
			
		}
		public void executeShoot() throws IOException{
			//soldier shooting state machine, call this
			

			ArrayList currentUnitList=controller.playerUnitlist;
			ArrayList targetUnitList = controller.AIUnitlist;
			if(side==1){
				currentUnitList=controller.playerUnitlist;
				targetUnitList=controller.AIUnitlist;}
			else if(side==2){
				currentUnitList=controller.AIUnitlist;
				targetUnitList=controller.playerUnitlist;
				
			}
			if(shootingState>0&shootingState<3){
				this.rotation=rotateToPosition(targetUnitList,enemytargetname);
			}
			
				
				System.out.println("Shooting state is "+shootingState);
			switch(shootingState){
			case 1:
				//aim state, rotates, waits for aimtimer
				//goes to back to idle or goes to excecute shoot
				aquireTimer[0]=System.currentTimeMillis();
				this.defaultImage=altSprites[1];
				if(aquireTimer[0]>aquireTimer[1]){
					//end of aiming
					System.out.println(aquireTimer[1]+"asdfasdfasdfasdfasdf");
					aquireTimer[1]=System.currentTimeMillis()+500+(int)(Math.random()*1000);
					
					shootingState=2;
				}
				break;
			case 2:
				//excecute shoot, calls the child classes shooting method inherited from weapon shooting class
				//excecutes until either a state change or clip runs out

				
				tryfireAt(currentUnitList,targetname,targetUnitList,enemytargetname);
				if(this.currentClip<=0){
					this.shootingState=3;

					this.currentClip=weapon.clipSize;
					this.reloadTimer[1]=System.currentTimeMillis()+1000+(int)(Math.random()*300);
				}
				this.defaultImage=altSprites[1];
				break;
			case 3://reloading timer, goes to idle when done
					//if inturupted then just assume reloading is done
				this.reloadTimer[0]=System.currentTimeMillis();
				this.defaultImage=altSprites[0];
				if(reloadTimer[0]>reloadTimer[1]){
					reloadTimer[1]=System.currentTimeMillis()+600+(int)(Math.random()*200);
					shootingState=-1;
				}
				break;
			case 4:
				System.out.println("no shoot");
				//indefinte no shoot, stays in this until told it can shoot again
				break;
			default:
				//idle state, checks for targets,goes into aim state from here
				this.defaultImage=altSprites[0];
				this.currentClip=weapon.clipSize;
				if(side==1){
					currentUnitList= controller.playerUnitlist;
					targetUnitList=controller.AIUnitlist;
				}
				else if(side==2){
					currentUnitList=controller.AIUnitlist;
					targetUnitList=controller.playerUnitlist;
				}
				
				enemytargetname=controller.findListDistance(currentUnitList, targetUnitList, targetname);
				
				if(enemytargetname==-1){
					System.out.println("target is not aquired");
					
				}
				else{
					int size=targetUnitList.size();
					for(int a=0;a<size;a++){
						Soldier tempsol=(Soldier) targetUnitList.get(a);
						if(tempsol.targetname==enemytargetname){
							float distance=(float) (Math.sqrt(Math.pow(tempsol.x-x,2)+Math.pow(tempsol.y-y,2)));

							System.out.println("distance check is "+distance+" "+this.weapon.MaxRange);
							if(distance<this.weapon.MaxRange){
							System.out.println("idle state stopped");
							aquireTimer[1]=(long) (System.currentTimeMillis()+500+Math.random()*1200);
							
							shootingState=1;
							}
						}
					}
					
				}
				break;
			}
		}
	public float rotateToPosition(ArrayList list,int enemytargetname){
		float theta=0;
		int size=list.size();
		for(int a=0;a<size;a++){
			Soldier sol=(Soldier) list.get(a);
			if(sol.targetname==enemytargetname){
				int x2=sol.x;
				int y2=sol.y;
				float xdis=x2-this.x;
				float ydis=y2-this.y;
				float tan=ydis/xdis;
				theta=(float) ((float) Math.atan(tan)+((-90*Math.PI)/180));
				break;
			}
		}
		
		//theta=0;
		System.out.println("The rotation is "+theta);
		return theta;
	}
	
	
	
	
	public void damage(int ammount) throws IOException{
		health=health-ammount;
		bloodSprite blood=new bloodSprite(this.x+Gun.randomRange(),this.y+Gun.randomRange(),30,30);
		Game_Applet.Instantiate(gameController.cosmeticList, blood);
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
		this.movex=x;
		this.movey=y;
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

