package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import clinicProject.entity.Patient;
import clinicProject.utils.FileManager;

public class MenuGUI extends JFrame{
	
	private LoginGUI loginGUI;
	private SignUpGUI signUpGUI;
	private static final long serialVersionUID = 1L;
    private JTextArea patientInfoTextArea = new JTextArea();


	public MenuGUI() {
		super("Clinic Management System");
		this.init();
	    this.loginGUI = new LoginGUI();
	    this.signUpGUI = new SignUpGUI();
	}
	
	public void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        generateMainPanel();
	}
	
	public JMenuBar generateMenu() {
		JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        
        JMenuItem loadMenuItem = new JMenuItem("Load Patients");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        
        fileMenu.add(loadMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        
        JMenu loginMenu = new JMenu("Login");
        menuBar.add(loginMenu);
        
        JMenuItem loginMenuItem = new JMenuItem("Login");
        JMenuItem signUpMenuItem = new JMenuItem("Sign Up");
        
        loginMenu.add(loginMenuItem);
        loginMenu.add(signUpMenuItem);
        
        loadMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               List<Patient> pList = FileManager.getAllPatients();
               String content = "";
               for(Patient p : pList) {
            	   content += "ID: "+p.getID()+" Name: "+p.getName()+" Age: "+p.getAge()+"\n";
               }
               patientInfoTextArea.setText(content);
            }
        });
        
        exitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        loginMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginGUI.setVisible(true);
            }
        });
        
        signUpMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUpGUI.setVisible(true);
            }
        });
        
        return menuBar;
	}
	
	public void generateMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.setJMenuBar(generateMenu());
		mainPanel.add(new JScrollPane(patientInfoTextArea), BorderLayout.CENTER);
		this.add(mainPanel);
	}

}
