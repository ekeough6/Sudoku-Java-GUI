import info.gridworld.actor.Actor;
import java.awt.Color;

public class Six extends Actor {
	
	public Six(boolean fixed) {
		super();
		if(fixed)
			setColor(Color.red);
	}	
}
