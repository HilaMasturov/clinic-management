package clinicProject.entity;

public abstract class Employee extends Person {
	int employeeNum;
	String licenseID;
	static int employeesCount;

//------------------------------constructors-------------------------------------	 

	
	public Employee(String ID, String Name, int age,int employeeNum , String licenseID) {
		super(ID,Name,age);
		setEmployeeNum(employeeNum);
		setLicenseID (licenseID);
		addToEmployeesCount();
	}

	public Employee() {
		super();
		setEmployeeNum(0);
		setLicenseID (null);
		addToEmployeesCount();
	}
//---------------------------------methods-----------------------------------

	
	public int getEmployeeNum() {
		return employeeNum;
	}

	public void setEmployeeNum(int employeeNum) {
		this.employeeNum = employeeNum;
	}

	public String getLicenseID() {
		return licenseID;
	}

	public void setLicenseID(String licenseID) {
		this.licenseID = licenseID;
	}

	public static int getEmployeesCount() {
		return employeesCount;
	}

	public static void resetEmployeesCount() {
		Employee.employeesCount = 0;
	}

	public static void addToEmployeesCount() {
		Employee.employeesCount ++;
	}
	
	@Override
	public String toString() {
		return "employee [employeeNum=" + employeeNum + ", licenseID=" + licenseID + ", ID=" + ID + ", Name=" + Name
				+ ", age=" + age + "]";
		}

	public boolean ifDoctor() {
		if (this instanceof Doctor)return true;
		return false;
	}
	
	
}
