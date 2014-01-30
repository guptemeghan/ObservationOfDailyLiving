import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JButton;
import javax.swing.JTable;

import java.sql.*;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.table.*;

import oracle.jdbc.util.Login;

import java.awt.Font;

import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import java.awt.Color;

public class SendMessage extends JFrame {

	private JPanel contentPane;
	String username = LoginPage.ST_id;
	String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
	String user = "aprasad3"; // For example, "jsmith"
	String passwd = "200015261"; // Your 9 digit student ID number
	private JButton btnSendMsg;
	private JTextArea textArea;
	private JTextArea textArea_1;
	/**
	 * /** Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SendMessage frame = new SendMessage();
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
	public SendMessage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTo = new JLabel("To :");
		lblTo.setForeground(Color.WHITE);
		lblTo.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblTo.setBounds(10, 25, 46, 14);
		contentPane.add(lblTo);

		JLabel lblMessage = new JLabel("Message");
		lblMessage.setForeground(Color.WHITE);
		lblMessage.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblMessage.setBounds(10, 50, 83, 14);
		contentPane.add(lblMessage);

		JLabel lblRcvr = new JLabel("");
		lblRcvr.setForeground(Color.WHITE);
		lblRcvr.setFont(new Font("Segoe UI", Font.BOLD, 11));
		lblRcvr.setBounds(47, 25, 226, 14);
		contentPane.add(lblRcvr);
		lblRcvr.setText(ManageHealthFriends.HF_name);

		textArea_1 = new JTextArea();
		textArea_1.setBounds(10, 75, 357, 132);
		contentPane.add(textArea_1);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.setFocusPainted(false);
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection conn = DriverManager.getConnection(jdbcURL,
							user, passwd);
					Statement stmt = conn.createStatement();
					String sql = "INSERT INTO HealthFriendMessages VALUES (msg_seq.NEXTVAL,'"
							+ LoginPage.ST_id
							+ "','"
							+ ManageHealthFriends.HF_id
							+ "','"
							+ textArea_1.getText() + "',0,sysdate)";
					stmt.executeQuery(sql);
					int selectedOption = JOptionPane.showConfirmDialog(null, 
	                        "Message Sent. Send another Message ?", 
	                        "Choose", 
	                        JOptionPane.YES_NO_OPTION); 
						if (selectedOption == JOptionPane.YES_OPTION) {
							textArea_1.setText("");
						}
						else
						{
							setVisible(false);
							ManageHealthFriends mhf = new ManageHealthFriends();
							mhf.setVisible(true);
						}
				} catch (SQLException e1) {
					//e1.printStackTrace();
				} catch (ClassNotFoundException ew) {
					//ew.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(216, 227, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFocusPainted(false);
		btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnCancel.setBackground(Color.WHITE);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna close the window?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
					if (selectedOption == JOptionPane.YES_OPTION) {
						setVisible(false);
						ManageHealthFriends mhf = new ManageHealthFriends();
						mhf.setVisible(true);
					}
			}
		});
		btnCancel.setBounds(319, 227, 89, 23);
		contentPane.add(btnCancel);
		

	}
	public JTextArea getTextArea() {
		return textArea_1;
	}
}
