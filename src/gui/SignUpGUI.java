package gui;

import javax.swing.*;

import clinicProject.entity.Patient;
import clinicProject.utils.FileManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpGUI extends JFrame {
    private static final long serialVersionUID = 1L;
	private JTextField nameTextField;
    private JTextField idTextField;
    private JTextField ageTextField;
	private boolean IsDisabled;
	private AppointmentGUI appointmentGUI;


    public SignUpGUI() {
        super("Sign Up");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    this.appointmentGUI = new AppointmentGUI();
        this.init();
    }

    public void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 200);

        JPanel signUpPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        this.add(signUpPanel);

        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField();
        JLabel idLabel2 = new JLabel("ID:");
        idTextField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        ageTextField = new JTextField();
        JButton signUpButton = new JButton("Sign Up");

        signUpPanel.add(nameLabel);
        signUpPanel.add(nameTextField);
        signUpPanel.add(idLabel2);
        signUpPanel.add(idTextField);
        signUpPanel.add(ageLabel);
        signUpPanel.add(ageTextField);
        signUpPanel.add(new JLabel());
        signUpPanel.add(signUpButton);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String patientName = nameTextField.getText();
                String patientID = idTextField.getText();
                String patientAge = ageTextField.getText();

                if (!patientName.matches("^[a-zA-Z\\s]*$")) {
                    JOptionPane.showMessageDialog(null, "Name can only contain letters and spaces.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!patientAge.matches("^[0-9]*$")) {
                    JOptionPane.showMessageDialog(null, "Age can only contain numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                	Patient patient = new Patient(patientID,patientName,Integer.parseInt(patientAge));
                	FileManager.savePatient(patient);
  
                	appointmentGUI.getPatient(patient.getName(), patient.getID(), Integer.toString(patient.getAge()));
                	
                    JOptionPane signUpSuccessPopup = new JOptionPane("Successful Sign Up", JOptionPane.INFORMATION_MESSAGE);
                    JDialog signUpSuccessDialog = signUpSuccessPopup.createDialog("Success");
                    signUpSuccessDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    signUpSuccessDialog.setVisible(true);
                    if(IsDisabled) {
                    	dispose();
                    }
                    else {
                    setVisible(false);
                    appointmentGUI.setVisible(true);
                    }
                }
            }
        });

        this.setLocationRelativeTo(null);
    }
    public void setDisabled() {
    	IsDisabled = true;
    }
}
