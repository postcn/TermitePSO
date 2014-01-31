import java.util.ArrayList;
import java.util.Random;


/**
 * Defines the structure of the termite used to search for a peak.
 */
public class Termite {
	private int x;//The x coordinate representing this termite's position.
	private int y;//The y coordinate representing this termite's position.
	private double pStrength;//The strength of this termite's pheromone relative to other termites.
	private int movesLeft;//The number of moves remaining for this termite to make.
	private double pImportance;
	
	/**
	 * Default constructor takes the basic information needed to create a single
	 * termite instance.
	 *
	 * @param x - The x coordinate representing this termite's position.
	 * @param y - The y coordinate representing this termite's position.
	 * @param pStrength - How many layers of pheromon this termite lays	TODO double check
	 * @param pImportance - How important this termite's pheromone is
	 */
	public Termite(int x, int y, double pStrength, double pImportance) {
//		this(x, y, pStrength,Integer.MAX_VALUE, pImportance);
		this(x, y, pStrength,10, pImportance);
	}
	
	/**
	 * Constructor that creates a termite with a specific number of moves.
	 *
	 * @param x - The x coordinate representing this termite's position.
	 * @param y - The y coordinate representing this termite's position.
	 * @param pStrength - The strength of this termite's pheromone relative to other termites.
	 * @param moves - The number of moves this termite can make.
	 * @param pImportance - The importance of this termite's pheromone relative to other termites.
	 */
	public Termite(int x, int y, double pStrength, int moves, double pImportance) {
		this.setX(x);
		this.setY(y);
		this.setpStrength(pStrength);
		this.setMovesLeft(moves);
		this.setpImportance(pImportance);
	}
	
	
	/**
	 * Takes grid and determines where to move and moves there.
	 * Only lay pheromones at end of each turn (determined by the calling function).
	 *
	 * @param g
	 */
	public void move(Grid g) {
		
		ArrayList<Double> probs = new ArrayList<Double>();
		double sum = 0;
		for (Grid.Direction i: g.getDirections(this.getX(), this.getY())) {
			probs.set(i.getValue(), g.getElevation(getX()+i.changeX(),getY() +
					i.changeY())*1/this.getpImportance() + this.getpImportance()*readPheromones(g, i));
			sum += probs.get(i.getValue());
		}
		int i;
		for (i=0; i<probs.size(); i++) {
			probs.set(i, probs.get(i)/sum);
		}
		
		Random r = new Random();
		double d = r.nextDouble();
		for (i=0; i<probs.size(); i++) {
			if (d<probs.get(i)) {
				break;
			}
		}
		
		Grid.Direction move = Grid.Direction.getDirection(i);
		this.setX(this.getX() + move.changeX());
		this.setY(this.getY() + move.changeY());
		this.setMovesLeft(this.getMovesLeft() - 1);
	}
	
	/**
	 * Examines the pheromones currently on the grid.
	 * Uses one of multiple functions of achieve this in different ways.
	 *
	 * @param g
	 * @param direction
	 * @return The pheromone level detected in the given direction.
	 */
	public double readPheromones(Grid g, Grid.Direction direction) {
		//Used for determining direction on grid
		//Process the pheromone information
		return 0;
	}
	
	/**
	 * Places this termite's pheromones.
	 *
	 * @param g
	 */
	public void layPheromones(Grid g) {
		//Update pheromones at grid location.
		g.addPheromone(this.getX(), this.getY(), this.getpStrength());
	}

	/**
	 * Sets the strength of this termite's pheromone.
	 * @param pStrength
	 */
	public void setpStrength(double pStrength) {
		this.pStrength = pStrength;
	}

	/**
	 * @return the strength of this termite's pheromone
	 */
	public double getpStrength() {
		return this.pStrength;
	}

	/**
	 * Sets the y value of this termite's position.
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return Returns the y value of this termite's position.
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Sets the x value of this termite's position.
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return Returns the x value of this termite's position.
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Sets the strength of this termite's pheromone.
	 * @param pImportance
	 */
	public void setpImportance(double pImportance) {
		this.pImportance = pImportance;
	}

	/**
	 * @return the importance of this termite's pheromone
	 */
	public double getpImportance() {
		return this.pImportance;
	}

	/**
	 * Sets the number of moves alloted to this termite.
	 * @param movesLeft
	 */
	public void setMovesLeft(int movesLeft) {
		this.movesLeft = movesLeft;
	}

	/**
	 * @return the number of moves this termite has remaining.
	 */
	public int getMovesLeft() {
		return this.movesLeft;
	}
	
}
