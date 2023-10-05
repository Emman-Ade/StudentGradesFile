import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
/**FileTestPrac.java
 * Created on: Oct 5, 2023
 * Last Modified on: Oct 5, 2023
 * Name: Emmanuel Adeniyi
 * Purpose: Display an understanding of the file system in java, and applying useful ideas such as changing all of the students averages
 * Description: Taking a file with students grades and creating a file with their new curved grades
 */
public class FileTestPrac {

	//throwing a FileNotFoundException to see if a file exists at all
	public static void main(String[] args) throws FileNotFoundException {

		//creating our file using the correct directory we put it in
		File file = new File("scores2.txt");

		//creating output file with new name
		File outputFile = new File("curvedscores.txt");

		//using try with resources to declare all resources that can be closed executing file code to see whether it exists
		try(


				//creating our PrintWriter with that takes our outputFile
				PrintWriter output = new PrintWriter(outputFile);

				//creating scanner object that takes our original file and reads it
				Scanner scan = new Scanner(file);
				){

			//reading every line
			while(scan.hasNextLine()) {

				//using try to test whether this line of code has the right number format
				try {


					//String with all lines of the file
					String a = scan.nextLine();


					//getting rid of the String characters in the file 
					String[] parts = a.split("\\s+");

					//creating a StringBuilder that takes an Array of characters that we have in our file
					StringBuilder num = new StringBuilder();

					//initializing the sum and count to calculate our curved average
					double sum = 0;

					int count = 0;

					//for loop to iterate all of our values in the file and add them all up into to sum and count all the variables we have with count
					for (String part : parts) {

						//Once again we will test lines of code with the try block to see if we have a NumberFormatException using the catch block
						try {

							//using parse double to return a double from the String in part
							double value = Double.parseDouble(part);
							sum += value;
							count++;
						}catch(NumberFormatException ex) {

							//If whatever is in the file is not a number assume it's a name using  append()
							if(num.length() > 0) {
								num.append(" ");
							}else {
								//otherwise print what we have from our for loop, this makes it so that we print both the names and new values
								num.append(part);
							}
						}
					}

					// using if(count >0) so we can start at when calculating our average
					if(count > 0) {

						//getting our average
						double average = sum / count;

						//then calculating our curvedAverage by adding 50% of the original average to the students' grades
						double curvedAverage = average + (average * .50);

						//printing out our newGrades using only two decimal places 
						String newGrades = String.format("%.2f",curvedAverage);


						//printing out our output using the toString() with our StringBuilder to get all our names and newGrades to get our updated grades
						output.println(num.toString() + " " + newGrades);
					}


				}catch(NumberFormatException ex) {
					System.out.println("Invalid value");
				}
			}

			//closing our original and new output files
			scan.close();
			output.close();

			//if the file does not exist print out "File not found"
		}catch(FileNotFoundException ex) {
			System.out.println("File not found");
		}

	}
}