/*
 * File:	Process.java
 * Course: 	Operating Systems
 * Code: 	1DV512
 * Author: 	Suejb Memeti
 * Date: 	November, 2018
 */

public class Process {
	private int processId;
	private int arrivalTime;
	private int burstTime;
	private int completedTime;
	private int turnaroundTime;
	private int waitingTime;

	public Process(int processId, int arrivalTime, int burstTime) {
		this.processId = processId;
		this.arrivalTime = arrivalTime;
		this.burstTime = burstTime;
	}

	/**
	 * Sets completed time, but also waiting time and turnaround time.
	 * Decreases unnesecarry confusion and errors.
	 * @param completedTime
	 */
	public void setCompletedTime(int completedTime) {
		this.completedTime = completedTime;
		this.turnaroundTime = this.completedTime - this.arrivalTime;
		this.waitingTime = this.turnaroundTime-this.burstTime;
	}

	/**
	 * Maybe use this.
	 * @param waitingTime
	 */
	private void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public int getArrivalTime(){
		return arrivalTime;
	}

	public int getBurstTime(){
		return burstTime;
	}

	public int getCompletedTime(){
		return completedTime;
	}

	public int getTurnaroundTime(){
		return turnaroundTime;
	}

	public int getWaitingTime(){
		return waitingTime;
	}

	public int getProcessId(){
		return processId;
	}
}
