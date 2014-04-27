package blood_mud.Scripts.Assests.generic_soldier;
import blood_mud.Scripts.playerSoldier;

public class generic_soldier extends playerSoldier{
	
	
	public void soldierAI(){
		//this method sets the state machine
		int currentState = 0;				//temporary variable,
		
		//do ai stuff here
		
		state=currentState;
	}
	public void soldierState(){
		//this is the behaviors
		soldierAI();
		
		switch(state){
		//state machine for soldier ai
		case 1:
			
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
			break;
		case 7:
			break;
		default:
			break;
		}
	}

}
