import java.util.ArrayList;


public class PSORunner {
	ArrayList<Particle> particles;
	Point globalBest;
	Grid g;
	double global;
	double local;
	double random;
	int iterations;
	
	public PSORunner(int iterations,int numParticles, Grid g, double globalStrength, double localStrength, double randomStrength) {
		this.g = g;
		particles = new ArrayList<Particle>();
		for (int i=0; i<numParticles; i++) {
			particles.add(new Particle(g));
		}
		this.global = globalStrength;
		this.local = localStrength;
		this.iterations = iterations;
		this.random = randomStrength;
	}
	
	public Point run() {
		while(iterations > 0) {
			iterations--;
			for (Particle p: particles) {
				p.move(g);
				Point cur = p.currentPoint;
				if (globalBest == null) {
					globalBest = new Point(-1,-1);
				}
				int el = g.getElevation(globalBest.xPos, globalBest.yPos);
				int tel = g.getElevation(cur.xPos, cur.yPos);
				if (tel > el) {
					globalBest = cur;
				}
			}
			for (Particle p: particles) {
				p.updateVector(globalBest, global, local, random);
			}
		}
		return globalBest;
	}
}
