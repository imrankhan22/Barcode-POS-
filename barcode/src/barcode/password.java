package barcode;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;

public class password extends JFrame {

	private JPanel contentPane;
	private JPasswordField password;
	java.sql.Connection conn=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					password frame = new password();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public password() {
		conn=Connector.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 453, 199);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PASSWORD");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblNewLabel.setBounds(147, 11, 148, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New Password");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(45, 62, 104, 20);
		contentPane.add(lblNewLabel_1);
		
		password = new JPasswordField();
		password.setBounds(175, 64, 142, 18);
		contentPane.add(password);
		
		JButton btn_update = new JButton("Update");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="update password set pass='"+password.getText().toString()+"'";
					PreparedStatement pst=conn.prepareStatement(query);	
					pst.execute();			
					pst.close();
					JOptionPane.showMessageDialog(null, "Password Update");
					dispose();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		
		btn_update.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_update.setBounds(140, 107, 89, 23);
		contentPane.add(btn_update);
	}
}
