import info.gridworld.actor.Actor;
import java.awt.Color;

public class Five extends Actor {
	
	public Five(boolean fixed) {
		super();
		if(fixed)
			setColor(Color.red);
	}	
}
