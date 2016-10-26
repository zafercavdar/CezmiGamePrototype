package parser;

import java.io.File;

// This filter contains some code from:
// http://stackoverflow.com/questions/1246857/using-a-filedialog-to-choose-a-file-of-a-certain-extension
// The link is used to understand how to extend a FileFilter and filter the extensions. I inserted one of the code snippet
// from the stackoverflow.com as mentioned in the forum answers. But Java documentation also contains getDescription and accept
// method's descriptions.

public class XMLFilter extends javax.swing.filechooser.FileFilter {
	public boolean accept(File file) {
		String filename = file.getName();
		return filename.endsWith(".xml");
	}
	public String getDescription() {
		return "*.xml";
	}
}
