package blood_mud.Scripts;

public class Soldier extends Prefab{
	//this is a soldier class, all soldiers inherit from this class
	
	public float moveSpeed;				//movespeed
	public float xSpeed;			//x and y movespeeds
	public float ySpeed;
	public int moral;
	public int health;
	public int rifle;
	public int side;				//determines the faction, 0 for other, 1 for player, 2 for ai, 3 for possibly a 2nd ai
	public int state;
	
	public void moveTo(int newX,int newY){
		//sets x and y movespeeds to get to coords, no messing with rotations

		float distance=(float) (Math.sqrt(Math.pow(newX-x,2)+Math.pow(y-newY,2)));
		if(distance>.1){
		float xDistance=newX-x;
		float yDistance=y-newY;
		float tan=(float) Math.tan(yDistance/xDistance);
		float theta=(float)Math.atan(tan);
		xSpeed=(float) (Math.cos(theta)*moveSpeed);
		ySpeed=(float)(Math.sin(theta)*moveSpeed);
		}
		else{
			xSpeed=0;
			ySpeed=0;
		}
	}
	public void shootAt(Soldier target){
		//rotates to target, checks fire type of current weapon, excecutes weapon fire algorithem. instantiates shell on ground
	}
	public void damage(int ammount){
		health=health-ammount;
		if(health<-30){
			//excecute gib command and kill comand
		}
	}
}
