package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import animations.ApplicationWindow;

public class GameTakoz {

	private int x;
	private int y;
	private int size = 1;
	private Color color = new Color(255,255,255);
	private double L = ApplicationWindow.screenSize / 20;

	public GameTakoz(int x, int y){
		this.x = x;
		this.y = y;
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
			g.fillRect((int ) (x*L), (int) (y*L), (int) (size*L) , (int) (size*L));
		}
	}

	public int getSize() {
		return size;
	}

	public Rectangle boundingBox() {
		// effect: Returns the smallest rectangle that completely covers the
		//         current position of the ball.

		// a Rectangle is the x,y for the upper left corner and then the
		// width and height
		return new Rectangle((int)((x)*L), (int) ((y)*L), (int) ((size)*L+1), (int)((size)*L+1));
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
