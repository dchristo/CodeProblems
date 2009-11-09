package code.statePatternRovers;


public class StateFacingNorth implements State {


	public void moveForward(Rover rover) {
		rover.setPosY(rover.getPosY()+1);
	}

	public void rotateLeft(Rover rover) {
		rover.setState(rover.getConcreteState("W"));
	}

	public void rotateRight(Rover rover) {
		rover.setState(rover.getConcreteState("E"));
	}
	
	/*
	 * A naming convenience when
	 * typing the current state to the
	 * console.
	 */
	public String getStatus(){
		return "N";
	}

	public boolean isBlocked(Rover rover, Grid grid) {
		if(rover.getPosY()==grid.getGridY()){return true;}
		return false;
	}

}
