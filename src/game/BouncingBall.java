package game;
import java.awt.*;

public class BouncingBall {
  // Overview: A BouncingBall is a mutable data type.  It simulates a
  // rubber ball bouncing inside a two dimensional box.  It also
  // provides methods that are useful for creating animations of the
  // ball as it moves.

  private int x = (int)((Math.random() * 100.0) + 100.0);
  private int y = (int)((Math.random() * 100.0) + 100.0);
  private int vx = /*(int)((Math.random() * 10.0) + 10.0);*/ 5;
  private int vy = /* (int)((Math.random() * 10.0) + 10.0); */ 5;
  private int radius = 6;
  private Color color = new Color(255, 0, 0);

  public void move() {
    // modifies: this
    // effects: Move the ball according to its velocity.  Reflections off
    // walls cause the ball to change direction.
    x += vx;
    if (x <= radius) { x = radius; vx = -vx; }
    if (x >= 600-radius) { x = 600-radius; vx = -vx; }
 
    y += vy;
    if (y <= radius) { y = radius; vy = -vy; }
    if (y >= 600-radius) { y = 600-radius; vy = -vy; }
    
  }

  public void randomBump() {
    // modifies: this
    // effects: Changes the velocity of the ball by a random amount
    vx += (int)((Math.random() * 10.0) - 5.0);
    vx = -vx;
    vy += (int)((Math.random() * 10.0) - 5.0);
    vy = -vy;
  }

  public void paint(Graphics g) {
    // modifies: the Graphics object <g>.
    // effects: paints a circle on <g> reflecting the current position
    // of the ball.

    // the "clip rectangle" is the area of the screen that needs to be
    // modified
    Rectangle clipRect = g.getClipBounds();

    // For this tiny program, testing whether we need to redraw is
    // kind of silly.  But when there are lots of objects all over the
    // screen this is a very important performance optimization
    if (clipRect.intersects(this.boundingBox())) {
      g.setColor(color);
      g.fillOval(x-radius, y-radius, radius+radius, radius+radius);
    }
  }

  public Rectangle boundingBox() {
    // effect: Returns the smallest rectangle that completely covers the
    //         current position of the ball.

    // a Rectangle is the x,y for the upper left corner and then the
    // width and height
    return new Rectangle(x-radius, y-radius, radius+radius+1, radius+radius+1);
  }
}

 
  // Note the very indirect way control flow works during an animation:
  //
  // (1) We set up an eventListener with a reference to the animationWindow.
  // (2) We set up a timer with a reference to the eventListener.
  // (3) We call timer.start().
  // (4) Every 20 milliseconds the timer calls eventListener.actionPerformed()
  // (5) eventListener.actionPerformed() modifies the logical
  //     datastructure (e.g. changes the coordinates of the ball).
  // (6) eventListener.actionPerformed() calls myWindow.repaint.
  // (7) Swing schedules, at some point in the future, a call to 
  //      myWindow.paint()
  // (8) myWindow.paint() tells various objects to paint
  //     themselves on the provided Graphics context.
  //
  // This may seem very complicated, but it makes the coordination of
  // all the various different kinds of user input much easier.  For
  // example here is how control flow works when the user presses the
  // mouse button:
  //
  // (1) We set up an eventListener (actually we just use the same
  //     eventListener that is being used by the timer.)
  // (2) We register the eventListener with the window using the
  //     addMouseListener() method.
  // (3) Every time the mouse button is pressed inside the window the
  //     window calls eventListener.mouseClicked().
  // (4) eventListener.mouseClicked() modifies the logical
  //     datastructures.  (In this example it calls ball.randomBump(), but
  //     in other programs it might do something else, including request a
  //     repaint operation).
  //