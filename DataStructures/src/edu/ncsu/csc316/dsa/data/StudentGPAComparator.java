package edu.ncsu.csc316.dsa.data;

import java.util.Comparator;

/**
 * Comparator for comparing Students based on GPA
 * @author Dr. King
 * @author Ben Morris
 *
 */
public class StudentGPAComparator implements Comparator<Student> {

	/**
	 * Compares students based on GPA in descending order.
	 * Uses the natural ordering of Students as the secondary sorting criteria.
	 * @return less than 0 if two has a higher GPA than 1
	 * 		   greater than 0 if one has a higher GPA than 2
	 * 		   result of compareTo() if the GPAs are equal
	 */
	@Override
	public int compare(Student one, Student two) {
		int comparison = 0;
		comparison = ((Double) two.getGpa()).compareTo((Double) one.getGpa());
		if(comparison == 0)
			return one.compareTo(two);
		return comparison;
	}
}
