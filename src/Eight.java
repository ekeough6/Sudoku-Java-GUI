import info.gridworld.actor.Actor;
import java.awt.Color;

public class Eight extends Actor {

	public Eight(boolean fixed) {
		super();
		if(fixed)
			setColor(Color.red);
	}	
}
