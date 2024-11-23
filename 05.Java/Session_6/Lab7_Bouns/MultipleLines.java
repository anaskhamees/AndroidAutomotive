import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class MultipleLines extends Applet {

    class Line {
        public int x1, y1, x2, y2;

        Line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    private ArrayList<Line> lines = new ArrayList<>();
    private int currentX, currentY, endX, endY;
    private boolean dragging = false;

    public void init() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
                currentX = event.getX();
                currentY = event.getY();
                dragging = true;  
            }

            public void mouseReleased(MouseEvent event) {
                if (dragging) {
                    endX = event.getX();
                    endY = event.getY();
                    lines.add(new Line(currentX, currentY, endX, endY));  
                    dragging = false;  
                    repaint();  
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent event) {
                if (dragging) {
                    endX = event.getX();
                    endY = event.getY();
                    repaint();  
                }
            }
        });
    }

    public void paint(Graphics lineObject) {
        for (Line line : lines) {
            lineObject.drawLine(line.x1, line.y1, line.x2, line.y2);
        }

        if (dragging) {
            lineObject.drawLine(currentX, currentY, endX, endY);
        }
    }
}

