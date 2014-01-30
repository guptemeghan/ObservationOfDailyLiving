import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

public class DisplayPatients extends JFrame {

	private JPanel contentPane;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayPatients frame = new DisplayPatients();
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
	public DisplayPatients() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
		btnBack.setBounds(166, 227, 89, 23);
		contentPane.add(btnBack);
		

		String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
		String user = "aprasad3"; // For example, "jsmith"
		String passwd = "200015261"; // Your 9 digit student ID number
		int usertype;
		int counter = 0;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(jdbcURL,user, passwd);
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery("SELECT * FROM USERS ");
			String id = null;
			String name = null;
			String address = null;
			String city = null;
			String state = null;
			String pincode = null;
			String name1 = "";
			String fixedname = null;
			String[] a = new String [3];
			String[] b = new String [3];
			rs.last();
			int size = 1;
			int length = rs.getRow();
			rs.first();
			while (rs.next()) {
				
				name1 = rs.getString("name");
				System.out.println(name1);
				a = name1.split(" ", 3);
				b = (ViewPatientsByName.searchname).split(" ",3);
				System.out.println(b[0]);
				if (name1.equalsIgnoreCase(ViewPatientsByName.searchname) || a[0].equalsIgnoreCase(b[0]) || a[1].equalsIgnoreCase(b[0])) 
				{
					id = rs.getString("ID");
					fixedname = name1;
				}
				else
				{
					setVisible(false);
					counter++;
					if (counter == (length - 1))
					
					{
					JOptionPane.showMessageDialog(null, "Name not found. Please try again");
					
					new PhysicianHome().setVisible(true);
					}
					
				}
			}
				rs.close();
				
				ResultSet rs1 = stmt.executeQuery("SELECT * FROM OBSERVATIONS WHERE PTID ='"+ id +"'");
				rs1.last();
				size = rs1.getRow();
				rs1.first();
				int p = 0;
				int q;
				String rows[][] =  new String[size][8];
				while(rs1.next())
				{
					q=0;
					rows[p][q] = id;
					q++;
					rows[p][q] = rs1.getString("TNAME");
					q++;
					rows[p][q] = rs1.getString(5);
					q++;
					rows[p][q] = rs1.getString(8);
					q++;
					rows[p][q] = rs1.getString(6);
					q++;
					rows[p][q] = rs1.getString(9);
					q++;
					rows[p][q] = rs1.getString(3);
					q++;
					rows[p][q] = rs1.getString(2);
					q++;
					p++;
				}
				
				
					//System.out.println(id + name + address + city + state + pincode);
									//}
				
			
			String columns[] = { "Patient ID","Type","Attribute","Observation","Attribute","Observation", "Time of Recording","Time of Observation"};
			
			/*rows[0][0] = id;
			rows[0][1] = name;
			rows[0][2] = address;
			rows[0][3] = city;
			rows[0][4] = state;
			rows[0][5] = pincode;*/
			
			table = new JTable( rows, columns );
			table.setFont(new Font("Segoe UI", Font.BOLD, 11));
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setFont(new Font("Segoe UI", Font.BOLD, 11));
			scrollPane.setBackground(Color.WHITE);
			scrollPane.setBounds(0, 0, 450, 200);
			scrollPane.setViewportView(table);
			
			contentPane.add(scrollPane);
			// setVisible(false);
			// new ViewPatientsByName().setVisible(true);
			//rs.close();
		
	
			//System.out.println(counter + " "  + length);

		} catch (SQLException e1) {
			//e1.printStackTrace();
		} catch (ClassNotFoundException ew) {
			//ew.printStackTrace();
		}
		
		
	}

}
