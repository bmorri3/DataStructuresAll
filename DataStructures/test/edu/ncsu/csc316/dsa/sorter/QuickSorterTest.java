package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.manager.StudentManager;

/**
 * Class to test QuickSorter with various generic classes and Student
 * @author Dr. King
 * @author Ben Morris
 */

public class QuickSorterTest {

	/** Integer value of 1 */
	private static final Integer ONE = Integer.valueOf(1);
	/** Integer value of 2 */
	private static final Integer TWO = Integer.valueOf(2);
	/** Integer value of 3 */
	private static final Integer THREE = Integer.valueOf(3);
	/** Integer value of 4 */
	private static final Integer FOUR = Integer.valueOf(4);
	/** Integer value of 5 */
	private static final Integer FIVE = Integer.valueOf(5);
	
	/** List of Integers in ascending order */
	private Integer[] dataAscending = { 1, 2, 3, 4, 5 };
	/** List of Integers in descending order */
	private Integer[] dataDescending = { 5, 4, 3, 2, 1 };
	/** List of Integers in random order */
	private Integer[] dataRandom = { 4, 1, 5, 3, 2 };
	
    /** Student 1 */
	private Student student1 = new Student("Ben", "Jones", 10000, 31, 4.0, "bbjones1");
	/** Student 2 */
	private Student student2 = new Student("Ben", "Morris", 11111, 31, 4.0, "bcmorri4");
	/** Student 3 */
	private Student student3 = new Student("Ben", "Morris", 23456, 31, 4.0, "bcmorri4");
	/** Student 4 */
	private Student student4 = new Student("Kendra", "Williams", 56789, 23, 4.1, "klwilli1");
	/** List of Students in ascending order */
	private Student[]studentAscending = {student1, student2, student3, student4};
	/** List of Students in descending order */
	private Student[]studentDescending = {student4, student3, student2, student1};
	/** List of Students in random order */
	private Student[]studentRandom = {student2, student1, student4, student3};
	
	/** IsertionSorter Integer instance with random pivot */
	private QuickSorter<Integer> integerSorterRandom;
	/** IsertionSorter Student instance with random pivot */
	private QuickSorter<Student> studentSorterRandom;
	/** IsertionSorter Integer instance with first pivot */
	private QuickSorter<Integer> integerSorterFirst;
	/** IsertionSorter Student instance with first pivot */
	private QuickSorter<Student> studentSorterFirst;
	/** IsertionSorter Integer instance with last pivot */
	private QuickSorter<Integer> integerSorterLast;
	/** IsertionSorter Student instance with last pivot */
	private QuickSorter<Student> studentSorterLast;
	/** IsertionSorter Integer instance with middle pivot */
	private QuickSorter<Integer> integerSorterMiddle;
	/** IsertionSorter Student instance with middle pivot */
	private QuickSorter<Student> studentSorterMiddle;

	/** seting up new Sorter classes */
	@Before
	public void setUp() {
		integerSorterRandom = new QuickSorter<Integer>();
		studentSorterRandom = new QuickSorter<Student>();
		integerSorterFirst = new QuickSorter<Integer>(QuickSorter.FIRST_ELEMENT_SELECTOR);
		studentSorterFirst = new QuickSorter<Student>(QuickSorter.FIRST_ELEMENT_SELECTOR);
		integerSorterLast = new QuickSorter<Integer>(QuickSorter.LAST_ELEMENT_SELECTOR);
		studentSorterLast = new QuickSorter<Student>(QuickSorter.LAST_ELEMENT_SELECTOR);
		integerSorterMiddle = new QuickSorter<Integer>(QuickSorter.MIDDLE_ELEMENT_SELECTOR);
		studentSorterMiddle = new QuickSorter<Student>(QuickSorter.MIDDLE_ELEMENT_SELECTOR);
	}

	/** Test to sort Integers */
	@Test
	public void testSortIntegers() {
		
		// Sort using the random pivot
		integerSorterRandom.sort(dataAscending);
		assertEquals(ONE, dataAscending[0]);
		assertEquals(TWO, dataAscending[1]);
		assertEquals(THREE, dataAscending[2]);
		assertEquals(FOUR, dataAscending[3]);
		assertEquals(FIVE, dataAscending[4]);

		integerSorterRandom.sort(dataDescending);
		assertEquals(ONE, dataDescending[0]);
		assertEquals(TWO, dataDescending[1]);
		assertEquals(THREE, dataDescending[2]);
		assertEquals(FOUR, dataDescending[3]);
		assertEquals(FIVE, dataDescending[4]);

		integerSorterRandom.sort(dataRandom);
		assertEquals(ONE, dataRandom[0]);
		assertEquals(TWO, dataRandom[1]);
		assertEquals(THREE, dataRandom[2]);
		assertEquals(FOUR, dataRandom[3]);
		assertEquals(FIVE, dataRandom[4]);
		
		// Sort using the first pivot
		integerSorterFirst.sort(dataAscending);
		assertEquals(ONE, dataAscending[0]);
		assertEquals(TWO, dataAscending[1]);
		assertEquals(THREE, dataAscending[2]);
		assertEquals(FOUR, dataAscending[3]);
		assertEquals(FIVE, dataAscending[4]);

		integerSorterFirst.sort(dataDescending);
		assertEquals(ONE, dataDescending[0]);
		assertEquals(TWO, dataDescending[1]);
		assertEquals(THREE, dataDescending[2]);
		assertEquals(FOUR, dataDescending[3]);
		assertEquals(FIVE, dataDescending[4]);

		integerSorterFirst.sort(dataRandom);
		assertEquals(ONE, dataRandom[0]);
		assertEquals(TWO, dataRandom[1]);
		assertEquals(THREE, dataRandom[2]);
		assertEquals(FOUR, dataRandom[3]);
		assertEquals(FIVE, dataRandom[4]);
		
		// Sort using the last pivot
		integerSorterLast.sort(dataAscending);
		assertEquals(ONE, dataAscending[0]);
		assertEquals(TWO, dataAscending[1]);
		assertEquals(THREE, dataAscending[2]);
		assertEquals(FOUR, dataAscending[3]);
		assertEquals(FIVE, dataAscending[4]);

		integerSorterLast.sort(dataDescending);
		assertEquals(ONE, dataDescending[0]);
		assertEquals(TWO, dataDescending[1]);
		assertEquals(THREE, dataDescending[2]);
		assertEquals(FOUR, dataDescending[3]);
		assertEquals(FIVE, dataDescending[4]);

		integerSorterLast.sort(dataRandom);
		assertEquals(ONE, dataRandom[0]);
		assertEquals(TWO, dataRandom[1]);
		assertEquals(THREE, dataRandom[2]);
		assertEquals(FOUR, dataRandom[3]);
		assertEquals(FIVE, dataRandom[4]);
		
		// Sort using the middle pivot
		integerSorterMiddle.sort(dataAscending);
		assertEquals(ONE, dataAscending[0]);
		assertEquals(TWO, dataAscending[1]);
		assertEquals(THREE, dataAscending[2]);
		assertEquals(FOUR, dataAscending[3]);
		assertEquals(FIVE, dataAscending[4]);

		integerSorterMiddle.sort(dataDescending);
		assertEquals(ONE, dataDescending[0]);
		assertEquals(TWO, dataDescending[1]);
		assertEquals(THREE, dataDescending[2]);
		assertEquals(FOUR, dataDescending[3]);
		assertEquals(FIVE, dataDescending[4]);

		integerSorterMiddle.sort(dataRandom);
		assertEquals(ONE, dataRandom[0]);
		assertEquals(TWO, dataRandom[1]);
		assertEquals(THREE, dataRandom[2]);
		assertEquals(FOUR, dataRandom[3]);
		assertEquals(FIVE, dataRandom[4]);
	}

	/** Test to sort Students */
	@Test
	public void testSortStudent() {
		
		// Sort using the random pivot
		studentSorterRandom.sort(studentAscending);
		assertEquals(student1, studentAscending[0]);
		assertEquals(student2, studentAscending[1]);
		assertEquals(student3, studentAscending[2]);
		assertEquals(student4, studentAscending[3]);

		studentSorterRandom.sort(studentDescending);
		assertEquals(student1, studentDescending[0]);
		assertEquals(student2, studentDescending[1]);
		assertEquals(student3, studentDescending[2]);
		assertEquals(student4, studentDescending[3]);

		studentSorterRandom.sort(studentRandom);
		assertEquals(student1, studentRandom[0]);
		assertEquals(student2, studentRandom[1]);
		assertEquals(student3, studentRandom[2]);
		assertEquals(student4, studentRandom[3]);
		
		// Sort using the first pivot
		studentSorterFirst.sort(studentAscending);
		assertEquals(student1, studentAscending[0]);
		assertEquals(student2, studentAscending[1]);
		assertEquals(student3, studentAscending[2]);
		assertEquals(student4, studentAscending[3]);

		studentSorterFirst.sort(studentDescending);
		assertEquals(student1, studentDescending[0]);
		assertEquals(student2, studentDescending[1]);
		assertEquals(student3, studentDescending[2]);
		assertEquals(student4, studentDescending[3]);

		studentSorterFirst.sort(studentRandom);
		assertEquals(student1, studentRandom[0]);
		assertEquals(student2, studentRandom[1]);
		assertEquals(student3, studentRandom[2]);
		assertEquals(student4, studentRandom[3]);

		// Sort using the last pivot
		studentSorterLast.sort(studentAscending);
		assertEquals(student1, studentAscending[0]);
		assertEquals(student2, studentAscending[1]);
		assertEquals(student3, studentAscending[2]);
		assertEquals(student4, studentAscending[3]);

		studentSorterLast.sort(studentDescending);
		assertEquals(student1, studentDescending[0]);
		assertEquals(student2, studentDescending[1]);
		assertEquals(student3, studentDescending[2]);
		assertEquals(student4, studentDescending[3]);

		studentSorterLast.sort(studentRandom);
		assertEquals(student1, studentRandom[0]);
		assertEquals(student2, studentRandom[1]);
		assertEquals(student3, studentRandom[2]);
		assertEquals(student4, studentRandom[3]);
		
		// Sort using the middle pivot
		studentSorterMiddle.sort(studentAscending);
		assertEquals(student1, studentAscending[0]);
		assertEquals(student2, studentAscending[1]);
		assertEquals(student3, studentAscending[2]);
		assertEquals(student4, studentAscending[3]);

		studentSorterMiddle.sort(studentDescending);
		assertEquals(student1, studentDescending[0]);
		assertEquals(student2, studentDescending[1]);
		assertEquals(student3, studentDescending[2]);
		assertEquals(student4, studentDescending[3]);

		studentSorterMiddle.sort(studentRandom);
		assertEquals(student1, studentRandom[0]);
		assertEquals(student2, studentRandom[1]);
		assertEquals(student3, studentRandom[2]);
		assertEquals(student4, studentRandom[3]);
	}
	
	/** Testing using a file and student manager and QuickSort*/
	@Test
	public void testFileStudentManagerQuick() {
		
		// Sort using the random pivot
		StudentManager sm = new StudentManager("input/student_ascendingID.csv", studentSorterRandom);
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
		
		// Sort using the first pivot
		sm = new StudentManager("input/student_ascendingID.csv", studentSorterFirst);
		sorted = sm.sort();
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
		
		// Sort using the last pivot
		sm = new StudentManager("input/student_ascendingID.csv", studentSorterLast);
		sorted = sm.sort();
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
		
		// Sort using the middle pivot
		sm = new StudentManager("input/student_ascendingID.csv", studentSorterMiddle);
		sorted = sm.sort();
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
}
