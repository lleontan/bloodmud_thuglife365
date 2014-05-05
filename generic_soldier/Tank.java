package generic_soldier;
import java.io.IOException;

import Scripts.Game_Applet;
import Scripts.Rifle;
import Scripts.playerSoldier;

public class Tank extends playerSoldier{
	public Tank(int x,int y,int height,int width) throws IOException{
		
		//constructor
		//this.assetPath="C:\\Users\\Janet\\git\\blood and mud\\blood_mud\\src\\blood_mud\\Scripts\\Assests\\treetopdown.jpg";
		this.assetPath="C:\\Users\\Leon Tan\\git\\blood\\bloodmud_thuglife365\\generic_soldier\\soldier2.jpg";
		
		this.altSpritesPath[1]="C:\\Users\\Leon Tan\\git\\blood\\bloodmud_thuglife365\\generic_soldier\\soldier2Fire.jpg";//fire sprite
		//this.altSpritesPath[1]="C:\\Users\\Leon Tan\\git\\blood\\bloodmud_thuglife365\\generic_soldier\\.jpg";
		//temporarily commented out

		this.altSprites[1]=get_image(altSpritesPath[1]);
		this.altSprites[0]=get_image(assetPath);
		
		;
		//ALT SPRITE 2 IS DEATH SPRITE
		this.altSpritesPath[2]="C:\\Users\\Leon Tan\\git\\blood\\bloodmud_thuglife365\\generic_soldier\\Tank1.jpg";
		this.altSprites[2]=get_image(altSpritesPath[2]);
		
		
		//do not ever use pngs, use jpgs or more compressed file types for preformance
		this.x=x;
		this.y=y;
		this.height=height;
		this.width=width;
		this.defaultImage=get_image(assetPath);
		this.side=2;		//set side for fucking everything
		
		this.actualMovespeed=1;
		
		this.movex=x;
		this.movey=Game_Applet.windowsizey+1000;
		
		this.weapon=new Rifle();
		
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

		System.out.println(state);
	}
	public void soldierState() throws IOException{
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
			executeShoot();
			
		case 7:
			//move to, at bottom because many states have movement
			
			//moveTo does seem to change the movespeeds but it isn't
			if(x<0){
				movex=200;
			}
			if(y<0){
				movey=Game_Applet.windowsizey+500;
			}
			
			System.out.println("speeds   "+xSpeed+" "+ySpeed+"\nPosition is  "+x+" "+y+"\nMove Orders are "+movex+" "+movey);
			moveTo(movex,movey);			//this caused a really bad error
			break;
		default:
			break;
		}
	}

}
