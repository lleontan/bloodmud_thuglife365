package blood_mud.Scripts;

import java.util.ArrayList;

public class Collisions {
	//Collision class, put collision methods in here
	//public static boolean canChangeDirection=true;
	
	
	//redo this class
	
	public static String LastHitName;
	public String CurrentHit;
	public boolean goingRight;			//to prevent getting stuck inside an object, not currently in use
	public boolean goingDown;
	public boolean canBouncex=false;				//lock x&y are to prevent bouncing, trust me we need them
	public boolean canBouncey=false;		//replace these two with a slope equation later
	public boolean hasAlreadyCollided;
	

	public boolean donotresetcollisioncounter=false;				//can or cannot reset frame counter, not part of constructor manually reset
	public boolean ReturnCollision(int xBall,int yBall,int xBallWidth,int yBallHeight,int xBox,int yBox, int boxWidth,int Boxheight){
		boolean hascollided=false;
		if(((xBall>xBox)&&xBall<(xBox+boxWidth))&&			//upper left corner
				((yBall<(yBox+Boxheight))&&(yBall>yBox))){
			hascollided=true;
		}else
		if(((xBall+xBallWidth>xBox)&&xBall+xBallWidth<(xBox+boxWidth))&&//Upper right corner
				((yBall<(yBox+Boxheight))&&(yBall>yBox))){
			hascollided=true;
		}else
		if(((xBall>xBox)&&xBall<(xBox+boxWidth))&&			//bottom left corner
				((yBall+yBallHeight<(yBox+Boxheight))&&(yBall+yBallHeight>yBox))){
			hascollided=true;
		}else
		if(((xBall+xBallWidth>xBox)&&xBall+xBallWidth<(xBox+boxWidth))&&//bottom right corner
				((yBall+yBallHeight<(yBox+Boxheight))&&(yBall+yBallHeight>yBox))){
			hascollided=true;
		}
		if(hascollided==true&&donotresetcollisioncounter==false){
		}
		return hascollided;
	}
	public cosmeticSprite checkPosition(int x,int y,ArrayList checkList){
		cosmeticSprite returnSprite=new cosmeticSprite();
		int listSize=checkList.size();
		for(int a=0;a<listSize;a++){
			cosmeticSprite tempcos=(cosmeticSprite) checkList.get(a);
			if(checkCollision(x,y,tempcos)){
				
			}
		}
		
		return returnSprite;
	}
	public boolean checkCollision(int x, int y,cosmeticSprite cos2){
		boolean returnboo=false;
		
		float cos2x=cos2.x;
		//finish this thing
		if(x>cos2x&&x<(cos2.width+cos2x)){}
		
		return returnboo;
	}
}
	/*
	public void ShiftOut(int xBall,int yBall, int xBallWidth,int yBallHeight,int xBox,int yBox, int boxWidth,int Boxheight){
		int left=xBall;
		int right=xBall;
		int up=yBall;
		int down=yBall;
		while(true){
			left--;
			right++;
		if(left<xBox){
			pongblocker.xBall=left;
			break;
			}
		else if(right>xBox+boxWidth){
			pongblocker.xBall=right;
			break;
		}
		}
	while(true){
		up--;
		down++;
		if(up<xBox){}
		else if(down>xBox){}
		break;
		}
	}*/