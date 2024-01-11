package clinicProject.entity;

public class Appointment {
    private String date;
    private int time;
    private int employee;
    private Patient patient;
	private boolean isDoc;

    // Constructor
    public Appointment(String date,int employee,Patient patient,boolean isDoc) {
  
        this.date = date;
        this.patient= patient;
        this.employee=employee;
        this.setIsDoc(isDoc);
    }


	public int getEmployee() {
		return employee;
	}

	public void setEmployee(int employee) {
		this.employee = employee;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "appointment [date=" + date + ", time=" + time + "]\n";
	}

	// Getter methods
    public String getDate() {
        return date;
    }


    // Setter methods (if needed)
    public void setDate(String date) {
        this.date = date;
    }


	public boolean getIsDoc() {
		return isDoc;
	}

	public void setIsDoc(boolean isDoc) {
		this.isDoc = isDoc;
	}


}

