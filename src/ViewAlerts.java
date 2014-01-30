import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import java.util.*;
import java.util.Date;
import java.text.*;

import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ViewAlerts extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JLabel lblAlerts;
	private JButton btnBackButton;
	private JButton btnClearButton;
	String username = LoginPage.ST_id;
	String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
	String user = "aprasad3"; // For example, "jsmith"
	String passwd = "200015261"; // Your 9 digit student ID number

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewAlerts frame = new ViewAlerts();
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
	public ViewAlerts() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(jdbcURL,
					user, passwd);
			Statement stmt = conn.createStatement();
			String sql = "UPDATE users SET lastlogin= sysdate where id'"
					+ LoginPage.ST_id + "'";
			stmt.executeQuery(sql);
			conn.close();
		} catch (SQLException e1) {
			//e1.printStackTrace();
		} catch (ClassNotFoundException ew) {
			//ew.printStackTrace();
		}
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager
					.getConnection(jdbcURL, user, passwd);
			Statement stmt = conn.createStatement();
			String sql = "Select tname AS Alert,info1text AS Info,info1value AS Value,info2text AS Info,info2value AS Value,alertdate AS DateTriggered FROM alerts where ptid ='"
					+ username + "' and indicator=0";
			System.out.println(sql);
			// PreparedStatement prepStmt = conn.prepareStatement(sql);
			// prepStmt.setString(1,username);
			ResultSet rs = stmt.executeQuery(sql);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 50, 414, 175);
			contentPane.add(scrollPane);

			table = new JTable(buildTableModel(rs)) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			scrollPane.setViewportView(table);
			table.setShowVerticalLines(false);
			table.setShowHorizontalLines(false);
			table.setFont(new Font("Segoe UI", Font.BOLD, 11));
			// contentPane.add(alertTable);
			conn.close();
		} catch (SQLException e1) {
			//e1.printStackTrace();
		} catch (ClassNotFoundException ew) {
			//ew.printStackTrace();
		}
		
		lblAlerts = new JLabel("Alerts");
		lblAlerts.setForeground(Color.WHITE);
		lblAlerts.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblAlerts.setBounds(202, 25, 46, 14);
		contentPane.add(lblAlerts);

		btnClearButton = new JButton("Clear");
		btnClearButton.setFocusPainted(false);
		btnClearButton.setBackground(Color.WHITE);
		btnClearButton.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection(jdbcURL,
							user, passwd);
					conn.setAutoCommit(false);
					Statement stmt = conn.createStatement();
					String sql = "UPDATE alerts SET indicator=1 where ptid ='"
							+ LoginPage.ST_id + "'";
					stmt.executeQuery(sql);
					Date sysDate = new Date();
					Calendar cal = Calendar.getInstance();
					String timeStamp = sysDate.getDate()
							+ "-"
							+ new SimpleDateFormat("MMM-YY").format(cal
									.getTime());
					String sql2 = "UPDATE alerts SET alertdate='"
							+ timeStamp + "' where ptid ='"
							+ LoginPage.ST_id + "'";
					stmt.executeQuery(sql2);
					String sql3 = "Select tname AS Alert,info1text AS Info,info1value AS Value,info2text AS Info,info2value AS Value,alertdate AS DateTriggered FROM alerts where ptid ='"
							+ username + "' and indicator=0";
					System.out.println(sql);

					ResultSet rs = stmt.executeQuery(sql3);
					table.setModel(buildTableModel(rs));
					conn.commit();
					conn.close();
				} catch (SQLException e1) {
					////e1.printStackTrace();
				} catch (ClassNotFoundException ew) {
					////ew.printStackTrace();
				}
			}
		});
		
		btnClearButton.setBounds(236, 227, 89, 23);
		contentPane.add(btnClearButton);
		btnBackButton = new JButton("Back");
		btnBackButton.setFocusPainted(false);
		btnBackButton.setBackground(Color.WHITE);
		btnBackButton.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnBackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				PatientHome ph = new PatientHome();
				ph.setVisible(true);
			}
		});
		btnBackButton.setBounds(335, 227, 89, 23);
		contentPane.add(btnBackButton);
	}

}
