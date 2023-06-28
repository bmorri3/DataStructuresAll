package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.manager.StudentManager;

/** 
 * Class to test RadixSorter class
 * @author Dr. King
 * @author Ben Morris 
 */
public class RadixSorterTest {
	
	/** Student 1 */
	private Student sOne;
	/** Student 2 */
	private Student sTwo;
	/** Student 3 */
	private Student sThree;
	/** Student 4 */
	private Student sFour;
	/** Student 5 */
	private Student sFive;
	
	/** Radix Sorter instance */
	private RadixSorter<Student> sorter;
	
	/** Instance of StudentManager */
	private StudentManager sm;

	/** Pre-test setup */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sThree = new Student("ThreeFirst", "ThreeLast", 3, 3, 3.0, "threeUnityID");
		sFour = new Student("FourFirst", "FourLast", 4, 4, 4.0, "fourUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		
		sorter = new RadixSorter<Student>();
	}
	
	/** Testing sortStudent */
	@Test
	public void testSortStudent() {
		Student[] original = { sTwo, sOne, sFour, sThree, sFive };
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
		assertEquals(sThree, original[2]);
		assertEquals(sFour, original[3]);
		assertEquals(sFive, original[4]);
	}
	
	/** Testing a Student list with one element */
	@Test
	public void testOneStudent() {
		Student[] original = {sOne};
		sorter.sort(original);
		assertEquals(sOne, original[0]);
	}

	/** Testing a Student list with two elements */
	@Test
	public void testTwoStudents() {
		Student[] original = {sTwo, sOne};
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sTwo, original[1]);
	}
	
	/** Testing a Student list with all elements the same */
	@Test
	public void testAllEquals() {
		Student[] original = {sOne, sOne, sOne, sOne, sOne, sOne, sOne};
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sOne, original[1]);
		assertEquals(sOne, original[2]);
		assertEquals(sOne, original[3]);
		assertEquals(sOne, original[4]);
		assertEquals(sOne, original[5]);
		assertEquals(sOne, original[6]);
	}

	/** Testing a Student list with multiple frequencies */
	@Test
	public void testMultipleFrequencies() {
		Student[] original = {sOne, sTwo, sTwo, sOne, sOne, sThree, sFive};
		sorter.sort(original);
		assertEquals(sOne, original[0]);
		assertEquals(sOne, original[1]);
		assertEquals(sOne, original[2]);
		assertEquals(sTwo, original[3]);
		assertEquals(sTwo, original[4]);
		assertEquals(sThree, original[5]);
		assertEquals(sFive, original[6]);
	}
	
	/** Testing using a file and student manager */
	@Test
	public void testFileStudentManagerRadix() {
		
		RadixSorter<Student> radix = new RadixSorter<Student>();
		sm = new StudentManager("input/student_ascendingID.csv", radix);
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
}
