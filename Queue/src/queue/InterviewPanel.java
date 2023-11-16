package queue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class InterviewTimer extends Thread {
	private Queue<CandidateDetails> interviewQueue;
	private ArrayList<String> interviewDetails;

	public InterviewTimer(Queue<CandidateDetails> interviewQueue, ArrayList<String> interviewDetails) {
		this.interviewQueue = interviewQueue;
		this.interviewDetails = interviewDetails;
	}

	@Override
	public void run() {
		while (true) {
			try {
				sleep(30000); // Sleep for 30 seconds

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (!interviewQueue.isEmpty()) {
				CandidateDetails nextCandidate = interviewQueue.poll();
				interviewDetails.add(nextCandidate.getName());
				System.out.println("Interview Completed..." + nextCandidate.getName() + " Moving  the next candidate.");

			}
		}
	}
}

public class InterviewPanel {

	private Queue<CandidateDetails> interviewQueue = new LinkedList<>();
	private ArrayList<String> interviewDetails = new ArrayList<>();

	public static void main(String[] args) {
		InterviewPanel interviewPanel = new InterviewPanel();
		interviewPanel.start();
	}

	public void start() {
		Scanner scan = new Scanner(System.in);

		InterviewTimer interviewTimer = new InterviewTimer(interviewQueue, interviewDetails);
		interviewTimer.start();

		while (true) {

			System.out.println("Select an option:");
			System.out.println("1. Add New candidate ");
			System.out.println("2. Current Interview details");
			System.out.println("3. Remainder Name lists:");
			System.out.println("4. All candidates Names");
			System.out.println("5. Exit");
			System.out.println("----------------------------");

			int choice = scan.nextInt();

			switch (choice) {
			case 1: {
				System.out.println("Enter Candidate Name:");
				String candidateName = scan.next();
//				System.out.println("Enter Candidate college:");
//				String college = scan.next();
				CandidateDetails candidate = new CandidateDetails(candidateName);
				interviewQueue.add(candidate);
				interviewDetails.add(candidateName);
				System.out.println(candidateName + " in interview queue");
				break;
			}
			case 2: {
				if (!interviewQueue.isEmpty()) {
					CandidateDetails nextCandidate = interviewQueue.poll();
					System.out.println("Interviewing: " + nextCandidate.getName());
				} else {
					int n = interviewQueue.size();
					System.out.println(n + " candidates in the interview list.");
				}
				break;
			}
			case 3: {
				if (interviewQueue.isEmpty()) {
					System.out.println("No candidates waiting for interview.");
				} else {
					int count = 1;
					for (CandidateDetails name : interviewQueue) {
						System.out.println(count++ + "--> " + name.getName());
					}
				}
				break;
			}
			case 4: {
				if (interviewDetails.isEmpty()) {
					System.out.println("No candidates have finished interviews.");
				} else {
					int count = 1;
					for (String str : interviewDetails) {
						System.out.println(count++ + "." + str);
					}
				}
				break;
			}

			case 5: {
//				interviewTimer.interrupt(); // Stop the interview timer thread
				System.out.println("Exit...");
				System.exit(0);
			}
			}
		}
	}
}
