package code.statePatternRovers;

/*
 * The command interface is used
 * to create and store concrete
 * commands (L,R,M) to the rover,
 * for further use by a client
 * 
 */
public interface Command {
	void execute(Rover aRover);
}
