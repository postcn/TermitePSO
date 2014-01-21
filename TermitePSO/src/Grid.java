import java.util.ArrayList;


public class Grid {
	private ArrayList<ArrayList<Integer>> elevations;
	private ArrayList<ArrayList<Double>> pheromones;
	
	public enum Direction {NORTH, SOUTH, EAST, WEST};
	
	public ArrayList<Direction> getDirections() {
		return new ArrayList<Direction>();
	}
	
	public int getElevation(int x, int y) {
		return elevations.get(x).get(y);
	}
	
}
