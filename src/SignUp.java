import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ButtonGroup;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.factories.FormFactory;
//import com.jgoodies.forms.layout.RowSpec;

//import java.awt.GridLayout;
//import java.beans.Statement;
import java.sql.*;

import javax.swing.SwingConstants;
import java.awt.Color;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private final ButtonGroup buttonGroup_3 = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSignUpPage = new JLabel("Sign Up Page");
		lblSignUpPage.setForeground(Color.WHITE);
		lblSignUpPage.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblSignUpPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUpPage.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSignUpPage.setBounds(141, 11, 139, 28);
		contentPane.add(lblSignUpPage);

		JLabel lblId = new JLabel("ID");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblId.setBounds(25, 50, 46, 14);
		contentPane.add(lblId);

		textField = new JTextField();
		textField.setBounds(104, 47, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblName.setBounds(25, 71, 46, 14);
		contentPane.add(lblName);

		textField_1 = new JTextField();
		textField_1.setBounds(104, 69, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblStreetAddress = new JLabel("Street Address");
		lblStreetAddress.setForeground(Color.WHITE);
		lblStreetAddress.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblStreetAddress.setBounds(25, 95, 87, 14);
		contentPane.add(lblStreetAddress);

		textField_2 = new JTextField();
		textField_2.setBounds(104, 93, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel = new JLabel("City");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNewLabel.setBounds(230, 96, 46, 14);
		contentPane.add(lblNewLabel);

		textField_3 = new JTextField();
		textField_3.setBounds(267, 93, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblState = new JLabel("State");
		lblState.setForeground(Color.WHITE);
		lblState.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblState.setBounds(25, 121, 46, 14);
		contentPane.add(lblState);

		textField_4 = new JTextField();
		textField_4.setBounds(104, 118, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Pincode");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNewLabel_1.setBounds(211, 121, 46, 14);
		contentPane.add(lblNewLabel_1);

		textField_5 = new JTextField();
		textField_5.setBounds(267, 118, 86, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		JLabel lblAge = new JLabel("Age");
		lblAge.setForeground(Color.WHITE);
		lblAge.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblAge.setBounds(25, 146, 46, 14);
		contentPane.add(lblAge);

		textField_6 = new JTextField();
		textField_6.setBounds(104, 143, 86, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);

		final JRadioButton rdbtnNewRadioButton = new JRadioButton("Male");
		rdbtnNewRadioButton.setBackground(Color.BLACK);
		rdbtnNewRadioButton.setForeground(Color.WHITE);
		rdbtnNewRadioButton.setFont(new Font("Segoe UI", Font.BOLD, 11));
		buttonGroup_1.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(104, 171, 65, 23);
		contentPane.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Female");
		rdbtnNewRadioButton_1.setBackground(Color.BLACK);
		rdbtnNewRadioButton_1.setForeground(Color.WHITE);
		rdbtnNewRadioButton_1.setFont(new Font("Segoe UI", Font.BOLD, 11));
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(171, 171, 73, 23);
		contentPane.add(rdbtnNewRadioButton_1);

		JLabel lblSex = new JLabel("Sex");
		lblSex.setForeground(Color.WHITE);
		lblSex.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblSex.setBounds(25, 171, 46, 14);
		contentPane.add(lblSex);

		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setBackground(Color.WHITE);
		btnSignUp.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnSignUp.setFocusPainted(false);
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
				if (rdbtnNewRadioButton.isSelected()) {
					abc = "M";
				} else {
					abc = "F";
				}

				if (textField.getText().equals("")
						|| textField_1.getText().equals("")
						|| passwordField.getText().equals("")
						|| textField_2.getText().equals("")
						|| textField_3.getText().equals("")
						|| textField_4.getText().equals("")
						|| textField_5.getText().equals("")
						|| textField_6.getText().equals("")) {
					setVisible(false);
					(new SignUp()).setVisible(true);
				} else {
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
						pst.setString(1, (textField.getText()));
						pst.setString(2, (textField_1.getText()));

						pst.setString(3, (textField_2.getText()));
						pst.setInt(4, Integer.parseInt(textField_6.getText()));
						pst.setString(5, abc);

						pst.setString(6, (passwordField.getText()));
						pst.setString(7, "Yes");
						pst.setString(8, null);
						pst.setInt(9, 0);
						pst.setString(10, (textField_3.getText()));
						pst.setString(11, (textField_4.getText()));
						pst.setString(12, (textField_5.getText()));
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
					setVisible(false);
					(new LoginPage()).setVisible(true);

				}

			}
		});
		btnSignUp.setBounds(80, 227, 89, 23);
		contentPane.add(btnSignUp);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblPassword.setBounds(211, 50, 65, 14);
		contentPane.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(267, 47, 86, 20);
		contentPane.add(passwordField);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblStatus.setBounds(25, 196, 46, 14);
		contentPane.add(lblStatus);

		final JRadioButton rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBackground(Color.BLACK);
		rdbtnYes.setForeground(Color.WHITE);
		rdbtnYes.setFont(new Font("Segoe UI", Font.BOLD, 11));
		buttonGroup_2.add(rdbtnYes);
		rdbtnYes.setBounds(104, 197, 46, 23);
		contentPane.add(rdbtnYes);

		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBackground(Color.BLACK);
		rdbtnNo.setForeground(Color.WHITE);
		rdbtnNo.setFont(new Font("Segoe UI", Font.BOLD, 11));
		buttonGroup_3.add(rdbtnNo);
		rdbtnNo.setBounds(171, 197, 109, 23);
		contentPane.add(rdbtnNo);

		JButton btnBack = new JButton("Back");
		btnBack.setBackground(Color.WHITE);
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnBack.setFocusPainted(false);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new PatientOrPhysician().setVisible(true);
			}
		});
		btnBack.setBounds(191, 227, 89, 23);
		contentPane.add(btnBack);

	}
}
