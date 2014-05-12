package Scripts;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Weapon {
	public float MaxRange = 500;
	public float effectiveMaxRange;			//don't be afraid to change, move, or add other range and damage variables
	public float effectiveMinRange;
	public float minDamage;					//add in a critical hit chance against AI soldiers?
	public float maxDamage;
	
	public int clipSize;					//clip size
	public float firerate;					//maximum potential shots per second
	public float reloadTime;				//when clip is empty. soldier experience should decrease this
	public abstract void shoot(ArrayList list, int targetname, ArrayList targetUnitList, int enemytargetname) throws IOException;
}
