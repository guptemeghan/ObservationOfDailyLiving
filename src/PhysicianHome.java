import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;
import java.sql.*;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PhysicianHome extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PhysicianHome frame = new PhysicianHome();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PhysicianHome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton addObservationType = new JButton("Add Observation Type");
		addObservationType.setFocusPainted(false);
		addObservationType.setBackground(Color.WHITE);
		addObservationType.setFont(new Font("Segoe UI", Font.BOLD, 11));
		addObservationType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new AddObsType().setVisible(true);
			}
		});
		addObservationType.setBounds(50, 72, 141, 23);
		contentPane.add(addObservationType);

		JButton viewPatients = new JButton("View Patients");
		viewPatients.setFocusPainted(false);
		viewPatients.setBackground(Color.WHITE);
		viewPatients.setFont(new Font("Segoe UI", Font.BOLD, 11));
		viewPatients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				(new ViewPatients()).setVisible(true);

			}
		});
		viewPatients.setBounds(50, 139, 141, 23);
		contentPane.add(viewPatients);

		JButton addAssociation = new JButton("Add Association");
		addAssociation.setFocusPainted(false);
		addAssociation.setBackground(Color.WHITE);
		addAssociation.setFont(new Font("Segoe UI", Font.BOLD, 11));
		addAssociation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new AddAsso().setVisible(true);
			}
		});
		addAssociation.setBounds(223, 72, 166, 23);
		contentPane.add(addAssociation);

		JLabel lblPhysicianHome = new JLabel("Physician Home");
		lblPhysicianHome.setForeground(Color.WHITE);
		lblPhysicianHome.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblPhysicianHome.setBounds(139, 11, 166, 23);
		contentPane.add(lblPhysicianHome);
		
		JButton btnViewReports = new JButton("View Reports");
		btnViewReports.setFocusPainted(false);
		btnViewReports.setBackground(Color.WHITE);
		btnViewReports.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnViewReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ViewReports().setVisible(true);
			}
		});
		btnViewReports.setBounds(223, 139, 166, 23);
		contentPane.add(btnViewReports);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.setBackground(Color.WHITE);
		btnExit.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(161, 198, 89, 23);
		contentPane.add(btnExit);
		
		JButton btnAssignClassTo = new JButton("Assign Class to Patient");
		btnAssignClassTo.setFocusPainted(false);
		btnAssignClassTo.setBackground(Color.WHITE);
		btnAssignClassTo.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnAssignClassTo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			setVisible(false);
			new AssignClassToPatient().setVisible(true);
			}
		});
		btnAssignClassTo.setBounds(126, 105, 179, 23);
		contentPane.add(btnAssignClassTo);
		
		JLabel lblNewLabel = new JLabel("logout");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				LoginPage lp=new LoginPage();
				lp.setVisible(true);
			}
		});
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(378, 236, 46, 14);
		contentPane.add(lblNewLabel);
	}
}
