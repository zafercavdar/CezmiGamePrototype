package animations;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class AnimationEventListener extends MouseAdapter
implements MouseMotionListener, KeyListener, ActionListener
{
  // overview: AnimationEventListener is an inner class that
  // responds to all sorts of external events, and provides the
  // required semantic operations for our particular program.  It
  // owns, and sends semantic actions to the ball and window of the
  // outer class

  // MouseAdapter gives us empty methods for the MouseListener
  // interface: mouseClicked, mouseEntered, mouseExited, mousePressed,
  // and mouseReleased.

  // for this example we only need to override mouseClicked
  public void mouseClicked(MouseEvent e) {
    // modifes: the ball that this listener owns
    // effects: causes the ball to be bumped in a random direction
    ball.randomBump();
  }

  // Here's the MouseMotionListener interface
  public void mouseDragged(MouseEvent e) { }
  public void mouseMoved(MouseEvent e) { }

  // Here's the KeyListener interface
  public void keyPressed(KeyEvent e) {
    // modifes: the ball that this listener owns
    // effects: causes the ball to be bumped in a random direction but
    //          only if one of the keys A-J is pressed.
    int keynum = e.getKeyCode();

    if ((keynum >= 65) && (keynum <= 74)) {
      System.out.println("keypress " + e.getKeyCode());
      ball.randomBump();
    }
  }
  
  public void keyReleased(KeyEvent e) { }
  public void keyTyped(KeyEvent e) { }

  // this is the callback for the timer
  public void actionPerformed(ActionEvent e) {
    // modifes: both the ball and the window that this listener owns
    // effects: causes the ball to move and the window to be updated
    //          to show the new position of the ball.

    Rectangle oldPos = ball.boundingBox();

    ball.move();              // make changes to the logical animation state
    
    Rectangle repaintArea = oldPos.union(ball.boundingBox());

    // Have Swing tell the AnimationWindow to run its paint()
    // method.  One could also call repaint(), but this would
    // repaint the entire window as opposed to only the portion that
    // has changed.
    repaint(repaintArea.x,
            repaintArea.y,
            repaintArea.width,
            repaintArea.height);
  }
}