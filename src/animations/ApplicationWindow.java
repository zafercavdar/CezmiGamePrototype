package animations;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import game.Game;
import parser.XMLFileChooser;
import parser.XMLParser;
import parser.XMLSaver;

public class ApplicationWindow extends JFrame {
	// overview: An ApplicationWindow is a top level program window that
	// contains a toolbar and an animation window.

	public static int screenSize = 600;

	protected AnimationWindow animationWindow;

	public ApplicationWindow() {
		// effects: Initializes the application window so that it contains
		//          a toolbar and an animation window.

		// Title bar
		super("CezmiGame Prototype Program");
		this.setResizable(false);

		// respond to the window system asking us to quit
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		//Create the toolbar.
		JToolBar toolBar = new JToolBar();
		addButtons(toolBar);

		//Create the animation area used for output.
		animationWindow = new AnimationWindow();
		// Put it in a scrollPane, (this makes a border)
		JScrollPane scrollPane = new JScrollPane(animationWindow);

		//Lay out the content pane.
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setPreferredSize(new Dimension(screenSize, screenSize+32));

		contentPane.add(toolBar, BorderLayout.NORTH);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		setContentPane(contentPane);
	}

	JButton run = null;
	JButton stop = null;
	JButton load = null;
	JButton save = null;
	JButton edit = null;
	JButton quit = null;

	protected void addButtons(JToolBar toolBar) {
		// modifies: toolBar
		// effects: adds Run, Stop and Quit buttons to toolBar

		run = new JButton("Run");
		run.setToolTipText("Start the animation");
		// when this button is pushed it calls animationWindow.setMode(true)
		run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				animationWindow.setMode(true);
				run.setEnabled(false);
				stop.setEnabled(true);
			}
		});
		toolBar.add(run);

		stop = new JButton("Stop");
		stop.setToolTipText("Stop the animation");
		stop.setEnabled(false);
		// when this button is pushed it calls animationWindow.setMode(false)
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				animationWindow.setMode(false);
				stop.setEnabled(false);
				run.setEnabled(true);
			}
		});
		toolBar.add(stop);

		load = new JButton("Load XML");
		load.setToolTipText("Load XML File");
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				boolean valid = false;

				while (!valid){
					// User selects new file
					final XMLFileChooser fc = new XMLFileChooser();
					fc.showOpenDialog(null);

					String loc = fc.getSelectedFile().getAbsolutePath();
					System.out.println(loc);

					//String loc = "src\\xmls\\CezmiPrototype3.xml";
					XMLParser parser = new XMLParser(loc);
					valid = parser.validateXMLFile();

					if (valid){
						boolean secondCheck = parser.parseXMLFile();
						if (!secondCheck){
							valid = false;
						} else {
							animationWindow.setEditMode(false);
							animationWindow.setMode(false);
							run.setEnabled(true);
							animationWindow.repaint();
						}
					}
				}
			}
		});
		toolBar.add(load);

		save = new JButton("Save as XML");
		save.setToolTipText("Save as XML File");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				XMLSaver xmlSaver = new XMLSaver();
				xmlSaver.save();
				load.setEnabled(true);
				edit.setText("Edit Mode");
				animationWindow.setEditMode(false);
			}
		});
		toolBar.add(save);

		edit = new JButton("Edit Mode");
		edit.setToolTipText("Opens creative Edit Mode");
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (edit.getText() != "Cancel"){
					edit.setText("Cancel");
					animationWindow.setMode(false);
					Game.takozlar.clear();
					Game.cezmi = null;
					Game.ball = null;
					run.setEnabled(false);
					stop.setEnabled(false);
					load.setEnabled(false);
					
					animationWindow.repaint();
					animationWindow.setEditMode(true);
				} else {
					edit.setText("Edit Mode");
					load.setEnabled(true);
					animationWindow.setEditMode(false);
				}
			}
		});
		toolBar.add(edit);

		quit = new JButton("Quit");
		quit.setToolTipText("Quit the program");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		toolBar.add(quit);

	}
}

