package code.statePatternRovers;


public class Client {


	public static void main(String[] args) {
		Grid grid = new Grid(5,5);
		
		Rover rover1 = new Rover(1,2,"N");
		String signal1 = "LMLMLMLMMLLLLRRRRRRMMMMM";
		
		Rover rover2 = new Rover(3,3,"E");
		String signal2 = "MMRMMRMRRM";
		
		new ValidateAndExecute().executeSignalIfPossible(grid, rover1, signal1);
		new ValidateAndExecute().executeSignalIfPossible(grid, rover2, signal2);

	}

}
