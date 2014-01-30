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
import javax.swing.JTable;
import javax.swing.JLabel;
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

public class HealthFriendDetails extends JFrame {

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
	private JTable alertTable;
	private JLabel lblptid;

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
	public HealthFriendDetails() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 625, 321);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel lblObservations = new JLabel("Observations");
		lblObservations.setForeground(Color.WHITE);
		lblObservations.setBounds(27, 124, 114, 14);
		lblObservations.setFont(new Font("Segoe UI", Font.BOLD, 11));

		JLabel lblAlerts = new JLabel("Alerts");
		lblAlerts.setForeground(Color.WHITE);
		lblAlerts.setBounds(27, 11, 34, 14);
		lblAlerts.setFont(new Font("Segoe UI", Font.BOLD, 11));
		contentPane.setLayout(null);
		contentPane.add(lblAlerts);
		contentPane.add(lblObservations);

		String username = ManageHealthFriends.HF_id;
		String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
		System.out.println(username);
		String user = "aprasad3"; // For example, "jsmith"
		String passwd = "200015261"; // Your 9 digit student ID number
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager
					.getConnection(jdbcURL, user, passwd);
			Statement stmt = conn.createStatement();
			String sql = "select tname AS Type,info1text AS Observation1,info1value AS Value,info2text AS Observation2,info2value AS Value,info3text AS Observation3,info3value AS Value from observations where ptid ='"
					+ username + "'";
			System.out.println(sql);
			// PreparedStatement prepStmt = conn.prepareStatement(sql);
			// prepStmt.setString(1,username);
			ResultSet rs = stmt.executeQuery(sql);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setFont(new Font("Segoe UI", Font.BOLD, 11));
			scrollPane.setBounds(27, 149, 560, 96);
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
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					ManageHealthFriends mhf = new ManageHealthFriends();
					mhf.setVisible(true);
				}
			});
			btnBack.setBounds(498, 256, 89, 23);
			contentPane.add(btnBack);

		}

		catch (SQLException e1) {
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

			JScrollPane alertscrollPane = new JScrollPane();
			alertscrollPane.setFont(new Font("Segoe UI", Font.BOLD, 11));
			alertscrollPane.setBounds(27, 27, 560, 86);
			contentPane.add(alertscrollPane);

			alertTable = new JTable(buildTableModel(rs)) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			alertscrollPane.setViewportView(alertTable);
			alertTable.setShowVerticalLines(false);
			alertTable.setShowHorizontalLines(false);
			alertTable.setFont(new Font("Tahoma", Font.BOLD, 11));
			// contentPane.add(alertTable);
		} catch (SQLException e1) {
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
