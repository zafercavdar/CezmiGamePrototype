import java.awt.Color;

public class GameBall {
	private double x;
	private double y;
	private double xVelocity;
	private double yVelocity;
	private double radius = 0.5;
	private Color color = new Color(255, 0, 0);

	
	public GameBall(double x, double y, double xVelocity, double yVelocity){
		this.x = x;
		this.y = y;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
	}
	
	public void move(){
		
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
