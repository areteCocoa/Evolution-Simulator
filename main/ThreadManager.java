package main;

public class ThreadManager {
	
	private static Thread[] threads;
	
	public static Thread getThread(String name) {
		updateList();
		for(int count=0; count<threads.length; count++) {
			if(threads[count].getName().equalsIgnoreCase(name)) {
				return threads[count];
			}
		}
		return null;
	}
	
	private static void updateList() {
		if(threads == null) {
			threads = new Thread[Thread.activeCount()];
		}
		Thread.enumerate(threads);
	}
}
