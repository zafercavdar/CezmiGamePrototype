package GameObjects;
import java.util.ArrayList;

import Parser.XMLParser;
import animations.ApplicationWindow;
import animations.AnimationWindow;
import animations.AnimationEventListener;

public class Game {
	
	public static BouncingBall ball = new BouncingBall();
	public static ArrayList<GameTakoz> takozlar = new ArrayList<GameTakoz>();
	public static Cezmi cezmi;
	
	public static void main(String[] args) {
		
		String loc = "src\\xmls\\CezmiPrototype3.xml";
		XMLParser parser = new XMLParser(loc);
		parser.parseXMLFile();
		
	    ApplicationWindow frame = new ApplicationWindow();
	    // the following code realizes the top level application window
	    frame.pack();
	    frame.setVisible(true);
	  }
}