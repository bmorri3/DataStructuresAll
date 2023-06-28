package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Class to test StudentIDComparator
 * @author Ben Morris
 */
public class StudentIDComparatorTest {

	/** Student 1 */
	private Student sOne;
	/** Student 2 */
	private Student sTwo;
	/** Instance of StudentIDComparator Class */
	private StudentIDComparator comparator;

	/** Pre-test setup */
	@Before
	public void setUp() {
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityID");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityID");

		comparator = new StudentIDComparator();
	}

	/** Testing compare() */
	@Test
	public void testCompare() {
		assertTrue(comparator.compare(sOne, sTwo) < 0);
		assertFalse(comparator.compare(sTwo, sOne) < 0);
		assertSame(comparator.compare(sOne,  sOne), 0);
		assertNotSame(comparator.compare(sOne, sTwo), 0);
		assertTrue(comparator.compare(sTwo, sOne) > 0);
		assertFalse(comparator.compare(sOne, sTwo) > 0);
	}
}
