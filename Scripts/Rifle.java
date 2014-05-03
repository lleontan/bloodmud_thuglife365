package Scripts;

import java.io.IOException;
import java.util.ArrayList;

import generic_soldier.BulletHole;

public class Rifle extends Gun{
	public int maxClipSize=15;//size of mag
	public float fireRate=10;//time in between shots
	public float baseAccuracy=(float).9;//machineguns=low,rifles=high
	
	public float effectiveRange=500;//everything within this distance is at 100% accuracy
	public float maxRange=700;
	
}
