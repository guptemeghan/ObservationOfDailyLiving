import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPasswordField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.awt.Color;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	public static String ST_id;
	//public static String abcdef;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
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
	private String username;
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = this.textField.getText();
	}

	public LoginPage() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblLoginPage = new JLabel("Login");
		lblLoginPage.setForeground(Color.WHITE);
		lblLoginPage.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblLoginPage.setBounds(179, 1, 179, 34);
		contentPane.add(lblLoginPage);

		JLabel lblNewLabel = new JLabel("  username");
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(179, 77, 63, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("   password\r\n");
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(179, 128, 63, 14);
		contentPane.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFocusPainted(false);
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!(textField.getText().equals("") || passwordField.getText()
						.equals(""))) {
					String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
					String user = "aprasad3"; // For example, "jsmith"
					String passwd = "200015261"; // Your 9 digit student ID
													// number
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection conn = DriverManager.getConnection(jdbcURL,
								user, passwd);
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery("SELECT * FROM USERS");

						while (rs.next()) {
							String id = rs.getString("ID");
							String pwd = rs.getString("PASSWORD");
							ST_id = textField.getText();
							if (textField.getText().equals(id)
									&& passwordField.getText().equals(pwd)) {
								setVisible(false);
								if(rs.getInt(9) == 0)
								{
									PatientHome phome = new PatientHome();
									phome.setPatientname(textField.getText());
									phome.setVisible(true);
								}
								else
								{
									new PhysicianHome().setVisible(true);
								}
								
								
							}
							
							/*else
							{
								JOptionPane.showMessageDialog(null, "Invalid user id or password. Please try again");
								textField.setText("");
								passwordField.setText("");
							}
							*/
							
							
							
							System.out.println("id :" + id + " pass " + pwd);
						}
					} catch (SQLException e1) {
						//e1.printStackTrace();
					} catch (ClassNotFoundException ew) {
						//ew.printStackTrace();
					}

				} else {
					System.out.println("fgfg");
				}
			}
		});

		btnNewButton.setBounds(164, 189, 89, 23);
		contentPane.add(btnNewButton);

		textField = new JTextField();
		textField.setBounds(167, 97, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(165, 147, 87, 20);
		contentPane.add(passwordField);
	}

}
