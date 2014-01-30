import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.DriverManager;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.sql.*;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;

public class ViewPatientsByName extends JFrame {

	static JPanel contentPane;
	private JTextField textField;
	static  String searchname;
	// JLabel label1 ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPatientsByName frame = new ViewPatientsByName();
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
	public ViewPatientsByName() {
		setTitle("View Patients By Name");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 384, 133);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblEnterName = new JLabel("Enter Name");
		lblEnterName.setForeground(Color.WHITE);
		lblEnterName.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblEnterName.setBounds(32, 35, 76, 28);
		contentPane.add(lblEnterName);

		textField = new JTextField();
		textField.setBounds(105, 35, 152, 28);
		contentPane.add(textField);
		textField.setColumns(10);

		final JButton btnSearch = new JButton("Search");
		btnSearch.setFocusPainted(false);
		btnSearch.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnSearch.setBackground(Color.WHITE);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*if(textField.getText().equals(null) || textField.getText().equals(""))
					setVisible(false);
					new ViewPatientsByName().setVisible(true);*/
				//btnSearch.enable(false);
				searchname = textField.getText();
				// System.out.println(searchname);
				//if(searchname.length() == 0)
				if(textField.getText().isEmpty())	
				{
					JOptionPane.showMessageDialog(null, "Textfield cannot be empty");
				}
				else
				{
				setVisible(false);
				new DisplayPatients().setVisible(true);
				}
			}
		});
		btnSearch.setBounds(269, 38, 89, 23);
		contentPane.add(btnSearch);

	}
}
