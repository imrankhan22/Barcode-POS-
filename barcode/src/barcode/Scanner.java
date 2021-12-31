package barcode;

import java.awt.EventQueue;

import javax.swing.JFrame;

import com.sun.corba.se.pept.transport.Connection;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JList;
import javax.swing.ImageIcon;

public class Scanner {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Scanner window = new Scanner();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	java.sql.Connection conn=null;
	private String Row_id=null;
	private JTextField txtCompanyName;
	private JTextField txtbarcode;
	private JTextField barcode;
	private JTextField txtdescription;
	private JTextField description;
	private JTextField txtCompany;
	private JTextField company;
	private JTextField txtLocation;
	private JTextField location;
	private JTextField txtQuantity;
	private JTextField quantity;
	private JTextField txtcategory;
	private JTextField category;
	private JTextField txtprice;
	private JTextField price;
	private JTextField txtTotal;
	private JTextField total;
	private JTextField txtCash;
	private JTextField cash;
	private JTextField txtChange;
	private JTextField change;
	private JButton btn_printbill;
	private DefaultTableModel model;
	private JButton btn_stockreport;
	private JButton btn_addstock;
	private JButton btn_salereport;
	private JButton btn_update;
	private JButton btn_employeedetails;
	private JButton btn_removestock;
	private JButton btn_password;
	private JButton save;
	private JTextField txtDiscount;
	private JTextField discount;
	private JButton btn_newbill;
	private JButton btn_supplierdetails;
	private int bill=0,i,x;
	private JTable table;
	private JList list;
	private JButton btnCheckStock;
	
	
	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	
	//adding in list
	
	
	public void loadList(){
		try {
			String query="select name from Details where name like '%"+description.getText().toString()+"%'";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			DefaultListModel dfl=new DefaultListModel();
			while(rs.next()){
				dfl.addElement(rs.getString("name"));
			}
			list.setModel(dfl);
			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	//adding in sale report
	
	
	public void add_in_sale_report(){
		
		DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat time = new SimpleDateFormat("HH:mm:ss");
		 Date dateobj = new Date();
		 int rows=table.getRowCount();
		 for(int row = 1; row<rows; row++)
		 {
			 String des=(String)table.getValueAt(row, 0);
			 String com=(String)table.getValueAt(row, 1);;
			 String qua=(String)table.getValueAt(row, 2);;
			 String pri=(String)table.getValueAt(row, 4);;
			 
		try {
			String query="insert into sale (name,company,quantity,price,date,time) values (?,?,?,?,?,?)";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1, des);
			pst.setString(2, com);
			pst.setString(3, qua);
			pst.setString(4, pri);
			pst.setString(5, date.format(dateobj).toString());
			pst.setString(6, time.format(dateobj).toString());
			pst.execute();				
			pst.close();		
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e);	
		}
		
		
		 }
		 
		
		
	}
	
	int checkPassword(){
		String pass = null;
		String str=null;
		
		int i=0;
		pass=JOptionPane.showInputDialog(null,"Enter Password !");
		try {
			
			String query="select * from password where pass=? ";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1, pass);
			ResultSet rs=pst.executeQuery();
			str=rs.getString("pass");
			if(pass.equals(str)){
				i=1;
			}
			rs.close();
			pst.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Wrong password");
		}
		
	
		return i;
	}
	
	public void setTable(){
		model = new DefaultTableModel();
        model.addColumn("Description");
        model.addColumn("Name");
        model.addColumn("Quantity");
        model.addColumn("PRICE/UNIT");
        model.addColumn("Price");

		  model.addRow(new Object[]{"DESCRIPTION", "COMPANY","QUANTITY","PRICE/UNIT","PRICE"});
	}
	public void addRow(){
		int i = 0,j;
		//insert into main table
		int s=(Integer.parseInt(quantity.getText().toString()))*(Integer.parseInt(price.getText().toString()));
	        model.addRow(new Object[]{description.getText(), company.getText(), quantity.getText(),price.getText(),""+s});
	        j=Integer.parseInt(quantity.getText().toString());
	        String str1 = null,str2 = null,str3 = null,str4 = null,str5 = null,str6 = null,str7 = null,str8 = null;
	       //getting data from database 

	    	try {
				String query="select * from Details where barcode=? ";
				PreparedStatement pst=conn.prepareStatement(query);
				pst.setString(1, barcode.getText().toString());
				ResultSet rs=pst.executeQuery();
				str1=rs.getString("barcode");
				str2=rs.getString("name");
				str3=rs.getString("company");
				str4=rs.getString("location");
				i=Integer.parseInt(rs.getString("quantity"));
				str6=rs.getString("category");
				str7=rs.getString("purchase");
				str8=rs.getString("sale");
				rs.close();
				pst.close();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
	 
	        j=i-j;
	        
	        //updating database
	    
	        
	    	try {
				//String query="update Details set barcode='"+str1+"',name='"+str2+"',company='"+str3+"',location='"+str4+"',quantity='"+j+"',category='"+str6+"',purchase='"+str7+"',sale='"+str8+"'";
	    		String query="UPDATE Details SET quantity='"+j+"' WHERE rowid ='"+Row_id+"'";
	    		PreparedStatement pst=conn.prepareStatement(query);
				pst.executeUpdate();			
				pst.close();
						
			} 
			
			catch (Exception e) {
						JOptionPane.showMessageDialog(null, e);
					}
	 
	        
	        
	        
	  		
	}
	
	
	
	public Scanner() {
		conn=Connector.dbConnector();
		
		initialize();
	
		
		
	}
	public void set_values(){
		barcode.setText("");
		description.setText("");
		price.setText("");
		company.setText("");
		category.setText("");
		quantity.setText("");
		location.setText("");
		
	
	}
	
	public void new_table(){
		model = (DefaultTableModel) table.getModel();
		int rowCount = model.getRowCount();
		//Remove rows one by one from the end of the table
		for (int i = rowCount - 1; i > 0; i--) {
		    model.removeRow(i);
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(175, 238, 238));
		//frame.setBounds(70, 70, 1300, 1200);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(00,0,screenSize.width,screenSize.height);
		frame.setVisible(true);
		//frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		txtCompanyName = new JTextField();
		
		txtCompanyName.setBackground(new Color(175, 238, 238));
		txtCompanyName.setEditable(false);
		txtCompanyName.setFont(new Font("Tahoma", Font.BOLD, 50));
		txtCompanyName.setHorizontalAlignment(SwingConstants.CENTER);
		txtCompanyName.setText("COMPANY");
		txtCompanyName.setBounds(0, 0,screenSize.width, 118);
		frame.getContentPane().add(txtCompanyName);
		txtCompanyName.setColumns(10);
		
		txtbarcode = new JTextField();
		txtbarcode.setBackground(new Color(175, 238, 238));
		txtbarcode.setEditable(false);
		txtbarcode.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtbarcode.setHorizontalAlignment(SwingConstants.CENTER);
		txtbarcode.setText("BAR CODE");
		txtbarcode.setBounds(0, 118, 106, 32);
		frame.getContentPane().add(txtbarcode);
		txtbarcode.setColumns(10);
		
		barcode = new JTextField();
		barcode.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				String str=barcode.getText().toString();
				try {
					String query="select * from Details where barcode='"+str+"' ";
					PreparedStatement pst=conn.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					description.setText(rs.getString("name"));
					company.setText(rs.getString("company"));
					category.setText(rs.getString("category"));
					location.setText(rs.getString("location"));
					price.setText(rs.getString("sale"));
					quantity.setText("1");
					pst.close();
					rs.close();	
					
					query="SELECT rowid, * FROM Details WHERE barcode='"+barcode.getText().toString()+"'";
					PreparedStatement pst2=conn.prepareStatement(query);
					ResultSet rs2=pst2.executeQuery();
					Row_id=rs2.getString("rowid");
					pst2.close();
					rs2.close();	
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		barcode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		barcode.setBounds(105, 118, 151, 32);
		frame.getContentPane().add(barcode);
		barcode.setColumns(10);
		
		txtdescription = new JTextField();
		txtdescription.setBackground(new Color(175, 238, 238));
		txtdescription.setEditable(false);
		txtdescription.setHorizontalAlignment(SwingConstants.CENTER);
		txtdescription.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtdescription.setText("DESCRIPTION");
		txtdescription.setBounds(321, 118, 140, 32);
		frame.getContentPane().add(txtdescription);
		txtdescription.setColumns(10);
		
		description = new JTextField();
		description.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				loadList();
				
				//String selected = list.getSelectedValue().toString();
				//description.setText(selected);
				String str=description.getText();
				try {
					String query="select * from Details where name='"+str+"' ";
					PreparedStatement pst=conn.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					while(rs.next()){
					barcode.setText(rs.getString("barcode"));
					company.setText(rs.getString("company"));
					category.setText(rs.getString("category"));
					location.setText(rs.getString("location"));
					quantity.setText("1");
					price.setText(rs.getString("sale"));
				}
					pst.close();
					rs.close();
					
					
					query="SELECT rowid, * FROM Details WHERE barcode='"+barcode.getText().toString()+"'";
					PreparedStatement pst2=conn.prepareStatement(query);
					ResultSet rs2=pst2.executeQuery();
					Row_id=rs2.getString("rowid");
					pst2.close();
					rs2.close();	
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		description.setFont(new Font("Dialog", Font.PLAIN, 14));
		description.setBounds(461, 118, 200, 32);
		frame.getContentPane().add(description);
		description.setColumns(10);
		
		txtCompany = new JTextField();
		txtCompany.setBackground(new Color(175, 238, 238));
		txtCompany.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtCompany.setEditable(false);
		txtCompany.setText("COMPANY");
		txtCompany.setHorizontalAlignment(SwingConstants.CENTER);
		txtCompany.setBounds(660, 118, 141, 32);
		frame.getContentPane().add(txtCompany);
		txtCompany.setColumns(10);
		
		company = new JTextField();
		
		company.setFont(new Font("Tahoma", Font.PLAIN, 14));
		company.setBounds(793, 118, 174, 32);
		frame.getContentPane().add(company);
		company.setColumns(10);
		
		txtLocation = new JTextField();
		txtLocation.setBackground(new Color(175, 238, 238));
		txtLocation.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtLocation.setEditable(false);
		txtLocation.setText("LOCATION");
		txtLocation.setHorizontalAlignment(SwingConstants.CENTER);
		txtLocation.setBounds(0, 151, 124, 32);
		frame.getContentPane().add(txtLocation);
		txtLocation.setColumns(10);
		
		location = new JTextField();
		
		location.setBounds(124, 151, 132, 32);
		frame.getContentPane().add(location);
		location.setColumns(10);
		
		txtQuantity = new JTextField();
		txtQuantity.setBackground(new Color(175, 238, 238));
		txtQuantity.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtQuantity.setEditable(false);
		txtQuantity.setText("QUANTITY");
		txtQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		txtQuantity.setBounds(256, 151, 141, 32);
		frame.getContentPane().add(txtQuantity);
		txtQuantity.setColumns(10);
		quantity = new JTextField();
		quantity.setBounds(398, 151, 82, 32);
		frame.getContentPane().add(quantity);
		quantity.setColumns(10);
		txtcategory = new JTextField();
		txtcategory.setBackground(new Color(175, 238, 238));
		txtcategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtcategory.setEditable(false);
		txtcategory.setText("CATEGORY");
		txtcategory.setHorizontalAlignment(SwingConstants.CENTER);
		txtcategory.setBounds(481, 151, 94, 32);
		frame.getContentPane().add(txtcategory);
		txtcategory.setColumns(10);
		category = new JTextField();
		category.setBounds(575, 151, 164, 32);
		frame.getContentPane().add(category);
		category.setColumns(10);
		txtprice = new JTextField();
		txtprice.setBackground(new Color(175, 238, 238));
		txtprice.setHorizontalAlignment(SwingConstants.CENTER);
		txtprice.setText("PRICE");
		txtprice.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtprice.setEditable(false);
		txtprice.setBounds(738, 151, 117, 32);
		frame.getContentPane().add(txtprice);
		txtprice.setColumns(10);
		price = new JTextField();
		price.setBounds(855, 151, 112, 32);
		frame.getContentPane().add(price);
		price.setColumns(10);
		JButton btn_removeitem = new JButton("Remove Item");
		btn_removeitem.setIcon(new ImageIcon("pics//Apps-Dialog-Remove-icon.png"));
		btn_removeitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				  String des = table.getModel().getValueAt(row, 0).toString();
	                String quan = table.getModel().getValueAt(row, 2).toString();
	                int i=Integer.parseInt(quan);
	                if(row != -1) {
	                	int column=3;
	                	int column1=2;
	                	
	                	String value = table.getModel().getValueAt(row, column).toString();
	                	String value1 = table.getModel().getValueAt(row, column1).toString();
	                	bill-=((Integer.parseInt(value1))*(Integer.parseInt(value)));
	                	total.setText(""+bill);
	                    model.removeRow(row);
	                    
	                }
	                
	             
	                
	                
	               
	            	try{

						 String query="select rowid,quantity from Details where name='"+des+"'";
						PreparedStatement pst2=conn.prepareStatement(query);
						ResultSet rs2=pst2.executeQuery();
						Row_id=rs2.getString("rowid");
						i+=Integer.parseInt(rs2.getString("quantity"));
						pst2.close();
						rs2.close();
						}catch (Exception e) {
							JOptionPane.showMessageDialog(null, e);
						}	
	            	
	            	
	            	
	            	
	            	
	            	
	            	
	            	
					try {
						String query="update Details set quantity='"+i+"' where rowid='"+Row_id+"'";
						PreparedStatement pst=conn.prepareStatement(query);
						pst.execute();			
						pst.close();		
					} 
					catch (Exception e) {
								JOptionPane.showMessageDialog(null, e);
							}
	          }
		});
		btn_removeitem.setBackground(SystemColor.controlHighlight);
		btn_removeitem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_removeitem.setBounds(269, 601, 190, 52);
		frame.getContentPane().add(btn_removeitem);
		
		txtTotal = new JTextField();
		txtTotal.setForeground(new Color(0, 0, 255));
		txtTotal.setBackground(new Color(175, 238, 238));
		txtTotal.setFont(new Font("Tahoma", Font.BOLD, 24));
		txtTotal.setEditable(false);
		txtTotal.setText("Total");
		txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotal.setBounds(682, 589, 132, 46);
		frame.getContentPane().add(txtTotal);
		txtTotal.setColumns(10);
		
		total = new JTextField();
		total.setEditable(false);
		total.setText("0");
		total.setHorizontalAlignment(SwingConstants.CENTER);
		total.setFont(new Font("Tahoma", Font.BOLD, 18));
		total.setBounds(814, 589, 164, 46);
		frame.getContentPane().add(total);
		total.setColumns(10);
		txtCash = new JTextField();
		txtCash.setForeground(new Color(0, 0, 205));
		txtCash.setBackground(new Color(175, 238, 238));
		txtCash.setEditable(false);
		txtCash.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtCash.setText("CASH");
		txtCash.setHorizontalAlignment(SwingConstants.CENTER);
		txtCash.setBounds(682, 635, 132, 40);
		frame.getContentPane().add(txtCash);
		txtCash.setColumns(10);
		cash = new JTextField();
		cash.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				int i=Integer.parseInt(total.getText().toString());
				int j=Integer.parseInt(cash.getText().toString());
				j=j-i;
				change.setText(""+j);
			}
		});
		cash.setFont(new Font("Tahoma", Font.BOLD, 14));
		cash.setHorizontalAlignment(SwingConstants.CENTER);
		cash.setBounds(814, 635, 164, 40);
		frame.getContentPane().add(cash);
		cash.setColumns(10);
		
		txtChange = new JTextField();
		txtChange.setForeground(new Color(0, 0, 255));
		txtChange.setBackground(new Color(175, 238, 238));
		txtChange.setEditable(false);
		txtChange.setText("CHANGE");
		txtChange.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtChange.setHorizontalAlignment(SwingConstants.CENTER);
		txtChange.setBounds(682, 673, 132, 40);
		frame.getContentPane().add(txtChange);
		txtChange.setColumns(10);
		
		change = new JTextField();
		change.setBackground(Color.WHITE);
		change.setEditable(false);
		change.setFont(new Font("Tahoma", Font.BOLD, 14));
		change.setHorizontalAlignment(SwingConstants.CENTER);
		change.setBounds(814, 673, 164, 40);
		frame.getContentPane().add(change);
		change.setColumns(10);
		
		btn_printbill = new JButton("Print BILL");
		btn_printbill.setIcon(new ImageIcon("pics//Device-Printer-icon.png"));
		
		btn_printbill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				add_in_sale_report();
				print prt=new print(table,total.getText(),cash.getText(),discount.getText(),change.getText());
			    prt.setVisible(true);
			
			}
		});
		btn_printbill.setBackground(SystemColor.controlHighlight);
		btn_printbill.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_printbill.setBounds(491, 601, 170, 52);
		frame.getContentPane().add(btn_printbill);
		
		btn_stockreport = new JButton("Stock Report");
		btn_stockreport.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_stockreport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i=checkPassword();
				if(i==1){
				stock_report add=new stock_report();
				add.setVisible(true);
				add.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}
		});
		btn_stockreport.setBounds(996, 144, 205, 46);
		frame.getContentPane().add(btn_stockreport);
		btn_addstock = new JButton("Add Stock");
		btn_addstock.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_addstock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i=checkPassword();
				if(i==1){
				add_stock add=new add_stock();
				add.setVisible(true);
				}
			}
		});
		btn_addstock.setBounds(996, 201, 205, 46);
		frame.getContentPane().add(btn_addstock);
		
		btn_salereport = new JButton("Sale Report");
		btn_salereport.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_salereport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i=checkPassword();
				if(i==1){
				sale_report report=new sale_report();
				report.setVisible(true);
				}
			}
		});
		btn_salereport.setBounds(996, 372, 205, 46);
		frame.getContentPane().add(btn_salereport);
		
		btn_update = new JButton("Update Item");
		btn_update.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i=checkPassword();
				if(i==1){
				update_item update=new update_item();
				update.setVisible(true);
				}
				
				
			}
		});
		btn_update.setBounds(996, 258, 205, 46);
		frame.getContentPane().add(btn_update);
		
		btn_supplierdetails = new JButton("Supplier Details");
		btn_supplierdetails.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_supplierdetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i=checkPassword();
				if(i==1){
				supplier_details supply=new supplier_details();
				supply.setVisible(true);
				}
			}
		});
		btn_supplierdetails.setBounds(996, 486, 205, 46);
		frame.getContentPane().add(btn_supplierdetails);
		
		btn_employeedetails = new JButton("Employee Details");
		btn_employeedetails.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_employeedetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i=checkPassword();
				if(i==1){
				employee_details employee=new employee_details();
				employee.setVisible(true);
				}
			}
		});
		btn_employeedetails.setBounds(996, 543, 205, 46);
		frame.getContentPane().add(btn_employeedetails);
		
		btn_removestock = new JButton("Remove Stock");
		btn_removestock.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_removestock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i=checkPassword();
				if(i==1){
				removed_stock rm=new removed_stock();
				rm.setVisible(true);
				}
				
				
			}
		});
		btn_removestock.setBounds(996, 315, 205, 46);
		frame.getContentPane().add(btn_removestock);
		
		btn_password = new JButton("Password");
		btn_password.setFont(new Font("Tahoma", Font.BOLD, 13));
		btn_password.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i=checkPassword();
				if(i==1){
				password pas=new password();
				pas.setVisible(true);
				}
				
			}
		});
		btn_password.setBounds(996, 605, 205, 46);
		frame.getContentPane().add(btn_password);
		
		save = new JButton("");
		save.setIcon(new ImageIcon("pics//1477106528_Save.png"));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sale_report stock=new sale_report("Save");
				stock.setVisible(false);
				
				String query="DELETE FROM sale WHERE rowid >= 1";
				PreparedStatement pst;
				try {
					pst = conn.prepareStatement(query);
					pst.execute();
					pst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.dispose();
			}
		});
		save.setBounds(1048, 662, 94, 52);
		frame.getContentPane().add(save);
		
		txtDiscount = new JTextField();
		txtDiscount.setEditable(false);
		txtDiscount.setForeground(new Color(0, 0, 255));
		txtDiscount.setBackground(new Color(175, 238, 238));
		txtDiscount.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtDiscount.setHorizontalAlignment(SwingConstants.CENTER);
		txtDiscount.setText("Discount");
		txtDiscount.setBounds(682, 548, 132, 40);
		frame.getContentPane().add(txtDiscount);
		txtDiscount.setColumns(10);
		discount = new JTextField();
		discount.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent arg0) {
				int i=Integer.parseInt(discount.getText().toString());
			int j=x;
			j=j-i;
			total.setText(""+j);
			}
			
		});
		
	
		
		discount.setHorizontalAlignment(SwingConstants.CENTER);
		discount.setFont(new Font("Tahoma", Font.PLAIN, 12));
		discount.setBounds(814, 548, 164, 40);
		frame.getContentPane().add(discount);
		discount.setColumns(10);
		
		btn_newbill = new JButton("New BILL");
		btn_newbill.setIcon(new ImageIcon("pics//Files-New-File-icon.png"));
		btn_newbill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new_table();
				bill=0;
				total.setText("0");
			}
		});
		btn_newbill.setFont(new Font("Tahoma", Font.BOLD, 14));
		btn_newbill.setBounds(35, 601, 170, 52);
		frame.getContentPane().add(btn_newbill);
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("pics//1477106265_add-buy-plus-shopping-cart.png"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int q=Integer.parseInt(quantity.getText().toString());
				bill+=(q*(Integer.parseInt(price.getText().toString())));
				x=bill;
				total.setText(""+bill);	
				addRow();
				set_values();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnNewButton.setBounds(256, 118, 65, 32);
		frame.getContentPane().add(btnNewButton);
		setTable();
         table = new JTable(model);
		
		table.setBounds(10, 194, 671, 353);
		frame.getContentPane().add(table);
		
		list = new JList();
		list.setBackground(new Color(175, 238, 238));
		list.setBounds(691, 194, 249, 343);
		frame.getContentPane().add(list);
		
		btnCheckStock = new JButton("Check Stock");
		btnCheckStock.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnCheckStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int i=checkPassword();
				if(i==1){
				zero_quantity zero=new zero_quantity();
				zero.setVisible(true);
				}
			}
		});
		btnCheckStock.setBounds(996, 429, 205, 46);
		frame.getContentPane().add(btnCheckStock);
	}
}
