package blood_mud.Scripts;

public abstract class Prefab extends cosmeticSprite{
	//prefab object
	public int length,width;	//may be put into cosmetic sprite
	public float hitboxRadius;		//for the purposes of explosions and flamethrowers
	public float effectboxRadius;		//some units(sarge, officer, medic) have and passive aoe
}
