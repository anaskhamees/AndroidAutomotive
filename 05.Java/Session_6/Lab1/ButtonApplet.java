import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ButtonApplet extends Applet 
{
	private int counter;
	Button IncBtn;
	Button DecBtn;

	public void init()
	{
		IncBtn=new Button("+");
		IncBtn.addActionListener(new IncBtnListener());
		add(IncBtn);
		
		DecBtn=new Button("-");
		
		DecBtn.addActionListener(new ActionListener(){
		
		public void actionPerformed(ActionEvent e)
		  { 
			counter--;
			repaint();	
		  } 
		}
		);
		
		add(DecBtn);
	}
	
	public void paint(Graphics g)
	{
		g.drawString("Count Value: "+counter,50,200);
	
	}

	class IncBtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			counter++;
			repaint();	
		}

	}
	
} 
