package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Class to test StudentGPAComparator
 * @author Ben Morris
 */
public class StudentGPAComparatorTest {

	/** Student 1 */
	private Student sOne;
	/** Student 2 */
	private Student sTwo;
	/** Student 3 */
	private Student sFive;
	/** Student 6 */
	private Student sSix;
	/** Instance of Class SudentGPAComparator */
	private StudentGPAComparator comparator;

	/** Pre-test setup */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");
		sFive = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityID");
		sSix = new Student("FiveFirst", "FiveLast", 6, 5, 5.0, "fiveUnityID");

		comparator = new StudentGPAComparator();
	}

	/** Testing compare() */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sTwo, sOne) < 0);
		assertFalse(comparator.compare(sOne, sTwo) < 0);
		assertSame(comparator.compare(sOne,  sOne), 0);
		assertNotSame(comparator.compare(sOne,  sTwo), 0);
		assertTrue(comparator.compare(sFive,  sSix) < 0);
		assertFalse(comparator.compare(sSix, sFive) < 0);
		assertTrue(comparator.compare(sSix, sFive) > 0);
		assertFalse(comparator.compare(sFive, sSix) > 0);
	}

}
