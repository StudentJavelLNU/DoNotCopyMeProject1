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
				//Making a "jump" to arrival time, a jump where the cpu is idle.
				//Nothing to be made about with the FCFS algorithm.
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
		String headAndFoot = "";
		StringBuilder body = new StringBuilder();
		body.append(String.format("%1$3s","PID"));
		body.append(String.format("%1$5s","AT"));
		body.append(String.format("%1$5s","BT"));
		body.append(String.format("%1$5s","CT"));
		body.append(String.format("%1$5s","TAT"));
		body.append(String.format("%1$5s","WT"));

		for (int i = 0; i< body.length();i++){
			headAndFoot += "-";
		}

		for (Process process: processes){
			body.append(newLine);
			body.append(String.format("%1$3s",process.getProcessId()));
			body.append(String.format(" %1$4s",process.getArrivalTime()));
			body.append(String.format(" %1$4s",process.getBurstTime()));
			body.append(String.format(" %1$4s",process.getCompletedTime()));
			body.append(String.format(" %1$4s",process.getTurnaroundTime()));
			body.append(String.format(" %1$4s",process.getWaitingTime()));
		}
		System.out.println(headAndFoot+newLine+body+newLine+headAndFoot);
	}

	public void printGanttChart() {
		StringBuilder stringTop = new StringBuilder();
		stringTop.append("%%%%%%%%%%%%%%%% GANTT CHART %%%%%%%%%%%%%%%%");
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
			stringBody.append(String.format("%"+(process.getBurstTime())+"S%d","P",process.getProcessId()));

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
				int offset = 1+(process.getArrivalTime()-CT);
				stringBody.append(String.format("%"+offset+"d",process.getArrivalTime()));
			}
			int offset = 3+ process.getBurstTime();
			stringBody.append(String.format("%"+offset+"d",process.getCompletedTime()));
			CT = process.getCompletedTime();
		}

		stringBody.append(newLine+newLine);
		stringBody.append("¶ - indicates CPU idle time"+newLine);

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
