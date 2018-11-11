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

public class FCFS{

	// The list of processes to be scheduled
	public ArrayList<Process> processes;
	private int totalRunningTime;

	// Class constructor
	public FCFS(ArrayList<Process> processes) {
		this.totalRunningTime = 0;
		this.processes = sortProcessesAccordingToArrivalTimes(processes);
	}


	public void run() {
		for (Process process: processes){
			/*
			Since there is no add method in this class, just the constructor,
			what you have is what you execute.
			*/
			process.setCompletedTime(this.totalRunningTime+process.getBurstTime());
			this.totalRunningTime = process.getCompletedTime();
		}
	}

	public void printTable() {
		// TODO Print the list of processes in form of a table here
		StringBuilder stringBuilder;
		for(Process process: processes) {
			stringBuilder = new StringBuilder();
			for (int i = 0; i < (process.getBurstTime()/2)-1; i++) {
			stringBuilder.append(" ");
			}
			stringBuilder.append("P"+process.getProcessId());
		}

	}

	public void printGanttChart() {
		// TODO Print the demonstration of the scheduling algorithm using Gantt Chart

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
