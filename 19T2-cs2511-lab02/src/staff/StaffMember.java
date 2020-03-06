package staff;

import java.util.Date;

/**
 * A staff member
 *
 */
public class StaffMember {
	public String name;
	public double salary;
	public String hireDate;
	public String endDate;
	
	public StaffMember(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}
	//getter for name and salary, getter and setter for hireDate and endDate
	public String getName() {
		return name;
	}
	public double getSalary() {
		return salary;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	
	@Override
	public String toString() {
		return getClass().toString() + " [name=" + name + ", salary=" + salary + ", hireDate=" + hireDate + ", endDate=" + endDate
				+ "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StaffMember other = (StaffMember) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (hireDate == null) {
			if (other.hireDate != null)
				return false;
		} else if (!hireDate.equals(other.hireDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(salary) != Double.doubleToLongBits(other.salary))
			return false;
		return true;
	}
	
}
