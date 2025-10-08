/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilosreloj;



 import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.Color;
import java.util.Random;
/**
 *
 * @author 2dami
 */
public class ThreadChangeColor implements Runnable {

    
    private JLabel labelIzq;
    private JLabel labelDer;
    private volatile boolean running = true;
    public Thread thread;
    
    private Random random;
    
    public ThreadChangeColor(JLabel labelIzq, JLabel labelDer) {
        this.labelIzq = labelIzq;
        this.labelDer = labelDer;
    }
    
   public void start() {
        if (thread == null || !thread.isAlive()) {
            running = true;
            thread = new Thread(this, "ColorThread");
            thread.start();
        }
    }
    public void stop() {
        running = false;
        if (thread != null) {
            thread.interrupt();
        }
    }
    
  @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                
            }
            Color c1 = new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));
            Color c2 = new Color((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256));

           
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    labelIzq.setForeground(c1);
                    labelDer.setForeground(c2);
                }
            });
        }
    }
}
