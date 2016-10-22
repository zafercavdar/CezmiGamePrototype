package animations;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;

import game.Game;
import game.GameTakoz;

public class AnimationWindow extends JPanel {
	// overview: an AnimationWindow is an area on the screen in which a
	// bouncing ball animation occurs.  AnimationWindows have two modes:
	// on and off.  During the on mode the ball moves, during the off
	// mode the ball doesn't move.

	private AnimationEventListener eventListener;
	//private BouncingBall ball;
	private Timer timer;
	private boolean mode;
	public static int frameRate = 50;

	public AnimationWindow() {
		// effects: initializes this to be in the off mode.

		super();                    // do the standard JPanel setup stuff
		this.setOpaque(true);
		this.setBackground(Color.BLACK);
		
		//ball = new BouncingBall();

		// this only initializes the timer, we actually start and stop the
		// timer in the setMode() method
		eventListener = new AnimationEventListener(this);
		// The first parameter is how often (in milliseconds) the timer
		// should call us back.  50 milliseconds = 20 frames/second
		timer = new Timer(1000 / frameRate, eventListener);
		mode = false;
	}

	// This is just here so that we can accept the keyboard focus
	public boolean isFocusTraversable() { return true; }

	public void paint(Graphics g) {
		// modifies: <g>
		// effects: Repaints the Graphics area <g>.  Swing will then send the
		//          newly painted g to the screen.

		// first repaint the proper background color (controlled by
		// the windowing system)
		super.paint(g);
		Game.ball.paint(g);
		for (GameTakoz takoz :Game.takozlar){
			takoz.paint(g);
		}
		Game.cezmi.paint(g);
	}

	public void setMode(boolean m) {
		// modifies: this
		// effects: changes the mode to <m>.

		if (mode == true) {
			// we're about to change mode: turn off all the old listeners
			removeMouseListener(eventListener);
			removeMouseMotionListener(eventListener);
			removeKeyListener(eventListener);
		}

		mode = m;

		if (mode == true) {
			// the mode is true: turn on the listeners
			addMouseListener(eventListener);
			addMouseMotionListener(eventListener);
			addKeyListener(eventListener);
			requestFocus();           // make sure keyboard is directed to us
			timer.start();
		}
		else {
			timer.stop();
		}
	}
}
