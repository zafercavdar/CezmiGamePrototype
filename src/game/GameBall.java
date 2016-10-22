package game;
import java.awt.*;

public class GameBall {
	private double x = 9.5;
	private double y = 19.5;
	private double xVelocity;
	private double yVelocity;

	private double radius = 0.25;
	private Color color = new Color(128, 0, 128);
	private int screenSize = 20;
	private int L = 30;

	public GameBall(){
		this.xVelocity = (Math.random() * 5.0) -2.5;
		this.yVelocity = -(Math.random() * 2.5);
		System.out.println(xVelocity + " , " + yVelocity);
	}

	public GameBall(double xVelocity, double yVelocity){

		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
	}

	public void move(){

		if (x + xVelocity <= radius) {
			x = radius; 
			xVelocity = -xVelocity; 
		}
		else if (x + xVelocity >= screenSize-radius) {
			x = screenSize-radius; 
			xVelocity = -xVelocity; 
		} else {
			x += xVelocity;
		}

		if (y + yVelocity <= radius) {
			y = radius; 
			yVelocity = -yVelocity; 
		}

		else if (y + yVelocity >= screenSize - radius) { 
			y = screenSize-radius; 
			yVelocity *= -1; 
		} else {
			y += yVelocity;
		}
	}

	public void paint(Graphics g) {
		// modifies: the Graphics object <g>.
		// effects: paints a circle on <g> reflecting the current position
		// of the ball.

		// the "clip rectangle" is the area of the screen that needs to be
		// modified
		Rectangle clipRect = g.getClipBounds();

		// For this tiny program, testing whether we need to redraw is
		// kind of silly.  But when there are lots of objects all over the
		// screen this is a very important performance optimization
		if (clipRect.intersects(this.boundingBox())) {
			g.setColor(color);
			g.fillOval((int) ((x-radius)* L), (int) ((y-radius)*L), (int) ((radius+radius)*L), (int) ((radius+radius)*L));
		}
	}

	public Rectangle boundingBox() {
		// effect: Returns the smallest rectangle that completely covers the
		//         current position of the ball.

		// a Rectangle is the x,y for the upper left corner and then the
		// width and height
		return new Rectangle((int)((x-radius)*L), (int) ((y-radius)*L), (int) ((radius+radius)*L+1), (int)((radius+radius)*L+1));
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getxVelocity() {
		return xVelocity;
	}

	public void setxVelocity(double xVelocity) {
		this.xVelocity = xVelocity;
	}

	public double getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(double yVelocity) {
		this.yVelocity = yVelocity;
	}
}