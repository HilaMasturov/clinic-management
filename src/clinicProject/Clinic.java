package clinicProject;

import java.util.Vector;
import java.util.ArrayList;
import java.util.List;

import clinicProject.entity.Appointment;
import clinicProject.entity.Doctor;
import clinicProject.entity.Nurse;
import clinicProject.entity.Patient;
import clinicProject.entity.Schedule;
import clinicProject.entity.Service;
import clinicProject.entity.Shift;
import clinicProject.entity.Treatment;
import gui.LoginGUI;
import threads.CreateServiceThread;
import threads.CreateTreatmentThread;

import java.util.Scanner;

public class Clinic {
	static Vector<Treatment> Treatments = new Vector<Treatment>();
	static Vector<Service> Services = new Vector<Service>();
	static Vector<Doctor> Doctors = new Vector<Doctor>();
	static Vector<Nurse> Nurses = new Vector<Nurse>();
	static Vector<Patient> Patients = new Vector<Patient>();
	static Vector<Appointment> Appointments = new Vector<Appointment>();
	static Vector<Shift> Shifts = new Vector<Shift>();
	static Vector<Schedule> Schdule = new Vector<Schedule>();

//----------------------------------------------------____------------------------------

	public void addDoctorManualy() {
		Doctor doc1 = new Doctor("111", "Avi", 50, 1111, "54", "Cardiologist");
		Doctor doc2 = new Doctor("112", "Yosi", 60, 5555, "54", "Family Doctor");
		Doctor doc3 = new Doctor("113", "Rina", 40, 6666, "54", "Orthopedic Doctor");
		Doctor doc4 = new Doctor("114", "David", 30, 7777, "54", "Kids");
		Doctor doc5 = new Doctor("115", "Amit", 55, 8888, "54", "General");
		Doctor doc6 = new Doctor("116", "Shaked", 535, 9999, "54", "Clown");
		Doctors.add(doc1);
		Doctors.add(doc2);
		Doctors.add(doc3);
		Doctors.add(doc4);
		Doctors.add(doc5);
		Doctors.add(doc6);
	}

	public void addNurseManualy() {
		Nurse n1 = new Nurse("111", "Avi", 50, 2222, "54");
		Nurse n2 = new Nurse("112", "Yosi", 60, 3333, "54");
		Nurse n3 = new Nurse("113", "Rina", 40, 4444, "54");

		Nurses.add(n1);
		Nurses.add(n2);
		Nurses.add(n3);
	}

	public static void addDoctor() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter doctor ID: ");
		String doctorID = scanner.nextLine();
		System.out.print("Enter doctor Name: ");
		String doctorName = scanner.nextLine();
		System.out.print("Enter doctor age: ");
		int doctorAge = scanner.nextInt();
		System.out.print("Enter doctor employee number: ");
		int doctorEmployeeNum = scanner.nextInt();
		System.out.print("Enter doctor license ID: ");
		String doctorLicenseID = scanner.next();
		System.out.print("Enter doctor proficiency: ");
		String doctorProficiency = scanner.next();

		Doctor newDoctor = new Doctor(doctorID, doctorName, doctorAge, doctorEmployeeNum, doctorLicenseID,
				doctorProficiency);
		Doctors.add(newDoctor);

		Doctor.addDoctorCount();

		System.out.println("Doctor added successfully!");
	}

	public static void addNurse() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter nurse ID: ");
		String nurseID = scanner.nextLine();
		System.out.print("Enter nurse Name: ");
		String nurseName = scanner.nextLine();
		System.out.print("Enter nurse age: ");
		int nurseAge = scanner.nextInt();
		System.out.print("Enter nurse employee number: ");
		int nurseEmployeeNum = scanner.nextInt();
		System.out.print("Enter nurse license ID: ");
		String nurseLicenseID = scanner.next();

		Nurse newNurse = new Nurse(nurseID, nurseName, nurseAge, nurseEmployeeNum, nurseLicenseID);
		Nurses.add(newNurse);

		System.out.println("Nurse added successfully!");
	}

	// Function to display all doctors
	public static void displayAllDoctors() {
		System.out.println("List of Doctors:");
		for (Doctor doctor : Doctors) {
			System.out.println(doctor.toString());
		}
	}

	// Function to display all nurses
	public static void displayAllNurses() {
		System.out.println("List of Nurses:");
		for (Nurse nurse : Nurses) {
			System.out.println(nurse.toString());
		}
	}

	public static void createShift(int employee, Appointment appointment, String date) {
		Shift newShift = new Shift(employee, appointment, date);
		Shifts.add(newShift);
	}

	// Function to create an appointment and schedule it
	public static int schduleAppointment(String prof, boolean role, Patient pt, String date) {
		List<Shift> shiftList = new ArrayList<>();
		for (Shift item : Shifts) {
			if (item.getDate().equals(date)) {
				shiftList.add(item);
			}
		}

		if (role) { // If doctor
			List<Doctor> doctorProf = new ArrayList<>();
			for (Doctor item : Doctors) {
				if (item.getProfisiency().equals(prof)) {
					doctorProf.add(item);
				}
			}

			for (Doctor item : doctorProf) {
				int employeeNum1 = item.getEmployeeNum();
				for (Shift item1 : shiftList) {
					if (item1.getEmployee() == employeeNum1) {
						item1.addAppointmentInShift();
						Appointment newApp = new Appointment(date, employeeNum1, pt, true);
						Appointments.add(newApp);
						Schdule.add(new Schedule(newApp, pt));
						return 1;

					}
				}
			}
		} else {
			for (Nurse item : Nurses) {
				int employeeNum1 = item.getEmployeeNum();
				for (Shift item1 : shiftList) {
					if (item1.getEmployee() == employeeNum1) {

						item1.addAppointmentInShift();
						Appointment newApp = new Appointment(date, employeeNum1, pt, false);
						Appointments.add(newApp);
						Schdule.add(new Schedule(newApp, pt));
						return 1;
					}
				}
			}
		}
		return 0;
	}

	public static void createNewTreatment() {
	System.out.println("Please enter your employee number:");
	Scanner scanner = new Scanner(System.in);
	
	int employeeNum = scanner .nextInt();

	Doctor doctor = Clinic.findDoctor(employeeNum);
	if (doctor != null) {

		System.out.println("Please enter ID of the patient asking for treatment:");
		String pID = scanner.next();

		if (LoginGUI.isPatientExists(pID) != null) {

			Patient patient = LoginGUI.isPatientExists(pID);
			Clinic myClinic = new Clinic();

			System.out.println("Please enter suggested medicine:");
			String medicine = scanner.next();

			System.out.println("Please enter your diagnose:");
			String diagnostic = scanner.next();

			Thread thread = new Thread(
					new CreateTreatmentThread(Treatments, doctor, patient, medicine, diagnostic));
			thread.start();
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("Patient ID Not Found!");
		}
	} else {
		System.out.println("Employee number Not Found!");
	}
}
	
	public static void createNewService() {
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Please enter your employee number:");

		int employeeNum = scanner.nextInt();

		Nurse nurse = Clinic.findNurse(employeeNum);
		if (nurse != null) {

			System.out.println("Please enter ID of the patient asking for service:");
			String pID = scanner.next();

			if (LoginGUI.isPatientExists(pID) != null) {

				Patient patient = LoginGUI.isPatientExists(pID);

				System.out.println("Choose the desired service type:");
				for (Service.serviceType type : Service.serviceType.values()) {
					System.out.println(type.ordinal() + 1 + ". " + type.getDescription());
				}

				int choice;

				do {
					System.out.print("Enter your choice (1-" + Service.serviceType.values().length + "): ");
					while (!scanner.hasNextInt()) {
						System.out.println("Invalid input. Please enter a number.");
						scanner.next();
					}
					choice = scanner.nextInt();
				} while (choice < 1 || choice > Service.serviceType.values().length);

				Service.serviceType selectedServiceType = Service.serviceType.values()[choice - 1];
				System.out.println("You have selected: " + selectedServiceType.getDescription());

				Thread thread = new Thread(new CreateServiceThread(Services, nurse, patient, selectedServiceType));
				thread.start();
				try {
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			} else {
				System.out.println("Patient ID Not Found!");
			}
		} else {
			System.out.println("Employee number Not Found!");
		}

	}

	// Function to find a nurse by employee number
	public static Nurse findNurse(int employeeNum) {
		for (Nurse item1 : Nurses) {
			if (item1.getEmployeeNum() == employeeNum) {
				return item1;
			}
		}
		return null; // Return null if nurse is not found
	}

	// Function to find a doctor by employee number
	public static Doctor findDoctor(int employeeNum) {
		for (Doctor item1 : Doctors) {
			if (item1.getEmployeeNum() == employeeNum) {
				return item1;
			}
		}
		return null; // Return null if doctor is not found
	}

	// Function to find a patient by ID
	public static Patient findPatient(String ID) {
		for (Patient item1 : Patients) {
			if (item1.getID().equals(ID)) {
				return item1;
			}
		}
		return null; // Return null if patient is not found
	}

}
