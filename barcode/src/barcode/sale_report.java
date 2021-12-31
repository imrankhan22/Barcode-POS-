package barcode;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class sale_report extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField totalsale;
	private double price=0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sale_report frame = new sale_report();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void total_sale(){
		
		
		try {
			String query="select * from sale";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				price+=Integer.parseInt(rs.getString("price").toString());
			}
			totalsale.setText(""+price);
			
			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	
	
	
	
	
	
	

	/**
	 * Create the frame.
	 */
	java.sql.Connection conn=null;
	
	
	
	///****** to excell
	
	
	public void toExcel(JTable table, File file){
		try{
			TableModel model = table.getModel();
			FileWriter excel = new FileWriter(file);

			for(int i = 0; i < model.getColumnCount(); i++){
				excel.write(model.getColumnName(i) + "\t");
			}

			excel.write("\n");

			for(int i=0; i< model.getRowCount(); i++) {
				for(int j=0; j < model.getColumnCount(); j++) {
					excel.write(model.getValueAt(i,j).toString()+"\t");
				}
				excel.write("\n");
			}

			excel.close();
		}catch(IOException e){ System.out.println(e); }
	}
	
	
	
	
	
	
	public sale_report(String str){
		conn=Connector.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1008, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JScrollPane table_sale = new JScrollPane();
		table_sale.setBounds(10, 62, 972, 418);
		contentPane.add(table_sale);
		
		table = new JTable();
		table_sale.setViewportView(table);
	
		
		//table
		
		try {
			String query="select name,company,quantity,price,date,time from sale";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//file 
		
		
		
		
		
		
		
		         
		                JFileChooser fc = new JFileChooser();
		                
		                String filename = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

		                
		                    String path ="E:";

							int len = filename.length();
							String ext = "";
							String file = "";

							if(len > 4){
								ext = filename.substring(len-4, len);
							}

							if(ext.equals(".xls")){
								file = path + "\\" + filename; 
							}else{
								file = path + "\\" + filename + ".xls"; 
							}
							toExcel(table, new File(file));
							}
						
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	
	
	
	
	
	
	public sale_report() {
		conn=Connector.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1008, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SALE REPORT");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblNewLabel.setBounds(354, 11, 251, 37);
		contentPane.add(lblNewLabel);
		
		JScrollPane table_sale = new JScrollPane();
		table_sale.setBounds(10, 62, 972, 418);
		contentPane.add(table_sale);
		
		table = new JTable();
		table_sale.setViewportView(table);
		
		JButton btn_view = new JButton("View");
		btn_view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query="select name,company,quantity,price,date,time from sale";
					PreparedStatement pst=conn.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					e.printStackTrace();
				}
				total_sale();
			}
		});
		btn_view.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_view.setBounds(841, 494, 94, 23);
		contentPane.add(btn_view);
		
		JLabel lblNewLabel_1 = new JLabel("Total Sale");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(46, 494, 94, 23);
		contentPane.add(lblNewLabel_1);
		
		totalsale = new JTextField();
		totalsale.setText("");
		totalsale.setBounds(134, 496, 142, 23);
		contentPane.add(totalsale);
		totalsale.setColumns(10);
	}
}
