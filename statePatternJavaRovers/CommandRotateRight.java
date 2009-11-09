package code.statePatternRovers;

public class CommandRotateRight implements Command {

	/*
	 * The rover delegates to the surrent state
	 */
	public void execute(Rover aRover) {
		aRover.getState().rotateRight(aRover);
	}


}
