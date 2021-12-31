package barcode;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class supplier_details extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					supplier_details frame = new supplier_details();
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
	
	public supplier_details() {
		conn=Connector.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 932, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SUPPLIER DETAILS");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblNewLabel.setBounds(380, 11, 212, 32);
		contentPane.add(lblNewLabel);
		
		JScrollPane table_supplier = new JScrollPane();
		table_supplier.setBounds(10, 54, 896, 322);
		contentPane.add(table_supplier);
		
		table = new JTable();
		table_supplier.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				
				int row=table.getSelectedRow();
				String str=(table.getModel().getValueAt(row, 0).toString());
				int action=JOptionPane.showConfirmDialog(null, "DO You Really Want To Delete It","Delete",JOptionPane.YES_NO_OPTION);
				if(action==0){
				try {
					String query="Delete from supplier where name='"+str+"' ";
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
		
		JButton btn_download = new JButton("View");
		btn_download.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query="select name,company,address,contact from supplier";
					PreparedStatement pst=conn.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btn_download.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_download.setBounds(143, 390, 100, 23);
		contentPane.add(btn_download);
		
		JButton btn_new = new JButton("New");
		btn_new.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				supplier_update update=new supplier_update();
				update.setVisible(true);
			}
		});
		btn_new.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_new.setBounds(37, 390, 80, 23);
		contentPane.add(btn_new);
	}

}
