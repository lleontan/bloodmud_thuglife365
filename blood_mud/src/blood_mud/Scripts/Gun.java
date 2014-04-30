package blood_mud.Scripts;

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

	public void shoot() {
		//do hit algorithm
		//if hit then call the damage calculator in the Soldier class
		//
		
	}

	public void hitAlgorithm(Soldier shooter,Soldier target,int shooterName,int targetName){
		//checks to see whether or not a shot hit
		//use target object to get values of target
		float distance=findDistance(shooter, target);	//gabe check my distance
		shooter.rifle;
	}

	public float findDistance(Soldier shooter,Soldier target){
		//finds the distance between 2 objects
		
		//gabe check my distance code
		float distance=(float) (Math.sqrt(Math.pow(shooter.x-target.x,2)+Math.pow(shooter.y-target.y,2)));
		return distance;
	}
}
