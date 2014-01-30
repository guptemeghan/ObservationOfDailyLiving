import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.DriverManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.sql.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class ViewPatients extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewPatients frame = new ViewPatients();
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
	public ViewPatients() {
		setTitle("View Patients-Main Page");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 214);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		int count = 0;
		final JButton btnViewBy = new JButton("View By Name");
		btnViewBy.setBackground(Color.WHITE);
		btnViewBy.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnViewBy.setFocusPainted(false);
		btnViewBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//if(btnViewBy.isSelected())
				{
				setVisible(false);
				new ViewPatientsByName().setVisible(true);
				}
			}
		});
		btnViewBy.setBounds(154, 54, 109, 32);
		contentPane.add(btnViewBy);

		JButton btnViewByType = new JButton("View By Type");
		btnViewByType.setBackground(Color.WHITE);
		btnViewByType.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnViewByType.setFocusPainted(false);
		btnViewByType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ViewPatientsByType().setVisible(true);
			}
		});
		btnViewByType.setBounds(154, 108, 109, 32);
		contentPane.add(btnViewByType);

		JButton btnBack = new JButton("Back");
		btnBack.setBackground(Color.WHITE);
		btnBack.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnBack.setFocusPainted(false);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new PhysicianHome().setVisible(true);

			}
		});
		btnBack.setBounds(335, 150, 89, 23);
		contentPane.add(btnBack);

	}
}
