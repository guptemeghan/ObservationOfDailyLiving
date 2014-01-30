import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JRadioButton;
import java.awt.Color;

public class SignUpPhysician extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpPhysician frame = new SignUpPhysician();
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
	public SignUpPhysician() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPhysicainSignUp = new JLabel("Physician Sign Up");
		lblPhysicainSignUp.setForeground(Color.WHITE);
		lblPhysicainSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhysicainSignUp.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblPhysicainSignUp.setBounds(141, 11, 166, 24);
		contentPane.add(lblPhysicainSignUp);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(39, 56, 46, 14);
		contentPane.add(lblName);

		JLabel lblId = new JLabel("Id");
		lblId.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblId.setForeground(Color.WHITE);
		lblId.setBounds(39, 87, 46, 14);
		contentPane.add(lblId);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(39, 179, 46, 14);
		contentPane.add(lblPassword);

		JLabel lblClinic = new JLabel("Clinic");
		lblClinic.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblClinic.setForeground(Color.WHITE);
		lblClinic.setBounds(39, 210, 46, 14);
		contentPane.add(lblClinic);

		textField = new JTextField();
		textField.setBounds(102, 53, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(102, 84, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(102, 177, 86, 20);
		contentPane.add(passwordField);

		textField_2 = new JTextField();
		textField_2.setBounds(102, 208, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		final JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBackground(Color.BLACK);
		rdbtnMale.setForeground(Color.WHITE);
		rdbtnMale.setBounds(102, 146, 60, 23);
		contentPane.add(rdbtnMale);

		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBackground(Color.BLACK);
		rdbtnFemale.setForeground(Color.WHITE);
		rdbtnFemale.setBounds(169, 146, 69, 23);
		contentPane.add(rdbtnFemale);

		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnSignUp.setBackground(Color.WHITE);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
				String user = "aprasad3"; // For example, "jsmith"
				String passwd = "200015261"; // Your 9 digit student ID number
				String abc = "";
				java.util.Date date = new java.util.Date();
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());

				/*
				 * if (rdbtnYes.isSelected()){
				 * 
				 * }
				 */
				if (rdbtnMale.isSelected()) {
					abc = "M";
				} else {
					abc = "F";
				}

				/*
				 * if(textField.getText() == "" || textField_1.getText() == ""
				 * || textField_2.getText() == "" || textField_3.getText() == ""
				 * || textField_4.getText() == "" || textField_5.getText() == ""
				 * ) {
				 * 
				 * }
				 */
				// else
				{
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
					} catch (ClassNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					Connection conn = null;
					try {
						conn = DriverManager.getConnection(jdbcURL, user,
								passwd);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
					}
					try {

						PreparedStatement pst = conn
								.prepareStatement("INSERT INTO USERS VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
						pst.setString(1, (textField_1.getText()));
						pst.setString(2, (textField.getText()));

						pst.setString(3, "Hidden");
						pst.setInt(4, Integer.parseInt(textField_3.getText()));
						pst.setString(5, abc);

						pst.setString(6, (passwordField.getText()));
						pst.setString(7, "No");
						pst.setString(8, (textField_2.getText()));
						pst.setInt(9, 1);
						pst.setString(10, null);
						pst.setString(11, null);
						pst.setString(12, null);
						pst.setDate(13, sqlDate);
						pst.setInt(14, 0);
						pst.executeUpdate();
						pst.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
					}
					try {
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
					}
				}

				setVisible(false);
				(new PhysicianHome()).setVisible(true);

			}
		});
		btnSignUp.setBounds(300, 227, 89, 23);
		contentPane.add(btnSignUp);

		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblAge.setForeground(Color.WHITE);
		lblAge.setBounds(39, 125, 46, 14);
		contentPane.add(lblAge);

		textField_3 = new JTextField();
		textField_3.setBounds(102, 115, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblSex = new JLabel("Sex");
		lblSex.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblSex.setForeground(Color.WHITE);
		lblSex.setBounds(39, 150, 46, 14);
		contentPane.add(lblSex);

	}
}
