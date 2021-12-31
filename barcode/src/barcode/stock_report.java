package barcode;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.SystemColor;
import java.awt.Font;

import javax.swing.JScrollPane;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class stock_report extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtStockReport;
	private JScrollPane table_stock;
	private JButton btn_view;
	private JButton btn_update;
	java.sql.Connection conn=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stock_report frame = new stock_report();
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
	
	
	
	
	
	
	
	
	
	//frame with no arguments
	public stock_report() {
		conn=Connector.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 903, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		table_stock = new JScrollPane();
		table_stock.setBounds(10, 46, 867, 414);
		contentPane.add(table_stock);
		
		table = new JTable();
		table_stock.setViewportView(table);
		table.setBackground(SystemColor.control);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				
				int row=table.getSelectedRow();
				String str=(table.getModel().getValueAt(row, 0).toString());
				int action=JOptionPane.showConfirmDialog(null, "DO You Really Want To Delete It","Delete",JOptionPane.YES_NO_OPTION);
				if(action==0){
				try {
					String query="Delete from Details where barcode='"+str+"' ";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.execute();
					pst.close();
					JOptionPane.showMessageDialog(null, "Data Deleted");
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				}
				
			}
		});
		
		txtStockReport = new JTextField();
		txtStockReport.setEditable(false);
		txtStockReport.setFont(new Font("Arial Black", Font.BOLD, 18));
		txtStockReport.setBackground(SystemColor.control);
		txtStockReport.setText("STOCK REPORT");
		txtStockReport.setHorizontalAlignment(SwingConstants.CENTER);
		txtStockReport.setBounds(296, 11, 200, 39);
		contentPane.add(txtStockReport);
		txtStockReport.setColumns(10);
		
		btn_view = new JButton("View");
		btn_view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query="select barcode,name,company,location,quantity,category,purchase,sale from Details";
					PreparedStatement pst=conn.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btn_view.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_view.setBounds(732, 471, 102, 23);
		contentPane.add(btn_view);
		
		btn_update = new JButton("Update");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				update_item item=new update_item();
				item.setVisible(true);
				
			}
		});
		
		btn_update.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_update.setBounds(48, 473, 89, 23);
		contentPane.add(btn_update);
	}
}
