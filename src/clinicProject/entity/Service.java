package clinicProject.entity;

public class Service {
	
	
	@Override
	public String toString() {
		return "Service [patient=" + patient + ", nurse=" + nurse + ", serviceType=" + serviceType.getDescription() + "]";
	}




	public enum serviceType {
        V1("firstAid"),
        V2("vaccine"),
        V3("blood test");

        private final String description;

        serviceType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    private Patient patient;
    private Nurse nurse;
    private serviceType serviceType;

    
	

//------------------------------constructors-------------------------------------	 
  
    
    public Service(Patient patient, Nurse nurse, serviceType serviceType) {
        this.setPatient(patient);
        this.setNurse(nurse);
        this.setServiceType(serviceType);
    }



	public Patient getPatient() {
		return patient;
	}




	public void setPatient(Patient patient) {
		this.patient = patient;
	}




	public Nurse getNurse() {
		return nurse;
	}




	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}




	public serviceType getServiceType() {
		return serviceType;
	}




	public void setServiceType(serviceType serviceType) {
		this.serviceType = serviceType;
	}


}
