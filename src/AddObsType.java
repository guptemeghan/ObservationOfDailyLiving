import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

import java.sql.*;
import java.awt.Color;
public class AddObsType extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblObservation;
	private JLabel lblAttributes;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddObsType frame = new AddObsType();
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
	public AddObsType() {
		setTitle("Add Observation Type-Physician");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblType = new JLabel("Type");
		lblType.setForeground(Color.WHITE);
		lblType.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblType.setHorizontalAlignment(SwingConstants.LEFT);
		lblType.setBounds(20, 26, 78, 27);
		contentPane.add(lblType);
		
		textField_2 = new JTextField();
		textField_2.setBounds(122, 27, 95, 27);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		lblObservation = new JLabel("Observation");
		lblObservation.setForeground(Color.WHITE);
		lblObservation.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblObservation.setHorizontalAlignment(SwingConstants.LEFT);
		lblObservation.setBounds(20, 65, 78, 33);
		contentPane.add(lblObservation);
		
		String[] categories = {"Behavioral","Physiological","Psychological"};
		contentPane.setLayout(null);

		final JComboBox categorylist = new JComboBox(categories);
		categorylist.setBounds(121, 69, 136, 26);
		contentPane.add(categorylist);
		
		lblAttributes = new JLabel("First Attribute");
		lblAttributes.setForeground(Color.WHITE);
		lblAttributes.setHorizontalAlignment(SwingConstants.LEFT);
		lblAttributes.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblAttributes.setBounds(20, 120, 78, 27);
		contentPane.add(lblAttributes);
		
		textField_3 = new JTextField();
		textField_3.setBounds(121, 114, 136, 33);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblDisease = new JLabel("Class");
		lblDisease.setForeground(Color.WHITE);
		lblDisease.setHorizontalAlignment(SwingConstants.LEFT);
		lblDisease.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblDisease.setBounds(20, 262, 78, 25);
		contentPane.add(lblDisease);
		
		String[] disease = {"N/A","COPD","HIV","Blood Pressure","High Risk Pregnancy"};
		contentPane.setLayout(null);

		final JComboBox diseaseList = new JComboBox(disease);
		diseaseList.setBounds(121, 262, 136, 26);
		contentPane.add(diseaseList);
		

		String[] a = {"Greater than","less than","Greater than or equal to","Less than or equal to"};
		final JComboBox comboBox = new JComboBox(a);
		comboBox.setBounds(218, 159, 126, 20);
		contentPane.add(comboBox);
		
		
		final JComboBox comboBox_1 = new JComboBox(a);
		comboBox_1.setBounds(218, 232, 126, 20);
		contentPane.add(comboBox_1);
		
				
		JButton btnAdd = new JButton("Add");
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setFocusPainted(false);
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int op = 1;
				int op2 = 1;
				
			    	    
			   
				if(textField_2.getText().equals("") || textField_3.getText().equals(""))// || textField_5.getText().equals("") || textField_5.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Textfields cannot be empty");
					setVisible(false);
					(new AddObsType()).setVisible(true);
				}
				else
				{
					String typename = textField_2.getText();
					String categoryname = (String) categorylist.getSelectedItem();
					
					String attributes = textField_3.getText();
					
					int threshold = 0;
					if(textField_4.getText().equals(""))
					{
						threshold = 0;
					}
					else
					{
						threshold = Integer.parseInt(textField_4.getText());
					}
					
					
					String attributes2 = null;
					if(textField_5.getText().equals(""))
					{
						attributes2 = null;
					}
					else
					{
						attributes2 = textField_5.getText();
					}
					int threshold2 = 0;
					if(textField_4.getText().equals(""))
						
					{
						threshold2 = 0;
					}
					else
					{
					threshold2 = Integer.parseInt(textField_6.getText());
					}
					
					
					
					
					
					
					String condition = (String) comboBox.getSelectedItem();
					String condition2 = (String) comboBox_1.getSelectedItem();
					
					String diseasename = (String) diseaseList.getSelectedItem();
					int abc = 2;
					if(diseasename.equals("N/A"))
					{
						diseasename = null;
						abc = 0;
					}
					
					if(condition.equalsIgnoreCase("less than"))
						op=2;
					if(condition.equalsIgnoreCase("greater than or equal to"))
						op=3;
					if(condition.equalsIgnoreCase("less than or equal to"))
						op=4;
					if(textField_4.getText().equals(""))
						op = 0;
					
					if(condition2.equalsIgnoreCase("less than"))
						op2=2;
					if(condition2.equalsIgnoreCase("greater than or equal to"))
						op2=3;
					if(condition2.equalsIgnoreCase("less than or equal to"))
						op2=4;
					
					if(textField_6.getText().equals(""))
						op2 = 0;
					
					int ctid = 21;
					if (categoryname.equalsIgnoreCase("Physiological"))
					{
						ctid = 22;
					}
					if (categoryname.equalsIgnoreCase("Psychological"))
					{
						ctid = 23;
					}
					String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
					String user = "aprasad3";	// For example, "jsmith"
				    String passwd = "200015261";	// Your 9 digit student ID number	
					
					
					
					
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
					} catch (ClassNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					Connection conn = null;
					try {
						conn = DriverManager.getConnection(jdbcURL, user, passwd);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
					}
					Statement stmt = null;
					try {
						stmt = conn.createStatement();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					ResultSet rs = null;
					try {
						rs = stmt.executeQuery("SELECT * FROM TYPE");
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					int typeid = 0;
					int demo = 0;
					try {
						while(rs.next())
						{
							demo = rs.getInt(1);
							if(demo > typeid)
							{
								typeid = demo;
							}
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					//System.out.println(typeid);
				try {
					
				PreparedStatement pst = conn.prepareStatement("INSERT INTO TYPE VALUES (?,?,?,?,?,?,?,?,?)");
					pst.setInt(1,(typeid+1));
					pst.setString(2,typename);
					
					pst.setString(3,attributes);
					pst.setString(4,attributes2);
					pst.setString(5,null);
					
					pst.setString(6,diseasename);
					pst.setInt(7,ctid);
					pst.setString(8, null);
					pst.setInt(9,abc);
					pst.executeUpdate();
					pst.close();
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
				try {
					rs.close();
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				
				ResultSet rs1 = null;
				try {
					rs1 = stmt.executeQuery("SELECT * FROM INFODETAILS");
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				
				int typeid1 = 0;
				int demo1 = 0;
				try {
					while(rs1.next())
					{
						demo1 = rs1.getInt(1);
						if(demo1 > typeid1)
						{
							typeid1 = demo1;
						}
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					PreparedStatement pst1 = conn.prepareStatement("INSERT INTO INFODETAILS VALUES (?,?,?,?,?,?)");
					pst1.setInt(1, (typeid1+1));
					pst1.setString(2,attributes);
					pst1.setInt(3,  threshold);
					pst1.setString(4, typename);
					pst1.setInt(5, ctid);
					pst1.setInt(6,op);
					pst1.executeQuery();
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				try {
					rs1.close();
				} catch (SQLException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
				
				
				ResultSet rs2 = null;
				try {
					rs2 = stmt.executeQuery("SELECT * FROM INFODETAILS");
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				
				
				
				int typeid12 = 0;
				int demo12 = 0;
				try {
					while(rs2.next())
					{
						demo12 = rs2.getInt(1);
						if(demo12 > typeid12)
						{
							typeid12 = demo12;
						}
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				if(textField_5.getText().equals("") )
				{
					
				}
				else
				{
				try {
					PreparedStatement pst2 = conn.prepareStatement("INSERT INTO INFODETAILS VALUES (?,?,?,?,?,?)");
					pst2.setInt(1, (typeid12+1));
					pst2.setString(2,attributes2);
					pst2.setInt(3,  threshold2);
					pst2.setString(4, typename);
					pst2.setInt(5, ctid);
					pst2.setInt(6,op2);
					pst2.executeQuery();
					
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				
				}
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
	
				setVisible(false);
				(new PhysicianHome()).setVisible(true);
				
				}
				
				//System.out.println(typename + " " + categoryname + " " + attributes + ctid);
			}
		});
		btnAdd.setBounds(49, 312, 89, 23);
		contentPane.add(btnAdd);
		
		JLabel lblThreshold = new JLabel("Threshold");
		lblThreshold.setForeground(Color.WHITE);
		lblThreshold.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblThreshold.setHorizontalAlignment(SwingConstants.LEFT);
		lblThreshold.setBounds(20, 158, 78, 22);
		contentPane.add(lblThreshold);
		
		textField_4 = new JTextField();
		textField_4.setBounds(122, 159, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(Color.WHITE);
		btnBack.setFocusPainted(false);
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new PhysicianHome().setVisible(true);
			}
		});
		btnBack.setBounds(204, 312, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblAttribute = new JLabel("Second Attribute");
		lblAttribute.setForeground(Color.WHITE);
		lblAttribute.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblAttribute.setHorizontalAlignment(SwingConstants.LEFT);
		lblAttribute.setBounds(20, 201, 92, 14);
		contentPane.add(lblAttribute);
		
		textField_5 = new JTextField();
		textField_5.setBounds(122, 196, 135, 27);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblThreshold_1 = new JLabel("Threshold");
		lblThreshold_1.setForeground(Color.WHITE);
		lblThreshold_1.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblThreshold_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblThreshold_1.setBounds(20, 231, 68, 20);
		contentPane.add(lblThreshold_1);
		
		textField_6 = new JTextField();
		textField_6.setBounds(122, 231, 86, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		
		
	}
}
