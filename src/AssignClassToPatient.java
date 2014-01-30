import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.sql.*;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class AssignClassToPatient extends JFrame {

	private JPanel contentPane;
	static String b = null;
	//static String pid = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AssignClassToPatient frame = new AssignClassToPatient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AssignClassToPatient() {
		setFont(new Font("Segoe UI", Font.BOLD, 11));
		setTitle("Assignment");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 192);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
			
		final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
		final String user = "aprasad3"; // For example, "jsmith"
		final String passwd = "200015261"; // Your 9 digit student ID number
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager
					.getConnection(jdbcURL, user, passwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Statement stmt = null;
		try {
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		String sql = "select name from users minus select name from users where id in (select ptid from patientclass group by ptid having count(ptid) = 4)";
		int rowcount = 1;
		ResultSet rs1  = null;
		try {
			rs1 = stmt.executeQuery(sql);
			
			rs1.last();
			rowcount = rs1.getRow();
			rs1.first();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String a[] = new String[rowcount];
		int i = 0;
		try {
			while(rs1.next())
			{
				a[i] = rs1.getString("NAME");
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final JComboBox comboBox = new JComboBox(a);
		comboBox.setBounds(27, 54, 116, 20);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("Continue");
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnNewButton.setFocusPainted(false);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				b = (String) comboBox.getSelectedItem();
				setVisible(false);
				new Assignment().setVisible(true);
			};
			
		});
		btnNewButton.setBounds(193, 53, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnBack.setFocusPainted(false);
		btnBack.setForeground(Color.BLACK);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			setVisible(false);
			new PhysicianHome().setVisible(true);
			}
			
		});
		btnBack.setBounds(121, 110, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblPatient = new JLabel("Patient");
		lblPatient.setForeground(Color.WHITE);
		lblPatient.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblPatient.setBounds(28, 29, 46, 14);
		contentPane.add(lblPatient);
		
	}
}
