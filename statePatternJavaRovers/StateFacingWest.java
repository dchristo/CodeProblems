package code.statePatternRovers;

public class StateFacingWest implements State {


	public void moveForward(Rover rover) {
		rover.setPosX(rover.getPosX()-1);
	}

	public void rotateLeft(Rover rover) {
		rover.setState(rover.getConcreteState("S"));
	}

	public void rotateRight(Rover rover) {
		rover.setState(rover.getConcreteState("N"));
	}
	public String getStatus(){
		return "W";
	}

	public boolean isBlocked(Rover rover, Grid grid) {
		if(rover.getPosX()==0){return true;}
		return false;
	}

}
