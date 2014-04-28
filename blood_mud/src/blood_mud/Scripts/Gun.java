package blood_mud.Scripts;

public class Gun extends Weapon{
	//all bullet based weapons extend this class
	long fireTimer;
	long reloadTimer;
	public Gun(){
		setReloadTimer();
		setFireTimer();
		
	}
	public void fireAt(Soldier target){
		 fireTimer-=System.currentTimeMillis();
		 if(fireTimer<0){
			 setReloadTimer();
		 }
	}
	public void setReloadTimer(){
		reloadTimer=(long) reloadTime;
	}
	public void setFireTimer(){
		fireTimer=(long)firerate;
	}
	public void hitAlgorythem(Soldier target){
		//checks to see whether or not a shot hit
		//use target object to get values of target
	}
	public void damageAlgorythem(){
		//calculates the amount of damage the shot will do
	}
}
