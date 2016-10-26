package game;
import java.awt.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import animations.AnimationWindow;
import animations.ApplicationWindow;
import physics.Geometry;
import physics.Vect;

public class GameBall {
	private int screenSize = 20;
	private double radius = 0.25;
	
	private double x = screenSize/2 - radius;
	private double y = screenSize - 2*radius;
	private double xVelocity;
	private double yVelocity;
	private boolean insideTheTakoz = false;
	private int forbidCount = 4;
	private boolean ohal = false;

	private Color color = new Color(128, 0, 128);
	
	private int L = ApplicationWindow.screenSize / 20;

	public GameBall(){
		this.xVelocity = Math.round((Math.random() * 100)) / 100.0 + 2.0;
		this.yVelocity = Math.round(-(Math.random() * 100)) / 100.0 - 2.0;
		System.out.println(xVelocity + " , " + yVelocity);
	}

	public GameBall(double xVelocity, double yVelocity){

		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
	}

	public void move(){

		double rate = AnimationWindow.frameRate;
		
		if (insideTheTakoz){
			insideTheTakoz = false;
			forbidCount = 4;
			ohal = true;
		}
		
		if (ohal && forbidCount > 0){
			forbidCount--;
		} else {
			ohal = false;
			forbidCount = 4;
		}
		
		if (!ohal){
			checkTakozCollision();
		}
		
		checkCezmiCollision();
		
		if (x + xVelocity/rate <= 0) {
			x = 0; 
			xVelocity = -xVelocity; 
		}
		else if (x + xVelocity/rate >= screenSize-2*radius) {
			x = screenSize-2*radius; 
			xVelocity = -xVelocity; 
		} else {
			x += xVelocity / rate;
		}

		if (y + yVelocity/rate <= 0) {
			y = 0; 
			yVelocity = -yVelocity; 
		}

		else if (y + yVelocity/rate >= screenSize - 2*radius) { 
			y = screenSize-2*radius; 
			yVelocity *= -1; 
		} else {
			y += yVelocity/rate;
		}
		
		//System.out.println(Math.max(Math.abs(xVelocity), Math.abs(yVelocity))/rate);
	}
	
	// This code contains some code from: 
	// http://stackoverflow.com/questions/401847/circle-rectangle-collision-detection-intersection
	// The technique used to find the closest point of any square to the center of a circle is adapted from above link.
	// Clamp method is used as helper method.
	
	private double clamp(double value, double min, double max){
		if (value <= min){
			return min;
		} else if (value >= max){
			return max;
		} else{
			return value;
		}
	}
	
	public void checkTakozCollision(){
		double sensitivity = /*(Math.min(Math.abs(xVelocity),Math.abs(yVelocity))) / AnimationWindow.frameRate*/ 0.08;
		
		for (GameTakoz takoz: Game.takozlar){
			double takozLeftX = takoz.getX();
			double takozRightX = takoz.getX() + takoz.getSize();
			double takozTopY = takoz.getY();
			double takozBottomY = takoz.getY() + takoz.getSize();
			
			double closestX = clamp(this.x + radius, takozLeftX, takozRightX);
			double closestY = clamp(this.y + radius, takozTopY, takozBottomY);
			double distanceX = this.x + radius - closestX;
			double distanceY = this.y + radius - closestY;
			double distance = Math.sqrt(distanceX*distanceX + distanceY*distanceY);
			if (distance <= radius){
				insideTheTakoz = true;
				if (takozRightX - this.x < sensitivity){
					System.out.println("Hit the takoz from RIGHT");
					xVelocity *= -1;
				} else if ((this.x + 2*radius) - takozLeftX < sensitivity){
					System.out.println("Hit the takoz from LEFT");
					xVelocity *= -1;
				} else if ((this.y + 2*radius) - takozTopY < sensitivity){
					System.out.println("Hit the takoz from TOP");
					yVelocity *= -1;
				} else if (takozBottomY - this.y < sensitivity){
					System.out.println("Hit the takoz from BOTTOM");
					yVelocity *= -1;
				} else {
					System.out.println("Unexpected takoz collision");
				}
				break;
			} else {
				insideTheTakoz = false;
			}
		}
	}
	
	public  void checkCezmiCollision(){
		if (Game.cezmi.intersects(x + radius, y + radius, radius)){
			Vect cezmiVector = new Vect(Game.cezmi.getX() + Game.cezmi.getRadius(), Game.cezmi.getY() + Game.cezmi.getRadius());
			Vect ballVector = new Vect(x + radius, y + radius);
			Vect velocityVector = new Vect(xVelocity, yVelocity);
			Vect finalVelocityVector = Geometry.reflectCircle(cezmiVector, ballVector, velocityVector);
			
			xVelocity = finalVelocityVector.x();
			yVelocity = finalVelocityVector.y();
		}
	}

	public void paint(Graphics g) {

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
