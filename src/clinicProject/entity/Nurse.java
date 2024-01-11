package clinicProject.entity;

public class Nurse extends Employee {

	
	//------------------------------constructors-------------------------------------	 
	
	
	public Nurse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Nurse(String ID, String Name, int age, int employeeNum, String licenseID) {
		super(ID, Name, age, employeeNum, licenseID);
		// TODO Auto-generated constructor stub
	}



	//---------------------------------methods-----------------------------------
	
	@Override
	public String toString() {
		return "nurse [employeeNum=" + employeeNum + ", licenseID=" + licenseID + ", ID=" + ID + ", Name=" + Name
				+ ", age=" + age + "]";
	}
	
}
