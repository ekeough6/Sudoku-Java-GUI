import info.gridworld.actor.Actor;
import java.awt.Color;

public class Four extends Actor {
	
	public Four(boolean fixed) {
		super();
		if(fixed)
			setColor(Color.red);
	}	
}
