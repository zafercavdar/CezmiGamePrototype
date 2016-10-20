package animations;

import java.awt.BorderLayout;
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

public class ApplicationWindow extends JFrame {
	  // overview: An ApplicationWindow is a top level program window that
	  // contains a toolbar and an animation window.

	  protected AnimationWindow animationWindow;

	  public ApplicationWindow() {
	    // effects: Initializes the application window so that it contains
	    //          a toolbar and an animation window.

	    // Title bar
	    super("Swing Demonstration Program");

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
	    contentPane.setPreferredSize(new Dimension(510, 530));
	    contentPane.add(toolBar, BorderLayout.NORTH);
	    contentPane.add(scrollPane, BorderLayout.CENTER);
	    setContentPane(contentPane);
	  }

	  protected void addButtons(JToolBar toolBar) {
	    // modifies: toolBar
	    // effects: adds Run, Stop and Quit buttons to toolBar

	    JButton button = null;

	    button = new JButton("Run");
	    button.setToolTipText("Start the animation");
	    // when this button is pushed it calls animationWindow.setMode(true)
	    button.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        animationWindow.setMode(true);
	      }
	    });
	    toolBar.add(button);

	    button = new JButton("Stop");
	    button.setToolTipText("Stop the animation");
	    // when this button is pushed it calls animationWindow.setMode(false)
	    button.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        animationWindow.setMode(false);
	      }
	    });
	    toolBar.add(button);

	    button = new JButton("Quit");
	    button.setToolTipText("Quit the program");
	    button.addActionListener(new ActionListener() {
	      public void actionPerformed(ActionEvent e) {
	        System.exit(0);
	      }
	    });
	    toolBar.add(button);
	  }
	}

