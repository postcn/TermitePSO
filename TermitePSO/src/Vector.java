import java.util.Random;


public class Vector {
	int xVel;
	int yVel;
	
	public Vector(int xVelocity, int yVelocity) {
		this.yVel = yVelocity;
		this.xVel = xVelocity;
	}
	
	public Vector add(Vector v) {
		this.xVel = v.xVel + this.xVel;
		this.yVel = v.yVel + this.yVel;
		return this;
	}
	
	public static Vector betweenPoints(Point one, Point two) {
		return new Vector(one.xPos - two.xPos, one.yPos - two.yPos);
	}
	
	public static Vector createRandom() {
		Random r = new Random();
		return new Vector(r.nextInt(10)-5,r.nextInt(10)-5);
	}
	
	public void multiply(double d) {
		this.xVel = (int) (xVel * d);
		this.yVel = (int) (yVel * d);
	}
}
