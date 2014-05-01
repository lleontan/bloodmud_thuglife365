package blood_mud.Scripts;

import java.io.IOException;
import java.util.ArrayList;

import blood_mud.Scripts.Assests.generic_soldier.Tank;
import blood_mud.Scripts.Assests.generic_soldier.generic_soldier;

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
		waveTimer[0]=(long) .5;	//timers are either in milliseconds or seconds, I forget which
		waveTimer[1]=(long) .5;
		waveTimer[2]=(long) .5;
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
		waveTimer[0]-=System.currentTimeMillis();
		if(waveTimer[0]<0){
			sendNewWave();
			resetTimer(waveTimer,0);
		}
	}
	public void sendNewWave() throws IOException{
		for(int a=0;a<waveNumber;a++){
		Soldier tempSol = new Tank(50, -20, 30, 30);
		
		Game_Applet.Instantiate(AIUnitlist,tempSol );
		
		}
	}
	public long[] resetTimer(long[]timer,float percentError){
		//note, use percent error as a decimal
		timer[0]=System.currentTimeMillis();
		long difference=(long) (percentError*timer[2]);		//uses base duration to set actual duration
		
		long timerRandomness=(long) (Math.random()*(difference*2)+(timer[2]-difference));//gabe check math
		timer[1]=System.currentTimeMillis()+timerRandomness;
		timer[0]=timer[1];
		//make target timer=timer
		return timer;
	}

	public static int findListDistance(ArrayList list,ArrayList targetList,int unitIndex){
		//finds the closest thing in the arraylist
		//returns the arrayList index
		
		//-1 for nothing
		int size=targetList.size();
		Soldier sol=(Soldier)list.get(unitIndex);
		
		
		float lowestDistance=-1;
		int lowestDistanceReference=-1;
		
		for(int a=0;a<size;a++){
			Soldier targetSol=(Soldier)targetList.get(a);
			
			//gabe check my distance code
			float distance=(float) (Math.sqrt(Math.pow(sol.x-targetSol.x,2)+Math.pow(sol.y-targetSol.y,2)));
			if(a==0){
				lowestDistance=distance;
				lowestDistanceReference=targetSol.targetname;
			}
			else if(distance<lowestDistance){
				lowestDistance=distance;
				lowestDistanceReference=targetSol.targetname;
			}
		}
		return lowestDistanceReference;
	}
}
