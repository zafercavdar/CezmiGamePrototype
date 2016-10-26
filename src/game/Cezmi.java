package game;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import animations.ApplicationWindow;

public class Cezmi {

	private double radius = 1; 
	
	public double getRadius() {
		return radius;
	}

	private Color color = new Color(24, 242, 247);
	private int screenSize = 20;
	private int L = ApplicationWindow.screenSize / 20;

	private int x; 
	private int score;
	private int y = 19;
	public Rectangle prevBoundingbox = new Rectangle(screenSize*L, screenSize*L, 0, 0);
	
	public Cezmi(){
		super();
		this.x = 9;
		while (x>= 8 && x<= 10){
			this.x = (int)(Math.random() * 19);
			if (x == 19){
				x = 18;
			}
		}
	}

	public Cezmi(int x, int score) {
		super();
		this.x = x;
		this.score = score;
	}

	public void move(int mode){
		if (mode == 0 && x-1>= 0){
			// LEFT
			prevBoundingbox = this.boundingBox();
			x -= 1;
		} else if (mode == 1 && x+1 <= screenSize - 2*radius){
			//RIGHT
			prevBoundingbox = this.boundingBox();
			x += 1;
		} else {
			System.out.println("Cannot move.");
		}
	}

	public void paint(Graphics g){
		
		Rectangle clipRect = g.getClipBounds();

		if (clipRect.intersects(this.boundingBox())) {
			g.setColor(color);
			g.fillOval((int) (x*L), (int) (y*L), (int) (2*radius*L), (int)(2*radius*L));
		}
	}
	
	public Rectangle boundingBox() {
		// effect: Returns the smallest rectangle that completely covers the
		//         current position of the ball.

		// a Rectangle is the x,y for the upper left corner and then the
		// width and height
		return new Rectangle((int)((x)*L), (int) ((y)*L), (int) ((2*radius)*L+1), (int)((2*radius)*L+1));
	}
	
	public boolean intersects(double x, double y, double ballRadius){
		double distance = Math.sqrt((this.x + radius - x)*(this.x + radius - x) + (this.y + radius - y)*(this.y + radius - y));
		return (distance <= ballRadius + radius);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}



}
