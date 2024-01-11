
//Hila Masturov 318568110
//Inbar Sarel Barhom 301805362
//Dolev Mishali 206603060
//Roi Elbaz 305691792


package clinicProject;

import java.util.Scanner;

import gui.LoginGUI;
import gui.MenuGUI;
import gui.SignUpGUI;

public class Main {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		Clinic myClinic = new Clinic();
		myClinic.addDoctorManualy();
		myClinic.addNurseManualy();

		boolean menu = true;
		while (menu) {
			System.out.println("Welcome to our clinic!\n");
			System.out.print("If you are an existing: \n doctor (d),\n "
					+ "patient (p),\n nurse (n),\n or other (o),\n " + "press the corresponding key:\n ");
			char choice = scanner.next().charAt(0);

			switch (choice) {
			case 'o':
				displayOtherMenu(scanner);
				break;
			case 'p':
				displayPatientMenu(scanner);
				break;
			case 'd':
				displayDoctorMenu(scanner);
				break;
			case 'n':
				displayNurseMenu(scanner);
				break;
			case 's':
				menu = false;
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private static void displayOtherMenu(Scanner scanner) {
		boolean exit = false;

		while (!exit) {
			System.out.println("Other Menu:");
			System.out.println("1. Add Doctor");
			System.out.println("2. Add Nurse");
			System.out.println("3. Display All Doctors");
			System.out.println("4. Display All Nurses");
			System.out.println("9. Exit");
			System.out.print("Choose an action: ");
			int actionChoice = scanner.nextInt();

			switch (actionChoice) {
			case 1:
				Clinic.addDoctor();
				break;
			case 2:
				Clinic.addNurse();
				break;
			case 3:
				Clinic.displayAllDoctors();
				break;
			case 4:
				Clinic.displayAllNurses();
				break;
			case 9:
				exit = true;
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private static void displayPatientMenu(Scanner scanner) {

		LoginGUI loginGUI = new LoginGUI();
		SignUpGUI signUpGUI = new SignUpGUI();
		MenuGUI menuGUI = new MenuGUI();

		boolean exit = false;

		while (!exit) {
			System.out.println("Patient Menu:");
			System.out.println("1. Sign In");
			System.out.println("2. Sign Up");
			System.out.println("3. Schedule Appointment");
			System.out.println("9. Exit");
			System.out.print("Choose an action: ");
			int actionChoice = scanner.nextInt();

			switch (actionChoice) {
			case 1:
				loginGUI.setDisabled();
				loginGUI.setVisible(true);
				break;
			case 2:
				signUpGUI.setDisabled();
				signUpGUI.setVisible(true);
				break;
			case 3:
				menuGUI.setVisible(true);
				break;
			case 9:
				exit = true;
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
		System.out.println("Exiting the patient menu. Goodbye!");
	}

	private static void displayDoctorMenu(Scanner scanner) {

		boolean exit = false;

		while (!exit) {
			System.out.println("Doctor Menu:");
			System.out.println("1. Add Treatment");
			System.out.println("9. Exit");
			System.out.print("Choose an action: ");
			int actionChoice = scanner.nextInt();

			try {
				switch (actionChoice) {
				case 1:
					Clinic.createNewTreatment();
					break;
				case 9:
					exit = true;
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			} catch (Exception e) {
				System.out.println("Error");
				e.printStackTrace();
			}
		}
	}

	private static void displayNurseMenu(Scanner scanner) {

		boolean exit = false;

		while (!exit) {
			System.out.println("Nurse Menu:");
			System.out.println("1. Add Service");
			System.out.println("9. Exit");
			System.out.print("Choose an action: ");
			int actionChoice = scanner.nextInt();

			// Placeholder for functions to handle each choice in the Nurse menu
			switch (actionChoice) {
			case 1:
				Clinic.createNewService();
				break;
			case 9:
				exit = true;
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}

	}

}
