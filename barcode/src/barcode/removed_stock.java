package barcode;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;
import javax.swing.ImageIcon;

public class removed_stock extends JFrame {

	private JPanel contentPane;
	private JTextField barcode;
	private JTextField name;
	private JTextField company;
	private JTextField location;
	private JTextField quantity;
	private JTextField category;
	private JTextField purchase_price;
	private JTextField sale_price;
	java.sql.Connection conn=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					removed_stock frame = new removed_stock();
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
	public void set_values(){
		barcode.setText("");
		name.setText("");
		company.setText("");
		category.setText("");
		quantity.setText("");
		location.setText("");
		purchase_price.setText("");
		sale_price.setText("");
		
		
	
	}
	public removed_stock() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 662, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	conn=Connector.dbConnector();
		
		JLabel lblAddStock = new JLabel("REMOVE STOCK");
		lblAddStock.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblAddStock.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddStock.setBounds(117, 11, 242, 40);
		contentPane.add(lblAddStock);
		
		JLabel lblNewLabel = new JLabel("BAR CODE");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(59, 85, 104, 27);
		contentPane.add(lblNewLabel);
		
		barcode = new JTextField();
		barcode.setBounds(188, 85, 171, 27);
		contentPane.add(barcode);
		barcode.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("PRODUCT NAME");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(59, 123, 104, 27);
		contentPane.add(lblNewLabel_1);
		
		name = new JTextField();
		name.setBounds(188, 123, 171, 27);
		contentPane.add(name);
		name.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("COMPANY");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(59, 164, 104, 27);
		contentPane.add(lblNewLabel_2);
		
		company = new JTextField();
		company.setBounds(188, 161, 171, 32);
		contentPane.add(company);
		company.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("LOCATION");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(59, 202, 104, 27);
		contentPane.add(lblNewLabel_3);
		
		location = new JTextField();
		location.setBounds(188, 202, 171, 27);
		contentPane.add(location);
		location.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("QUANTITY");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(59, 240, 104, 27);
		contentPane.add(lblNewLabel_4);
		
		quantity = new JTextField();
		quantity.setBounds(188, 240, 171, 27);
		contentPane.add(quantity);
		quantity.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("CATEGORY");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(59, 278, 104, 27);
		contentPane.add(lblNewLabel_5);
		
		category = new JTextField();
		category.setBounds(188, 279, 171, 27);
		contentPane.add(category);
		category.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("PURCHASE PRICE");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(59, 319, 119, 27);
		contentPane.add(lblNewLabel_6);
		
		purchase_price = new JTextField();
		purchase_price.setBounds(188, 319, 171, 27);
		contentPane.add(purchase_price);
		purchase_price.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("SALE PRICE");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_7.setBounds(59, 357, 104, 27);
		contentPane.add(lblNewLabel_7);
		
		sale_price = new JTextField();
		sale_price.setBounds(188, 357, 171, 32);
		contentPane.add(sale_price);
		sale_price.setColumns(10);
		
		JButton delete = new JButton("DELETE");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
						try {
							String query="Delete from Details where barcode='"+barcode.getText().toString()+"' ";
							PreparedStatement pst=conn.prepareStatement(query);
							pst.execute();
							pst.close();
							JOptionPane.showMessageDialog(null, "Data Deleted");
							set_values();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e);
						}
				
			}
			});
		
		
		delete.setFont(new Font("Tahoma", Font.BOLD, 12));
		delete.setBounds(156, 421, 119, 27);
		contentPane.add(delete);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setToolTipText("Search");
		btnNewButton.setIcon(new ImageIcon("pics//1477109814_search.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try {
					String query="select * from Details where barcode=? ";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1, barcode.getText().toString());
					ResultSet rs=pst.executeQuery();
					barcode.setText(rs.getString("barcode"));
					name.setText(rs.getString("name"));
					company.setText(rs.getString("company"));
					location.setText(rs.getString("location"));
					quantity.setText(rs.getString("quantity"));
					category.setText(rs.getString("category"));
					purchase_price.setText(rs.getString("purchase"));
					sale_price.setText(rs.getString("sale"));
					rs.close();
					pst.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(422, 85, 46, 32);
		contentPane.add(btnNewButton);
		
		
	}
}
