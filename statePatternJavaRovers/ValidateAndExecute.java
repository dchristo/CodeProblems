package code.statePatternRovers;

import java.util.regex.Pattern;

public class ValidateAndExecute {
	
	
	/*
	 * Do some custom validations and error handling.
	 * 
	 */	
	private String cannotAdvance = "Rover reached grid boundaries. Cannot advance";
	private String badCommand = "Current command is invalid. Rover stopped";
	private String badDeploy = "Rover cannot be deployed outside the boundaries of a prespecified grid";

	private boolean isRoverAtTheEdge(Rover aRover, Grid aGrid){		
		return aRover.getState().isBlocked(aRover, aGrid);
	}

	/*
	 * Range checking
	 */
	private boolean isRoverProperlyDeployed(Rover aRover, Grid aGrid){
		if(aRover.getPosX()>=0 && aRover.getPosX()<=aGrid.getGridX() && aRover.getPosY()>=0 && aRover.getPosY()<=aGrid.getGridY()){
			return true;
		}
		return false;
	}

	/*
	 * If all validations pass then the rover 
	 * is able to execute the signal.
	 */
	public void executeSignalIfPossible(Grid aGrid, Rover aRover, String aSignal){
		if(isRoverProperlyDeployed(aRover, aGrid)){
			for (int i = 0; i < aSignal.length(); i++){
				char currentCommand = aSignal.charAt(i);
				//Check each command for validity
				if(Pattern.matches("[LRM]*", Character.toString(currentCommand))){					
					if(isRoverAtTheEdge(aRover, aGrid) && currentCommand=='M'){
						System.out.println(cannotAdvance);
						break;
					}else{
						aRover.getCommand(currentCommand).execute(aRover);
					}
				}else{
					System.out.println(badCommand);
					break;
				}
			}
			System.out.println(aRover.getPosX()+" "+aRover.getPosY()+" "+aRover.getState().getStatus());
		}else{
			System.out.println(badDeploy);
		}
	}

}
