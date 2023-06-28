package edu.ncsu.csc316.dsa.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/** 
 * Class to test StudentReader class
 * @author Dr. King
 * @author Ben Morris 
 */
public class StudentReaderTest {
	
	/** First line from randomOrder.csv */
	private static final Student STUDENT_1 = new Student("Lacie", "Mott", 4, 18, 2.94, "mottl");
	
	/** Testing reading the input file */
	@Test
	public void testReadFile() {
		Student[] contents = StudentReader.readInputAsArray("input/student_ascendingID.csv");
		assertEquals("Amber", contents[0].getFirst());
		assertEquals("Ara", contents[1].getFirst());
		assertEquals("Lacie", contents[2].getFirst());
		assertEquals("Idalia", contents[3].getFirst());
		assertEquals("Evelin", contents[4].getFirst());
		assertEquals("Lewis", contents[5].getFirst());
		assertEquals("Alicia", contents[6].getFirst());
		assertEquals("Tyree", contents[7].getFirst());
		assertEquals("Loise", contents[8].getFirst());
		assertEquals("Roxann", contents[9].getFirst());
		assertEquals("Nichole", contents[10].getFirst());
		assertEquals("Charlene", contents[11].getFirst());
		assertEquals("Shanti", contents[12].getFirst());
		assertEquals("Cristine", contents[13].getFirst());
		assertEquals("Tanner", contents[14].getFirst());
		assertEquals("Dante", contents[15].getFirst());
	}
	
	/** 
	 * Testing processLine() 
	 * @throws FileNotFoundException "Input file not found." if File not found
	 * */
	@Test
	public void testProcessLine() throws FileNotFoundException {
		
		String firstLine = null;
		
		// Open input file
		try {
			Scanner scan = new Scanner(new FileInputStream("input/student_randomOrder.csv"), "UTF8");
			// Throw away header line
			scan.nextLine();
			//Get first line of Student information
			firstLine = scan.nextLine();
		}
		catch (FileNotFoundException e) {
			throw new FileNotFoundException("Input file not found."); 
		}
		
		//Test that processing a line is equivalent to explicitly creating a student with the same information
		assertEquals(STUDENT_1, StudentReader.processLine(firstLine));
		
	}
	
}
