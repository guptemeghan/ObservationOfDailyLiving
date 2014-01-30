import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;

import java.awt.Font;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ViewObservations extends JFrame {

	public static DefaultTableModel buildTableModel(ResultSet rs)
			throws SQLException {

		ResultSetMetaData metaData = rs.getMetaData();

		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}

		// data of the table
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}

		return new DefaultTableModel(data, columnNames);
	}

	private JPanel contentPane;
	private JTable ObsTable;
	private JTable ObsvTable;
	private JLabel lblptid;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JComboBox comboBox_2;
	private JComboBox comboBox_3;
	private JComboBox comboBox_4;
	private JComboBox comboBox_5;
	private JComboBox comboBox_6;
	String tname="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HealthFriendDetails frame = new HealthFriendDetails();
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
	public ViewObservations() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 321);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblObservations = new JLabel("Observations");
		lblObservations.setForeground(Color.WHITE);
		lblObservations.setBounds(247, 11, 114, 14);
		lblObservations.setFont(new Font("Segoe UI", Font.BOLD, 11));
		contentPane.add(lblObservations);

		String username = LoginPage.ST_id;
		String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
		String user = "aprasad3"; // For example, "jsmith"
		String passwd = "200015261"; // Your 9 digit student ID number
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager
					.getConnection(jdbcURL, user, passwd);
			Statement stmt = conn.createStatement();
			String sql = "select tname AS Type,info1text AS Information1,info1value AS Value,info2text AS Information2,info2value AS Value from observations where ptid ='"
					+ username + "'";
			System.out.println(sql);
			// PreparedStatement prepStmt = conn.prepareStatement(sql);
			// prepStmt.setString(1,username);
			ResultSet rs = stmt.executeQuery(sql);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setFont(new Font("Segoe UI", Font.BOLD, 11));
			scrollPane.setBounds(27, 82, 560, 173);
			contentPane.add(scrollPane);

			ObsvTable = new JTable(buildTableModel(rs)) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			scrollPane.setViewportView(ObsvTable);
			ObsvTable.setShowVerticalLines(false);
			ObsvTable.setShowHorizontalLines(false);
			ObsvTable.setFont(new Font("Tahoma", Font.BOLD, 11));
			ObsvTable.setCellSelectionEnabled(false);

			JButton btnBack = new JButton("Back");
			btnBack.setFocusPainted(false);
			btnBack.setFont(new Font("Segoe UI", Font.PLAIN, 11));
			btnBack.setBackground(Color.WHITE);
			btnBack.setBounds(498, 256, 89, 23);
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					PatientHome ph = new PatientHome();
					ph.setVisible(true);
				}
			});
			contentPane.add(btnBack);
		}
		catch (SQLException e1) {
			//e1.printStackTrace();
		} catch (ClassNotFoundException ew) {
			//ew.printStackTrace();
		}
		String[] date = { "", "01", "02", "03", "04", "05", "06", "07", "08",
				"09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
				"19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
				"29", "30", "31" };
		String[] month = { "", "01", "02", "03", "04", "05", "06", "07", "08",
				"09", "10", "11", "12" };
		String[] year = { "", "2009", "2010", "2011", "2012", "2013", "2014",
				"2015" };
		
		JLabel lblType = new JLabel("Type");
		lblType.setForeground(Color.WHITE);
		lblType.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblType.setBounds(27, 35, 46, 14);
		contentPane.add(lblType);
		
		JLabel lblDateFrom = new JLabel("Date From");
		lblDateFrom.setForeground(Color.WHITE);
		lblDateFrom.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblDateFrom.setBounds(134, 35, 108, 14);
		contentPane.add(lblDateFrom);
		
		final JComboBox comboBox_1 = new JComboBox(date);
		comboBox_1.setBounds(134, 51, 43, 20);
		contentPane.add(comboBox_1);
		
		final JComboBox comboBox_2 = new JComboBox(month);
		comboBox_2.setBounds(179, 51, 49, 20);
		contentPane.add(comboBox_2);
		
		final JComboBox comboBox_3 = new JComboBox(year);
		comboBox_3.setBounds(238, 51, 71, 20);
		contentPane.add(comboBox_3);
		
		final JComboBox comboBox_4 = new JComboBox(date);
		comboBox_4.setBounds(319, 51, 43, 20);
		contentPane.add(comboBox_4);
		
		JLabel lblDateTp = new JLabel("Date To");
		lblDateTp.setForeground(Color.WHITE);
		lblDateTp.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblDateTp.setBounds(319, 35, 108, 14);
		contentPane.add(lblDateTp);
		
		final JComboBox comboBox_5 = new JComboBox(month);
		comboBox_5.setBounds(367, 51, 46, 20);
		contentPane.add(comboBox_5);
		
		final JComboBox comboBox_6 = new JComboBox(year);
		comboBox_6.setBounds(423, 51, 66, 20);
		contentPane.add(comboBox_6);
		
		JButton btnDisplay = new JButton("Display");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tname=="Select" || comboBox_1.getSelectedItem().toString()=="" || comboBox_2.getSelectedItem().toString()=="" || comboBox_3.getSelectedItem().toString()=="" || comboBox_4.getSelectedItem().toString()=="" ||comboBox_5.getSelectedItem().toString()=="" ||comboBox_6.getSelectedItem().toString()=="")
				{
					if(tname=="Select" && !(comboBox_1.getSelectedItem().toString()=="" || comboBox_2.getSelectedItem().toString()=="" || comboBox_3.getSelectedItem().toString()=="" || comboBox_4.getSelectedItem().toString()=="" ||comboBox_5.getSelectedItem().toString()=="" ||comboBox_6.getSelectedItem().toString()==""))
					{
					JOptionPane.showMessageDialog(null,"Please Correct Observation Type");
					}
					else
						JOptionPane.showMessageDialog(null,"Please Correct Date");
					
				}
				else
				{
				String fromtimeStamp = comboBox_2.getSelectedItem().toString()
						+"/"
						+ comboBox_1.getSelectedItem().toString()
						+"/"
						+ comboBox_3.getSelectedItem().toString();
				String totimeStamp = comboBox_5.getSelectedItem().toString()
						+"/"
						+ comboBox_4.getSelectedItem().toString()
						+"/"
						+ comboBox_6.getSelectedItem().toString();

				String username = LoginPage.ST_id;
				String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
				String user = "aprasad3"; // For example, "jsmith"
				String passwd = "200015261"; // Your 9 digit student ID number
				try
				{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conn = DriverManager
						.getConnection(jdbcURL, user, passwd);
				Statement stmt = conn.createStatement();
				String sql = "select tname AS Type,info1text AS Observation1,info1value AS Value,info2text AS Observation2,info2value AS Value from observations where ptid ='"+LoginPage.ST_id+"' AND tname='"+tname+"' AND dtimerecord> to_date('"+fromtimeStamp+"' ,'mm/dd/yyyy') AND dtimerecord<to_date('"+totimeStamp+"' ,'mm/dd/yyyy')";
				System.out.println(sql);
				ResultSet rs = stmt.executeQuery(sql);
				ObsvTable.setModel(buildTableModel(rs));
				}
				catch (SQLException e1) {
					//e1.printStackTrace();
				} catch (ClassNotFoundException ew) {
					//ew.printStackTrace();
				}
				}
			}
		});
		btnDisplay.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnDisplay.setBounds(498, 50, 89, 23);
		contentPane.add(btnDisplay);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager
					.getConnection(jdbcURL, user, passwd);
			Statement stmt = conn.createStatement();
			String sql = "SELECT DISTINCT t.tname from type t where t.generalFlag = 0 UNION SELECT DISTINCT t.tname from type t where t.generalFlag = 1 and t.ptid='"+LoginPage.ST_id+"' UNION SELECT DISTINCT t.tname from type t,patientclass p where t.generalFlag = 2 and p.CNAME=t.class and p.PTID='"+LoginPage.ST_id+"'";
			ResultSet rs = stmt.executeQuery(sql);
			Vector<String> temp = new Vector<String>();
			temp.add("Select");
			while (rs.next()) {
				temp.add(rs.getString("tname"));
			}
		final JComboBox comboBox = new JComboBox(temp);
		tname=comboBox.getSelectedItem().toString();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tname=comboBox.getSelectedItem().toString();
			}
		});
		comboBox.setBounds(27, 51, 97, 20);
		contentPane.add(comboBox);
		conn.close();
		}
		catch (SQLException e1) {
			//e1.printStackTrace();
		} catch (ClassNotFoundException ew) {
			//ew.printStackTrace();
		}

	}

	public String getPatientid() {
		return this.lblptid.getText();
	}

	public void setPatientid(String text) {
		this.lblptid.setText(text);
	}
}
