package code.statePatternRovers;

public class StateFacingEast implements State {


	public void moveForward(Rover rover) {
		rover.setPosX(rover.getPosX()+1);
	}

	public void rotateLeft(Rover rover) {
		rover.setState(rover.getConcreteState("N"));
	}

	public void rotateRight(Rover rover) {
		rover.setState(rover.getConcreteState("S"));
	}
	public String getStatus(){
		return "E";
	}

	public boolean isBlocked(Rover rover, Grid grid) {
		if(rover.getPosX()==grid.getGridX()){return true;}
		return false;
	}
}
