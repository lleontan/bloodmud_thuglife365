package generic_soldier;
import java.io.IOException;

import Scripts.Rifle;
import Scripts.playerSoldier;
public class generic_soldier extends playerSoldier{
	public generic_soldier(int x,int y,int height,int width) throws IOException{
		
		//constructor
		//this.assetPath="C:\\Users\\Janet\\git\\blood and mud\\blood_mud\\src\\blood_mud\\Scripts\\Assests\\generic_soldier\\Spec.jpg";
		this.assetPath="C:\\Users\\Leon Tan\\git\\blood\\bloodmud_thuglife365\\generic_soldier\\soldierTwo.jpg";
		
		this.altSpritesPath[1]="C:\\Users\\Leon Tan\\git\\blood\\bloodmud_thuglife365\\generic_soldier\\soldierOneFire.jpg";//fire sprite
		//this.altSpritesPath[1]="C:\\Users\\Leon Tan\\git\\blood\\bloodmud_thuglife365\\generic_soldier\\.jpg";
		//temporarily commented out

		this.altSprites[1]=get_image(altSpritesPath[1]);
		this.altSprites[0]=get_image(assetPath);
		//ALT SPRITE 2 IS DEATH SPRITE
		this.altSpritesPath[2]="C:\\Users\\Leon Tan\\git\\blood\\bloodmud_thuglife365\\generic_soldier\\Tank1.jpg";
		this.altSprites[2]=get_image(altSpritesPath[2]);
		
		//do not ever use pngs, use jpgs or more compressed file types for preformance
		this.x=x;
		this.y=y;
		this.height=height;
		this.width=width;
		this.defaultImage=get_image(assetPath);
		this.side=1;		//set side for fucking everything
		
		this.weapon=new Rifle();
		this.rifle=35;
		
		this.movex=x+1;
		this.movey=y-30;

		aquireTimer[0]=(long) .5;	//timers are either in milliseconds or seconds, I forget which
		aquireTimer[1]=(long) .5;
		aquireTimer[2]=(long) .5;
		
		baseFireTimer[0]=(long) .5;	//this is a pain in the ass, make timers thier own class instead of an array
		baseFireTimer[1]=(long) .5;
		baseFireTimer[2]=(long) .5;
		
		reloadTimer[0]=(long) .5;
		reloadTimer[1]=(long) .5;
		reloadTimer[2]=(long) .5;
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
			currentState=6;
		}
		
		//do ai stuff here
		
		
		state=currentState;

		System.out.println("state is "+state);
	}
	public void soldierState() throws IOException{
		//this is the behaviors
		if(this.isSuppressed==false){
		soldierDecision();
		}
		else{
			int unsuppressed=(int) (Math.random()*100);
			if(unsuppressed<25){
				this.isSuppressed=false;
			}
			this.state=3;
		}
		
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
			//suppressed state
			System.out.println("Is Suppressed");
			
			this.xSpeed=this.xSpeed/2;
			this.ySpeed=this.ySpeed/2;
			moveTo(movex,movey);
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			//shooting
			executeShoot();  
		case 7:
			if(x<0){
				movex=200;
			}
			if(y<0){
				movey=200;
			}
			
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
