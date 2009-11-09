package code.statePatternRovers;

public class CommandMoveForward implements Command {

	/*
	 * The rover delegates to the surrent state.
	 */
	public void execute(Rover aRover) {
		aRover.getState().moveForward(aRover);
	}

}
