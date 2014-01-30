import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PatientHome extends JFrame {

	private JPanel contentPane;
	private JLabel patientname;
	private JLabel lblAlerts;
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
					PatientHome frame = new PatientHome();
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
	public PatientHome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		final JLabel lblAlerts = new JLabel("Self Alerts !");
		lblAlerts.setVisible(false);
		lblAlerts.setForeground(Color.RED);
		lblAlerts.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblAlerts.setBounds(287, 137, 64, 14);
		contentPane.add(lblAlerts);
		
		JButton viewAlerts = new JButton("View Alerts");
		viewAlerts.setFocusPainted(false);
		viewAlerts.setBackground(Color.WHITE);
		viewAlerts.setFont(new Font("Segoe UI", Font.BOLD, 11));
		viewAlerts.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblAlerts.setVisible(false);
				setVisible(false);
				ViewAlerts va = new ViewAlerts();
				va.setVisible(true);
			}
		});
		viewAlerts.setBounds(231, 96, 177, 30);
		contentPane.add(viewAlerts);
		
		//Check for Self Alerts
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager
					.getConnection(jdbcURL, user, passwd);
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM Alerts where ptid ='" + LoginPage.ST_id
					+ "' and indicator=0";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				lblAlerts.setVisible(true);
			}
			conn.close();
		} catch (SQLException e1) {
			//e1.printStackTrace();
		} catch (ClassNotFoundException ew) {
			//ew.printStackTrace();
		}
		
		JLabel lblHFAlerts = new JLabel("Friend Alerts !");
		lblHFAlerts.setVisible(false);
		lblHFAlerts.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblHFAlerts.setForeground(new Color(255, 0, 0));
		lblHFAlerts.setBounds(185, 176, 89, 14);
		contentPane.add(lblHFAlerts);
		
		//Check for HealthFriend Alerts
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager
							.getConnection(jdbcURL, user, passwd);
					conn.setAutoCommit(false);
					Statement stmt = conn.createStatement();
					String sql = "SELECT DISTINCT H.PATIENTID from HEALTHFRIENDS H WHERE H.FRIENDID IN (SELECT  distinct u.id  FROM users u , alerts a WHERE u.ID=a.PTID AND a.indicator=0 AND (a.ALERTDATE-u.LASTLOGIN)>7) AND H.PATIENTID = '"+LoginPage.ST_id+"'";
					ResultSet rs = stmt.executeQuery(sql);
					if (rs.next()) {
						lblHFAlerts.setVisible(true);
					}
					conn.close();
				} catch (SQLException e1) {
					////e1.printStackTrace();
				} catch (ClassNotFoundException ew) {
					////ew.printStackTrace();
				}
		
		JLabel lblMessages = new JLabel("Messages");
		lblMessages.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				ViewMessages vm=new ViewMessages();
				vm.setVisible(true);
			}
		});
		lblMessages.setVisible(false);
		lblMessages.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMessages.setForeground(new Color(30, 144, 255));
		lblMessages.setBounds(345, 19, 79, 14);
		contentPane.add(lblMessages);
		
		//Check for Messages
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(jdbcURL, user, passwd);
			conn.setAutoCommit(false);
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM HealthFriendMessages where msgto ='" + LoginPage.ST_id
					+ "' and readflag=0";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				lblMessages.setVisible(true);
			}
			conn.close();
		} catch (SQLException e1) {
			////e1.printStackTrace();
		} catch (ClassNotFoundException ew) {
			////ew.printStackTrace();
		}

		JButton enterObservation = new JButton("Enter Observation");
		enterObservation.setFocusPainted(false);
		enterObservation.setBackground(Color.WHITE);
		enterObservation.setFont(new Font("Segoe UI", Font.BOLD, 11));
		enterObservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				EnterObservation eo = new EnterObservation();
				eo.setVisible(true);
			}
		});
		enterObservation.setBounds(42, 58, 177, 30);
		contentPane.add(enterObservation);

		JButton viewObservation = new JButton("View Observation");
		viewObservation.setFocusPainted(false);
		viewObservation.setFont(new Font("Segoe UI", Font.BOLD, 11));
		viewObservation.setBackground(Color.WHITE);
		viewObservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ViewObservations vo = new ViewObservations();
				vo.setVisible(true);
			}
		});
		viewObservation.setBounds(42, 96, 177, 30);
		contentPane.add(viewObservation);

		JButton addObservation = new JButton("Add Observation Type");
		addObservation.setFocusPainted(false);
		addObservation.setBackground(Color.WHITE);
		addObservation.setFont(new Font("Segoe UI", Font.BOLD, 11));
		addObservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
				new AddObservationType().setVisible(true);

			}
		});
		addObservation.setBounds(231, 58, 177, 30);
		contentPane.add(addObservation);



		JLabel lblPatientHome = new JLabel("Patient Home");
		lblPatientHome.setForeground(Color.WHITE);
		lblPatientHome.setFont(new Font("Segoe UI", Font.BOLD, 20));
		lblPatientHome.setBounds(151, 9, 188, 24);
		contentPane.add(lblPatientHome);

		patientname = new JLabel("");
		patientname.setVisible(false);
		patientname.setBounds(197, 46, 46, 14);
		contentPane.add(patientname);

		JButton manageHealthfriends = new JButton("Manage Healthfriends");
		manageHealthfriends.setFocusPainted(false);
		manageHealthfriends.setFont(new Font("Segoe UI", Font.BOLD, 11));
		manageHealthfriends.setBackground(Color.WHITE);
		manageHealthfriends.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				ManageHealthFriends mhf = new ManageHealthFriends();
				mhf.setPatientid(patientname.getText());
				mhf.setVisible(true);
			}
		});
		manageHealthfriends.setBounds(134, 193, 177, 30);
		contentPane.add(manageHealthfriends);
		
		JLabel lblLogout = new JLabel("logout");
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				LoginPage lp=new LoginPage();
				lp.setVisible(true);
			}
		});
		lblLogout.setForeground(Color.WHITE);
		lblLogout.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblLogout.setBounds(378, 236, 46, 14);
		contentPane.add(lblLogout);
		
	}

	public String getPatientname() {
		return this.patientname.getText();
	}

	public void setPatientname(String text) {
		patientname.setText(text);
	}
}
