package blood_mud.Scripts;

public abstract class Soldier extends SoldierAI{
	//this is a soldier class, all soldiers inherit from this class
	
	//use abstract classes if you want a soldier to give its own definition,
	//almost everything a soldier does should have some degree of randomness
	//example shooting,
	public float maxMovespeed=2;				//maximum movespeed
	public float actualMovespeed=maxMovespeed;
	public float xSpeed=0;			//x and y movespeeds
	public float ySpeed=0;
	public int morale=15;
	public int health=100;
	public int rifle=15;
	
	public int movex=60;					//call moveTo with these variables
	public int movey=60;
	
	public float aquireTime=(float) .2;		//default values, change later
	public float aimTime=(float) .2;
	public int side=(int) .2;				//determines the faction, 0 for other, 1 for player, 2 for ai, 3 for possibly a 2nd ai
	public int state=0;
	
	public float aquireTimer;
	public float aimTimer;
	public Soldier(){
		//constructor, you can make another one with more arguments if needed
		
	}
	public void moveTo(int newX,int newY){
		//sets x and y movespeeds to get to coords, no messing with rotations
		
		System.out.println(newX+" "+newY);
		//serious issue with my math, gabe you fix
		float distance=(float) (Math.sqrt(Math.pow(newX-x,2)+Math.pow(newY-y,2)));
		if(distance>.1){
		float xDistance=newX-x;
		float yDistance=newY-y;
		float tan=(float) Math.tan(yDistance/xDistance);
		float theta=(float)Math.atan(tan);				//angle measure
		xSpeed=(float) (Math.cos(theta)*actualMovespeed);
		ySpeed=(float)(Math.sin(theta)*actualMovespeed);
		}
		else{
			xSpeed=0;
			ySpeed=0;
		}
	}
	public void shootAt(Soldier target){
		//aquires and rotates to target, checks fire type of current weapon, excecutes weapon fire algorithem. instantiates shell on ground
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
	public void setAquisitionTimer(){
		aquireTimer=(long)aquireTime;
	}
	public void setAimTimer(){
		aimTimer=(long)aimTime;
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
}
