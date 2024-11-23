import java.awt.*;
import java.applet.Applet;
public class FontsApplet extends Applet
{
	String [] fontNames;
	
	public void init()
	{
		/* Get The Local Graphics Environment */
		GraphicsEnvironment GE=GraphicsEnvironment.getLocalGraphicsEnvironment();
		/* retrieve the array of available fonts*/
		fontNames=GE.getAvailableFontFamilyNames();	
	}

	public void paint(Graphics G)
	{
		/* Set the starting coordinates for drawing */
		int x=20;
		int y=20; 
		/* */

		for(String fontName: fontNames)
		{
			G.setFont(new Font(fontName, Font.PLAIN, 14));
			G.drawString(fontName,x,y);
			y+=20;
		}
	}
}
