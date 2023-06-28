package edu.ncsu.csc316.dsa.sorter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.manager.StudentManager;

/**
 * Class to test SelectionSorter with various generic classes and Student
 * @author Ben Morris
 */

public class SelectionSorterTest {

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
	private Student student1 = new Student("Ben", "Jones", 12345, 31, 4.0, "bbjones1");
	/** Student 2 */
	private Student student2 = new Student("Ben", "Morris", 12345, 31, 4.0, "bcmorri4");
	/** Student 3 */
	private Student student3 = new Student("Ben", "Morris", 23456, 31, 4.0, "bcmorri4");
	/** Student 4 */
	private Student student4 = new Student("Kendra", "Williams", 12345, 23, 4.1, "klwilli1");
	/** List of Students in ascending order */
	private Student[]studentAscending = {student1, student2, student3, student4};
	/** List of Students in descending order */
	private Student[]studentDescending = {student4, student3, student2, student1};
	/** List of Students in random order */
	private Student[]studentRandom = {student2, student1, student4, student3};
	
	/** SelectionSorter Integer instance */
	private SelectionSorter<Integer> integerSorter;
	/** IsertionSorter Student instance */
	private SelectionSorter<Student> studentSorter;

	/** seting up new Sorter classes */
	@Before
	public void setUp() {
		integerSorter = new SelectionSorter<Integer>();
		studentSorter = new SelectionSorter<Student>();
	}

	/** Test to sort Integers */
	@Test
	public void testSortIntegers() {
		integerSorter.sort(dataAscending);
		assertEquals(ONE, dataAscending[0]);
		assertEquals(TWO, dataAscending[1]);
		assertEquals(THREE, dataAscending[2]);
		assertEquals(FOUR, dataAscending[3]);
		assertEquals(FIVE, dataAscending[4]);

		integerSorter.sort(dataDescending);
		assertEquals(ONE, dataDescending[0]);
		assertEquals(TWO, dataDescending[1]);
		assertEquals(THREE, dataDescending[2]);
		assertEquals(FOUR, dataDescending[3]);
		assertEquals(FIVE, dataDescending[4]);

		integerSorter.sort(dataRandom);
		assertEquals(ONE, dataRandom[0]);
		assertEquals(TWO, dataRandom[1]);
		assertEquals(THREE, dataRandom[2]);
		assertEquals(FOUR, dataRandom[3]);
		assertEquals(FIVE, dataRandom[4]);
	}

	/** Test to sort Students */
	@Test
	public void testSortStudent() {
		studentSorter.sort(studentAscending);
		assertEquals(student1, studentAscending[0]);
		assertEquals(student2, studentAscending[1]);
		assertEquals(student3, studentAscending[2]);
		assertEquals(student4, studentAscending[3]);

		studentSorter.sort(studentDescending);
		assertEquals(student1, studentDescending[0]);
		assertEquals(student2, studentDescending[1]);
		assertEquals(student3, studentDescending[2]);
		assertEquals(student4, studentDescending[3]);

		studentSorter.sort(studentRandom);
		assertEquals(student1, studentRandom[0]);
		assertEquals(student2, studentRandom[1]);
		assertEquals(student3, studentRandom[2]);
		assertEquals(student4, studentRandom[3]);
	}
	
	/** Testing using a file and student manager and SelectionSort */
	@Test
	public void testFileStudentManagerSelection() {
		
		SelectionSorter<Student> sorter = new SelectionSorter<Student>();
		StudentManager sm = new StudentManager("input/student_ascendingID.csv", sorter);
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
}
