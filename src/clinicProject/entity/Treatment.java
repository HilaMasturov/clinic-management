package clinicProject.entity;

public class Treatment {
	Patient patient;
	Doctor doctor;
	String medicine;
	String diagnose;
	
	
	public Treatment(clinicProject.entity.Patient patient, clinicProject.entity.Doctor doctor, String medicine, String diagnose) {
		super();
		this.patient = patient;
		this.doctor = doctor;
		this.medicine = medicine;
		this.diagnose = diagnose;
	}

	@Override
	public String toString() {
		return "treatment [patient=" + patient + "\n, doctor=" + doctor + "\n, medicine=" + medicine + "----" + ", diagnose=" + diagnose + "]\n\n";
	}
	
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public String getMedicine() {
		return medicine;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}
	public String getDiagnose() {
		return diagnose;
	}
	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}
	
}
