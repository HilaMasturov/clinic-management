package gui;

import javax.swing.*;

import clinicProject.Clinic;
import clinicProject.entity.Patient;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppointmentGUI extends JFrame {
    private static final long serialVersionUID = 1L;
	private String selectedDate;
    private String selectedProficiency;
    private String patientID;
    private String patientAge;
    private String patientName;
    private boolean isDoctorSelected;

    public AppointmentGUI() {
        super("Appointment");
        init();
    }

    public void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        generateAppointmentPanel();
    }

    public void generateAppointmentPanel() {
        JPanel appointmentPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        JRadioButton doctorRadioButton = new JRadioButton("Doctor");
        JRadioButton nurseRadioButton = new JRadioButton("Nurse");
        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(doctorRadioButton);
        radioButtonGroup.add(nurseRadioButton);

        JLabel proficiencyLabel = new JLabel("Proficiency:");
        String[] proficiencyOptions = {"General","Family Doctor", "Orthopedic Doctor", "Kids","Clown", "Cardiologist"};
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

        this.add(appointmentPanel);


        appointmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	selectedDate = dateTextField.getText();
                selectedProficiency = (String) proficiencyComboBox.getSelectedItem(); 
                isDoctorSelected = doctorRadioButton.isSelected();
                Patient p1 = new Patient(patientID,patientName,Integer.parseInt(patientAge));
                String dateStr = dateTextField.getText();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date = dateFormat.parse(dateStr);
            		Clinic.createShift(1111, null, selectedDate);
                    int result = Clinic.schduleAppointment(selectedProficiency,isDoctorSelected,p1,selectedDate);              
                    if(result > 0) {
                        JOptionPane.showMessageDialog(null, "Appointment Scheduled Successfully to 08:00 AM", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Appointment Schedule Faild", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    dispose();
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date format (yyyy-MM-dd).", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void getPatient(String name, String id, String age) {
    	patientName = name;
    	patientID = id;
    	patientAge = age;
    }
}
