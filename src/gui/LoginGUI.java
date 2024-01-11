package gui;

import javax.swing.*;

import clinicProject.entity.Patient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class LoginGUI extends JFrame {
    private static final long serialVersionUID = 1L;
	private JTextField idTextField;
	private SignUpGUI signUpGUI;
	private AppointmentGUI appointmentGUI;
	private boolean IsDisabled;
    private static final String PATIENT_FILE = "patients.txt";

    public LoginGUI() {
        super("Login");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    this.signUpGUI = new SignUpGUI();
	    this.appointmentGUI = new AppointmentGUI();
        this.init();
    }

    public void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 150);

        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        this.add(loginPanel);

        JLabel idLabel = new JLabel("ID:");
        idTextField = new JTextField();
        JButton loginButton = new JButton("Send");
        
        loginPanel.add(idLabel);
        loginPanel.add(idTextField);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idTextField.getText();

                if (!id.matches("^[0-9]*$")) {
                    JOptionPane.showMessageDialog(null, "Invalid ID format (numbers only).", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (isPatientExists(id) != null) {
                	if(IsDisabled) {
                        JOptionPane.showMessageDialog(null, "Signed Up Successesfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                		dispose();
                	}else {
                	Patient p = isPatientExists(id);
                	appointmentGUI.getPatient(p.getName(), p.getID(), Integer.toString(p.getAge()));
                	signUpGUI.setVisible(false);
                	setVisible(false);
                    appointmentGUI.setVisible(true);}
                } else {
                    JOptionPane.showMessageDialog(null, "The ID doesn't exist, please sign up.", "Error", JOptionPane.ERROR_MESSAGE);
                    setVisible(false);
                    if(IsDisabled) {
                    	dispose();
                	}else {
                    signUpGUI.setVisible(true);
                	}
                }
            }
        });

        this.setLocationRelativeTo(null);
    }
    public static Patient isPatientExists(String id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATIENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	String[] data = line.split(",");
                if (data[1].contains(id)) {
                    return new Patient(data[1], data[0], Integer.parseInt(data[2]));
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error checking patient existence.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public void setDisabled() {
    	IsDisabled = true;
    }
}
