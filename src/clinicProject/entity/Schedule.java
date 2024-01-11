package clinicProject.entity;

public class Schedule {
	Appointment appointment;
	Patient patient;
	
	
//------------------------------constructors-------------------------------------	 

	
	public Schedule(clinicProject.entity.Appointment appointment, clinicProject.entity.Patient patient) {
		this.appointment = appointment;
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "schdule [appointment=" + appointment + "\n, patient=" + patient + "]\n\n";
	}

}
