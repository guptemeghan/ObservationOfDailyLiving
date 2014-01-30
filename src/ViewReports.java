import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;


public class ViewReports extends JFrame {

	private JPanel contentPane;
	private JTable table;
	static String reporttype = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewReports frame = new ViewReports();
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
	public ViewReports() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 359, 151);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setForeground(Color.BLACK);
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("SELECT DISTINCT TNAME FROM INFODETAILS WHERE INFOTHRESHOLD > '"+ 0 + "'");
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet rs1 = null;
		try {
			rs1 = stmt.executeQuery("SELECT DISTINCT TNAME FROM INFODETAILS WHERE INFOTHRESHOLD > '"+ 0 + "'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int k = 0;
		String[] t = new String[count];
		try {
			while(rs1.next())
			{
				t[k] = rs1.getString("TNAME");
				k++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final JComboBox comboBox = new JComboBox(t);
		comboBox.setBounds(90, 38, 119, 20);
		contentPane.add(comboBox);
		
		JButton btnViewReport = new JButton("View Report");
		btnViewReport.setFocusPainted(false);
		btnViewReport.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnViewReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reporttype = (String) comboBox.getSelectedItem();
				setVisible(false);
				new DisplayReport().setVisible(true);
		
			}
		});
		btnViewReport.setBounds(219, 37, 108, 23);
		contentPane.add(btnViewReport);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFocusPainted(false);
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			setVisible(false);
			new PhysicianHome().setVisible(true);
			
			}
		});
		btnBack.setBounds(128, 69, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblType = new JLabel("Type");
		lblType.setForeground(Color.WHITE);
		lblType.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblType.setBounds(10, 41, 46, 14);
		contentPane.add(lblType);
	}
}
