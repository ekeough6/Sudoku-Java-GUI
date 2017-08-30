import info.gridworld.actor.Actor;
import java.awt.Color;

public class Nine extends Actor {
	
	public Nine(boolean fixed) {
		super();
		if(fixed)
			setColor(Color.red);
	}	
}
