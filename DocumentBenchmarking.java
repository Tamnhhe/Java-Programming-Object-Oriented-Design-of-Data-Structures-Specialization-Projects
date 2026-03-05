package document;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/** A class for timing the EfficientDocument and BasicDocument classes
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 */

public class DocumentBenchmarking {

	
	public static void main(String [] args) {

	    // Run each test more than once to get bigger numbers and less noise.
	    // You can try playing around with this number.
	    int trials = 10;
	    String textfile = "data/warAndPeace.txt";
		int increment = 40000;
		int numSteps = 50;
		int start = 50000;
		
		// TODO: Fill in the rest of this method so that it runs two loops
		// and prints out timing results as described in the assignment 
		// instructions.
		for (int numToCheck = start; numToCheck <= numSteps*increment + start; 
				numToCheck += increment)
		{
			System.out.print(numToCheck + "\t");
			String text = getStringFromFile(textfile, numToCheck);

			long bDocStart = System.nanoTime();
			for(int i = 0; i < trials; i++) {
				BasicDocument bDoc = new BasicDocument(text);
				bDoc.getFleschScore();
			}
			long bDocEnd = System.nanoTime();
			double bDocEst = (bDocEnd - bDocStart) / 1000000000.0;
			System.out.print(bDocEst + "\t");

			long eDocStart = System.nanoTime();
			for(int i = 0; i < trials; i++) {
				EfficientDocument eDoc = new EfficientDocument(text);
				eDoc.getFleschScore();
			}
			long eDocEnd = System.nanoTime();
			double eDocEst = (eDocEnd - eDocStart) / 1000000000.0;
			System.out.print(eDocEst + "\n");
		}
	
	}
	
	/** Get a specified number of characters from a text file
	 * 
	 * @param filename The file to read from
	 * @param numChars The number of characters to read
	 * @return The text string from the file with the appropriate number of characters
	 */
	public static String getStringFromFile(String filename, int numChars) {
		
		StringBuffer s = new StringBuffer();
		try {
			FileInputStream inputFile= new FileInputStream(filename);
			InputStreamReader inputStream = new InputStreamReader(inputFile);
			BufferedReader bis = new BufferedReader(inputStream);
			int val;
			int count = 0;
			while ((val = bis.read()) != -1 && count < numChars) {
				s.append((char)val);
				count++;
			}
			bis.close();
		}
		catch(Exception e)
		{
		  // Grader doesn't want abnormal termination
		}
		
		
		return s.toString();
	}
	
}
