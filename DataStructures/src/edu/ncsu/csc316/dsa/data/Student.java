package edu.ncsu.csc316.dsa.data;

/**
 * A student is comparable and identifiable.
 * Students have a first name, last name, id number, 
 * number of credit hours, gpa, and unityId.
 * 
 * @author Dr. King
 * @author Ben Morris
 *
 */
public class Student implements Comparable<Student>, Identifiable {
	/** First name */
	String first;
	/** Last name */
	String last;
	/** Student id number */
	int id;
	/** Student's credit hours */
	int creditHours;
	/** Student's gpa */
	double gpa;
	/** Student's unityId */
	String unityId;
	
	/**
	 * Constructor
	 * @param first first name of Student
	 * @param last last name of Student
	 * @param id Student id
	 * @param creditHours Hours the Student has earned
	 * @param gpa GPA of Student
	 * @param unityId unityId of Student
	 */
	public Student(String first, String last, int id, int creditHours, double gpa, String unityId) {
		this.first = first;
		this.last = last;
		this.id = id;
		this.creditHours = creditHours;
		this.gpa = gpa;
		this.unityId = unityId;
	}

	/**
	 * Gets first
	 * @return the first
	 */
	public String getFirst() {
		return first;
	}
	/**
	 * Sets first
	 * @param first the first to set
	 */
	public void setFirst(String first) {
		this.first = first;
	}
	/**
	 * Gets last
	 * @return the last
	 */
	public String getLast() {
		return last;
	}
	/**
	 * Sets last
	 * @param last the last to set
	 */
	public void setLast(String last) {
		this.last = last;
	}
	/**
	 * Gets id
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * Sets id
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * Gets creditHours
	 * @return the creditHours
	 */
	public int getCreditHours() {
		return creditHours;
	}
	/**
	 * Sets creditHours
	 * @param creditHours the creditHours to set
	 */
	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}
	/**
	 * Gets gpa
	 * @return the gpa
	 */
	public double getGpa() {
		return gpa;
	}
	/**
	 * Sets gpa
	 * @param gpa the gpa to set
	 */
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	/**
	 * Gets unityId
	 * @return the unityId
	 */
	public String getUnityId() {
		return unityId;
	}
	/**
	 * Sets unityId
	 * @param unityId the unityId to set
	 */
	public void setUnityId(String unityId) {
		this.unityId = unityId;
	}
	
	/**
	 * Overridden hashCode() for Student
	 * @return the hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + creditHours;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		long temp;
		temp = Double.doubleToLongBits(gpa);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((last == null) ? 0 : last.hashCode());
		result = prime * result + ((unityId == null) ? 0 : unityId.hashCode());
		return result;
	}
	
	/**
	 * Overridden equals() for Student
	 * Students are equal if the first names, last names, and IDs are equal
	 * @return true if this is equal to obj, false if not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (id != other.id)
			return false;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (last == null) {
			if (other.last != null)
				return false;
		} else if (!last.equals(other.last))
			return false;
		
		return true;
	}
	
	/**
	 * Implementation of Comparable compareTo.
	 * Ordering of students is based on last name (alphabetically in ascending order), first name 
	 * (alphabetically in ascending order), then Student ID number. No two students will ever have the same Student ID number.
	 * 
	 * @param student to compare to this
	 * @return greater than 0 if this is greater than student according to the criteria described in the method javadoc,
	 * 		   0 if this is equal to student,
	 * 		   less than 0 if this is less than student
	 */
	public int compareTo(Student student) {
		if(this.equals(student))
			return 0;
		if(this.getLast() == student.getLast())
			if(this.getFirst() == student.getFirst())
				if(this.getId() < student.getId())
					return -1;
				else
					return 1;
			else
				return this.getFirst().compareTo(student.getFirst());
		else
			return this.getLast().compareTo(student.getLast());
	}
	
	/** 
	 * Implementation of toString 
	 * @return "(First) (Last) is a student."
	 * */
	public String toString() {
		return first + " " + last + ": " + getId();
	}
	
}
