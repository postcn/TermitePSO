import java.util.ArrayList;
import java.util.Random;


public class Termite {
	int x;
	int y;
	double pStrength;
	int movesLeft;
	double pImportance;
	
	public Termite(int x, int y, double pStrength, int moves, double pImportance) {
		this.x = x;
		this.y = y;
		this.pStrength = pStrength;
		this.movesLeft = moves;
		this.pImportance = pImportance;
	}
	
	public Termite(int x, int y, double pStrength, double pImportance) {
//		this(x, y, pStrength,Integer.MAX_VALUE, pImportance);
		this(x, y, pStrength,10, pImportance);
	}
	
	public void move(Grid g) {
		//Takes grid and determines where to move
		//Move to location on grid
		//Only lay pheromones at end of each turn.
		ArrayList<Double> probs = new ArrayList<Double>();
		double sum = 0;
		for (Grid.Direction i: g.getDirections(this.x, this.y)) {
			probs.set(i.getValue(), g.getElevation(x+i.changeX(),y + i.changeY())*1/pImportance + pImportance*readPheromones(g, i));
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
		this.x += move.changeX();
		this.y += move.changeY();
		this.movesLeft--;
	}
	
	public double readPheromones(Grid g, Grid.Direction direction) {
		//Used for determining direction on grid
		//Process the pheromone information
		return 0;
	}
	
	public void layPheromones(Grid g) {
		//Update pheromones at grid location.
		g.addPheromone(x,y, pStrength);
	}
}
