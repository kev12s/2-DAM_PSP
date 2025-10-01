

import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class ThreadClock implements Runnable{

	private JLabel label;
	private int min;
	private int seg;
	private int pause = 1;
	public Thread threadClock;

	public ThreadClock(JLabel label, String name) {
		
		this.label = label;
		initialize();
	}
	 public void start () {
	      System.out.println("Starting " );
	      threadClock = new Thread (this, "Clock");
	      threadClock.start ();
	        
	   }
	public void run() {
		try {			
			while (pause!=3)
			{
				if (pause == 1) {
					Thread.sleep(1000);
					this.increment();
					this.display();
				}
				

			}
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void display() {
		String strSeg = Integer.toString(seg);
		String strMin = Integer.toString(min);
		 
		if (strSeg.length() == 1)
			strSeg = "0" + strSeg;
		if (strMin.length() == 1)
			strMin = "0" + strMin;
		String texto = strMin + ":" + strSeg;
		 
		SwingUtilities.invokeLater(() -> label.setText(texto));
	}

	private void increment() {
		if (pause!=3) {
		seg++;
		if (seg == 60) {
			seg = 0;
			min++;
			if (min == 60) {
				min = 0;
			}
		}
		}
	}

	public void initialize() {
		seg = 0;
		min = 0;
		this.display();
	}

	public void stop() {
		this.pause = 3;
		
	}
}
