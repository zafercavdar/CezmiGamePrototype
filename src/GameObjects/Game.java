package GameObjects;
import java.util.ArrayList;

import animations.ApplicationWindow;
import animations.AnimationWindow;
import animations.AnimationEventListener;

public class Game {
	
	public static BouncingBall ball = new BouncingBall();
	private static ArrayList<GameTakoz> takozlar;
	
	public static void main(String[] args) {
		
		
	    ApplicationWindow frame = new ApplicationWindow();
	    // the following code realizes the top level application window
	    frame.pack();
	    frame.setVisible(true);
	  }
}