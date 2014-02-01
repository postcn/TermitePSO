import java.util.Random;


public class Particle {

	Vector velocity;
	Point bestPositionFound;
	Point currentPoint;
	
	public Particle(Vector v, Point c) {
		this.velocity = v;
		this.currentPoint = c;
		this.bestPositionFound = c;
	}
	
	public Particle(Grid g) {
		//random assignment
		Random r = new Random();
		int x = r.nextInt(g.getXSize());
		int y = r.nextInt(g.getYSize());
		this.velocity = new Vector(x,y);
		x = r.nextInt(g.getXSize());
		y = r.nextInt(g.getYSize());
		this.currentPoint = new Point(x,y);
		this.bestPositionFound = currentPoint;
	}
	
	public void print() {
		System.out.println("Particle: Point("+currentPoint.xPos+","+currentPoint.yPos+") : Velocity("+velocity.xVel+","+velocity.yVel+")");
	}
	
	public void move(Grid g) {
		currentPoint.add(velocity, g);
		int el = g.getElevation(bestPositionFound.xPos, bestPositionFound.yPos);
		int tel = g.getElevation(currentPoint.xPos, currentPoint.yPos);
		if (tel > el) {
			bestPositionFound = currentPoint;
		}
	}
	
	public void updateVector(Point globalBest, double globalInfluence, double localInfluence, double randomInfluence) {
		Vector toGlobal = Vector.betweenPoints(currentPoint, globalBest);
		toGlobal.multiply(globalInfluence);
		Vector toLocal = Vector.betweenPoints(currentPoint, bestPositionFound);
		toLocal.multiply(localInfluence);
		Vector random = Vector.createRandom();
		random.multiply(randomInfluence);
		velocity.add(toGlobal);
		velocity.add(toLocal);
		velocity.add(random);
	}
}
