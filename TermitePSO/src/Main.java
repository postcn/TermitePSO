import java.util.ArrayList;
import java.util.Random;

/**
 * Begins our termite approach to PSO.
 */
public class Main {

	/**
	 * TODO Put here a description of what this method does.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Grid g = new Grid();
		g.setSize(10, 10);
		g.setRandomValues();
		g.printGrid();
			
		int numTermites = 20;
		int iterations = 10;
		
		double pStrength = 5;
		int moves = 5;
		double pImportance = 0.1;
		Random r = new Random();
		
		while (iterations>0) {
			ArrayList<Termite> termites = new ArrayList<Termite>();
		for (int i=0; i<numTermites; i++) {
			int x = r.nextInt(10);
			int y = r.nextInt(10);
			termites.add(new Termite(x,y,pStrength,moves,pImportance));
		}
		
		
			while(termites.get(0).getMovesLeft() > 0) {
				for (Termite t: termites) {
					t.move(g);
				}
			}
			for (Termite t: termites) {
				t.layPheromones(g);
			}
			iterations--;
			g.printPheromones();
			System.out.println("-----");
		}
		
	}

}
