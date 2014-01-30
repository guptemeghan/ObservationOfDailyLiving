import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
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
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import java.awt.Color;


public class ViewMessages extends JFrame {

	private JPanel contentPane;
	private JTable table;
	String username = LoginPage.ST_id;
	String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl";
	String user = "aprasad3"; // For example, "jsmith"
	String passwd = "200015261"; // Your 9 digit student ID number
	private JTable msgtable;
	private JLabel lblMsg;
	private JLabel lblMsg_1;
	private JButton btnNewButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewMessages frame = new ViewMessages();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
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
	/**
	 * Create the frame.
	 */
	public ViewMessages() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JLabel lblMessages = new JLabel("Messages");
		lblMessages.setForeground(Color.WHITE);
		lblMessages.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblMessages.setBounds(183, 0, 67, 23);
		contentPane.add(lblMessages);
		
		lblMsg_1 = new JLabel("");
		lblMsg_1.setForeground(Color.WHITE);
		lblMsg_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		lblMsg_1.setVerticalAlignment(SwingConstants.TOP);
		lblMsg_1.setBounds(20, 137, 391, 89);
		contentPane.add(lblMsg_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 25, 391, 101);
		contentPane.add(scrollPane);
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager
					.getConnection(jdbcURL, user, passwd);
			Statement stmt = conn.createStatement();
			String sql = "Select msgfrom as Sender, msgtext as Message,msgid from HealthFriendMessages where msgto='"+LoginPage.ST_id+"' and Readflag=0";
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			
			msgtable = new JTable(buildTableModel(rs)) {
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			msgtable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					lblMsg_1.setText(msgtable.getModel()
							.getValueAt(msgtable.getSelectedRow(), 1).toString());
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection conn = DriverManager
								.getConnection(jdbcURL, user, passwd);
						Statement stmt = conn.createStatement();
						String sql = " UPDATE HealthFriendMessages SET Readflag=1 where msgid="+msgtable.getModel()
								.getValueAt(msgtable.getSelectedRow(), 2).toString();
						stmt.executeQuery(sql);
					} catch (SQLException e1) {
						//e1.printStackTrace();
					} catch (ClassNotFoundException ew) {
						//ew.printStackTrace();
					}
				}
			});
			scrollPane.setViewportView(msgtable);
			msgtable.removeColumn(msgtable.getColumnModel().getColumn(2));

		} catch (SQLException e1) {
			//e1.printStackTrace();
		} catch (ClassNotFoundException ew) {
			//ew.printStackTrace();
		}
		btnNewButton = new JButton("Back");
		btnNewButton.setFocusPainted(false);
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 11));
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				PatientHome ph = new PatientHome();
				ph.setVisible(true);
			}
		});
		btnNewButton.setBounds(322, 227, 89, 23);
		contentPane.add(btnNewButton);
	}

	public JLabel getLblMsg() {
		return lblMsg_1;
	}
}
