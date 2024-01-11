package clinicProject.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import clinicProject.entity.Patient;


public class FileManager {
    private static final String FILE_PATH = "patients.txt";

    public static void savePatient(Patient patient) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(patient.getID() + "," + patient.getName() + "," + patient.getAge());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Patient getPatient(String ID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(ID)) {
                    return new Patient(parts[0], parts[1], Integer.parseInt(parts[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    patients.add(new Patient(parts[0], parts[1], Integer.parseInt(parts[2])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return patients;
    }
}