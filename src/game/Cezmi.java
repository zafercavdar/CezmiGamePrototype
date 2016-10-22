package game;
import java.awt.Color;

public class Cezmi {

	private Color bodyColor = new Color(123,123,123);
	private double radius = 1; 
	private int L = 30;

	private int x; 
	private int score;
	private int y = 19;

	public Cezmi(){
		super();
		this.x = 9;
	}

	public Cezmi(int x, int score) {
		super();
		this.x = x;
		this.score = score;
	}

	public void move(){

	}

	public void paint(){

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
