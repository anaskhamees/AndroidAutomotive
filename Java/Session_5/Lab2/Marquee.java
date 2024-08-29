import java.applet.Applet;
import java.util.Date;
import java.awt.Graphics;

import java.awt.Font;


public class Marquee extends Applet implements Runnable
{

	int x=0;
	int y=0;
	public void init()
	{
		Thread Mythread =new Thread(this);
		Mythread.start();
	
	}
	public void paint(Graphics G)
	{
		
		Font bigFont = new Font("SansSerif", Font.BOLD, 40); // You can 
        G.setFont(bigFont);
	
		G.drawString("Java World",x,getHeight()/2);
		x++;
		if(x==getWidth())
			x=0;
	
	}

public void run()
{
	while(true)
	{
		try{
			repaint();
			Thread.sleep(10);
		}catch (InterruptedException E ){E.printStackTrace();}
	
	}

}



}
