/*
 * File:	FCFS.java
 * Course: 	Operating Systems
 * Code: 	1DV512
 * Author: 	Suejb Memeti
 * Date: 	November, 2018
 *
 * Fucked up by: 	David Carlsson
 * Date of fubar: 	2018-11-11
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class FCFS{

	// The list of processes to be scheduled
	//A public arrayList instead of a private with an FCFS.add(), am not allowed to fix this.
	public ArrayList<Process> processes;

	final String newLine = System.getProperty("line.separator");
	private int totalRunningTime;
	private boolean endlessLoop = false;
	// Class constructor
	public FCFS(ArrayList<Process> processes) {
		this.processes = processes;
	}

	/**
	 *
	 * @throws UnsupportedOperationException If anyone tinkers with processes after construction.
	 */
	public void run() throws UnsupportedOperationException{
		this.processes = sortProcessesAccordingToArrivalTimes(processes);
		this.totalRunningTime = 0;
		int sizeOfOriginalArray = this.processes.size();

		for (Process process: processes){
			/*
			Since you can not recive tasks with a arrivaltime earlier than the existing i start by executeing these.
			*/
			if (this.processes.size()!=sizeOfOriginalArray){
				//This case is not given any description, so ignoring.
				throw new UnsupportedOperationException("You stupid twat, this is extremly unclear if we " +
						"are expected to handle this. Do not change the ArrayList after construction!");
			}
			if (process.getArrivalTime() > this.totalRunningTime){
				totalRunningTime = process.getArrivalTime();
			}
			process.setCompletedTime(this.totalRunningTime+process.getBurstTime());

			this.totalRunningTime = process.getCompletedTime();
		}
		while (endlessLoop){
			if (this.processes.size() != sizeOfOriginalArray){
				doTwatySortAndAcceptArrivalTimesGreaterThanTotalRunningTime();
			}
		}
	}

	/**
	 *
	 * @throws UnsupportedOperationException It is not implemented
	 */
	private void doTwatySortAndAcceptArrivalTimesGreaterThanTotalRunningTime() throws UnsupportedOperationException{
		//TODO Make less twaty
		throw new UnsupportedOperationException("Not implemented");
	}

	public void printTable() {
		// TODO Print the list of processes in form of a table here

	}

	public void printGanttChart() {
		// TODO Print the demonstration of the scheduling algorithm using Gantt Chart
		StringBuilder stringTop = new StringBuilder();
		stringTop.append("%%%%%%%%%%%%%%%% GANTT CHART %%%%%%%%%%%%%%%%"+newLine);
		StringBuilder stringBody = new StringBuilder();
		StringBuilder stringHeadAndFoot = new StringBuilder();

//DATA
		int CT = 0;
		for(Process process: processes) {
			stringBody.append('|');
			if (process.getArrivalTime() > CT){
				for (int i = 0; i<process.getArrivalTime()-CT;i++){
					stringBody.append('¶');
				}
				stringBody.append('|');
			}
			int mid = CT + (process.getCompletedTime()-CT-2)/2;
			/*for (int i = 1; i < mid-1; i++) {
				stringBody.append(' ');
			}*/
			stringBody.append(String.format("%"+(process.getCompletedTime())+"S%d","P",process.getProcessId()));
			//stringBody.append("P"+process.getProcessId());

			CT = process.getCompletedTime();
			stringBody.append("|");

		}
		stringBody.append(newLine);
		for(int i = 0; i<stringBody.length()-1;i++){
			stringHeadAndFoot.append('=');
		}
		stringTop.append(newLine).append(stringHeadAndFoot).append(newLine).append(stringBody)
				.append(stringHeadAndFoot);

		//Index timestamps
		stringBody= new StringBuilder();

		CT = 0;
		stringBody.append(newLine);
		stringBody.append(CT);
		for (Process process:processes){
			if (process.getArrivalTime() > CT) {
				for (int i = 0; i <(process.getArrivalTime()-CT); i++) {
					int offset = 3+process.getArrivalTime()-CT;
					stringBody.append(String.format("%"+offset+"d",process.getArrivalTime()));
				}
				stringBody.append(process.getArrivalTime());
			}

			for(int i= 0; i<process.getCompletedTime()-1;i++){
				stringBody.append(' ');
			}
			CT = process.getCompletedTime();
			stringBody.append(CT);
		}

		stringBody.append(newLine+newLine);
		stringBody.append("¶ - indicates CPU idle time");

stringTop.append(stringBody);


		System.out.println(stringTop);

	}
	private ArrayList<Process> sortProcessesAccordingToArrivalTimes(ArrayList<Process> temp_processes) {
		Collections.sort(temp_processes, new Comparator<Process>() {
			@Override
			public int compare(Process first, Process second) {
				return first.getArrivalTime() - second.getArrivalTime();
			}
		});
		return temp_processes;
	}
}
