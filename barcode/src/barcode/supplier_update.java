package barcode;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.PreparedStatement;

public class supplier_update extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField company;
	private JTextField address;
	private JTextField contact;

	/**
	 * Launch the application.
	 */
	
	public void set(){
		 name.setText("");
		company.setText("");
		address.setText("");
		contact.setText("");
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					supplier_update frame = new supplier_update();
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
	
	
	java.sql.Connection conn=null;

	
		
	public supplier_update() {
		conn=Connector.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 486, 340);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ADD SUPPLIER");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(127, 11, 235, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NAME");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(60, 75, 90, 23);
		contentPane.add(lblNewLabel_1);
		
		name = new JTextField();
		name.setBounds(170, 75, 171, 23);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Company Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(60, 109, 99, 23);
		contentPane.add(lblNewLabel_2);
		
		company = new JTextField();
		company.setBounds(170, 110, 171, 25);
		contentPane.add(company);
		company.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(60, 148, 99, 21);
		contentPane.add(lblNewLabel_3);
		
		address = new JTextField();
		address.setBounds(170, 146, 171, 23);
		contentPane.add(address);
		address.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Contact No");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(60, 180, 99, 23);
		contentPane.add(lblNewLabel_4);
		
		contact = new JTextField();
		contact.setBounds(170, 179, 171, 24);
		contentPane.add(contact);
		contact.setColumns(10);
		
		JButton btn_addupdate = new JButton("Add");
		btn_addupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
						try {
							String query="insert into supplier (name,company,address,contact) values (?,?,?,?)";
							
							PreparedStatement pst=conn.prepareStatement(query);
							
							
							pst.setString(1, name.getText());
							pst.setString(2, company.getText());
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
		btn_addupdate.setBounds(170, 230, 141, 30);
		contentPane.add(btn_addupdate);
	}

}
