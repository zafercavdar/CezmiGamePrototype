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
		
		Rectangle clipRect = g.getClipBounds();

		if (clipRect.intersects(this.boundingBox())) {
			g.setColor(color);
			g.fillRect((int ) (x*L), (int) (y*L), (int) (size*L) , (int) (size*L));
		}
	}

	public int getSize() {
		return size;
	}

	public Rectangle boundingBox() {
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
