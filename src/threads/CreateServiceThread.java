package threads;

import clinicProject.entity.Nurse;
import clinicProject.entity.Patient;
import clinicProject.entity.Service;
import clinicProject.entity.Service.serviceType;

import java.util.Vector;

public class CreateServiceThread implements Runnable{


    private final Vector<Service> services;
    private final Nurse nurse;
    private final Patient patient;
    private final serviceType serviceType;

    public CreateServiceThread(Vector<Service> services, Nurse nurse, Patient patient, serviceType serviceType) {
        this.services = services;
        this.nurse = nurse;
        this.patient = patient;
        this.serviceType = serviceType;
    }

    @Override
    public void run() {
        createService(nurse, patient, serviceType);
    }

    private void createService(Nurse nurse, Patient patient, serviceType serviceType) {
    	Service newSrevice = new Service( patient,  nurse,  serviceType);

        // Add the treatment to the Treatments vector
        synchronized (services) {
        	services.add(newSrevice);
            System.out.println("Service created:\n" + newSrevice.toString());
        }
    }
}
