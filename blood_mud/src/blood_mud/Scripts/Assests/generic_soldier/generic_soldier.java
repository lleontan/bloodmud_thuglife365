package blood_mud.Scripts.Assests.generic_soldier;
import java.io.IOException;

import blood_mud.Scripts.playerSoldier;

public class generic_soldier extends playerSoldier{
	public generic_soldier(int x,int y,int height,int width) throws IOException{
		//constructor
		this.assetPath="C:\\Users\\Janet\\git\\blood and mud\\blood_mud\\src\\blood_mud\\Scripts\\Assests\\treetopdown.jpg";
		this.x=x;
		this.y=y;
		this.height=height;
		this.width=width;
		this.defaultImage=app.get_image(assetPath);
	}
	public void soldierDecision(){
		//this method sets the state machine
		int currentState = 0;				//temporary variable,
		if(health<1){
			currentState=1;
		}
		else if(health<=10&&health>=1){
			currentState=2;
		}
		else{
			currentState=7;
		}
		
		//do ai stuff here
		
		
		state=currentState;

		System.out.println(state);
	}
	public void soldierState(){
		//this is the behaviors
		soldierDecision();
		switch(state){
		//state machine for soldier ai
		case 1:
			xSpeed=0;
			ySpeed=0;
			break;
		case 2:
			//wounded state
			xSpeed=0;
			ySpeed=0;
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			//pinned, cannot fire, moves more slowly, changes sprite to pinned
			
		case 7:
			//move to, at bottom because many states have movement
			
			//moveTo does seem to change the movespeeds but it isn't 
			System.out.println("speeds   "+xSpeed+" "+ySpeed+"\nPosition is  "+x+" "+y);
			moveTo(movex,movey);
			break;
		default:
			break;
		}
	}

}
