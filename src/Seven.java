import info.gridworld.actor.Actor;
import java.awt.Color;

public class Seven extends Actor {
	
	public Seven(boolean fixed) {
		super();
		if(fixed)
			setColor(Color.red);
	}	
}
