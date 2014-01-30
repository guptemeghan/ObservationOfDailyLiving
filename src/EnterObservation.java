import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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

public class EnterObservation extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	String username = LoginPage.ST_id;
	String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
	String user = "aprasad3"; // For example, "jsmith"
	String passwd = "200015261"; // Your 9 digit student ID number
	private JLabel info1label;
	private JLabel info2label;
	private JLabel lblDate;
	private JLabel lblTime;
	private JComboBox datecomboBox;
	private JComboBox monthcomboBox;
	private JComboBox yearcomboBox;
	private JComboBox hourcomboBox;
	private JComboBox minutecomboBox;
	private JButton btnSave;
	private JLabel info3label;
	String tname = "";
	private JComboBox AmPmcomboBox;
	private JComboBox ObsTypeComboBox;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EnterObservation frame = new EnterObservation();
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
	public EnterObservation() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// labels declared
		info1label = new JLabel("");
		info1label.setFont(new Font("Segoe UI", Font.BOLD, 12));
		info1label.setForeground(Color.WHITE);
		info1label.setBounds(22, 72, 136, 14);
		contentPane.add(info1label);

		textField = new JTextField();
		textField.setBounds(190, 72, 184, 42);
		textField.setVisible(false);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(190, 125, 184, 40);
		textField_1.setVisible(false);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		info3label = new JLabel("");
		info3label.setFont(new Font("Segoe UI", Font.BOLD, 12));
		info3label.setForeground(Color.WHITE);
		info3label.setBounds(22, 176, 136, 14);
		contentPane.add(info3label);

		textField_2 = new JTextField();
		textField_2.setBounds(190, 176, 184, 44);
		textField_2.setVisible(false);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager
					.getConnection(jdbcURL, user, passwd);
			Statement stmt = conn.createStatement();
			String sql = "SELECT DISTINCT t.tname from type t where t.generalFlag = 0 UNION SELECT DISTINCT t.tname from type t where t.generalFlag = 1 and t.ptid='"+LoginPage.ST_id+"' UNION SELECT DISTINCT t.tname from type t,patientclass p where t.generalFlag = 2 and p.CNAME=t.class and p.PTID='"+LoginPage.ST_id+"'";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);;
			System.out.println(rs.getRow());
			Vector<String> temp = new Vector<String>();
			temp.add("Select");
			while (rs.next()) {
				temp.add(rs.getString("tname"));
			}

			final JComboBox ObsTypeComboBox = new JComboBox();
			for(int i=0;i<temp.size();i++)
			{
			ObsTypeComboBox.addItem(temp.elementAt(i));
			}
			ObsTypeComboBox.setBounds(22, 31, 86, 20);
			ObsTypeComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						tname = ObsTypeComboBox.getSelectedItem().toString();
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection conn = DriverManager.getConnection(jdbcURL,
								user, passwd);
						Statement stmt = conn.createStatement();
						String sql = "select infoname from infodetails where tname  = '"
								+ ObsTypeComboBox.getSelectedItem().toString()
								+ "'";
						System.out.println(sql);
						ResultSet rs = stmt.executeQuery(sql);
						System.out.println(rs.getRow());
						Vector<String> temp = new Vector<String>();
						while (rs.next()) {
							temp.add(rs.getString("infoname"));
						}
						System.out.println(temp.size());
						if (temp.size() > 0) {
							setInfo1label(temp.get(0));
							textField.setVisible(true);
						}
						if (temp.size() > 1) {
							setInfo2label(temp.get(1));
							textField_1.setVisible(true);
						}
						conn.close();
					} catch (SQLException e1) {
						////e1.printStackTrace();
					} catch (ClassNotFoundException ew) {
						////ew.printStackTrace();
					}
				}
			});
			contentPane.add(ObsTypeComboBox);
			conn.close();
		} catch (SQLException e1) {
			//e1.printStackTrace();
		} catch (ClassNotFoundException ew) {
			//ew.printStackTrace();
		}

		JLabel lblSelectObservationType = new JLabel("Observation Type");
		lblSelectObservationType.setForeground(Color.WHITE);
		lblSelectObservationType.setBounds(22, 6, 108, 14);
		lblSelectObservationType.setFont(new Font("Segoe UI", Font.BOLD, 12));
		contentPane.add(lblSelectObservationType);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnCancel.setFocusPainted(false);
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setForeground(Color.BLACK);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				PatientHome ph = new PatientHome();
				ph.setVisible(true);
			}
		});
		btnCancel.setBounds(285, 227, 89, 23);
		contentPane.add(btnCancel);

		info2label = new JLabel("");
		info2label.setFont(new Font("Segoe UI", Font.BOLD, 12));
		info2label.setForeground(Color.WHITE);
		info2label.setBounds(22, 125, 136, 14);
		contentPane.add(info2label);

		String[] date = { "", "01", "02", "03", "04", "05", "06", "07", "08",
				"09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
				"19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
				"29", "30", "31" };
		String[] month = { "", "01", "02", "03", "04", "05", "06", "07", "08",
				"09", "10", "11", "12" };
		String[] year = { "", "2009", "2010", "2011", "2012", "2013", "2014",
				"2015" };
		String[] hour = { "", "00", "01", "02", "03", "04", "05", "06", "07",
				"08", "09", "10", "11" };
		String[] minutes = { "", "00", "01", "02", "03", "04", "05", "06",
				"07", "08", "09", "10", "11", "12", "13", "14", "15", "16",
				"17", "18", "19", "20", "21", "22", "23", "24", "25", "26",
				"27", "28", "29", "30", "31", "32", "33", "34", "35", "36",
				"37", "38", "39", "40", "41", "42", "43", "44", "45", "46",
				"47", "48", "49", "50", "51", "52", "53", "54", "55", "56",
				"57", "58", "59" };
		String[] ampm = { "", "AM", "PM" };
		lblDate = new JLabel("Date");
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblDate.setBounds(140, 6, 108, 14);
		contentPane.add(lblDate);

		lblTime = new JLabel("Time");
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblTime.setBounds(285, 6, 108, 14);
		contentPane.add(lblTime);

		datecomboBox = new JComboBox(date);
		datecomboBox.setBounds(140, 31, 43, 20);
		contentPane.add(datecomboBox);

		monthcomboBox = new JComboBox(month);
		monthcomboBox.setBounds(190, 31, 38, 20);
		contentPane.add(monthcomboBox);

		yearcomboBox = new JComboBox(year);
		yearcomboBox.setBounds(230, 31, 49, 20);
		contentPane.add(yearcomboBox);

		hourcomboBox = new JComboBox(hour);
		hourcomboBox.setBounds(285, 31, 43, 20);
		contentPane.add(hourcomboBox);

		minutecomboBox = new JComboBox(minutes);
		minutecomboBox.setBounds(331, 31, 43, 20);
		contentPane.add(minutecomboBox);

		AmPmcomboBox = new JComboBox(ampm);
		AmPmcomboBox.setBounds(381, 31, 43, 20);
		contentPane.add(AmPmcomboBox);

		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnSave.setFocusPainted(false);
		btnSave.setBackground(Color.WHITE);
		btnSave.setForeground(Color.BLACK);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(tname=="SELECT" || datecomboBox.getSelectedItem().toString()=="" || monthcomboBox.getSelectedItem().toString()=="" || yearcomboBox.getSelectedItem().toString()=="" || hourcomboBox.getSelectedItem().toString()=="" ||minutecomboBox.getSelectedItem().toString()=="" ||AmPmcomboBox.getSelectedItem().toString()=="")
					{
						if(tname=="SELECT" && !(datecomboBox.getSelectedItem().toString()=="" || monthcomboBox.getSelectedItem().toString()=="" || yearcomboBox.getSelectedItem().toString()=="" || hourcomboBox.getSelectedItem().toString()=="" ||minutecomboBox.getSelectedItem().toString()=="" ||AmPmcomboBox.getSelectedItem().toString()==""))
						{
						JOptionPane.showMessageDialog(null,"Please Correct Observation Type");
						}
						else
							JOptionPane.showMessageDialog(null,"Please Correct Date");
						
					}
					else
					{
					String timeStamp = yearcomboBox.getSelectedItem()
							.toString()
							+ monthcomboBox.getSelectedItem().toString()
							+ datecomboBox.getSelectedItem().toString()
							+ " "
							+ hourcomboBox.getSelectedItem().toString()
							+ ":"
							+ minutecomboBox.getSelectedItem().toString()
							+ ":00"
							+ " "
							+ AmPmcomboBox.getSelectedItem().toString();
					Date sysDate = new Date();
					Calendar cal = Calendar.getInstance();
					String recordtimeStamp = new SimpleDateFormat(
							"YYYYMMdd KK:MM:ss a").format(cal.getTime());
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection(jdbcURL,
							user, passwd);
					Statement stmt = conn.createStatement();
					String sql = "insert into Observations values (observation_seq.NEXTVAL,TO_DATE('"
							+ timeStamp
							+ "','yyyymmdd hh:mi:ss AM'),TO_DATE('"
							+ recordtimeStamp
							+ "','yyyymmdd hh:mi:ss AM'),'"
							+ LoginPage.ST_id
							+ "','"
							+ info1label.getText()
							+ "','"
							+ info2label.getText()
							+ "','"
							+ info3label.getText()
							+ "','"
							+ textField.getText()
							+ "','"
							+ textField_1.getText()
							+ "','"
							+ textField_2.getText() + "','" + tname + "')";
					System.out.println(sql);
					stmt.executeQuery(sql);
					int selectedOption = JOptionPane.showConfirmDialog(null, 
	                        "Observation Saved. Add New Observation ?", 
	                        "Choose", 
	                        JOptionPane.YES_NO_OPTION); 
						if (selectedOption == JOptionPane.YES_OPTION) {
							textField.setText("");
							textField_1.setText("");
							textField_2.setText("");
						}
						else
						{
							setVisible(false);
							PatientHome ph = new PatientHome();
							ph.setVisible(true);
						}
					}
				} catch (SQLException e1) {
					////e1.printStackTrace();
				} catch (ClassNotFoundException ew) {
					////ew.printStackTrace();
				}
			}
		});
		btnSave.setBounds(190, 227, 89, 23);
		contentPane.add(btnSave);

	}

	public JLabel getInfo1label() {
		return info1label;
	}

	public void setInfo1label(String text) {
		this.info1label.setText(text);
	}

	public JLabel getInfo2label() {
		return info2label;
	}

	public void setInfo2label(String text) {
		info2label.setText(text);
	}

	public JLabel getInfo3label() {
		return info3label;
	}
}
