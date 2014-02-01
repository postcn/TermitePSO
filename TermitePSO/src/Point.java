
public class Point {
	int xPos;
	int yPos;
	
	public Point(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}
	
	public void add(Vector v, Grid g) {
		this.xPos += v.xVel;
		this.yPos += v.yVel;
		int xMax = g.getXSize();
		int yMax = g.getYSize();
		if (xPos>= xMax) {
			xPos = xMax-1;
		}
		if (xPos < 0) {
			xPos = 0;
		}
		if (yPos>= yMax) {
			yPos = yMax-1;
		}
		if (yPos < 0) {
			yPos = 0;
		}
	}
}
