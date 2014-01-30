import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
;
public class AddAsso extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAsso frame = new AddAsso();
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
	public AddAsso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
		final String user = "aprasad3";	// For example, "jsmith"
	    final String passwd = "200015261";	// Your 9 digit student ID number
	    
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(jdbcURL, user, passwd);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		Statement stmt = null;
		try {
			stmt = (Statement) conn.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		ResultSet rs = null;
		try {
			rs = ((java.sql.Statement) stmt).executeQuery("SELECT DISTINCT TNAME FROM TYPE WHERE GENERALFLAG = '"+ 1 + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int count = 0;
		try {
			while(rs.next())
			{
				count++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		ResultSet rs1 = null;
		try {
			rs1 = ((java.sql.Statement) stmt).executeQuery("SELECT DISTINCT TNAME FROM TYPE WHERE GENERALFLAG = '"+ 1 + "'");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		//System.out.println(count);
		int i= 0;
		String abcd[] = new String[count];
		try {
			while(rs1.next())
			{
				abcd[i] = rs1.getString(1);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int j=0;j<count;j++)
		{
			System.out.println(abcd[j]);
		}
		
		String[] patientclass = {"HIV","COPD","OBESITY","HIGH RISK PREGNANCY"}; 
		final JComboBox comboBox = new JComboBox(patientclass);
		comboBox.setBounds(262, 88, 90, 20);
		contentPane.add(comboBox);
		
		final JComboBox comboBox_1 = new JComboBox(abcd);
		comboBox_1.setBounds(66, 88, 126, 20);
		contentPane.add(comboBox_1);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFocusPainted(false);
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnAdd.setBackground(Color.WHITE);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tname = (String) comboBox_1.getSelectedItem();
				String dname = (String) comboBox.getSelectedItem();
				Connection conn1 = null;
				try {
					conn1 = DriverManager.getConnection(jdbcURL, user, passwd);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
				
				Statement stmt1 = null;
				try {
					stmt1 = conn1.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
				String sql1 = "UPDATE type SET CLASS = '"+ dname +"' where tname='"+ tname + "'";
				String sql2 = "UPDATE type SET PTID ='"+ null+ "' where tname='"+ tname + "'";
				String sql3 = "UPDATE type SET GENERALFLAG = '"+ 2 +"' where tname='"+ tname + "'";
				try {
					stmt1.executeQuery(sql1);
					stmt1.executeQuery(sql2);
					stmt1.executeQuery(sql3);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
				setVisible(false);
				new PhysicianHome().setVisible(true);
				
				
			}
		});
		btnAdd.setBounds(181, 167, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnNewButton.setFocusPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new PhysicianHome().setVisible(true);
			}
		});
		btnNewButton.setBounds(181, 201, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Observation Type");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNewLabel.setBounds(66, 63, 126, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblClass = new JLabel("Class");
		lblClass.setForeground(Color.WHITE);
		lblClass.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblClass.setBounds(263, 63, 46, 14);
		contentPane.add(lblClass);
		
		JLabel lblCreateAssociation = new JLabel("Create Association");
		lblCreateAssociation.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblCreateAssociation.setForeground(Color.WHITE);
		lblCreateAssociation.setBounds(146, 11, 193, 23);
		contentPane.add(lblCreateAssociation);
		
		
	}
}
