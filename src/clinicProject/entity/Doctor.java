package clinicProject.entity;

public class Doctor extends Employee  {
	 String profisiency; 	//family \ kids\ cardio
	 static int doctorCount;

//------------------------------constructors-------------------------------------	 
	 
	 public Doctor(String ID, String Name, int age, int employeeNum, String licenseID, String profisiency) {
		super(ID, Name, age,employeeNum, licenseID);
		this.profisiency = profisiency;
	}

	public Doctor(String prof){
		super();
		setProfisiency(prof); 
	}
	public Doctor(){
		super();
		this.profisiency = null; 
	}

//---------------------------------methods-----------------------------------
	
	public static int getDoctorCount() {
		return doctorCount;
	}

	public static void resetDoctorCount() {
		Doctor.doctorCount = 0;
	}
	public static void addDoctorCount() {
		Doctor.doctorCount++;
	}

	public String getProfisiency() {
		return profisiency;
	}

	public void setProfisiency(String profisiency) {
		this.profisiency = profisiency;
	}

}
