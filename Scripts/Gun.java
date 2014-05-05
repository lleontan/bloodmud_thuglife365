package Scripts;

import generic_soldier.BulletHole;

import java.io.IOException;
import java.util.ArrayList;

public class Gun extends Weapon{
	//all bullet based weapons extend this class
	public int maxClipSize;//size of mag
	public float fireRate;//time in between shots
	public float baseAccuracy;//machineguns=low,rifles=high
	
	public float effectiveRange;//everything within this distance is at 100% accuracy
	public float maxRange;		//accuracy declines, will not shoot at things past this range
	public Gun(){
		
	}


	public boolean hitAlgorithm(Soldier shooter,Soldier target,int shooterName,int targetName){
		//checks to see whether or not a shot hit
		//use target object to get values of target
		System.out.println("Hit Algorithm");
		float distance=findDistance(shooter, target);	//gabe check my distance
		float distanceMultiplier;
		boolean didHit=false;
		if(distance<effectiveRange){
			distanceMultiplier=1;
		}
		//shooter.rifle;
		return didHit;
	}

	public float findDistance(Soldier shooter,Soldier target){
		//finds the distance between 2 objects
		
		//gabe check my distance code
		float distance=(float) (Math.sqrt(Math.pow(shooter.x-target.x,2)+Math.pow(shooter.y-target.y,2)));
		return distance;
	}

	public void shoot (ArrayList list, int targetname, ArrayList targetUnitList, int enemytargetname) throws IOException {
		int index=gameController.findUnitIndex(list, targetname);
		int targetindex=gameController.findUnitIndex(targetUnitList, enemytargetname);
		Soldier targetsol=(Soldier) targetUnitList.get(targetindex);
		
		//do stuff here
		boolean ishit=false;
		int hit=(int) (Math.random()*100);//we're going to temporarily use a straight percentage
		if(hit>80){
			targetsol.damage(30);
			
		}
		
		targetUnitList.set(targetindex, targetsol);
		BulletHole hole = new BulletHole(targetsol.x+randomRange(), targetsol.y+randomRange(), 5, 5);
		Game_Applet.Instantiate(gameController.cosmeticList, hole);
	}
	public static int randomRange(){
		int posneg=(int) (Math.random()*2+1);
		if(posneg==1){
			posneg=-1;
		}
		else if(posneg==2){
			posneg=1;
		}
		else{System.out.println("shit gone wrong with randomRange");}
		int returnnum=(int) (posneg*Math.random()*60+10);
		return returnnum;
		
	}
}
