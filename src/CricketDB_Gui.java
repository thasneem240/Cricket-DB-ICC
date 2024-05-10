import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;


import java.sql.*;
import javax.swing.JTextField;


public class CricketDB_Gui {

	private JFrame frame;
	
	private Connection conn;	
	private PreparedStatement pst;
	private ResultSet rs;
	private JTable resultTable;
	private JTextField txtTableName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					CricketDB_Gui window = new CricketDB_Gui();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CricketDB_Gui() 
	{
		initialize();
		connect();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 1210, 689);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnInsertData = new JButton("INSERT DATA");
		
		btnInsertData.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				showInsertWindow();
			}

			
		});
		
		
		btnInsertData.setBounds(100, 132, 161, 39);
		frame.getContentPane().add(btnInsertData);
		
		JLabel lblCricketDatabaseGui = new JLabel("CRICKET DATABASE GUI");
		lblCricketDatabaseGui.setFont(new Font("Dialog", Font.BOLD, 25));
		lblCricketDatabaseGui.setBounds(190, 12, 360, 54);
		frame.getContentPane().add(lblCricketDatabaseGui);
		
		JButton btnDeleteData = new JButton("DELETE DATA");
		btnDeleteData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				showDeleteWindow();
			}
		});
		btnDeleteData.setBounds(454, 132, 161, 39);
		frame.getContentPane().add(btnDeleteData);
		
		JButton btnShowTable = new JButton("SHOW TABLE");
		btnShowTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				showTables();
			}
		});
		btnShowTable.setBounds(100, 212, 161, 39);
		frame.getContentPane().add(btnShowTable);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 309, 1186, 331);
		frame.getContentPane().add(scrollPane);
		
		resultTable = new JTable();
		scrollPane.setViewportView(resultTable);
		
		JButton btnClearTable = new JButton("CLEAR TABLE");
		btnClearTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				clearFields();
			}
			
		});
		btnClearTable.setBounds(454, 212, 161, 39);
		frame.getContentPane().add(btnClearTable);
		
		txtTableName = new JTextField();
		txtTableName.setBounds(454, 263, 270, 34);
		frame.getContentPane().add(txtTableName);
		txtTableName.setColumns(10);
		
		JLabel lblEnterTheTable = new JLabel("Enter the Table or View name Here");
		lblEnterTheTable.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEnterTheTable.setBounds(100, 258, 336, 39);
		frame.getContentPane().add(lblEnterTheTable);
		
		JButton btnDisplay = new JButton("DISPLAY ALL THE ROWS");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				displayTable();
			}
		});
		btnDisplay.setBounds(776, 258, 209, 39);
		frame.getContentPane().add(btnDisplay);
		
	}
	
	// Display Insert Window
	
	private void showInsertWindow() 
	{
		InsertData insertDataWindow = new InsertData();
		insertDataWindow.setVisible(true);
		
	}
	
	// Display Delete Window
	
	private void showDeleteWindow() 
	{
		DeleteData deleteDataWindow = new DeleteData();
		deleteDataWindow.setVisible(true);
	}
	
	// Display All the Tables
	
	private void showTables() 
	{
		
		/* SQL Delete Statement */
		try 
		{
			pst = conn.prepareStatement("SHOW TABLES");
			
			rs = pst.executeQuery();
			
			// Success Message
			
			JOptionPane.showMessageDialog(null, " Successfully Loaded All the Availble table in the database");
			
			resultTable.setModel(DbUtils.resultSetToTableModel(rs));
			
			
		} 
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "SQL Exception: " + e.getMessage());
			clearFields();
		}
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
	
	
	private void clearFields() 
	{
		
		/* Clear the Table */
		
		resultTable.setModel(new DefaultTableModel());
		txtTableName.setText("");
		
		
	}
	
	
	private void displayTable() 
	{
		String tableName = txtTableName.getText();
		
		if(!(tableName.isEmpty())) 
		{
			/* SQL Delete Statement */
			
			try 
			{
				pst = conn.prepareStatement("Select * FROM " + tableName);
				
				rs = pst.executeQuery();
				
				// Success Message
				
				JOptionPane.showMessageDialog(null, " Successfully Loaded All the Availble table in the database");
				
				resultTable.setModel(DbUtils.resultSetToTableModel(rs));
				
				
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
}
