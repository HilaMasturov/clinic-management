package threads;

import clinicProject.entity.Doctor;
import clinicProject.entity.Patient;
import clinicProject.entity.Treatment;

import java.util.Vector;

public class CreateTreatmentThread implements Runnable {
    private final Vector<Treatment> treatments;
    private final Doctor doctor;
    private final Patient patient;
    private final String medicine;
    private final String diagnostic;

    public CreateTreatmentThread(Vector<Treatment> treatments, Doctor doctor, Patient patient, String medicine, String diagnostic) {
        this.treatments = treatments;
        this.doctor = doctor;
        this.patient = patient;
        this.medicine = medicine;
        this.diagnostic = diagnostic;
    }

    @Override
    public void run() {
        createTreatment(doctor, patient, medicine, diagnostic);
    }

    // Create a function to generate and return a treatment for a doctor and patient
    private void createTreatment(Doctor doctor, Patient patient, String medicine, String diagnostic) {
        Treatment newTreatment = new Treatment(patient, doctor, medicine, diagnostic);

        // Add the treatment to the Treatments vector
        synchronized (treatments) {
            treatments.add(newTreatment);
            System.out.println("Treatment created:\n" + newTreatment.toString());
        }
    }
}
