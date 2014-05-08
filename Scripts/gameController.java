package Scripts;
import generic_soldier.Tank;
import java.io.IOException;
import java.util.ArrayList;
public class gameController{
	long[] waveTimer=new long [3];
	int waveNumber=0;

static public ArrayList cosmeticList=new ArrayList();			//this is our arraylist of cosmetic battlefield damage(blown up trees)
static public ArrayList tempCosemeticList=new ArrayList();		//arraylist of temporary battle damage(bullet holes, explosions)
static public ArrayList AIUnitlist=new ArrayList();				//arraylists of all units on the battlefield, player and ai
static public ArrayList playerUnitlist=new ArrayList();
static public ArrayList structureList=new ArrayList();			//arraylist of all structures, ai and player(trees, rocks, trenches)

	
	public gameController(){
		namecount=0;
		//0 is timer,1 is current duration, 2 is base duration
		waveTimer[0]=(long) 1000;	//timers are either in milliseconds or seconds, I forget which
		waveTimer[1]=(long) 1000;
		waveTimer[2]=(long) 1000;
	}
	
	public static int namecount=0;//current name index,
	public static int getNewName(){
		//uses the class to find a name
		namecount++;
		return namecount;
	}
	public void excecuteController() throws IOException{
		//call this from run
		//call all other methods from this
		//Game_Applet.Instantiate( obj);
		System.out.println("Timer"+waveTimer[0]+"   "+waveTimer[1]);
		waveTimer[0]=System.currentTimeMillis();
		if(waveTimer[0]>waveTimer[1]){
			sendNewWave();
			waveTimer[1]=15000+System.currentTimeMillis();
			//waveTimer=resetTimer(waveTimer,1000,0);
		}
		System.out.println("\n\n\n"+AIUnitlist.size()+"\n\n\n\n\n\n");
	}
	public void sendNewWave() throws IOException{
		System.out.println("New Wave Sent");
		int spawnnum=(int) (waveNumber*.5+2);
		if(spawnnum>10){
			spawnnum=10;
		}
		for(int a=0;a<spawnnum;a++){
		Soldier tempSol = new Tank((int)(Math.random()*450+50), -90, 60, 60);
		
		Game_Applet.InstantiateEnemy(tempSol );
		
		}
		waveNumber++;
	}
	public long[] resetTimer(long[]timer,float duration,float percentError){
		
		//NOT WORKING DO NOT USE UNLESS FIXED
		
		
		//REMEMBER THAT THIS METHOD RETURNS A TIMER
		//IF YOU DON'T HAVE THE TIMER YOU WANT SET TO THIS IT WON'T DO ANYTHING
		//note, use percent error as a decimal
		timer[0]=System.currentTimeMillis();
		//long difference=(long) ((1-percentError)*timer[2]);		//uses base duration to set actual duration
		
		//long timerRandomness=(long) (Math.random()*(difference*2)+(timer[2]-difference));//gabe check math
		timer[1]=(long) (System.currentTimeMillis()+duration);
		
		//make target timer=timer
		return timer;
	}
	public static int findUnitIndex(ArrayList list,int unitname){
		int returnint=-1;
		int size=list.size();
		for(int a=0;a<size;a++){
			Soldier sol=(Soldier)list.get(a);
			if(sol.targetname==unitname){
				returnint=a;
				break;
			}
		}
		
		return returnint;
	}
	public static int findListDistance(ArrayList list,ArrayList targetList,int unitIndex){
		//finds the closest thing in the arraylist
		//returns the arrayList index
		
		//-1 for nothing
		int size=list.size();
		Soldier sol=null;
		for(int a=0;a<size;a++){
			sol=(Soldier)list.get(a);
			if(sol.targetname==unitIndex){
				break;
			}
		}
		float lowestDistance=-1;
		int lowestDistanceReference=-1;
		
		int targetlistsize=targetList.size();
		for(int a=0;a<targetlistsize;a++){
			
			Soldier targetSol=(Soldier)targetList.get(a);
			if(targetSol.isDead==true||targetSol.invisible_to_player==true){}
			else{
				//gabe check my distance code
				float distance=(float) (Math.sqrt(Math.pow(sol.x-targetSol.x,2)+Math.pow(sol.y-targetSol.y,2)));
				//L
				if(a==0){
					lowestDistance=distance;
					lowestDistanceReference=targetSol.targetname;
				}
				else if(distance<lowestDistance){
					lowestDistance=distance;
					lowestDistanceReference=targetSol.targetname;
				}
			}
		}
		return lowestDistanceReference;
	}
}
