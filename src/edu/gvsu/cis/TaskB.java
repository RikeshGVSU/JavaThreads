package edu.gvsu.cis;

import java.io.FileWriter;
import java.io.IOException;

public class TaskB implements Runnable {

	private Counter cnt;
	
	public TaskB(Counter cnt) {
		this.cnt = cnt;
	}

	@Override
	public void run() {
		for(int i=0; i<1000000; i++) {
			cnt.safeIncrement();
		}

	}
	/**
	 * Function to append the output to a CSV file
	 * @param numberOfThreads The number of threads used to execute the instructions.
	 * @param counterValue The final value of the counter.
	 * @param executionTime The total time to complete the execution
	 * @throws IOException
	 */
	public static void addToCsv(int numberOfThreads, int counterValue, long executionTime) throws IOException {
		int avgCountPerThread = counterValue/numberOfThreads;
		long avgTime = executionTime/numberOfThreads;
		FileWriter fileWriter = new FileWriter("/Users/rikeshpuri/Desktop/javafiles/workspace/LearningThreading/outputSync.csv", true);
		fileWriter.append(numberOfThreads + ",");
		fileWriter.append(counterValue + ",");
		fileWriter.append(executionTime + ",");
		fileWriter.append(avgCountPerThread + ",");
		fileWriter.append(avgTime + ",");
		fileWriter.append("\n");
		fileWriter.flush();
		fileWriter.close();
	}

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		int numberOfThreads = 32;
		for (int k = 0; k < 20; k++) {
			long startTime = System.nanoTime();
			Counter cnt = new Counter();
			Thread[] threads = new Thread[numberOfThreads];
			for (int i = 0; i < threads.length; i++) {
				threads[i] = new Thread(new TaskB(cnt));
				threads[i].start();
			}
			for (Thread thread : threads) {
				try {
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Threads = " + numberOfThreads);
			int counterValue = cnt.getCounter();
			System.out.println("Final Counter = " + counterValue);
			long endTime = System.nanoTime();
			long totalTime = (endTime - startTime);
			System.out.println("Execution Time: " + totalTime);
			addToCsv(numberOfThreads, counterValue, totalTime);
		}
		
	}

}
