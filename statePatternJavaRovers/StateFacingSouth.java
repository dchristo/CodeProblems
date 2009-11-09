package code.statePatternRovers;

public class StateFacingSouth implements State {


	public void moveForward(Rover rover) {
		rover.setPosY(rover.getPosY()-1);
	}

	public void rotateLeft(Rover rover) {
		rover.setState(rover.getConcreteState("E"));
	}

	public void rotateRight(Rover rover) {
		rover.setState(rover.getConcreteState("W"));
	}
	public String getStatus(){
		return "S";
	}

	public boolean isBlocked(Rover rover, Grid grid) {
		if(rover.getPosY()==0){return true;}
		return false;
	}
}
