package barcode;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class print extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JLabel company;
	private JLabel change;
    private JLabel chan_l;
	private JLabel cash;
	private JLabel cash_l;
	private JLabel total;
	private JLabel tot_l;
	private JLabel discount;
	private JLabel dis_l;
	int fr=254,tab=10,d=90,t=107,c=124,ch=141,com=158;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					print frame = new print();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	private  DefaultTableModel model;
	
	
	//self defined fucntion
private void printCard(){
		

		PrinterJob printjob = PrinterJob.getPrinterJob();
		printjob.setJobName(" personal card ");

		printjob.setPrintable (new Printable() {      
		     public int print(Graphics pg, PageFormat pf, int pageNum){                  

		         pf.setOrientation(PageFormat.LANDSCAPE);

		         if (pageNum > 0){
		            return Printable.NO_SUCH_PAGE;
		         }

		         Graphics2D g2 = (Graphics2D) pg;
		         g2.translate(pf.getImageableX(), pf.getImageableY());
		         g2.translate( 0f, 0f );
		         contentPane.paint(g2);

		         return Printable.PAGE_EXISTS;
		     }
		});

		if (printjob.printDialog() == false)
		   return;

		try {
		   printjob.print();
		}
		catch (PrinterException ex) {
		   System.out.println("NO PAGE FOUND."+ex);
		   }
		}
	public void added_Rows(JTable tt){
		int row;
		int name=0;
		int price=4;
		int quan=2;
		 int rows=tt.getRowCount();
		 for(row = 1; row<rows; row++)
		 {
		String value = tt.getModel().getValueAt(row, name).toString();
    	String value1 = tt.getModel().getValueAt(row, quan).toString();
    	String value2 = tt.getModel().getValueAt(row, price).toString();
		model.addRow(new Object[]{value, value1,value2});
		 }
		 
		 rows=rows*12;
		setsize(rows);
		
	
	}
	public void setsize(int row){
		setBounds(0, 0, 190, fr+=row);
		contentPane.setBounds(0, 0, 190, fr+=row);
		table.setBounds(0, 67, 185, tab+=row);
		int i=row;
		dis_l.setBounds(45, d+=i, 60, 14);
		discount.setBounds(130, d, 60, 14);
		tot_l.setBounds(45, t+=i, 60, 14);
		total.setBounds(130, t, 60, 14);
		cash_l.setBounds(45, c+=i, 60, 14);
		cash.setBounds(130, c, 60, 14);
		chan_l.setBounds(45, ch+=i, 60, 14);
		change.setBounds(130, ch, 60, 14);
		company.setBounds(10, com+=i, 155, 14);
	}
	
	
	
	
	

	public void setTable(){
	  
        model.addColumn("Name");
        model.addColumn("Quantity");
        model.addColumn("Price");

		  model.addRow(new Object[]{"NAME","QUANTITY","PRICE"});
	}
	
	
	
	
	
	
	
	/**
	 * Create the frame.
	 */
	
	public print() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	
	
	
	public print(JTable tt,String tot,String cas,String dis,String chang) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat time = new SimpleDateFormat("HH:mm:ss");
		setBounds(100, 100, 193, 94);
		
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		JLabel lblNewLabel_9 = new JLabel("COMPANY NAME");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_9.setBounds(41, 0, 130, 14);
		getContentPane().add(lblNewLabel_9);
		JLabel lblNewLabel_10 = new JLabel("INVOICE");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 13));
		Date dateobj = new Date();
		lblNewLabel_10.setBounds(66, 17, 73, 14);
		getContentPane().add(lblNewLabel_10);
		JLabel lblTime = new JLabel(time.format(dateobj).toString());
		lblTime.setBounds(10, 42, 60, 14);
		getContentPane().add(lblTime);
		JLabel lblDate = new JLabel(date.format(dateobj).toString());
		lblDate.setBounds(110, 42, 70, 14);
		getContentPane().add(lblDate);
		model=new DefaultTableModel();
		setTable();
		table = new JTable(model);
		table.setBounds(0, 10, 171, 10);
		
		getContentPane().add(table);
		
		 dis_l = new JLabel("DISCOUNT");
		dis_l.setBounds(45, 128, 60, 14);
		getContentPane().add(dis_l);
		discount = new JLabel(dis);
		
		discount.setBounds(130, 128, 40, 14);
		getContentPane().add(discount, BorderLayout.EAST);
		 tot_l = new JLabel("TOTAL");
		 
		tot_l.setBounds(45, 145, 60, 14);
		getContentPane().add(tot_l);
		 total = new JLabel(tot);
		 
		total.setBounds(130, 145, 40, 14);
		getContentPane().add(total);
		 cash_l = new JLabel("CASH");
		 
		cash_l.setBounds(45, 162, 60, 14);
		getContentPane().add(cash_l);
		cash = new JLabel(cas);
		
		
		cash.setBounds(130, 162, 40, 14);
		getContentPane().add(cash);
		 chan_l = new JLabel("CHANGE");
		 
		chan_l.setBounds(45, 179, 60, 14);
		getContentPane().add(chan_l);
		
		 change = new JLabel(chang);
		 
		change.setBounds(130, 179, 40, 14);
		getContentPane().add(change);		
		company = new JLabel("PUBLISHED BY TECHLAUNCH");
		company.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		company.setBounds(10, 196, 155, 14);
		getContentPane().add(company);
		added_Rows(tt);
		printCard();
		
		
		
	}
}
