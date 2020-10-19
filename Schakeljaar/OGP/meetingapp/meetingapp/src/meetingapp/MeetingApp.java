package meetingapp;

import java.util.Scanner;

/**
 * This object will work with time of day in hours and minutes to see a meeting
 * 
 */
public class MeetingApp {

	public static void main(String[] args) {
		try (Scanner scanner = new Scanner(System.in)) {
			scanner.useDelimiter(":|\\s");
			
			System.out.print("When does the meeting start (HH:MM): ");
			int startH = scanner.nextInt();
			int startM = scanner.nextInt();
			TimeOfDay startTime =  TimeOfDay.of(startH, startM);
			
			System.out.print("How long is the meeting in minutes: ");
			int duration = scanner.nextInt();
			TimeOfDay endTime = startTime.add(duration);
			
			System.out.printf("The meeting will end at: %02d:%02d", endTime.getHours(), endTime.getMinutes() );
		}
	}
}
