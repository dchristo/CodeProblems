package code.statePatternRovers;

public class CommandRotateLeft implements Command {


	/*
	 * The rover delegates to the surrent state
	 */
	public void execute(Rover aRover) {
		aRover.getState().rotateLeft(aRover);
	}

}
