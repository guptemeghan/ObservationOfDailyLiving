import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;


public class DisplayReport extends JFrame {

	private JPanel contentPane;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayReport frame = new DisplayReport();
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
	public DisplayReport() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnBack.setBackground(Color.WHITE);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ViewReports().setVisible(true);
			}
		});
		btnBack.setBounds(190, 211, 89, 23);
		contentPane.add(btnBack);
		
		final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
		final String user = "aprasad3";	// For example, "jsmith"
	    final String passwd = "200015261";	// Your 9 digit student ID number
	    
	    try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		Connection conn1 = null;
		try {
			conn1 = DriverManager.getConnection(jdbcURL, user, passwd);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		String sql = "SELECT tname,info1text,AVG(o.info1value),info2text,AVG(o.info2value) from observations o where (info1text in(select infoname from infodetails where not infothreshold is null AND tname='"+ViewReports.reporttype+"') OR info1text is null) AND (info2text in  (select infoname from infodetails where not infothreshold is null AND tname='"+ViewReports.reporttype+"') OR info2text is null) AND tname='"+ViewReports.reporttype+"' group by tname,info1text,info2text";
		Statement stmt1 = null;
		try {
			stmt1 = conn1.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		ResultSet rs2 = null;
		try {
			rs2 = stmt1.executeQuery(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		int counter = 0;
		try {
			while(rs2.next())
			{
				counter++;
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
		System.out.println("I am here");
		System.out.println(counter);
		ResultSet rs3 = null;
		try {
			rs3 = stmt1.executeQuery(sql);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		int p =0 ;
		int q=0;
		String columns[] = { "Type Name","Attribute 1","Average","Attribute 2","Average"};
		String rows[][] =  new String[counter][5];
		try {
			while(rs3.next())
			{
				q = 0;
				rows[p][q] = rs3.getString(1);
				q++;
				rows[p][q] = rs3.getString(2);
				q++;
				rows[p][q] = String.valueOf(rs3.getInt(3));
				q++;
				rows[p][q] = rs3.getString(4);
				q++;
				rows[p][q] = String.valueOf(rs3.getInt(5));
				p++;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
		/*for(int i = 0;i<p;i++)
			for(int j =0;j<=q;j++)
				System.out.println(rows[i][j]);*/
		//System.out.println("helllo");
		table = new JTable( rows, columns );
		table.setFont(new Font("Segoe UI", Font.BOLD, 11));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 434, 200);
		scrollPane.setViewportView(table);
		
		contentPane.add(scrollPane);
	
		
		
		
		
	}

}
