import java.applet.Applet;
import java.util.Date;
import java.awt.Graphics;
import java.lang.Thread;
import java.lang.InterruptedException;
import java.awt.Font;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class BallButton extends Applet 
{

	Button StartBtn;
	Button PauseBtn;
	int x=0;
	int y=(getHeight()/2);
	boolean DirectionX=true;
	boolean DirectionY=true;
	int startCount=0;
	Mythread th;
	public void init()
	{
		StartBtn=new Button("Start");
		StartBtn.addActionListener(new StartBtnListener());
		add(StartBtn);
		
		PauseBtn=new Button("Pause");
		PauseBtn.addActionListener(new PauseBtnListener());
		add(PauseBtn);
		
		 th=new Mythread();
		
	}
	
	class StartBtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(startCount==0)
			{th.start();}
			else
			{th.resume();}
			startCount++;
			repaint();	
		}

	}
	

	class PauseBtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			th.suspend();
			repaint();	
		}
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
	
	class Mythread extends Thread

{
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



}




