package game;
import java.util.ArrayList;

import parser.XMLParser;
import animations.ApplicationWindow;
import parser.*;

public class Game {

	public static GameBall ball;
	public static ArrayList<GameTakoz> takozlar = new ArrayList<GameTakoz>();
	public static Cezmi cezmi;

	public static void main(String[] args) {

		boolean valid = false;

		while (!valid){
			final XMLFileChooser fc = new XMLFileChooser();
			fc.showOpenDialog(null);

			String loc = fc.getSelectedFile().getAbsolutePath();
			System.out.println(loc);

			//String loc = "src\\xmls\\CezmiPrototype3.xml";
			XMLParser parser = new XMLParser(loc);
			valid = parser.validateXMLFile();

			if (valid){
				boolean secondCheck = parser.parseXMLFile();

				if (secondCheck){
					ApplicationWindow frame = new ApplicationWindow();
					// the following code realizes the top level application window
					frame.pack();
					frame.setVisible(true);
				} else {
					valid = false;
				}
			}
		}
	}
}