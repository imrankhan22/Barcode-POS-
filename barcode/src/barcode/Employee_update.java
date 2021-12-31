package barcode;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;

public class Employee_update extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField cnic;
	private JTextField address;
	private JTextField contact;

	/**
	 * Launch the application.
	 */
	
	
	public void set(){
		 name.setText("");
		cnic.setText("");
		address.setText("");
		contact.setText("");
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employee_update frame = new Employee_update();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	java.sql.Connection conn=null;

	/**
	 * Create the frame.
	 */
	public Employee_update() {
		conn=Connector.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 371);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ADD/UPDATE EMPLOYEE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblNewLabel.setBounds(71, 11, 319, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NAME");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(51, 70, 85, 24);
		contentPane.add(lblNewLabel_1);
		
		name = new JTextField();
		name.setBounds(131, 70, 181, 24);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("CNIC");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(51, 105, 85, 24);
		contentPane.add(lblNewLabel_2);
		
		cnic = new JTextField();
		cnic.setBounds(131, 105, 181, 24);
		contentPane.add(cnic);
		cnic.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(51, 140, 85, 24);
		contentPane.add(lblNewLabel_3);
		
		address = new JTextField();
		address.setBounds(131, 140, 181, 24);
		contentPane.add(address);
		address.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Contact No");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(51, 175, 85, 24);
		contentPane.add(lblNewLabel_4);
		
		contact = new JTextField();
		contact.setBounds(131, 175, 181, 24);
		contentPane.add(contact);
		contact.setColumns(10);
		
		JButton btn_addupdate = new JButton("Add");
		btn_addupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
						try {
							String query="insert into employer (name,cnic,address,contact) values (?,?,?,?)";
							
							PreparedStatement pst=conn.prepareStatement(query);
							
							
							pst.setString(1, name.getText());
							pst.setString(2, cnic.getText());
							pst.setString(3, address.getText());
							pst.setString(4, contact.getText());

							pst.execute();				
							pst.close();
							JOptionPane.showMessageDialog(null, "Data Saved");	
							set();
						} catch (Exception e) {
							e.printStackTrace();
						}
				
			}
			
		});
		btn_addupdate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_addupdate.setBounds(127, 259, 138, 30);
		contentPane.add(btn_addupdate);
	}

}
