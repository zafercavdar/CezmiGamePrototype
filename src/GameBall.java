
public class GameBall {
	private double x;
	private double y;
	private double xVelocity;
	private double yVelocity;
	
	public GameBall(double x, double y, double xVelocity, double yVelocity){
		this.x = x;
		this.y = y;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
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
