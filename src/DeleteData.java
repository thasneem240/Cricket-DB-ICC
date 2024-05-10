import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class DeleteData extends JFrame {

	private JPanel contentPane;
	private JTextField txtTableName;
	
	private Connection conn;	
	private PreparedStatement pst;
	private ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteData frame = new DeleteData();
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
	public DeleteData() 
	{
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 552, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtTableName = new JTextField();
		txtTableName.setBounds(52, 197, 240, 49);
		txtTableName.setFont(new Font("Dialog", Font.BOLD, 15));
		txtTableName.setColumns(10);
		contentPane.add(txtTableName);
		
		JButton btnDeleteAllRows = new JButton("Delete All the rows");
		btnDeleteAllRows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				deleteAllTheRows();
			}
		});
		btnDeleteAllRows.setFont(new Font("Dialog", Font.BOLD, 16));
		btnDeleteAllRows.setBackground(Color.ORANGE);
		btnDeleteAllRows.setBounds(330, 197, 210, 49);
		contentPane.add(btnDeleteAllRows);
		
		JLabel lblPleaseEnterThe = new JLabel("Please Enter the table name");
		lblPleaseEnterThe.setBounds(58, 150, 218, 35);
		contentPane.add(lblPleaseEnterThe);
		
		connect();
	}
	
	
	
	/* Connect the Database Server */
	public void connect() 
	{
		/* Variables */
		final String jdbcURL = "jdbc:mysql://localhost:3306/CricketDB_20535155";
		final String user = "thasneem";
		final String password = "Thasneem@123";
		
		try 
		{
			// register MySQL thin driver with DriverManager service
			// It is optional for JDBC4.x version
			Class.forName("com.mysql.jdbc.Driver");
			
			// Establish the Connection
			
			conn = DriverManager.getConnection(jdbcURL,user,password);

		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	private void deleteAllTheRows()
	{
		String tableName = txtTableName.getText();
		
		if(!(tableName.isEmpty()))
		{
			/* SQL Delete Statement */
			try 
			{
				pst = conn.prepareStatement("DELETE FROM " + tableName );
				
				//pst.setString(1, tableName);
				
				int deletedRows = pst.executeUpdate();
				
				// Success Message
				
				JOptionPane.showMessageDialog(null, " Successfully Deleted All the data From " + tableName + " the table.");
				clearFields();
				
			} 
			catch (SQLException e) 
			{
				JOptionPane.showMessageDialog(null, "SQL Exception: " + e.getMessage());
				clearFields();
			}
			
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Empty Field!! Please input the name of the table ");
		}
		
	}
	
	
	
	private void clearFields() 
	{
		txtTableName.setText("");
	}
}
