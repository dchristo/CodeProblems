package code.statePatternRovers;

import java.util.HashMap;
import java.util.Map;

public class Rover {
	private int posX;
	private int posY;
	
	/*
	 * Store the current state. This
	 * variable will reference one
	 * of the stored concrete states, 
	 * which will be changing depending 
	 * on the logic.
	 */
	private State currentState;
	
	/*
	 * Getters and setters for the
	 * current rover position.
	 */
	public int getPosX(){
		return posX;
	}
	public void setPosX(int posX){
		this.posX = posX;
	}

	public int getPosY(){
		return posY;
	}
	public void setPosY(int posY){
		this.posY = posY;
	}
	
	/*
	 * Getters and setters for the current state.
	 */
	public void setState(State state){
		this.currentState = state;
	}
	public State getState(){
		return currentState;
	}

	
	/*
	 * A final map to store and
	 * pull out the chosen concrete state
	 * depending on the logic. It will be
	 * used to set the current state.
	 */
	private final Map<String, State> concreteStates = new HashMap<String, State>();
	
	/*
	 * A final map to store commands for
	 * a client to use when controlling 
	 * the rover (L,R,M)
	 */
	private final Map<Character, Command> commands = new HashMap<Character, Command>();
	
	/*
	 * Constructor for rover, initialising 
	 * a rover with position and direction.
	 */
	public Rover(int posX, int posY, String initialDirection){		
		this.posX = posX;
		this.posY = posY;
		
		/*
		 * Initialize concrete states and 
		 * store them on the map for retrieval.
		 */
		concreteStates.put("N", new StateFacingNorth());
		concreteStates.put("W", new StateFacingWest());
		concreteStates.put("S", new StateFacingSouth());
		concreteStates.put("E", new StateFacingEast());
		
		/*
		 * Create and store the rover concrete commands
		 * on the map with corresponding keys to L,R,M
		 * to be used by a client object.
		 */
		commands.put('L', new CommandRotateLeft());
		commands.put('R', new CommandRotateRight());
		commands.put('M', new CommandMoveForward());
				
		/*
		 * Initialize the current state. Using the
		 * the constructor argument as key, pull
		 * the appropriate concrete state from the map, 
		 * and set it as the current state.
		 */
		this.currentState = concreteStates.get(initialDirection);
	}
	
	/*
	 * Get a concrete state from the map.
	 */	
	public State getConcreteState(String concreteState){
		return concreteStates.get(concreteState);
	}
	
	/*
	 * A client will use this to 
	 * get a command from the map.
	 */
	public Command getCommand(char command){
		return commands.get(command);
	}
}
