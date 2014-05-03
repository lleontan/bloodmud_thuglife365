package Scripts;

import java.io.IOException;

public abstract class SoldierAI extends Prefab{
	public abstract void soldierDecision();
	public abstract void soldierState() throws IOException;
}
