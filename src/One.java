import info.gridworld.actor.Actor;
import java.awt.Color;

public class One extends Actor{
	
	public One(boolean fixed) {
		super();
		if(fixed)
			setColor(Color.red);
	}	
}
