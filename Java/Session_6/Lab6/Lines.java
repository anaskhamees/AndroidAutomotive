import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;




public class Lines extends Applet {


class Line
{
    public int x1, y1, x2, y2;
    Line()
    {
    	x1=0;
    	y1=0;
    	x2=0;
    	y2=0;
    }
}

Line [] arrLines=new Line[3];
int lineIdx=0;

    public void init() {
    
    for(int objIdx=0;objIdx<3;objIdx++)
{
	arrLines[objIdx]=new Line();
}

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent event) {
            if(lineIdx<3)
            {
      		arrLines[lineIdx].x1 = event.getX(); 
                arrLines[lineIdx].y1 = event.getY(); 
            }
            
            }
            
            public void mouseReleased (MouseEvent event){
		if (lineIdx<=2)
		{
			lineIdx++;
			repaint();
		}
	}		
        });


        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent event) {
            
            	if(lineIdx<3)
            	{
            		arrLines[lineIdx].x2 = event.getX(); 
                	arrLines[lineIdx].y2 = event.getY(); 
               		repaint(); 

            	}
                            }
        });
    }

    public void paint(Graphics g) {

	for(int lineNum=0;lineNum<3;lineNum++)
	{
		g.drawLine(arrLines[lineNum].x1,arrLines[lineNum].y1,arrLines[lineNum].x2,arrLines[lineNum].y2);
	}
         

    }
}

