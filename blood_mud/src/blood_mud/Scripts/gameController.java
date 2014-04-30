package blood_mud.Scripts;

public class gameController extends Game_Applet{
	public gameController(){
		namecount=0;
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
		
	}
}
