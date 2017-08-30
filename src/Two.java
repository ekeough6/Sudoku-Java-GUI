import info.gridworld.actor.Actor;
import java.awt.Color;

public class Two extends Actor {
	
	public Two(boolean fixed) {
		super();
		if(fixed)
			setColor(Color.red);
	}
}
