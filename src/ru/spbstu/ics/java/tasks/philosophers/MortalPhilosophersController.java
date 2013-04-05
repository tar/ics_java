package ru.spbstu.ics.java.tasks.philosophers;

/**
 * 
 * This class is the Runnable interface implementation for the thread 
 * that will control all <code>MortalPhilosopher</code>s and kill those 
 * who haven't a meal for <code>MAX_TIME_WITHOUT_MEAL</code> ms.
 * 
 * @see MortalPhilosopher
 * @author Oleg Rekin
 *
 */
public class MortalPhilosophersController implements Runnable {

	public static final int SLEEP_TIME = 100;
	public static final int MAX_TIME_WITHOUT_MEAL = 3000;
	
	private MortalPhilosopher[] _philosophers;
	
	private MortalPhilosophersController() { }
	
	public MortalPhilosophersController(MortalPhilosopher[] philosophers) {
		_philosophers = philosophers;
	}
	@Override
	public void run() {
		long systime = 0;
		System.out.println("Controller running...");
		while (!Thread.interrupted()) {
			for (int i = 0; i < _philosophers.length; i++) {
				if (_philosophers[i].isAlive()) {
					systime = System.currentTimeMillis();
					if ((systime -_philosophers[i].get_lastMealTime()) > 
							MAX_TIME_WITHOUT_MEAL) {
						_philosophers[i].interrupt();
					}
				}
			}
			try {
				Thread.sleep(SLEEP_TIME);
			} catch (InterruptedException e) {
				System.out.println("Controller thread was interrupted");
				//e.printStackTrace();
				break;
			}
		}
	}
	
}
