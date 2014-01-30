import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.Font;
import java.awt.Color;

public class AddObservationType extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddObservationType frame = new AddObservationType();
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
	public AddObservationType() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewTypeName = new JLabel("New Type Name");
		lblNewTypeName.setForeground(Color.WHITE);
		lblNewTypeName.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNewTypeName.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewTypeName.setBounds(30, 22, 93, 26);
		contentPane.add(lblNewTypeName);

		textField = new JTextField();
		textField.setBounds(161, 25, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Observation");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(30, 58, 93, 26);
		contentPane.add(lblNewLabel);

		String[] categories = { "Behavioral", "Physiological", "Psychological" };
		contentPane.setLayout(null);

		final JComboBox categorylist = new JComboBox(categories);
		categorylist.setBounds(161, 59, 136, 26);
		contentPane.add(categorylist);

		String[] threshold = {"Greater than","less than","Greater than or equal to","Less than or equal to"};
		final JComboBox comboBox = new JComboBox(threshold);
		comboBox.setBounds(270, 133, 109, 20);
		contentPane.add(comboBox);
		
		final JComboBox comboBox_1 = new JComboBox(threshold);
		comboBox_1.setBounds(270, 212, 109, 20);
		contentPane.add(comboBox_1);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setBackground(Color.WHITE);
		btnAdd.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnAdd.setFocusPainted(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {



				if (textField.getText().equals("") || textField_1.getText().equals(""))// || textField_2.getText().equals("") || textField_3.getText().equals("") || textField_4.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Textfields cannot be empty");
					setVisible(false);
					(new AddObservationType()).setVisible(true);
				} 
				else 
				{
					
					
					String typename = textField.getText();
					
					String categoryname = (String) categorylist.getSelectedItem();
							
					String attributes = textField_1.getText();
					int thresh = 0;
					if(textField_2.getText().equals(""))
						
					{
						thresh = 0;
					}
					else
					{
						thresh = Integer.parseInt(textField_2.getText());
					}
					String attributes2 = null;
					
					if(textField_3.getText().equals(""))
					{
						attributes2 = null;
					}
					else
					{
						attributes2 = textField_3.getText();
					}
					
					int thresh2 = 0;
					if(textField_4.getText().equals(""))
					{
						thresh2 = 0;
					}
					else
					{
						thresh2 = Integer.parseInt(textField_4.getText());
					}
					
					
					String condition = (String) comboBox.getSelectedItem();
					String condition2 = (String) comboBox_1.getSelectedItem();
					
					int op=1;
					if(condition.equalsIgnoreCase("less than"))
						op=2;
					if(condition.equalsIgnoreCase("greater than or equal to"))
						op=3;
					if(condition.equalsIgnoreCase("less than or equal to"))
						op=4;
					
					int op2=1;
					if(condition.equalsIgnoreCase("less than"))
						op2=2;
					if(condition.equalsIgnoreCase("greater than or equal to"))
						op2=3;
					if(condition.equalsIgnoreCase("less than or equal to"))
						op2=4;
					
					
					
					int ctid = 21;
					if (categoryname.equalsIgnoreCase("Physiological")) {
						ctid = 22;
					}
					if (categoryname.equalsIgnoreCase("Psychological")) {
						ctid = 23;
					}
					String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
					String user = "aprasad3"; // For example, "jsmith"
					String passwd = "200015261"; // Your 9 digit student ID number
					
					
					
					
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
					} catch (ClassNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					Connection conn = null;
					try {
						conn = DriverManager.getConnection(jdbcURL, user,
								passwd);
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
						while (rs.next()) {
							demo = rs.getInt(1);
							if (demo > typeid) {
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
						pst.setInt(1, (typeid + 1));
						pst.setString(2, typename);

						pst.setString(3, attributes);
						pst.setString(4, attributes2);
						pst.setString(5, null);

						pst.setString(6, null);
						pst.setInt(7, ctid);
						pst.setString(8,(LoginPage.ST_id));
						pst.setInt(9,1);

						pst.executeUpdate();
						pst.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
					}
					try {
						rs.close();
					} catch (SQLException e4) {
						// TODO Auto-generated catch block
						e4.printStackTrace();
					}
					
					ResultSet rs1=null;
					try {
						rs1=stmt.executeQuery("SELECT * FROM INFODETAILS");
					} catch (SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
								
					int typeid1 = 1;
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
					System.out.println(typeid1);
					try {
						PreparedStatement pst1 = conn.prepareStatement("INSERT INTO INFODETAILS VALUES (?,?,?,?,?,?)");
						pst1.setInt(1, (typeid1+1));
						pst1.setString(2,attributes);
						pst1.setInt(3,thresh);
						pst1.setString(4, typename);
						pst1.setInt(5, ctid);
						pst1.setInt(6,op);
						pst1.executeUpdate();
						pst1.close();
												
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}

					try {
						rs1.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
					}
					
					
					
					ResultSet rs2=null;
					try {
						rs2=stmt.executeQuery("SELECT * FROM INFODETAILS");
					} catch (SQLException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}
								
					int typeid12 = 1;
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
					System.out.println(typeid12);
					
					if(textField_3.getText().equals(""))
					{
						
					}
					else
					{
					try {
						PreparedStatement pst2 = conn.prepareStatement("INSERT INTO INFODETAILS VALUES (?,?,?,?,?,?)");
						pst2.setInt(1, (typeid12+1));
						pst2.setString(2,attributes2);
						pst2.setInt(3,thresh2);
						pst2.setString(4, typename);
						pst2.setInt(5, ctid);
						pst2.setInt(6,op2);
						pst2.executeUpdate();
						pst2.close();
												
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
					
					
					
					
					
					
					
					
					
					
					
					setVisible(false);
					(new PatientHome()).setVisible(true);

				}
			}
		});
		btnAdd.setBounds(89, 243, 89, 23);
		contentPane.add(btnAdd);

		JLabel lblAttributes = new JLabel("First Attribute");
		lblAttributes.setForeground(Color.WHITE);
		lblAttributes.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblAttributes.setHorizontalAlignment(SwingConstants.LEFT);
		lblAttributes.setBounds(30, 95, 124, 26);
		contentPane.add(lblAttributes);

		textField_1 = new JTextField();
		textField_1.setBounds(161, 96, 136, 26);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblThreshold = new JLabel("Threshold");
		lblThreshold.setForeground(Color.WHITE);
		lblThreshold.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblThreshold.setHorizontalAlignment(SwingConstants.LEFT);
		lblThreshold.setBounds(30, 133, 93, 20);
		contentPane.add(lblThreshold);
		
		textField_2 = new JTextField();
		textField_2.setBounds(161, 133, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBackground(Color.WHITE);
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnBack.setFocusPainted(false);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new PatientHome().setVisible(true);
			}
		});
		btnBack.setBounds(220, 243, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblAttributes_1 = new JLabel("Second Attribute");
		lblAttributes_1.setForeground(Color.WHITE);
		lblAttributes_1.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblAttributes_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblAttributes_1.setBounds(30, 176, 93, 26);
		contentPane.add(lblAttributes_1);
		
		textField_3 = new JTextField();
		textField_3.setBounds(161, 173, 136, 26);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblThreshold_1 = new JLabel("Threshold");
		lblThreshold_1.setForeground(Color.WHITE);
		lblThreshold_1.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblThreshold_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblThreshold_1.setBounds(30, 213, 86, 14);
		contentPane.add(lblThreshold_1);
		
		textField_4 = new JTextField();
		textField_4.setBounds(161, 210, 86, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		
		
		

	}
}
