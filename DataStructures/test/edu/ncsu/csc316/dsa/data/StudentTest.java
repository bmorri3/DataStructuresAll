package edu.ncsu.csc316.dsa.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/** 
 * Class to test Student class
 * @author Dr. King
 * @author Ben Morris 
 */
public class StudentTest {

	/** Integer Object */
	private static final Integer INTEGER = Integer.valueOf(0);
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
	/** Student 6 */
	private Student sSix;
	/** Null first name */
	private Student sNullFirst;
	/** Null last name */
	private Student sNullLast;
	/** Another Null last name, but same ID as sOne */
	private Student sNullLast2;
	/** Null Unity ID */
	private Student sNullUnity;
	/** Null Student */
	private Student sNullStudent;
	/**
	 * Setup for tests
	 */
	@Before
	public void setUp() {
		// Initialize Students to use for testing
		sOne = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityId");
		sTwo = new Student("TwoFirst", "TwoLast", 2, 2, 2.0, "twoUnityId");
		sThree = new Student("FiveFirst", "FiveLast", 5, 5, 5.0, "fiveUnityId");
		sFour = new Student("OneFirst", "OneLast", 1, 1, 1.0, "oneUnityId");
		sFive = new Student("OneFirst", "OneLast", 5, 1, 1.0, "oneUnityId");
		sSix = new Student("TwoFirst", "OneLast", 3, 1, 1.0, "oneUnityId");
		sNullFirst = new Student(null, "OneLast", 3, 1, 1.0, "oneUnityId");
		sNullLast = new Student("OneFirst", null, 3, 1, 1.0, "oneUnityId");
		sNullLast2 = new Student("OneFirst", null, 1, 1, 1.0, "oneUnityId");
		sNullUnity = new Student("OneFirst", "OneLast", 1, 1, 1.0, null);
		sNullStudent = null;
	}

	/**
	 * Testing setFirst()
	 */
	@Test
	public void testSetFirst() {
		sOne.setFirst("newOne");
		assertEquals("newOne", sOne.getFirst());
	}

	/**
	 * Testing setLast()
	 */
	@Test
	public void testSetLast() {
		sOne.setLast("newOne");
		assertEquals("newOne", sOne.getLast());
	}

	/**
	 * Testing setId
	 */
	@Test
	public void testSetId() {
		sOne.setId(100);
		assertEquals(100, sOne.getId());
	}

	/**
	 * testingSetGpa
	 */
	@Test
	public void testSetGpa() {
		sOne.setGpa(3.51);
		assertEquals(3.51, sOne.getGpa(), 0.001);
	}
	
	/**
	 * Testing setUnityId()
	 */
	@Test
	public void testSetUnityId() {
		sOne.setUnityId("oneUnity");
		assertEquals("oneUnity", sOne.getUnityId());
	}

	/**
	 * Testing compareTo()
	 */
	@Test
	public void testCompareTo() {
		assertTrue(sOne.compareTo(sTwo) < 0);
		assertTrue(sTwo.compareTo(sOne) > 0);
		assertSame(sOne.compareTo(sOne), 0);
		assertTrue(sFour.compareTo(sFive) < 0);
		assertTrue(sFour.compareTo(sSix) < 0);
	}
	
	/**
	 * Testing equals()
	 */
	@Test
	public void testEquals() {
		assertTrue(sOne.equals(sFour));
		assertFalse(sOne.equals(sThree));
		assertTrue(sOne.equals(sOne));
		assertFalse(sOne.equals(sTwo));
		assertFalse(sOne.equals(sNullStudent));
		assertFalse(sNullFirst.equals(sNullLast));
		assertFalse(sNullFirst.equals(sOne));
		assertFalse(sNullLast.equals(sTwo));
		assertFalse(sNullLast2.equals(sOne));
		assertFalse(sOne.equals(INTEGER));
	}
	
	/**
	 * Testing toString()
	 */
	@Test
	public void testToString() {
		assertEquals(sOne.toString(), "OneFirst OneLast: 1");
	}
	
	/**
	 * Testing getCreditHours()
	 */
	@Test
	public void testGetCreditHours() {
		assertEquals(1, sOne.getCreditHours());
	}
	
	/**
	 * Testing setCreditHours()
	 */
	@Test
	public void testSetCreditHours() {
		sOne.setCreditHours(11);
		assertEquals(11, sOne.getCreditHours());
	}
	
	/**
	 * Testing hashCode()
	 */
	@Test
	public void testHashCode() {
		assertEquals(sOne.hashCode(), sOne.hashCode());
		assertNotEquals(sOne.hashCode(), sTwo.hashCode());
		assertNotEquals(sNullFirst.hashCode(), sNullLast.hashCode());
		assertNotEquals(sNullFirst.hashCode(), sNullUnity.hashCode());
	}
}
