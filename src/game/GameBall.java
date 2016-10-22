package game;
import java.awt.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import animations.AnimationWindow;
import animations.ApplicationWindow;

public class GameBall {
	private double x = 9.5;
	private double y = 19.5;
	private double xVelocity;
	private double yVelocity;

	private double radius = 0.25;
	private Color color = new Color(128, 0, 128);
	private int screenSize = 20;
	private int L = ApplicationWindow.screenSize / 20;

	public GameBall(){
		//this.xVelocity = (Math.random() * 4) -2;
		//this.yVelocity = -(Math.random() * 2);
		this.xVelocity = 5;
		this.yVelocity = 3;
		System.out.println(xVelocity + " , " + yVelocity);
	}

	public GameBall(double xVelocity, double yVelocity){

		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
	}

	public void move(){

		double rate = AnimationWindow.frameRate;
		
		if (x + xVelocity/rate <= radius) {
			x = radius; 
			xVelocity = -xVelocity; 
		}
		else if (x + xVelocity/rate >= screenSize-radius) {
			x = screenSize-radius; 
			xVelocity = -xVelocity; 
		} else {
			x += xVelocity / rate;
		}

		if (y + yVelocity/rate <= radius) {
			y = radius; 
			yVelocity = -yVelocity; 
		}

		else if (y + yVelocity/rate >= screenSize - radius) { 
			y = screenSize-radius; 
			yVelocity *= -1; 
		} else {
			y += yVelocity/rate;
		}
		
		//System.out.println(Math.max(Math.abs(xVelocity), Math.abs(yVelocity))/rate);
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
			g.fillOval((int) (x* L), (int) (y*L), (int) ((2*radius)*L), (int) ((2*radius)*L));
		}
	}

	public Rectangle boundingBox() {
		// effect: Returns the smallest rectangle that completely covers the
		//         current position of the ball.

		// a Rectangle is the x,y for the upper left corner and then the
		// width and height
		return new Rectangle((int)((x)*L), (int) ((y)*L), (int) ((2*radius)*L+1), (int)((2*radius)*L+1));
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
