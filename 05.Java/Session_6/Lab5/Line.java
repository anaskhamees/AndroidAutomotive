import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class Line extends Applet {


    private int x1, y1, x2, y2;


    public void init() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                x1 = event.getX(); 
                y1 = event.getY(); 

            }
        });


        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent event) {
                x2 = event.getX(); 
                y2 = event.getY(); 
                repaint(); 
            }
        });
    }

    public void paint(Graphics g) {

            g.drawLine(x1, y1, x2, y2); 

    }
}

