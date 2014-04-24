package blood_mud.Scripts;

public abstract class Soldier extends Prefab{
	//this is a soldier class, all soldiers inherit from this class
	
	public int moral;
	public int health;
	public int rifle;
	public int side;				//determines the faction, 0 for other, 1 for player, 2 for ai, 3 for 2nd ai
}
