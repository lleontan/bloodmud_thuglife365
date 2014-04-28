package blood_mud.Scripts.Assests.generic_soldier;
import blood_mud.Scripts.playerSoldier;

public class generic_soldier extends playerSoldier{
	public void soldierAI(){
		//this method sets the state machine
		int currentState = 0;				//temporary variable,
		if(health<=10&&health>=1){
			state=1;
		}
		else{}
		
		//do ai stuff here
		
		
		state=currentState;
	}
	public void soldierState(){
		//this is the behaviors
		soldierAI();
		
		switch(state){
		//state machine for soldier ai
		case 1:
			//wounded state
			xSpeed=0;
			ySpeed=0;
			break;
		case 2:
			
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
			break;
		default:
			break;
		}
	}

}
