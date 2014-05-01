package Scripts;

public abstract class Weapon {
	public float effectiveMaxRange;			//don't be afraid to change, move, or add other range and damage variables
	public float effectiveMinRange;
	public float minDamage;					//add in a critical hit chance against AI soldiers?
	public float maxDamage;
	
	public int clipSize;					//clip size
	public float firerate;					//maximum potential shots per second
	public float reloadTime;				//when clip is empty. soldier experience should decrease this
	public abstract void shoot();
}
