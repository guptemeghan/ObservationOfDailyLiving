import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JButton;
import javax.swing.JTable;

import java.sql.*;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.table.*;

import oracle.jdbc.util.Login;

import java.awt.Font;

import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ManageHealthFriends extends JFrame {

	private JPanel contentPane;
	private JLabel patientid;
	private JTable table;
	static String HF_id;
	static String HF_name;
	private JButton btnAddNewFriend;
	private JButton btnRisk;
	String username = LoginPage.ST_id;
	String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
	String user = "aprasad3"; // For example, "jsmith"
	String passwd = "200015261"; // Your 9 digit student ID number
	private JButton btnSendMsg;
	private JTable table_1;
	private JLabel lblHealthFriends;
	private JLabel lblInactiveFriends;
	private JButton btnSend2;
	private JButton btnBack;
	private JButton btnAllFriends;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageHealthFriends frame = new ManageHealthFriends();
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
	public ManageHealthFriends() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 333);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		btnAddNewFriend = new JButton("Add New Friend");
		btnAddNewFriend.setFocusPainted(false);
		btnAddNewFriend.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnAddNewFriend.setBackground(Color.WHITE);
		btnAddNewFriend.setBounds(264, 147, 160, 23);
		btnAddNewFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//System.out.println(table.getSelectedRow());
				setVisible(false);
				AddNewHealthFriend anhf = new AddNewHealthFriend();
				anhf.setVisible(true);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnAddNewFriend);

		patientid = new JLabel("");
		patientid.setBounds(98, 192, 181, 0);
		contentPane.add(patientid);

		JButton btnNewButton = new JButton("See Details");
		btnNewButton.setFocusPainted(false);
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(264, 203, 160, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientid.setText(table.getModel()
						.getValueAt(table.getSelectedRow(), 1).toString());
				setVisible(false);
				HF_id = patientid.getText();
				HealthFriendDetails hfd = new HealthFriendDetails();
				hfd.setVisible(true);
			}
		});
		contentPane.add(btnNewButton);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager
					.getConnection(jdbcURL, user, passwd);
			Statement stmt = conn.createStatement();
			String sql = "select name,id from users where id in (select friendid from healthfriends where patientid='"
					+ username + "') and Status='Yes'";
			ResultSet rs = stmt.executeQuery(sql);

			table = new JTable(buildTableModel(rs)) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			table.setBounds(11, 36, 113, 213);
			table.setShowVerticalLines(false);
			table.setShowHorizontalLines(false);
			table.setFont(new Font("Segoe UI", Font.BOLD, 11));
			table.setCellSelectionEnabled(false);
			contentPane.add(table);
			table.removeColumn(table.getColumnModel().getColumn(1));

		}

		catch (SQLException e1) {
			//e1.printStackTrace();
		} catch (ClassNotFoundException ew) {
			//ew.printStackTrace();
		}

		btnSendMsg = new JButton("Send Message");
		btnSendMsg.setFocusPainted(false);
		btnSendMsg.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnSendMsg.setBackground(Color.WHITE);
		btnSendMsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientid.setText(table.getModel()
						.getValueAt(table.getSelectedRow(), 1).toString());
				setVisible(false);
				HF_id = patientid.getText();
				patientid.setText(table.getModel()
						.getValueAt(table.getSelectedRow(), 0).toString());
				HF_name = patientid.getText();
				SendMessage sm = new SendMessage();
				sm.setVisible(true);
			}
		});
		btnSendMsg.setVisible(false);
		btnSendMsg.setBounds(11, 260, 113, 23);
		contentPane.add(btnSendMsg);

		btnRisk = new JButton("Find Friends at Risk");
		btnRisk.setFocusPainted(false);
		btnRisk.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnRisk.setBackground(Color.WHITE);
		btnRisk.setBounds(264, 174, 160, 23);
		btnRisk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnRisk.setVisible(false);
					btnAllFriends.setVisible(true);
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection(jdbcURL,
							user, passwd);
					Statement stmt = conn.createStatement();
					String sql = "select name,id from users where id in (select h.friendid from healthfriends h,alerts a where h.patientid='"
							+ username
							+ "' and a.ptid=h.friendid and a.indicator=0 group by h.friendid having count(a.alertid)>5) and Status='Yes'";
					ResultSet rs = stmt.executeQuery(sql);
					table.setModel(buildTableModel(rs));
					if (table.getValueAt(0, 1).toString() != "") {
						btnSendMsg.setVisible(true);
					}
					table.setShowVerticalLines(false);
					table.setShowHorizontalLines(false);
					table.setFont(new Font("Tahoma", Font.BOLD, 11));
					table.setCellSelectionEnabled(false);
					table.removeColumn(table.getColumnModel().getColumn(1));
				} catch (SQLException e1) {
					//e1.printStackTrace();
				} catch (ClassNotFoundException ew) {
					//ew.printStackTrace();
				}
			}
		});
		contentPane.add(btnRisk);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager
					.getConnection(jdbcURL, user, passwd);
			Statement stmt = conn.createStatement();
			String sql = "SELECT U.name, U.id from users U WHERE U.id in (SELECT DISTINCT H.FriendID FROM HEALTHFRIENDS H  WHERE H.FRIENDID IN (SELECT  distinct u.id  FROM users u , alerts a WHERE u.ID=a.PTID AND a.indicator=0 AND (a.ALERTDATE-u.LASTLOGIN)>7) AND H.PATIENTID = '"+LoginPage.ST_id+"')";
			ResultSet rs = stmt.executeQuery(sql);

			table_1 = new JTable(buildTableModel(rs)) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			table_1.setBounds(134, 36, 120, 213);
			table_1.setShowVerticalLines(false);
			table_1.setShowHorizontalLines(false);
			table_1.setFont(new Font("Segoe UI", Font.BOLD, 11));
			table_1.setCellSelectionEnabled(false);
			contentPane.add(table_1);
			table_1.removeColumn(table_1.getColumnModel().getColumn(1));
		}

		catch (SQLException e1) {
			//e1.printStackTrace();
		} catch (ClassNotFoundException ew) {
			//ew.printStackTrace();
		}

		
		lblHealthFriends = new JLabel("Health Friends");
		lblHealthFriends.setForeground(Color.WHITE);
		lblHealthFriends.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblHealthFriends.setBounds(11, 11, 114, 14);
		contentPane.add(lblHealthFriends);
		
		lblInactiveFriends = new JLabel("Inactive Friends");
		lblInactiveFriends.setForeground(Color.WHITE);
		lblInactiveFriends.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblInactiveFriends.setBounds(134, 11, 120, 14);
		contentPane.add(lblInactiveFriends);
		
		btnSend2 = new JButton("Send Message");
		btnSend2.setFocusPainted(false);
		btnSend2.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnSend2.setBackground(Color.WHITE);
		btnSend2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				patientid.setText(table_1.getModel()
						.getValueAt(table_1.getSelectedRow(), 1).toString());
				setVisible(false);
				HF_id = patientid.getText();
				patientid.setText(table_1.getModel()
						.getValueAt(table_1.getSelectedRow(), 0).toString());
				HF_name = patientid.getText();
				SendMessage sm = new SendMessage();
				sm.setVisible(true);
			}
		});
		btnSend2.setBounds(134, 260, 120, 23);
		contentPane.add(btnSend2);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				PatientHome ph=new PatientHome();
				ph.setVisible(true);
			}
		});
		btnBack.setFocusPainted(false);
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnBack.setBackground(Color.WHITE);
		btnBack.setBounds(264, 237, 160, 23);
		contentPane.add(btnBack);
		
		btnAllFriends = new JButton("All Friends");
		btnAllFriends.setBackground(Color.WHITE);
		btnAllFriends.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnAllFriends.setFocusPainted(false);
		btnAllFriends.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnAllFriends.setVisible(false);
					btnRisk.setVisible(true);
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager
							.getConnection(jdbcURL, user, passwd);
					Statement stmt = conn.createStatement();
					String sql = "select name,id from users where id in (select friendid from healthfriends where patientid='"
							+ username + "') and Status='Yes'";
					ResultSet rs = stmt.executeQuery(sql);

					table.setModel(buildTableModel(rs));
					table.removeColumn(table.getColumnModel().getColumn(1));
				}

				catch (SQLException e1) {
					//e1.printStackTrace();
				} catch (ClassNotFoundException ew) {
					//ew.printStackTrace();
				}

			}
		});
		btnAllFriends.setBounds(264, 174, 160, 23);
		contentPane.add(btnAllFriends);

	}

	public String getPatientid() {
		return this.patientid.getText();
	}

	public void setPatientid(String text) {
		this.patientid.setText(text);
	}
}
