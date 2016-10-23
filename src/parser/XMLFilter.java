package parser;

import java.io.File;

//This filter contains some code from:
//http://stackoverflow.com/questions/1246857/using-a-filedialog-to-choose-a-file-of-a-certain-extension

public class XMLFilter extends javax.swing.filechooser.FileFilter {
	public boolean accept(File file) {
		String filename = file.getName();
		return filename.endsWith(".xml");
	}
	public String getDescription() {
		return "*.xml";
	}
}
