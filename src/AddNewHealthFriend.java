import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.GroupLayout.Alignment;
import javax.swing.*;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.text.*;

import javax.swing.JLabel;
import javax.swing.table.*;

import oracle.jdbc.util.Login;

import java.awt.Font;

import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AddNewHealthFriend extends JFrame {

	private JPanel contentPane;

	private JTable FTable;
	String username = LoginPage.ST_id;
	String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
	String user = "aprasad3"; // For example, "jsmith"
	String passwd = "200015261"; // Your 9 digit student ID number
	private JComboBox classComboBox;
	String strClass;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddNewHealthFriend frame = new AddNewHealthFriend();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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

	/**
	 * Create the frame.
	 */
	public AddNewHealthFriend() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblSelectClass = new JLabel("Select Class");
		lblSelectClass.setForeground(Color.WHITE);
		lblSelectClass.setBounds(45, 25, 67, 14);
		lblSelectClass.setFont(new Font("Segoe UI", Font.BOLD, 11));

		JLabel lblSuggestedHealthFriends = new JLabel(
				"Suggested Health Friends");
		lblSuggestedHealthFriends.setForeground(Color.WHITE);
		lblSuggestedHealthFriends.setBounds(45, 92, 144, 14);
		lblSuggestedHealthFriends.setFont(new Font("Tahoma", Font.BOLD, 11));

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager
					.getConnection(jdbcURL, user, passwd);
			Statement stmt = conn.createStatement();
			String sql = "select distinct cname from patientclass where ptid='"
					+ username + "'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println(rs.getRow());
			Vector<String> temp = new Vector<String>();
			temp.add("Select");
			while (rs.next()) {
				temp.add(rs.getString("cname"));
			}
			final JComboBox classComboBox = new JComboBox(temp);
			classComboBox.setBounds(45, 50, 67, 20);
			classComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection conn = DriverManager.getConnection(jdbcURL,
								user, passwd);
						Statement stmt = conn.createStatement();
						strClass = classComboBox.getSelectedItem().toString();
						String sql = "SELECT name,id from users where id in (select p2.PTID from patientclass P1,patientclass p2 where P1.cname=p2.cname and P1.ptid <> P2.ptid and P1.ptid='"
								+ username
								+ "' AND P1.CNAME='"
								+ classComboBox.getSelectedItem().toString()
								+ "' AND P2.CNAME='"
								+ classComboBox.getSelectedItem().toString()
								+ "' AND NOT exists (SELECT H.FRIENDID FROM healthfriends H WHERE H.PatientID=P1.ptid AND H.FRIENDID=P2.ptid))";
						System.out.println(sql);
						ResultSet rs = stmt.executeQuery(sql);
						FTable.setModel(buildTableModel(rs));
						FTable.removeColumn(FTable.getColumnModel()
								.getColumn(1));
					} catch (SQLException e1) {
						//e1.printStackTrace();
					} catch (ClassNotFoundException ew) {
						//ew.printStackTrace();
					}
				}
			});
			contentPane.setLayout(null);
			contentPane.add(classComboBox);
		}

		catch (SQLException e1) {
			//e1.printStackTrace();
		} catch (ClassNotFoundException ew) {
			//ew.printStackTrace();
		}

		contentPane.add(lblSelectClass);
		contentPane.add(lblSuggestedHealthFriends);

		FTable = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		FTable.setForeground(Color.BLACK);
		FTable.setBounds(45, 117, 144, 114);
		FTable.setShowVerticalLines(false);
		FTable.setShowHorizontalLines(false);
		FTable.setFont(new Font("Tahoma", Font.BOLD, 11));
		FTable.setCellSelectionEnabled(false);

		contentPane.add(FTable);

		JButton btnAdd = new JButton("Add");
		btnAdd.setFocusPainted(false);
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnAdd.setBackground(Color.WHITE);
		btnAdd.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection(jdbcURL,
							user, passwd);
					Statement stmt = conn.createStatement();
					Date sysDate = new Date();
					Calendar cal = Calendar.getInstance();
					String timeStamp = sysDate.getDate()
							+ "-"
							+ new SimpleDateFormat("MMM-YY").format(cal
									.getTime());
					String sql = "insert into HealthFriends values (hf_seq.NEXTVAL,'"
							+ LoginPage.ST_id
							+ "','"
							+ FTable.getModel().getValueAt(
									FTable.getSelectedRow(), 1)
							+ "','"
							+ timeStamp + "')";
					stmt.executeQuery(sql);
					
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn1 = DriverManager.getConnection(jdbcURL,
							user, passwd);
					Statement stmt1 = conn1.createStatement();
					String sql1 = "SELECT name,id from users where id in (select p2.PTID from patientclass P1,patientclass p2 where P1.cname=p2.cname and P1.ptid <> P2.ptid and P1.ptid='"
							+ username
							+ "' AND P1.CNAME='"
							+	strClass
							+ "' AND P2.CNAME='"
							+ 	strClass
							+ "' AND NOT exists (SELECT H.FRIENDID FROM healthfriends H WHERE H.PatientID=P1.ptid AND H.FRIENDID=P2.ptid))";
					System.out.println(sql);
					
					ResultSet rs = stmt.executeQuery(sql1);
					FTable.setModel(buildTableModel(rs));
					FTable.removeColumn(FTable.getColumnModel()
							.getColumn(1));
					int selectedOption = JOptionPane.showConfirmDialog(null, 
	                        "Friend Added. Add another Friend ?", 
	                        "Choose", 
	                        JOptionPane.YES_NO_OPTION); 
						if (selectedOption == JOptionPane.YES_OPTION) {
						}
						else
						{
							setVisible(false);
							ManageHealthFriends mh = new ManageHealthFriends();
							mh.setVisible(true);
						}
				} catch (SQLException e1) {
					//e1.printStackTrace();
				} catch (ClassNotFoundException ew) {
					//ew.printStackTrace();
				}
			}
		});
		btnAdd.setBounds(232, 113, 89, 23);
		contentPane.add(btnAdd);

		JButton btnBack = new JButton("Back");
		btnBack.setFocusPainted(false);
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnBack.setBackground(Color.WHITE);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ManageHealthFriends mhf = new ManageHealthFriends();
				mhf.setVisible(true);
			}
		});
		btnBack.setBounds(232, 208, 89, 23);
		contentPane.add(btnBack);

	}
}
