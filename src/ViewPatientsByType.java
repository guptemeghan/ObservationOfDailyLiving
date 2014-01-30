import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.Color;

public class ViewPatientsByType extends JFrame {

	static JPanel contentPane;
	static String typename;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPatientsByType frame = new ViewPatientsByType();
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
	public ViewPatientsByType() {
		setTitle("View Patients By Type");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 377, 124);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
		String user = "aprasad3"; // For example, "jsmith"
		String passwd = "200015261"; // Your 9 digit student ID number
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		Connection conn = null;
		try {
			 conn = DriverManager.getConnection(jdbcURL,user, passwd);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		ResultSet rs=null;
		try {
			rs= stmt.executeQuery("SELECT DISTINCT TNAME FROM TYPE");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		int count = 0;
		try {
			while(rs.next())
			{
				count++;
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		try {
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		
		ResultSet rs1=null;
		try {
			rs1= stmt.executeQuery("SELECT DISTINCT TNAME FROM TYPE");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		
		String[] types = new String[count];
			//{ "Select type", "Diet", "Weight", "Exercise",
				//"Blood Pressure", "Exercise Tolerance", "Oxygen Saturation",
				//" Pain", "Mood", "Contraction", "Temperature" };
		int j = 0;
		try {
			while(rs1.next())
			{
				types[j] = rs1.getString("TNAME");
				j++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		for (int k=0;k< count;k++)
			System.out.println(types[k]);
		
		
		contentPane.setLayout(null);

		final JComboBox typelist = new JComboBox(types);
		typelist.setBounds(65, 25, 150, 27);
		contentPane.add(typelist);

		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				typename = (String) typelist.getSelectedItem();
				System.out.println(typename);
				
				setVisible(false);
				new DisplayPatientsByType().setVisible(true);

			}
		});
		btnView.setFont(new Font("Times New Roman", Font.BOLD, 15));
		btnView.setBounds(234, 28, 92, 24);
		contentPane.add(btnView);
		
		JLabel lblType = new JLabel("Type");
		lblType.setForeground(Color.WHITE);
		lblType.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblType.setBounds(9, 34, 46, 14);
		contentPane.add(lblType);
	}
}
