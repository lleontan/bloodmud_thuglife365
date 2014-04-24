package Prefabs;

import blood_mud.Scripts.playerSoldier;

public class generic_soldier extends playerSoldier{
	
	
	public int soldierAI(){
		int state = 0;
		return state;
	}
	public void soldierState(int currentState){
		//call this method every frame
		soldierAI();
		
		switch(currentState){
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
