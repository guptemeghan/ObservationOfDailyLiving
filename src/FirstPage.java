import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Cursor;

public class FirstPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstPage frame = new FirstPage();
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
	public FirstPage() {
		
		setForeground(Color.BLACK);
		setTitle("Observation Of Daily Living");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		contentPane.setBackground(Color.BLACK);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton login = new JButton("Login");
		login.setFocusPainted(false);
		login.setFont(new Font("Segoe UI", Font.BOLD, 11));
		login.setForeground(Color.BLACK);
		login.setBackground(Color.WHITE);
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				(new LoginPage()).setVisible(true);
			};

		});
		login.setBounds(165, 94, 89, 23);
		contentPane.add(login);

		JButton createUser = new JButton("Register");
		createUser.setForeground(Color.BLACK);
		createUser.setFocusPainted(false);
		createUser.setFont(new Font("Segoe UI", Font.BOLD, 11));
		createUser.setBackground(Color.WHITE);
		createUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				(new PatientOrPhysician()).setVisible(true);

			}
		});
		createUser.setBounds(165, 128, 89, 23);
		contentPane.add(createUser);

		final JButton exit = new JButton("Exit");
		exit.setFocusPainted(false);
		exit.setForeground(Color.BLACK);
		exit.setFont(new Font("Segoe UI", Font.BOLD, 11));
		exit.setBackground(Color.WHITE);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					System.exit(0);
			}
		});
		exit.setBounds(165, 162, 89, 23);
		contentPane.add(exit);

		JLabel odl = new JLabel("Observations Of Daily Living");
		odl.setForeground(Color.WHITE);
		odl.setFont(new Font("Segoe UI", Font.BOLD, 20));
		odl.setBounds(71, 40, 333, 43);
		contentPane.add(odl);
	}
}
