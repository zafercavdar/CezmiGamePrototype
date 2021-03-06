package parser;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import game.*;

import javax.swing.JOptionPane;
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import java.io.*;

public class XMLParser {

	private String fileDir = "";
	private boolean isFailed = false;
	private boolean validated = false;

	enum Constants {
		TAKOZ("takoz"), BALL("ball"), GIZMOS("gizmos"), CEZMI("cezmi");

		private String XMLTag;

		private Constants(String s){
			XMLTag = s;
		}

		public String getXMLTag(){
			return XMLTag;
		}
	}

	public XMLParser(String fileDir){
		this.fileDir = fileDir;
	}

	// This part contains some codes from:
	// http://docs.oracle.com/javase/7/docs/api/javax/xml/validation/package-summary.html
	// The try part of the code is taken from above Oracle documentation.
	
	public boolean validateXMLFile(){
		try {
			// parse an XML document into a DOM tree
			DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			try {
				Document document = parser.parse(new File(this.fileDir));
				// create a SchemaFactory capable of understanding WXS schemas
				SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

				// load a WXS schema, represented by a Schema instance
				Source schemaFile = new StreamSource(new File("src\\xmls\\CezmiGamePrototype.xsd"));
				Schema schema = factory.newSchema(schemaFile);

				// create a Validator instance, which can be used to validate an instance document
				Validator validator = schema.newValidator();

				// validate the DOM tree
				try {
					validator.validate(new DOMSource(document));
				} catch (SAXException e) {
					// instance document is invalid!
					e.printStackTrace();
					isFailed = true;
					alertUser(e.getLocalizedMessage());
				}
			} catch (SAXException | IOException e) {
				e.printStackTrace();
				isFailed = true;
				alertUser(e.getLocalizedMessage());
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			isFailed = true;
			alertUser(e.getLocalizedMessage());
		}

		validated = true;

		if (isFailed){
			System.out.println("Validation failed.");
		}

		return !isFailed;
	}

	// This method contains some codes from:
	// https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
	// I used article above to understand implementation techniques behind parsing an XML File.
	// Most of the method codes are written by me (except of first few lines).
	
	public boolean parseXMLFile(){

		boolean result = true;

		if (validated && !isFailed){
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();;
			File inputFile = new File(this.fileDir);

			DocumentBuilder builder = null;
			try {
				builder = factory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			}
			Document doc = null;
			try {
				doc = builder.parse(inputFile);
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			doc.getDocumentElement().normalize();

			NodeList balls = doc.getElementsByTagName("ball");
			Node ball = null;
			if (balls.getLength() == 1){
				ball = balls.item(0);
				System.out.println("Single Ball.");
				Element eBall = (Element) ball;
				double xVel = Double.parseDouble(eBall.getAttribute("xVelocity"));
				double yVel = Double.parseDouble(eBall.getAttribute("yVelocity"));
				GameBall gameBall = new GameBall(xVel, yVel);
				Game.ball = gameBall;

			} else if (balls.getLength() == 0){
				System.out.println("No balls found in XML File.");
				GameBall gameBall = new GameBall();
				Game.ball = gameBall;
			} else {
				System.out.println("Multiple balls.");
				isFailed = true;
			}

			NodeList cezmis = doc.getElementsByTagName("cezmi");
			Node cezmi = null;
			if (cezmis.getLength() == 1){
				cezmi = cezmis.item(0);
				System.out.println("Single Cezmi");
				Element eCezmi = (Element) cezmi;
				int x = Integer.parseInt(eCezmi.getAttribute("x"));
				int score = Integer.parseInt(eCezmi.getAttribute("score"));

				if (x < 0 || x > 18){
					result = false;
					System.err.println("Invalid CEZMI Position");
				} else {
					Cezmi gameCezmi = new Cezmi(x,score);
					Game.cezmi = gameCezmi;
				}

			} else if (cezmis.getLength() == 0){
				System.out.println("No Cezmi found in XML file.");
				Cezmi gameCezmi = new Cezmi();
				Game.cezmi = gameCezmi;

			} else {
				System.out.println("Multiple cezmis?");
				result = false;
				isFailed = true;
			}

			NodeList gizmos = doc.getElementsByTagName("gizmos");
			Node gizmo = gizmos.item(0);
			Element eGizmo = (Element) gizmo;
			NodeList takozList = eGizmo.getElementsByTagName(Constants.TAKOZ.XMLTag);

			Game.takozlar.clear();

			for (int i = 0; i < takozList.getLength(); i++){
				Node takoz = takozList.item(i);
				Element element = (Element) takoz;
				int x = Integer.parseInt(element.getAttribute("x"));
				int y = Integer.parseInt(element.getAttribute("y"));

				if (x< 0 || x > 19 || y < 0 || y > 19){
					result = false;
					System.err.println("Invalid Takoz Position Error");
				} else {
					GameTakoz gameTakoz = new GameTakoz(x, y);
					Game.takozlar.add(gameTakoz);
				}
			}
		} else {
			System.out.println("Document is not validated or validation is failed.");
		}

		return result;
	}

	public void alertUser(String e){
		JOptionPane.showMessageDialog(null, e + "\nInvalid XML File. Choose another one.");
	}
}
