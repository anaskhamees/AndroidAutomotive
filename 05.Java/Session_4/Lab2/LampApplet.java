import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;

public class LampApplet extends Applet {

    
    public void paint(Graphics graphic) {
        


        
        graphic.fillOval(100, 50, 200, 50);

        
        graphic.setColor(Color.RED);
        graphic.drawLine(100, 75, 75, 200);  
        graphic.drawLine(300, 75, 325, 200); 
        graphic.drawArc(75, 170, 250, 60, 0, -180); 

        
        graphic.setColor(Color.GREEN);
        graphic.fillOval(170, 100, 60, 100); 
        graphic.fillOval(110, 110, 40, 80);  
        graphic.fillOval(250, 110, 40, 80);  

        
        graphic.setColor(Color.BLACK);
        graphic.drawOval(170, 100, 60, 100); 
        graphic.drawOval(110, 110, 40, 80);  
        graphic.drawOval(250, 110, 40, 80);  

        
        graphic.drawLine(190, 230, 170, 320); 
        graphic.drawLine(210, 230, 230, 320); 

        
        graphic.drawRect(130, 320, 140, 20);
    }
}
