import java.applet.Applet;
import java.util.Date;
import java.awt.Graphics;
import java.awt.Font;


public class DateTime extends Applet implements Runnable
{
	public void init()
	{
		Thread Mythread =new Thread(this);
		Mythread.start();
	
	}
	public void paint(Graphics g)
	{
		Date myDate=new Date();
		
		Font bigFont = new Font("SansSerif", Font.BOLD, 40); // You can 
        g.setFont(bigFont);
	
		g.drawString(myDate.toString(),(getWidth()/2)-300,getHeight()/2);
	
	}

public void run()
{
	while(true)
	{
		try{
			repaint();
			Thread.sleep(1000);
		}catch (InterruptedException E ){E.printStackTrace();}
	
	}

}



}
