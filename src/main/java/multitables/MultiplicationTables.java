package multitables;

import java.math.*;
import java.util.*;
import java.io.*;
import java.util.regex.*;

public class MultiplicationTables {

	final private BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	// use Scanner instead ??

	final private Map<String, Integer> timesTable = new HashMap<>();


	/**
	 *
	 */
	public void run() throws IOException {
		System.out.print("Choose a Times table to practice [1 - 12]: ");

		String number = br.readLine();
		if ( !isValid(number) ) {
			System.out.println("ERROR: " + number + " is not valid - exiting...");
			System.exit(-1);
		}
		
		// Build times table after they choose which one to practice, 
		// e.g. 3's from 3*1 thru 3*12
		initTable(Integer.parseInt(number));
		loop();

	}

	/**
	 *
	 */
	private void initTable(int n) {
		int ans = 0;

		for (int i=1; i<=12; i++) {
			ans = n * i;
			String s = n + " x " + i;
			timesTable.put(s, new Integer(ans));
		}
	}


	private boolean isValid(String input) {
		return input.matches("[0-9]+[0-9]*");
	}

	/**
	 * Loops through the input selection and randomly selects problems in the 
	 * range being tested
	 */
	private void loop() throws IOException {
		String resp = "";
		int cnt = 0;
		int correct = 0;
		int ans = 0;

		// Randomly select an entry from the Times Table. 

		for (Map.Entry<String, Integer> entry : timesTable.entrySet()) {
			
			boolean good = false;

			 do {
			
				System.out.printf("Multiply %s : ", entry.getKey());
				ans = entry.getValue().intValue();

				// Read the input from the kbrd. 
				resp = br.readLine().trim();	
				good = isValid(resp);				
			} while (!good);

			// Check if the answer matches the correct one in the table. 
			if (Integer.parseInt(resp) == ans) {
				System.out.println(resp + " is Correct!");
				correct++;
			} else {
				System.out.println(resp + " is incorrect; the correct answer is " + ans);
			}

			// Log the result. 
			cnt++; 
		}

		System.out.println("");
		System.out.println("You got " + correct + " answers correct out of " + cnt + " questions"); 
		int score = Math.round(((float)correct / (float)cnt) * 100);
		System.out.println("Score: " + score);
	}

	/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
	// 
	public static void main(String[] args) {
		try {
			new MultiplicationTables().run();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}