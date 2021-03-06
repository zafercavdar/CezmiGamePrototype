package animations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import game.Game;
import game.GameTakoz;

public class AnimationEventListener extends MouseAdapter
implements MouseMotionListener, KeyListener, ActionListener
{
	// overview: AnimationEventListener is a class that
	// responds to all sorts of external events, and provides the
	// required semantic operations for our particular program.  It
	// owns, and sends semantic actions to the ball and window of the
	// outer class

	// MouseAdapter gives us empty methods for the MouseListener
	// interface: mouseClicked, mouseEntered, mouseExited, mousePressed,
	// and mouseReleased.

	private JPanel animationWindows = null;

	public AnimationEventListener(JPanel animationWindows){
		super();
		this.animationWindows = animationWindows;	
	}

	public void mouseClicked(MouseEvent e) {
		int L = ApplicationWindow.screenSize / 20;
		int x = e.getX() / L;
		int y = e.getY() / L;
		
		boolean intersects = false;
		
		for (GameTakoz takoz: Game.takozlar){
			if (takoz.getX() == x && takoz.getY() == y){
				intersects = true;
				Game.takozlar.remove(takoz);
				animationWindows.repaint();
				break;
			}
		}
		
		if (!intersects){
			GameTakoz newTakoz = new GameTakoz(x, y);
			Game.takozlar.add(newTakoz);
			animationWindows.repaint();
		}
		
	}

	// Here's the MouseMotionListener interface
	public void mouseDragged(MouseEvent e) { }
	public void mouseMoved(MouseEvent e) { }

	// Here's the KeyListener interface
	// Controls Cezmi's movements
	
	public void keyPressed(KeyEvent e) {
		
		int keynum = e.getKeyCode();

		if (keynum == 39){
			Game.cezmi.move(1);
		} else if (keynum == 37){
			Game.cezmi.move(0);
		}
	}

	public void keyReleased(KeyEvent e) { }
	public void keyTyped(KeyEvent e) { }

	// this is the callback for the timer
	public void actionPerformed(ActionEvent e) {

		//Rectangle oldPosBall = Game.ball.boundingBox();

		Game.ball.move();              // make changes to the logical animation state
		
		//Rectangle oldPosCezmi = Game.cezmi.prevBoundingbox;
		//Rectangle posCezmi = Game.cezmi.boundingBox();
		
		//Rectangle repaintArea2 = posCezmi.union(oldPosCezmi);
		//Rectangle repaintArea = oldPosBall.union(Game.ball.boundingBox());
		
		//repaintArea = repaintArea.union(repaintArea2);

		// Have Swing tell the AnimationWindow to run its paint()
		// method.  One could also call repaint(), but this would
		// repaint the entire window as opposed to only the portion that
		// has changed.

		//Game.ball.paint();
		animationWindows.repaint();
		/*animationWindows.repaint(repaintArea.x,
				repaintArea.y,
				repaintArea.width,
				repaintArea.height);*/
	}
}