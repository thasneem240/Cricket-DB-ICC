import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.*;
import java.sql.*;

public class InsertData extends JFrame 
{

	private JPanel jPanal;
	private JTextField txtTeam;
	
	
	private Connection conn;	
	private PreparedStatement pst;
	private ResultSet rs;
	private JTextField txtMatche;
	private JTextField txtPlayer;
	private JTextField txtBatting;
	private JButton btnInsertBatting;
	private JTextField txtBowling;
	private JButton btnInsertBowling;
	private JTextField txtFielding;
	private JButton btnInsertFielding;

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
					InsertData frame = new InsertData();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InsertData() 
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 787, 478);
		jPanal = new JPanel();
		jPanal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(jPanal);
		jPanal.setLayout(null);
		
		txtTeam = new JTextField();
		txtTeam.setFont(new Font("Dialog", Font.BOLD, 15));
		txtTeam.setBounds(28, 12, 252, 36);
		jPanal.add(txtTeam);
		txtTeam.setColumns(10);
		
		JButton btnInsertTeam = new JButton("Insert Team Data");
		btnInsertTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				insertTeamData();
			}

		});
		btnInsertTeam.setFont(new Font("Dialog", Font.BOLD, 22));
		btnInsertTeam.setBounds(328, 12, 293, 36);
		jPanal.add(btnInsertTeam);
		
		txtMatche = new JTextField();
		txtMatche.setFont(new Font("Dialog", Font.BOLD, 15));
		txtMatche.setColumns(10);
		txtMatche.setBounds(28, 71, 252, 36);
		jPanal.add(txtMatche);
		
		JButton btnInsertMatche = new JButton("Insert Match Data");
		btnInsertMatche.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				insertMatcheData();
			}
		});
		btnInsertMatche.setFont(new Font("Dialog", Font.BOLD, 22));
		btnInsertMatche.setBounds(328, 71, 293, 36);
		jPanal.add(btnInsertMatche);
		
		txtPlayer = new JTextField();
		txtPlayer.setFont(new Font("Dialog", Font.BOLD, 15));
		txtPlayer.setColumns(10);
		txtPlayer.setBounds(28, 137, 252, 36);
		jPanal.add(txtPlayer);
		
		JButton btnInsertPlayer = new JButton("Insert Player Data");
		btnInsertPlayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				insertPlayerData();
			}
		});
		btnInsertPlayer.setFont(new Font("Dialog", Font.BOLD, 22));
		btnInsertPlayer.setBounds(328, 137, 293, 36);
		jPanal.add(btnInsertPlayer);
		
		txtBatting = new JTextField();
		txtBatting.setFont(new Font("Dialog", Font.BOLD, 15));
		txtBatting.setColumns(10);
		txtBatting.setBounds(28, 204, 252, 36);
		jPanal.add(txtBatting);
		
		btnInsertBatting = new JButton("Insert Batting Data");
		btnInsertBatting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				insertBattingData();
			}
		});
		btnInsertBatting.setFont(new Font("Dialog", Font.BOLD, 22));
		btnInsertBatting.setBounds(328, 204, 293, 36);
		jPanal.add(btnInsertBatting);
		
		txtBowling = new JTextField();
		txtBowling.setFont(new Font("Dialog", Font.BOLD, 15));
		txtBowling.setColumns(10);
		txtBowling.setBounds(28, 272, 252, 36);
		jPanal.add(txtBowling);
		
		btnInsertBowling = new JButton("Insert Bowling Data");
		btnInsertBowling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				insertBowlingData();
			}
		});
		btnInsertBowling.setFont(new Font("Dialog", Font.BOLD, 22));
		btnInsertBowling.setBounds(328, 272, 293, 36);
		jPanal.add(btnInsertBowling);
		
		txtFielding = new JTextField();
		txtFielding.setFont(new Font("Dialog", Font.BOLD, 15));
		txtFielding.setColumns(10);
		txtFielding.setBounds(28, 342, 252, 36);
		jPanal.add(txtFielding);
		
		btnInsertFielding = new JButton("Insert Fielding Data");
		btnInsertFielding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				insertFieldingData();
			}
		});
		btnInsertFielding.setFont(new Font("Dialog", Font.BOLD, 22));
		btnInsertFielding.setBounds(328, 342, 293, 36);
		jPanal.add(btnInsertFielding);
		
		/* Connect With DB Server */
		
		connect();
	}


	/* To Insert Team Data Into the Database */
	
	private void insertTeamData() 
	{
		String fileName = txtTeam.getText();
		
		if(!(fileName.isEmpty()))
		{
			
			if(fileName.contains("Team")) 
			{
				// Read the .CSV file
				
				try 
				{
					BufferedReader br = new BufferedReader(new FileReader(fileName));
					
					String line = "";
					int count = 0;
					
					String team_Id;
					String team_Name;
					
					while( (line = br.readLine()) != null ) 
					{
						// Skip the First Line in the .csv File
						
						if(count != 0) 
						{
							String[] values = line.split(",");
							
							team_Id  = values[0];
							team_Name = values[1];
							
							/* SQL Insert Statement */
							pst = conn.prepareStatement("INSERT INTO Team(team_ID,team_name) "
									+ "values(?,?)");
							
							pst.setString(1, team_Id);
							pst.setString(2, team_Name);
							
							pst.executeUpdate();
							
							
						}
						
						count++;
					}
					
					
					
					// Close the Buffer reader
					
					br.close();
					
					// Success Message
					
					JOptionPane.showMessageDialog(null, "Team Data Record Successfully Added into the Database");
					clearFields();
					
				} 
				catch (FileNotFoundException e) 
				{
					JOptionPane.showMessageDialog(null, "File Not Found Exception " + e.getMessage());
					clearFields();
					
				} 
				catch (IOException e) 
				{
					JOptionPane.showMessageDialog(null, "IO Exception " + e.getMessage());
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
				JOptionPane.showMessageDialog(null, "Invalid file");
				clearFields();
			}
			
			
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Empty Field!! Please input .csv file ");
		}
			
		
	}
	
	
	/* To Insert Match Data Into the Database */
	
	private void insertMatcheData() 
	{
		String fileName = txtMatche.getText();
				
		if(!(fileName.isEmpty()))
		{
			
			if(fileName.contains("Match")) 
			{
				// Read the .CSV file
				
				try 
				{
					BufferedReader br = new BufferedReader(new FileReader(fileName));
					
					String line = "";
					int count = 0;
					
					String match_Id;
					String match_Name;
					
					while( (line = br.readLine()) != null ) 
					{
						// Skip the First Line in the .csv File
						
						if(count != 0) 
						{
							String[] values = line.split(",");
							
							//match_Id = Integer.parseInt(values[0]);
							match_Id  = values[0];
							match_Name = values[1];
							
							/* SQL Insert Statement */
							pst = conn.prepareStatement("INSERT INTO Matche(match_ID,match_name) "
									+ "values(?,?)");
							
							pst.setString(1, match_Id);
							pst.setString(2, match_Name);
							pst.executeUpdate();
							
							
						}
						
						count++;
					}
					
					// Close the Buffer reader
					br.close();
					
					
					// Success Message
					
					JOptionPane.showMessageDialog(null, "Match Data Record Successfully Added into the Database");
					clearFields();
					
				} 
				catch (FileNotFoundException e) 
				{
					JOptionPane.showMessageDialog(null, "File Not Found Exception " + e.getMessage());
					clearFields();
					
				} catch (IOException e) 
				{
					JOptionPane.showMessageDialog(null, "IO Exception " + e.getMessage());
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
				JOptionPane.showMessageDialog(null, "Invalid file");
				clearFields();
			}
		
			
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Empty Field!! Please input .csv file ");
		}
		
		
	}
	
	
	
	/* To Insert Player Data Into the Database */
	
	private void insertPlayerData() 
	{
		String fileName = txtPlayer.getText();
		
		if(!(fileName.isEmpty()))
		{
			
			String match_Id;
			
			match_Id = getMatchId(fileName);
			
			
			if(match_Id == null || !fileName.contains("Player") ) 
			{
				JOptionPane.showMessageDialog(null, "Invalid file");
				clearFields();
			}
			else 
			{
				// Read the .CSV file
				
				try 
				{
					BufferedReader br = new BufferedReader(new FileReader(fileName));
					
					String line = "";
					int count = 0;
					
					String team_Id;
					String name;
					String span;
					String total_matches;
					
					while( (line = br.readLine()) != null ) 
					{
						// Skip the First Line in the .csv File
						
						if(count != 0) 
						{
							String[] values = line.split(",");
							
							name = values[1];
							span = values[2];
							total_matches = values[3];
							
							team_Id = getTeamId(name);
							
							if(team_Id != null) 
							{
								
								/* SQL Insert Statement */
								pst = conn.prepareStatement("INSERT INTO Player(team_ID,name,match_ID,span,total_matches) "
										+ "values(?,?,?,?,?)");
								
								pst.setString(1, team_Id);
								pst.setString(2, name);
								pst.setString(3, match_Id);
								pst.setString(4, span);
								pst.setString(5, total_matches);
								
								pst.executeUpdate();
								
							}
						}
						
						count++;
					}
					
					
					
					// Close the Buffer reader
					
					br.close();
					
					// Success Message
					
					JOptionPane.showMessageDialog(null, "Player Data Record Successfully Added into the Database");
					clearFields();
					
				} 
				catch (FileNotFoundException e) 
				{
					JOptionPane.showMessageDialog(null, "File Not Found Exception " + e.getMessage());
					clearFields();
					
				} 
				catch (IOException e) 
				{
					JOptionPane.showMessageDialog(null, "IO Exception " + e.getMessage());
					clearFields();
					
				} 
				catch (SQLException e) 
				{
					JOptionPane.showMessageDialog(null, "SQL Exception: " + e.getMessage());
					clearFields();
				}
				
			}
			
			
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Empty Field!! Please input .csv file ");
		}
		
	}
	
	
	
	
	
/* To Insert batting Data Into the Database */
	
	private void insertBattingData() 
	{
		
		String fileName = txtBatting.getText();
		
		if(!(fileName.isEmpty()))
		{
			
			String match_Id;
			
			match_Id = getMatchId(fileName);
			
			
			if(match_Id == null || !fileName.contains("Batting")) 
			{
				JOptionPane.showMessageDialog(null, "Invalid file");
				clearFields();
			}
			else 
			{
				// Read the .CSV file
				
				try 
				{
					BufferedReader br = new BufferedReader(new FileReader(fileName));
					
					String line = "";
					int count = 0;
					
					String team_Id;
					String name;
					String total_run;
					String highest_score;
					String average;
					String zeros;
					String fiftys;
					String centuries;
					
					while( (line = br.readLine()) != null ) 
					{
						// Skip the First Line in the .csv File
						
						if(count != 0) 
						{
							String[] values = line.split(",");
							
							name = values[1];
							total_run = values[6];
							highest_score = values[7];
							average = values[8];
							zeros = values[13];
							fiftys = values[12];
							centuries = values[11];
							
							
							if(check(total_run))
							{
								total_run = null;
							}
							
							if(check(highest_score))
							{
								highest_score = null;
							}
							
							if(check(average))
							{
								average = null;
							}
							
							if(check(zeros))
							{
								zeros = null;
							}
							
							if(check(fiftys))
							{
								fiftys = null;
							}
							
							if(check(centuries))
							{
								centuries = null;
							}
							
							
							
							team_Id = getTeamId(name);
							
							if(team_Id != null) 
							{
								
								/* SQL Insert Statement */
								pst = conn.prepareStatement("INSERT INTO BattingDetail(team_ID,name,match_ID,total_run,highest_score,average,0s,50s,100s) "
										+ "values(?,?,?,?,?,?,?,?,?)");
								
								pst.setString(1, team_Id);
								pst.setString(2, name);
								pst.setString(3, match_Id);
								pst.setString(4, total_run);
								pst.setString(5, highest_score);
								pst.setString(6, average);
								pst.setString(7, zeros);
								pst.setString(8, fiftys);
								pst.setString(9, centuries);
								
								pst.executeUpdate();
								
							}
						}
						
						count++;
					}
					
					
					
					// Close the Buffer reader
					
					br.close();
					
					// Success Message
					
					JOptionPane.showMessageDialog(null, "Batting Data Record Successfully Added into the Database");
					clearFields();
					
				} 
				catch (FileNotFoundException e) 
				{
					JOptionPane.showMessageDialog(null, "File Not Found Exception " + e.getMessage());
					clearFields();
					
				} 
				catch (IOException e) 
				{
					JOptionPane.showMessageDialog(null, "IO Exception " + e.getMessage());
					clearFields();
					
				} 
				catch (SQLException e) 
				{
					JOptionPane.showMessageDialog(null, "SQL Exception: " + e.getMessage());
					clearFields();
				}
				
			}
			
			
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Empty Field!! Please input .csv file ");
		}
		
		
	}
	
	
	
/* To Insert bowling Data Into the Database */
	
	private void insertBowlingData() 
	{
		
		String fileName = txtBowling.getText();
		
		if(!(fileName.isEmpty()))
		{
			
			String match_Id;
			
			match_Id = getMatchId(fileName);
			
			
			if(match_Id == null || !fileName.contains("Bowling")) 
			{
				JOptionPane.showMessageDialog(null, "Invalid file");
				clearFields();
			}
			else 
			{
				// Read the .CSV file
				
				try 
				{
					BufferedReader br = new BufferedReader(new FileReader(fileName));
					
					String line = "";
					int count = 0;
					
					String team_Id;
					String name;
					String balls;
					String runs;
					String total_wickets;
					
					
					while( (line = br.readLine()) != null ) 
					{
						// Skip the First Line in the .csv File
						
						if(count != 0) 
						{
							String[] values = line.split(",");
							
							name = values[1];
							balls = values[5];
							runs = values[6];
							total_wickets = values[7];
						
							
							if(check(balls))
							{
								balls= null;
							}
							
							if(check(runs))
							{
								runs = null;
							}
							
							if(check(total_wickets))
							{
								total_wickets = null;
							}
							
							
							team_Id = getTeamId(name);
							
							
							if(team_Id != null) 
							{
								
								/* SQL Insert Statement */
								pst = conn.prepareStatement("INSERT INTO BowlingDetail(team_ID,name,match_ID,balls,runs,total_wickets) "
										+ "values(?,?,?,?,?,?)");
								
								pst.setString(1, team_Id);
								pst.setString(2, name);
								pst.setString(3, match_Id);
								pst.setString(4, balls);
								pst.setString(5, runs);
								pst.setString(6, total_wickets);
							
								pst.executeUpdate();
								
							}
						}
						
						count++;
					}
					
					
					
					// Close the Buffer reader
					
					br.close();
					
					// Success Message
					
					JOptionPane.showMessageDialog(null, "Bowling Data Record Successfully Added into the Database");
					clearFields();
					
				} 
				catch (FileNotFoundException e) 
				{
					JOptionPane.showMessageDialog(null, "File Not Found Exception " + e.getMessage());
					clearFields();
					
				} 
				catch (IOException e) 
				{
					JOptionPane.showMessageDialog(null, "IO Exception " + e.getMessage());
					clearFields();
					
				} 
				catch (SQLException e) 
				{
					JOptionPane.showMessageDialog(null, "SQL Exception: " + e.getMessage());
					clearFields();
				}
				
			}
			
			
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Empty Field!! Please input .csv file ");
		}
		
		
	}
	
	
	
/* To Insert Fielding Data Into the Database */
	
	private void insertFieldingData() 
	{
		
		String fileName = txtFielding.getText();
		
		if(!(fileName.isEmpty()))
		{
			
			String match_Id;
			
			match_Id = getMatchId(fileName);
			
			
			if(match_Id == null || !fileName.contains("Fielding")) 
			{
				JOptionPane.showMessageDialog(null, "Invalid file");
				clearFields();
			}
			else 
			{
				// Read the .CSV file
				
				try 
				{
					BufferedReader br = new BufferedReader(new FileReader(fileName));
					
					String line = "";
					int count = 0;
					
					String team_Id;
					String name;
					String catches;
					String stumps;
					String dismissals;
					
					
					while( (line = br.readLine()) != null ) 
					{
						// Skip the First Line in the .csv File
						
						if(count != 0) 
						{
							String[] values = line.split(",");
							
							name = values[1];
							catches = values[6];
							stumps = values[7];
							dismissals = values[5];
							
						
							if(check(catches))
							{
								catches= null;
							}
							
							if(check(stumps))
							{
								stumps = null;
							}
							
							if(check(dismissals))
							{
								dismissals = null;
							}
							
							
							team_Id = getTeamId(name);
							
							
							if(team_Id != null) 
							{
								
								/* SQL Insert Statement */
								pst = conn.prepareStatement("INSERT INTO FieldingDetail(team_ID,name,match_ID,catches,stumps,dismissals) "
										+ "values(?,?,?,?,?,?)");
								
								pst.setString(1, team_Id);
								pst.setString(2, name);
								pst.setString(3, match_Id);
								pst.setString(4, catches);
								pst.setString(5, stumps);
								pst.setString(6, dismissals);
							
								pst.executeUpdate();
								
							}
						}
						
						count++;
					}
					
					
					
					// Close the Buffer reader
					
					br.close();
					
					// Success Message
					
					JOptionPane.showMessageDialog(null, "Field Data Record Successfully Added into the Database");
					clearFields();
					
				} 
				catch (FileNotFoundException e) 
				{
					JOptionPane.showMessageDialog(null, "File Not Found Exception " + e.getMessage());
					clearFields();
					
				} 
				catch (IOException e) 
				{
					JOptionPane.showMessageDialog(null, "IO Exception " + e.getMessage());
					clearFields();
					
				} 
				catch (SQLException e) 
				{
					JOptionPane.showMessageDialog(null, "SQL Exception: " + e.getMessage());
					clearFields();
				}
				
			}
			
			
		}
		else 
		{
			JOptionPane.showMessageDialog(null, "Empty Field!! Please input .csv file ");
		}
		
		
	}
	
	
	
	private String getMatchId(String fileName) 
	{
		String matchId = null;
		
		
		if(fileName.contains("ODI")) 
		{
			matchId = "0";
		}
		else 
		{
			if(fileName.contains("T20")) 
			{
				matchId = "1";
			}
			else 
			{
				if(fileName.contains("TEST")) 
				{
					matchId = "2";
				}
				else 
				{
					if(fileName.contains("IPL")) 
					{
						matchId = "3";
					}
				}
			}
		}
		
		
		return matchId;
	}
	
	
	
	private String getTeamId(String name) 
	{
		String teamId = null;
		
		
		if(name.contains("INDIA"))
			teamId = "1";
		else
			if(name.contains("AUS"))
				teamId = "3";
			else
				if(name.contains("PAK"))
					teamId = "2";
				else
					if(name.contains("(IRE)"))
						teamId = "10";
					else 
						if(name.contains("NZ"))
							teamId = "6";
						else
							if(name.contains("BDESH"))
								teamId = "7";
							else
								if(name.contains("KENYA"))
									teamId = "11";
								else
									if(name.contains("(AFG)"))
										teamId = "12";
									else
										if(name.contains("BMUDA"))
											teamId = "17";
										else
											if(name.contains("NEPAL"))
												teamId = "20";
											else
												if(name.contains("UAE"))
													teamId = "15";
												else
													if(name.contains("(CAN)"))
														teamId = "14";
													else
														if(name.contains("SCOT"))
															teamId = "13";
														else
															if(name.contains("ZIM"))
																teamId = "8";
															else
																if(name.contains("HKG"))
																	teamId = "16";
																else
																	if(name.contains("(NAM)"))
																		teamId = "18";	
																	else
																		if(name.contains("(WI)"))
																			teamId = "4";
																		else
																			if(name.contains("(ENG)"))
																				teamId = "9";
																			else
																				if(name.contains("(SA)"))
																					teamId = "5";
																				else
																					if(name.contains("(NL)"))
																						teamId = "19";
																					else
																						if(name.contains("SL)"))
																							teamId = "0";
		
		
		return teamId;
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
	
	
	/* Clear the Text Fields */
	
	private void clearFields() 
	{
		txtTeam.setText("");
		txtMatche.setText("");
		txtPlayer.setText("");
		txtBatting.setText("");
		txtBowling.setText("");
		txtFielding.setText("");
	}
	
	
	
	private boolean check(String str) 
	{
		if(str.equals("-")) 
		{
			return true;
		}
		
		return false;
	}
}
