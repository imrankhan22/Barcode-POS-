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

public class employee_details extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employee_details frame = new employee_details();
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
	public employee_details() {
		conn=Connector.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 766, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EMPLOYEE DETAILS");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblNewLabel.setBounds(267, 11, 229, 28);
		contentPane.add(lblNewLabel);
		
		JScrollPane table_employee = new JScrollPane();
		table_employee.setBounds(10, 50, 730, 220);
		contentPane.add(table_employee);
		
		table = new JTable();
		table_employee.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				
				int row=table.getSelectedRow();
				String str=(table.getModel().getValueAt(row, 0).toString());
				int action=JOptionPane.showConfirmDialog(null, "DO You Really Want To Delete It","Delete",JOptionPane.YES_NO_OPTION);
				if(action==0){
				try {
					String query="Delete from employer where name='"+str+"' ";
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
		
		JButton btn_add = new JButton("Add");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Employee_update update=new Employee_update();
				update.setVisible(true);
			}
		});
		btn_add.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_add.setBounds(21, 281, 89, 23);
		contentPane.add(btn_add);
		
		JButton btn_update = new JButton("View");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String query="select name,cnic,address,contact from employer";
					PreparedStatement pst=conn.prepareStatement(query);
					ResultSet rs=pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btn_update.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btn_update.setBounds(120, 281, 89, 23);
		contentPane.add(btn_update);
	}

}
