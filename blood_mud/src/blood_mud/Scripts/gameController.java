package blood_mud.Scripts;

public class gameController extends cosmeticSprite{
	long Timer[]=new long [3];
	public gameController(){
		namecount=0;
		
		//0 is timer,1 is current duration, 2 is base duration
		Timer[0]=(long) .5;	//timers are either in milliseconds or seconds, I forget which
		Timer[1]=(long) .5;
		Timer[2]=(long) .5;

	}
	public int namecount=0;//current name index,
	public int getNewName(){
		//uses the class to find a name
		namecount++;
		return namecount;
	}
	public void excecuteController(){
		//call this from run
		//call all other methods from this
		//app.Instantiate( obj);
		Timer[0]-=System.currentTimeMillis();
	}

	public long[] resetTimer(long[]timer,float percentError){
		//note, use percent error as a decimal
		timer[0]=System.currentTimeMillis();
		long difference=(long) (percentError*timer[3]);		//uses base duration to set actual duration
		
		long timerRandomness=(long) (Math.random()*(difference*2)+(timer[3]-difference));//gabe check math
		timer[1]=System.currentTimeMillis()+timerRandomness;
		timer[0]=timer[1];
		//make target timer=timer
		return timer;
	}
}
