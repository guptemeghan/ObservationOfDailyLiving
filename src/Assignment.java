import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.sql.*;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Assignment extends JFrame {

	private JPanel contentPane;
	static String pid = null;
	int m = 0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Assignment frame = new Assignment();
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
	public Assignment() {
		setFont(new Font("Segoe UI", Font.BOLD, 11));
		setTitle("Assignment");
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 192);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		

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
		int rowcount = 1;
		int i = 0;
		ResultSet rs = null;
		String sql = "Select distinct cname from patientclass minus (select cname from patientclass where ptid in(select id from users where name = '"+ AssignClassToPatient.b +"'))";
		String sql1= "Select id from users where name ='"+ AssignClassToPatient.b +"'";
		try {
			rs = stmt.executeQuery(sql);
			rs.last();
			rowcount = rs.getRow();
			rs.first();
			
			
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
		ResultSet rs2 = null;
		try {
			rs2 = stmt.executeQuery(sql1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(rs2.next())
			{
				pid = rs2.getString(1);
			}
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			rs2.close();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		String x[] = new String[rowcount];
		ResultSet rs1 = null;
		try {
			rs1 = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs1.next())
			{
				x[i] = rs1.getString("cname");
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			rs1.close();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		int n = 0;
		ResultSet rs5=null;
		try {
			 rs5 = stmt.executeQuery("SELECT * FROM PATIENTCLASS");
			 while(rs5.next())
			 {
				 n = rs5.getInt(1);
					if (n > m)
					{
						m = n;
					}
			 }	 
			 
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		
		final JComboBox comboBox = new JComboBox(x);
		
		JButton btnAssign = new JButton("Assign");
		btnAssign.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnAssign.setBackground(Color.WHITE);
		btnAssign.setFocusPainted(false);
		btnAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn1 = null;
				try {
					 conn1 = DriverManager.getConnection(jdbcURL,user, passwd);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				} 
				String dis = (String) comboBox.getSelectedItem();
				try {
					PreparedStatement pst = conn1.prepareStatement("INSERT INTO PATIENTCLASS VALUES (?,?,?)");
					pst.setInt(1,(m+1));
					pst.setString(2,dis);
					pst.setString(3,pid);
					pst.executeUpdate();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Class Assignment Successful!!");
				setVisible(false);
				new PhysicianHome().setVisible(true);
				//System.out.println(dis);
			}
		});
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnBack.setBackground(Color.WHITE);
		btnBack.setFocusPainted(false);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new AssignClassToPatient().setVisible(true);
			}
		});
		
		JLabel lblClass = new JLabel("Class");
		lblClass.setForeground(Color.WHITE);
		lblClass.setFont(new Font("Segoe UI", Font.BOLD, 11));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(39)
					.addComponent(lblClass, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(38)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addGap(70)
					.addComponent(btnAssign, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(138)
					.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(22)
					.addComponent(lblClass, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnAssign))
					.addGap(34)
					.addComponent(btnBack))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
}
