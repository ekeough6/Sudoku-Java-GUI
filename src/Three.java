import info.gridworld.actor.Actor;
import java.awt.Color;

public class Three extends Actor{
	
	public Three(boolean fixed){
		super();
		if(fixed)
			setColor(Color.red);
	}
}
