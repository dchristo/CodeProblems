package code.statePatternRovers;

public interface State {
	
	/*
	 * State will not change, but rover will advance.
	 */
	public void moveForward(Rover aRover);
	
	/*
	 * State will change, rover position will stay the same.
	 */
	public void rotateLeft(Rover aRover);
	
	/*
	 * State will change, rover position will stay the same.
	 */
	public void rotateRight(Rover aRover);
	
	/*
	 * A validation method to check if
	 * rover has reached boundaries of grid.
	 */
	public boolean isBlocked(Rover aRover, Grid aGrid);
	
	/*
	 * Helper method to write the current
	 * direction to the console.
	 */
	public String getStatus();

}
