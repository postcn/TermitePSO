import java.util.ArrayList;
import java.util.Random;


public class Grid {
	private ArrayList<ArrayList<Integer>> elevations;
	private ArrayList<ArrayList<Double>> pheromones;
	
	public enum Direction {STAY(0), NORTH(1), SOUTH(2), EAST(3), WEST(4);
	private final int value;
	private Direction(int value) {
		this.value = value;
	}
	public static Direction getDirection(int value) {
		return Direction.values()[value];
	}
	public int getValue() {
		return value;
	}
	
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
	
	public ArrayList<Direction> getDirections(int x,int y) {
		return new ArrayList<Direction>();
	}
	
	public Grid() {
		this.elevations = new ArrayList<ArrayList<Integer>>();
		this.pheromones = new ArrayList<ArrayList<Double>>();
	}
	
	public void setSize(int x, int y) {
		for(int i=0; i<x; i++) {
			elevations.add(new ArrayList<Integer>());
			pheromones.add(new ArrayList<Double>());
			for(int j=0; j<y; j++) {
				elevations.get(i).add(0);
				pheromones.get(i).add(0.0);
			}
		}
	}
	
	public void setRandomValues() {
		
		for (int i=0; i<elevations.size(); i++) {
			Random r = new Random();
			for (int j=0; j<elevations.get(i).size(); j++) {
				int temp = r.nextInt(100);
				elevations.get(i).set(j, temp);
			}
		}
	}
	
	public int getElevation(int x, int y) {
		return elevations.get(x).get(y);
	}
	
	public double getPheromones(int x, int y) {
		return pheromones.get(x).get(y);
	}
	
	public void printGrid() {
		for(int i=0; i< elevations.size(); i++) {
			ArrayList<Integer> row = elevations.get(i);
			for (int j=0; j<row.size(); j++) {
				System.out.print(row.get(j) + " ");
			}
			System.out.print("\n");
		}
	}
	
	public void printPheromones() {
		for(int i=0; i< pheromones.size(); i++) {
			ArrayList<Double> row = pheromones.get(i);
			for (int j=0; j<row.size(); j++) {
				System.out.print(row.get(j) + " ");
			}
			System.out.print("\n");
		}
	}
	
	public void addPheromone(int x,int y, double amount) {
		this.pheromones.get(x).set(y,this.pheromones.get(x).get(y)+amount);
	}
	
}
