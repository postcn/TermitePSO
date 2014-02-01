import java.util.ArrayList;
import java.util.Random;

/**
 * Begins our termite approach to PSO.
 */
public class Main {

	/**
	 * Initializes the termite program.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Double> results = new ArrayList<Double>();
		ArrayList<Double> psoResults = new ArrayList<Double>(); 
		int runs = 100;
		for (int i=0; i<runs; i++) {
			Grid g = new Grid();
			g.setSize(10, 10);
			g.setRandomValues();
//			System.out.println("Printing the grid below:");
//			g.printGrid();
//			System.out.println("-------------------------");
				
			int numTermites = 20;
			int iterations = 100;
			
			double pStrength = 5;
			int moves = 10;
			double pImportance = 0.3;
			double decayRate = 0.15;
			double globalStrength = 0.25;
			double localStrength = 0.25;
			double randomStrength = 0.15;
			
			results.add(randomSearch(numTermites, iterations, pStrength, moves, pImportance, g, decayRate));
			psoResults.add(PSOSearch(iterations,numTermites, g,globalStrength,localStrength,randomStrength));
		}
		double accuracy = 0;
		for (Double d : results) {
			accuracy += d;
		}
		accuracy = accuracy / (double) results.size();
		System.out.println("Termites ran an average of " + accuracy+"% accuracy.");
		accuracy = 0;
		for (Double d : psoResults) {
			accuracy += d;
		}
		accuracy = accuracy / (double) results.size();
		System.out.println("PSO ran an average of " + accuracy+"% accuracy.");
	}

	/**
	 * Generates termites to move around the grid on their own subject to the
	 * following constraints.
	 *
	 * @param numTermites - Total number of termites to use.
	 * @param iterations - Number of rounds in which each termite will be able to move.
	 * @param pStrength - The strength of each termites pheromone.
	 * @param moves - The number of moves allotted to each termite each iteration.
	 * @param pImportance - The importance of each termites pheromone.
	 * @param g - The grid to be searched.
	 */
	private static double randomSearch(int numTermites, int iterations,
			double pStrength, int moves, double pImportance, Grid g, double decay) {
		
		Random r = new Random();
		
//		System.out.println("-------Beginning random search-------");
		while (iterations>0) {
			ArrayList<Termite> termites = new ArrayList<Termite>();
		
			//Generate the termites for this iteration
			for (int i=0; i<numTermites; i++) {
				int x = r.nextInt(10);
				int y = r.nextInt(10);
				termites.add(new Termite(x,y,pStrength,moves,pImportance));
			}
		
			//Let each termite move once, if it has at least one remaining move
			while(termites.get(0).getMovesLeft() > 0) {
				for (Termite t: termites) {
					t.move(g);
				}
			}
			//Lay pheromone after each termite has moved
			for (Termite t: termites) {
				t.layPheromones(g);
			}
			//Print the results of this iteration and prepare for the next
			iterations--;
//			System.out.println("Pheromones after iteration " + iterations);
//			g.printPheromones();
			g.decayPheromones(decay);
//			System.out.println("-----------------------------");
		}
		return g.getAccuracy();
	}
	
	private static double PSOSearch(int iterations,int numParticles, Grid g, 
			double globalStrength, double localStrength, double randomStrength) {
		PSORunner runner = new PSORunner(iterations, numParticles, g, globalStrength, localStrength, randomStrength);
		Point result = runner.run();
		return g.getAccuracy(result);
	}
}
