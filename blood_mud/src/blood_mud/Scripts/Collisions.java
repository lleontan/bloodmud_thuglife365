package blood_mud.Scripts;

import java.util.ArrayList;

public class Collisions {
	//Collision class, put collision methods in here
	public int checkPosition(int x,int y,ArrayList checkList){
		//takes an arraylist and checks for a collision
		int returnval=-1;		//returns position in the arrayList
		int listSize=checkList.size();
		for(int a=0;a<listSize;a++){
			cosmeticSprite tempcos=(cosmeticSprite) checkList.get(a);
			if(checkCollision(x,y,tempcos)){
				System.out.println("clicked on thing");
				returnval=a;
				break;//break the loop so we only select one unit
			}
		}
		
		return returnval;
	}
	public boolean checkCollision(int x, int y,cosmeticSprite cos){
		boolean returnboo=false;
		
		float cosx=cos.x;
		float cosy=cos.y;
		float cosh=cos.height;
		float cosw=cos.width;
		//finish this thing
		if((x>cosx&&x<(cosw+cosx))&&(y>cosy&&y<(cosy+cosh))){
			returnboo=true;
		}
		
		return returnboo;
	}
}