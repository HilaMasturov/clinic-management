package clinicProject.entity;

import java.util.Date;

public class Shift {

	int employee;
	Appointment appointment;
    private String date;
    
	
	
//------------------------------constructors-------------------------------------	 

	
	public Shift(int employee, Appointment appointment, String date) {
		this.employee = employee;
		this.appointment = appointment;
		this.date=date;
		
	}

	public int getEmployee() {
		return employee;
	}

	public void setEmployee(int employee) {
		this.employee = employee;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	public void addAppointmentInShift() {
		
	}
	

	@Override
	public String toString() {
		return "shift [employee=" + employee+ "\n" + ", appointment=" + appointment + "]\n\n";
	}

}



