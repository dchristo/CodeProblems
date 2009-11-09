package code.statePatternRovers;

/*
 * Here a simple grid class is declared,
 * with just two member variables, implying,
 * as per the problem, that the origin of the
 * grid is the point (0,0).
 */
public class Grid {
	private int gridX;
	private int gridY;
	
	public Grid(int gridX, int gridY){
		this.gridX = gridX;
		this.gridY = gridY;
	}
	
	public int getGridX(){
		return gridX;
	}
	
	public int getGridY(){
		return gridY;
	}
}
