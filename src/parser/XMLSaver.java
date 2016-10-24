package parser;

import java.io.File;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import game.Game;
import game.GameTakoz;

public class XMLSaver {


	// This part contains some codes from:
	// http://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/

	public void save(){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("board");
			doc.appendChild(rootElement);

			Element gizmos = doc.createElement("gizmos");
			rootElement.appendChild(gizmos);

			if (Game.ball != null){
				Element ball = doc.createElement("ball");
				rootElement.appendChild(ball);
				ball.setAttribute("xVelocity", Game.ball.getxVelocity() + "");
				ball.setAttribute("yVelocity", Game.ball.getyVelocity()+ "");
			} 
			
			if (Game.cezmi != null){
				Element cezmi = doc.createElement("cezmi");
				rootElement.appendChild(cezmi);
				cezmi.setAttribute("x", Game.cezmi.getX()+ "");
				cezmi.setAttribute("score", Game.cezmi.getScore()+ "");
			}
			
			for(GameTakoz takoz: Game.takozlar){
				Element eTakoz = doc.createElement("takoz");
				eTakoz.setAttribute("x", takoz.getX()+ "");
				eTakoz.setAttribute("y", takoz.getY()+ "");
				gizmos.appendChild(eTakoz);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			
			String preferredName;
			preferredName = JOptionPane.showInputDialog("XML File name?");
			preferredName = preferredName.replaceAll("[^A-Za-z0-9]", "");
			
			StreamResult result = new StreamResult(new File("src\\xmls\\" + preferredName + ".xml"));
			transformer.transform(source, result);
			System.out.println("Done! XML File is created!");
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
}
