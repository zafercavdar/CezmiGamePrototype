package parser;

import java.io.File;

import javax.swing.JFileChooser;

public class XMLFileChooser extends JFileChooser{

	public XMLFileChooser(){
		File defaultLoc = new File("src\\xmls");
		this.setCurrentDirectory(defaultLoc);
		this.setFileSelectionMode(JFileChooser.FILES_ONLY);
		this.addChoosableFileFilter(new XMLFilter());
		this.setAcceptAllFileFilterUsed(false);
	}
}
