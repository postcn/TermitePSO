import java.util.ArrayList;
import java.util.Random;


/**
 * Defines the Grid upon which the termites will search for maximum values.
 */
public class Grid {
	private ArrayList<ArrayList<Integer>> elevations;
	private ArrayList<ArrayList<Double>> pheromones;
	
	/**
	 * Defines the four directions a termite may move on the grid.
	 */
	public enum Direction {STAY(0), NORTH(1), SOUTH(2), EAST(3), WEST(4);
	
	private final int value;
	
	private Direction(int value) {
		this.value = value;
	}
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param value
	 * @return
	 */
	public static Direction getDirection(int value) {
		return Direction.values()[value];
	}
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	public int changeX() {
		if (this == STAY || this == SOUTH || this == NORTH) {
			return 0;
		}
		else if (this == EAST) {
			return 1;
		}
		else if (this == WEST) {
			return -1;
		}
		return 0;
	}
	
	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @return
	 */
	public int changeY() {
		if (this == STAY || this == EAST || this == WEST) {
			return 0;
		}
		else if (this == NORTH) {
			return 1;
		}
		else if (this == SOUTH) {
			return -1;
		}
		return 0;
	}
	}
	
	/**
	 * @param x
	 * @param y
	 * @return All possible directions a termite can travel.
	 */
	public ArrayList<Direction> getDirections(int x,int y) {
		return new ArrayList<Direction>();
	}
	
	/**
	 * Default constructor initializes a blank list to store the elevations
	 * of each space and another to store each of the pheromone levels.
	 */
	public Grid() {
		this.elevations = new ArrayList<ArrayList<Integer>>();
		this.pheromones = new ArrayList<ArrayList<Double>>();
	}
	
	/**
	 * Creates a, x by y grid.
	 *
	 * @param x
	 * @param y
	 */
	public void setSize(int x, int y) {
		for(int i=0; i<x; i++) {
			this.elevations.add(new ArrayList<Integer>());
			this.pheromones.add(new ArrayList<Double>());
			for(int j=0; j<y; j++) {
				this.elevations.get(i).add(0);
				this.pheromones.get(i).add(0.0);
			}
		}
	}
	
	/**
	 * Sets the value of each elevation to a random value.
	 *
	 */
	public void setRandomValues() {
		
		for (int i=0; i<this.elevations.size(); i++) {
			Random r = new Random();
			for (int j=0; j<this.elevations.get(i).size(); j++) {
				int temp = r.nextInt(100);
				this.elevations.get(i).set(j, temp);
			}
		}
	}
	
	/**
	 * @param x
	 * @param y
	 * @return The elevation of point (x,y)
	 */
	public int getElevation(int x, int y) {
		return this.elevations.get(x).get(y);
	}
	
	/**
	 * @param x
	 * @param y
	 * @return The amount of pheromone on space (x,y)
	 */
	public double getPheromones(int x, int y) {
		return this.pheromones.get(x).get(y);
	}
	
	/**
	 * Prints out a text representation of the grid's elevations.
	 *
	 */
	public void printGrid() {
		for(int i=0; i< this.elevations.size(); i++) {
			ArrayList<Integer> row = this.elevations.get(i);
			for (int j=0; j<row.size(); j++) {
				if(row.get(j) < 10){//Improve readability by evening the spacing
					System.out.print(" " + row.get(j) + " ");
				}
				else{
					System.out.print(row.get(j) + " ");
				}
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * Prints out a list of all pheromone levels.
	 *
	 */
	public void printPheromones() {
		for(int i=0; i< this.pheromones.size(); i++) {
			ArrayList<Double> row = this.pheromones.get(i);
			for (int j=0; j<row.size(); j++) {
				if(row.get(j) < 10){//Improve readability by evening the spacing
					System.out.print(" " + row.get(j) + " ");
				}
				else{
					System.out.print(row.get(j) + " ");
				}
			}
			System.out.print("\n");
		}
	}
	
	/**
	 * Adds the specified amount of pheromone to space (x,y) on the grid.
	 *
	 * @param x
	 * @param y
	 * @param amount
	 */
	public void addPheromone(int x,int y, double amount) {
		this.pheromones.get(x).set(y,this.pheromones.get(x).get(y)+amount);
	}
	
}
