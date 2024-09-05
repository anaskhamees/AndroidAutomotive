import java.applet.Applet;
import java.util.Date;
import java.awt.Graphics;
import java.awt.Font;

public class DateTime extends Applet {
    public void init() {
        Thread myThread = new Thread(() -> {
            while (true) {
                try {
                    repaint();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        myThread.start();
    }

    public void paint(Graphics g) {
        Date myDate = new Date();

        Font bigFont = new Font("SansSerif", Font.BOLD, 40);
        g.setFont(bigFont);

        g.drawString(myDate.toString(), (getWidth() / 2) - 300, getHeight() / 2);
    }
}

