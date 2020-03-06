package staff;


public class Lecturer extends StaffMember{
	public String school;
	public String academicStatus;
	
	public Lecturer(String name, double salary, String school, String academicStatus) {
		super(name, salary);
		this.school = school;
		this.academicStatus = academicStatus;
	}
	
	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getAcademicStatus() {
		return academicStatus;
	}

	public void setAcademicStatus(String academicStatus) {
		this.academicStatus = academicStatus;
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
		return super.toString() + " [school=" + school + ", academicStatus=" + academicStatus + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!super.equals(obj))
			return false;
		else {
			if(!school.equals(((Lecturer) obj).getSchool()))
				return false;
			if(!academicStatus.equals(((Lecturer) obj).getAcademicStatus()))
					return false;
		}
		return true;
	}
	
}
