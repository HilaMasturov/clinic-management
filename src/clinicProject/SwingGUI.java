package clinicProject;
import javax.swing.*;

import clinicProject.entity.Patient;
import clinicProject.utils.FileManager;

import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class SwingGUI {
    private static String clientId; // Variable to store the client ID
    private String selectedDate;
    private String selectedProficiency;
    private String patientID;
    private String patientAge;
    private String patientName;
    private boolean isDoctorSelected;
    private static final String PATIENT_FILE = "patients.txt";
    JTextArea patientInfoTextArea = new JTextArea();
    public SwingGUI() {
    	
    	// Create the main frame
        JFrame mainFrame = new JFrame("Clinic Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(400, 300);

        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);

        // Create the "File" menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // Create menu items
        JMenuItem saveMenuItem = new JMenuItem("Save Patients");
        JMenuItem loadMenuItem = new JMenuItem("Load Patients");
        JMenuItem exitMenuItem = new JMenuItem("Exit");

        // Add menu items to the "File" menu
        fileMenu.add(saveMenuItem);
        fileMenu.add(loadMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);

        // Create the "Login" menu
        JMenu loginMenu = new JMenu("Login");
        menuBar.add(loginMenu);

        // Create menu items for "Login" and "Sign Up"
        JMenuItem loginMenuItem = new JMenuItem("Login");
        JMenuItem signUpMenuItem = new JMenuItem("Sign Up");


        
        // Add menu items to the "Login" menu
        loginMenu.add(loginMenuItem);
        loginMenu.add(signUpMenuItem);

        
        // Create the main panel for the main window
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainFrame.add(mainPanel);

        // Create a text area to display patient information

        mainPanel.add(new JScrollPane(patientInfoTextArea), BorderLayout.CENTER);

        
        // Action listeners for menu items
        saveMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logic to save patient information to a file
                try (PrintWriter writer = new PrintWriter(new FileWriter(PATIENT_FILE))) {
                    // Write patient information to the file
                    // You can replace this with your actual patient data
                    writer.println("Name: John Doe, ID: 1, Age: 30");
                    writer.println("Name: Jane Smith, ID: 2, Age: 25");
                    writer.println("Name: Bob Johnson, ID: 3, Age: 45");
                    // Add more patients as needed

                    // Show a confirmation message
                    JOptionPane.showMessageDialog(mainFrame, "Patients saved to file.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Error saving patients to file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement logic to load patient information from a file
                try (BufferedReader reader = new BufferedReader(new FileReader(PATIENT_FILE))) {
                    StringBuilder patientInfo = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        patientInfo.append(line).append("\n");
                    }
                    patientInfoTextArea.setText(patientInfo.toString());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(mainFrame, "Error loading patients from file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Set the main frame properties
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    
        // Login Window
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(300, 150);
        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // Use GridLayout for better alignment
        loginFrame.add(loginPanel);

        JLabel idLabel = new JLabel("ID:");
        JTextField idTextField = new JTextField();
        JButton loginButton = new JButton("Send");

        loginPanel.add(idLabel);
        loginPanel.add(idTextField);
        loginPanel.add(new JLabel()); // Empty label for spacing
        loginPanel.add(loginButton);

        loginFrame.setLocationRelativeTo(null); // Center the login window
        loginFrame.setVisible(false);

        // Sign-up Window
        JFrame signUpFrame = new JFrame("Sign Up");
        signUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signUpFrame.setSize(400, 200);
        JPanel signUpPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        signUpFrame.add(signUpPanel);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameTextField = new JTextField();
        JLabel idLabel2 = new JLabel("ID:");
        JTextField idTextField2 = new JTextField();
        //idTextField2.setEditable(false); // ID field is not editable
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageTextField = new JTextField();
        JButton signUpButton = new JButton("Sign Up");

        signUpFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                nameTextField.setText("");
                ageTextField.setText("");
                idTextField2.setText("");
            }
        });
        signUpPanel.add(nameLabel);
        signUpPanel.add(nameTextField);
        signUpPanel.add(idLabel2);
        signUpPanel.add(idTextField2);
        signUpPanel.add(ageLabel);
        signUpPanel.add(ageTextField);
        signUpPanel.add(new JLabel()); // Empty label for spacing
        signUpPanel.add(signUpButton);

        signUpFrame.setLocationRelativeTo(null);
        signUpFrame.setVisible(false);

        // Appointment Window
        JFrame appointmentFrame = new JFrame("Appointment");
        appointmentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appointmentFrame.setSize(400, 300);
        JPanel appointmentPanel = new JPanel(new GridLayout(5, 2, 10, 10)); // 7 rows, 2 columns
        appointmentFrame.add(appointmentPanel);

        JRadioButton doctorRadioButton = new JRadioButton("Doctor");
        JRadioButton nurseRadioButton = new JRadioButton("Nurse");
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(doctorRadioButton);
        radioButtonGroup.add(nurseRadioButton);

        JLabel proficiencyLabel = new JLabel("Proficiency:");
        String[] proficiencyOptions = {"Family Doctor", "Orthopedic Doctor", "Kids", "Cardiologist"};
        JComboBox<String> proficiencyComboBox = new JComboBox<>(proficiencyOptions);

        JLabel dateLabel = new JLabel("Date (yyyy-MM-dd):");
        JTextField dateTextField = new JTextField();
        
        JButton appointmentButton = new JButton("Send");

        appointmentPanel.add(doctorRadioButton);
        appointmentPanel.add(nurseRadioButton);
        appointmentPanel.add(proficiencyLabel);
        appointmentPanel.add(proficiencyComboBox);
        appointmentPanel.add(dateLabel);
        appointmentPanel.add(dateTextField);
        appointmentPanel.add(new JLabel());
        appointmentPanel.add(appointmentButton);

        doctorRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proficiencyComboBox.setEnabled(true);
            }
        });

        nurseRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proficiencyComboBox.setEnabled(false);
            }
        });

        appointmentFrame.setLocationRelativeTo(null);
        appointmentFrame.setVisible(false);

        // Action listeners for buttons
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement your logic here
                String id = idTextField.getText();

                if (!id.matches("^[0-9]*$")) {
                    // Mark the invalid label in red
                    idLabel.setForeground(Color.RED);
                    JOptionPane.showMessageDialog(loginFrame, "Invalid ID format (numbers only).", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (isPatientExists(id) != null) {
                	Patient p = isPatientExists(id);
                    clientId = id;
                    patientName = p.getName();
                    patientID = p.getID();
                    patientAge = Integer.toString(p.getAge());
                    idLabel.setForeground(Color.BLACK);
                    signUpFrame.setVisible(false);
                    loginFrame.setVisible(false);
                    appointmentFrame.setVisible(true);
                    idTextField2.setText(clientId);
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "The ID doesn't exist, please sign up.", "Error", JOptionPane.ERROR_MESSAGE);
                    clientId = id;
                    loginFrame.setVisible(false);
                    signUpFrame.setVisible(true);
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                 patientName = nameTextField.getText();
                 patientID = idTextField2.getText();
                 patientAge = ageTextField.getText();

                if (!patientName.matches("^[a-zA-Z\\s]*$")) {
                    JOptionPane.showMessageDialog(signUpFrame, "Name can only contain letters and spaces.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!patientAge.matches("^[0-9]*$")) {
                    JOptionPane.showMessageDialog(signUpFrame, "Age can only contain numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                	Patient patient = new Patient(patientID,patientName,Integer.parseInt(patientAge));
                	FileManager.savePatient(patient);
  
                    signUpFrame.setVisible(false);
                    appointmentFrame.setVisible(true);

                    JOptionPane signUpSuccessPopup = new JOptionPane("Successful Sign Up", JOptionPane.INFORMATION_MESSAGE);
                    JDialog signUpSuccessDialog = signUpSuccessPopup.createDialog("Success");
                    signUpSuccessDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    signUpSuccessDialog.setVisible(true);

                }
            }
        });

        appointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement your validation logic here
            	selectedDate = dateTextField.getText();
                selectedProficiency = (String) proficiencyComboBox.getSelectedItem(); 
                isDoctorSelected = doctorRadioButton.isSelected();
                Patient p1 = new Patient(patientID,patientName,Integer.parseInt(patientAge));
                String dateStr = dateTextField.getText();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date = dateFormat.parse(dateStr);
                    JOptionPane.showMessageDialog(appointmentFrame, "Appointment Scheduled Successfully to 08:00 AM", "Success", JOptionPane.INFORMATION_MESSAGE);
                    Clinic.schduleAppointment(selectedProficiency,isDoctorSelected,p1,selectedDate);              
                    // Close the appointment window
                    appointmentFrame.dispose();
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(appointmentFrame, "Invalid date format (yyyy-MM-dd).", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        loginMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the login frame when "Login" menu item is selected
                signUpFrame.setVisible(false); // Hide sign-up frame if it's open
                loginFrame.setVisible(true);
            }
        });

        // Add action listener to the "Sign Up" menu item
        signUpMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the sign-up frame when "Sign Up" menu item is selected
                loginFrame.setVisible(false); // Hide login frame if it's open
                signUpFrame.setVisible(true);
            }
        });
        
    }
    
    static void saveAppointmentInfo(String patientInfo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PATIENT_FILE, true))) {
            writer.println(patientInfo);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving appointment information to file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static void appendPatientToFile(String patientInfo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PATIENT_FILE, true))) {
            // Append patient information to the file
            writer.println(patientInfo);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error saving patient information to file.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static Patient isPatientExists(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATIENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	String[] data = line.split(",");
                // Check if the line contains the patient ID
                if (data[1].contains(id)) {
                    return new Patient(data[1], data[0], Integer.parseInt(data[2]));
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error checking patient existence.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    
    private static void scheduleAppointment(String dateStr, String proficiency) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(dateStr);
            // Implement your appointment scheduling logic here
            // ...
        } catch (ParseException ex) {
            // Handle date parsing errors
        }
    }

    // Example method to display patient reception information (replace with your logic)
    private static String getPatientReceptionInfo() {
        // Implement your logic to retrieve patient reception information
        // Return the information as a formatted string
        return "Patient Reception Information:\n- Receptionist: Alice\n- Date: 2023-09-17";
    }

    // Example method to show a list of patients (replace with your logic)
    private static String getPatientsList() {
        // Implement your logic to retrieve the list of patients
        // Return the list as a formatted string
        return "Patients:\n1. John Doe\n2. Jane Smith\n3. Bob Johnson";
    }
    // Method to display information in the JTextArea
    public void displayInfo(String info) {
        patientInfoTextArea.setText(info);
    }

    // Other methods for GUI components and actions...

    // Example: Method to show a popup message
    public void showPopupMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    // Example: Method to update GUI elements based on user actions
    public void updateUserInterface() {
        // Implement logic to update GUI elements based on user actions
    }
}
