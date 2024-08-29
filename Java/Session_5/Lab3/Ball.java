import java.applet.Applet;
import java.awt.Graphics;


public class Ball extends Applet implements Runnable
{

	
	public void init()
	{
		int x=0;
		int y=(getHeight()/2);
		boolean DirectionX=true;
		boolean DirectionY=true;
		Thread Mythread =new Thread(this);
		Mythread.start();
	
	}
	public void paint(Graphics G)
	{

			G.fillOval(x, y, 20, 20);
				
        	if(DirectionX==true)
        	{
        		x+=2;
        		if(x>=getWidth())
        		{
        			DirectionX=false;
        		}
        		
        			if(DirectionY==true)
        			{
        				y+=2;
        				if(y>=getHeight())
        				{
        					DirectionY=false;
        				}
        			}
        			else
        			{
        				y-=2;
        				if(y<=0)
        				{
        					DirectionY=true;
        				}
        				
        			}
        	
        	}
        	else
        	{
        		x-=2;
        		if(x<=0)
        		{
        			DirectionX=true;
        		}
        			if(DirectionY==true)
        			{
        				y+=2;
        				if(y>=getHeight())
        				{
        					DirectionY=false;
        				}
        			}
        			else
        			{
        				y-=2;
        				if(y<=0)
        				{
        					DirectionY=true;
        				}
        				
        			}
        	
        	}
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
