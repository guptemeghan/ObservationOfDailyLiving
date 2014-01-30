import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class PatientOrPhysician extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientOrPhysician frame = new PatientOrPhysician();
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
	public PatientOrPhysician() {
		this.setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 173);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSelectUserType = new JLabel("Select User Type");
		lblSelectUserType.setForeground(Color.WHITE);
		lblSelectUserType.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblSelectUserType.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectUserType.setBounds(148, 11, 164, 24);
		contentPane.add(lblSelectUserType);

		JLabel lblNewLabel = new JLabel("Are you a Patient? Click ");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel.setBounds(69, 54, 142, 25);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Are you a Physician? Click ");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblNewLabel_1.setBounds(58, 90, 153, 24);
		contentPane.add(lblNewLabel_1);

		JButton btnHere = new JButton("Here");
		btnHere.setBackground(Color.WHITE);
		btnHere.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnHere.setFocusPainted(false);
		btnHere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				(new SignUp()).setVisible(true);
			}
		});
		btnHere.setBounds(247, 56, 89, 23);
		contentPane.add(btnHere);

		JButton btnHere_1 = new JButton("Here");
		btnHere_1.setBackground(Color.WHITE);
		btnHere_1.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnHere_1.setFocusPainted(false);
		btnHere_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				(new SignUpPhysician()).setVisible(true);
			}
		});
		btnHere_1.setBounds(247, 92, 89, 23);
		contentPane.add(btnHere_1);
	}
}
