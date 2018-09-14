package com.jobready.threading;

public class Application {

	public static void main(String[] args) {
		
		
//		Task thread1 = new Task();
//		thread1.start();
//		
//		Task thread2 = new Task();
//		thread2.start();
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				for (int i = 0; i < 1000; i++) {
					System.out.println(Thread.currentThread().getName() + "-" + i);
				} 
				
			}
			
		});
		t1.start();

	}

}

class Task extends Thread {
	String threadName;
	
	public Task() {
		this.threadName = Thread.currentThread().getName();
	}
	
	public Task(String threadName) {
		this.threadName = threadName;
	}
	public void run() {
		Thread.currentThread().setName(this.threadName);
		for (int i = 0; i < 1000; i++) {
			System.out.println(Thread.currentThread().getName() + "-" + i);
		} 
	}
}
