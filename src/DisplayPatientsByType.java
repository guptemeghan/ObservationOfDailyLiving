import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import java.sql.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class DisplayPatientsByType extends JFrame {

	private JPanel contentPane;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayPatientsByType frame = new DisplayPatientsByType();
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
	public DisplayPatientsByType() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("BACK");
		btnBack.setBackground(Color.WHITE);
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ViewPatients().setVisible(true);
			}
		});
		btnBack.setBounds(339, 510, 89, 23);
		contentPane.add(btnBack);
		
		String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
		String user = "aprasad3"; // For example, "jsmith"
		String passwd = "200015261"; // Your 9 digit student ID number
		int usertype;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(jdbcURL,user, passwd);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM OBSERVATIONS WHERE TNAME='"+ViewPatientsByType.typename +"' ORDER BY DTIMERECORD");
			int flag = 3;
			int rowcount = 0;
			while(rs.next())
			{
				rowcount++;
			}
			rs.close();
			ResultSet rs1 = stmt.executeQuery("SELECT * FROM OBSERVATIONS WHERE TNAME='"+ViewPatientsByType.typename +"' ORDER BY DTIMERECORD");
						
			String rows[][] = new String[rowcount][10];
			String columns[] = {"Patient ID","Type","Question 1","Observation","Question 2","Observation","Time of Observation", "Time of Recording"};
			int x = 0;
			int y;
			while(rs1.next())
			{
				y = 0;
				rows[x][y] = rs1.getString(4);
				y++;
				rows[x][y] = ViewPatientsByType.typename;
				y++;
				rows[x][y] = rs1.getString(5);
				y++;
				rows[x][y] = rs1.getString(8);
				y++;
				rows[x][y] = rs1.getString(6);
				y++;
				rows[x][y] = rs1.getString(9);
				y++;
				rows[x][y] = rs1.getTimestamp(2).toString();
				y++;
				rows[x][y] = rs1.getTimestamp(3).toString();
				y++;
				x++;
			}
			
			
			
			table = new JTable( rows, columns );
			table.setFont(new Font("Segoe UI", Font.BOLD, 11));
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 780, 500);
			scrollPane.setViewportView(table);
			contentPane.add(scrollPane);
			
			
			/*rs.close();
			int i = 0;
			ResultSet rs1 = stmt.executeQuery("SELECT PTID FROM OBSERVATIONS WHERE TNAME='"+ViewPatientsByType.typename +"'");
			while(rs1.next())
			{
				patid[i] = rs1.getString(1);
				i++;
			}
			String rows[][] = new String [count][6];
			rs1.close();
			int m = 0;
			int n;
			ResultSet rs2 = stmt.executeQuery("SELECT * FROM USERS");
			while(rs2.next())
			{
				for(int x = 0;x < count; x++)
				{
					if(patid[x].equals(rs2.getString(1)))
					{
						n=0;
						rows[m][n] = rs2.getString(1);
						n++;
						rows[m][n] = rs2.getString(2);
						n++;
						rows[m][n] = rs2.getString(3);
						n++;
						rows[m][n] = rs2.getString(10);
						n++;
						rows[m][n] = rs2.getString(11);
						n++;
						rows[m][n] = rs2.getString(12);
						n++;
						m++;
					}
				}
				
			}
			
			table = new JTable (rows,ColumnNames);
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 450, 200);
			scrollPane.setViewportView(table);
			
			contentPane.add(scrollPane);
			
			rs2.close();*/
			/*for(int k = 0;k<count; k++)
				System.out.println(patid[k]);*/

		} catch (SQLException e1) {
			//e1.printStackTrace();
		} catch (ClassNotFoundException ew) {
			//ew.printStackTrace();
		}
		
		
	}

}
