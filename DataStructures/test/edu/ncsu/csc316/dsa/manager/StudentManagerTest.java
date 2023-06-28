package edu.ncsu.csc316.dsa.manager;

import static org.junit.Assert.*;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.sorter.InsertionSorter;

/** 
 * Class to test StudentManager class
 * @author Dr. King
 * @author Ben Morris 
 */
public class StudentManagerTest {

	/** Instance of StudentManager */
	private StudentManager sm;
	
	/** Testing sort() */
	@Test
	public void testSort() {
		sm = new StudentManager("input/student_ascendingID.csv");
		Student[] sorted = sm.sort();
		assertEquals("Tanner", sorted[0].getFirst());
		assertEquals("Roxann", sorted[1].getFirst());
		assertEquals("Shanti", sorted[2].getFirst());
		assertEquals("Dante", sorted[3].getFirst());
		assertEquals("Cristine", sorted[4].getFirst());
		assertEquals("Ara", sorted[5].getFirst());
		assertEquals("Lewis", sorted[6].getFirst());
		assertEquals("Charlene", sorted[7].getFirst());
		assertEquals("Amber", sorted[8].getFirst());
		assertEquals("Lacie", sorted[9].getFirst());
		assertEquals("Idalia", sorted[10].getFirst());
		assertEquals("Tyree", sorted[11].getFirst());
		assertEquals("Evelin", sorted[12].getFirst());
		assertEquals("Alicia", sorted[13].getFirst());
		assertEquals("Loise", sorted[14].getFirst());
		assertEquals("Nichole", sorted[15].getFirst());
	}
	
	/** Testing sort() using StudentIDComparator as the sorting criteria */
	@Test
	public void testSortByID() {
		sm = new StudentManager("input/student_descendingID.csv", new InsertionSorter<>(new StudentIDComparator()));
		Student[] sorted = sm.sort();
		assertEquals("Amber", sorted[0].getFirst());
		assertEquals("Ara", sorted[1].getFirst());
		assertEquals("Lacie", sorted[2].getFirst());
		assertEquals("Idalia", sorted[3].getFirst());
		assertEquals("Evelin", sorted[4].getFirst());
		assertEquals("Lewis", sorted[5].getFirst());
		assertEquals("Alicia", sorted[6].getFirst());
		assertEquals("Tyree", sorted[7].getFirst());
		assertEquals("Loise", sorted[8].getFirst());
		assertEquals("Roxann", sorted[9].getFirst());
		assertEquals("Nichole", sorted[10].getFirst());
		assertEquals("Charlene", sorted[11].getFirst());
		assertEquals("Shanti", sorted[12].getFirst());
		assertEquals("Cristine", sorted[13].getFirst());
		assertEquals("Tanner", sorted[14].getFirst());
		assertEquals("Dante", sorted[15].getFirst());
	}
	
	/** Testing sort() using StudentGPAComparator as the sorting criteria */
	@Test
	public void testSortByGPA() {
		sm = new StudentManager("input/student_randomOrder.csv", new InsertionSorter<>(new StudentGPAComparator()));
		Student[] sorted = sm.sort();
		assertEquals("Nichole", sorted[0].getFirst());
		assertEquals("Alicia", sorted[1].getFirst());
		assertEquals("Charlene", sorted[2].getFirst());
		assertEquals("Cristine", sorted[3].getFirst());
		assertEquals("Dante", sorted[4].getFirst());
		assertEquals("Lacie", sorted[5].getFirst());
		assertEquals("Idalia", sorted[6].getFirst());
		assertEquals("Ara", sorted[7].getFirst());
		assertEquals("Loise", sorted[8].getFirst());
		assertEquals("Tanner", sorted[9].getFirst());
		assertEquals("Amber", sorted[10].getFirst());
		assertEquals("Roxann", sorted[11].getFirst());
		assertEquals("Tyree", sorted[12].getFirst());
		assertEquals("Evelin", sorted[13].getFirst());
		assertEquals("Shanti", sorted[14].getFirst());
		assertEquals("Lewis", sorted[15].getFirst());
	}
}
