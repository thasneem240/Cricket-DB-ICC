### Part 2. Implementing the database ###

## a.

# Create a Database

CREATE DATABASE CricketDB_20535155;


# Change the Database

USE CricketDB_20535155;


# Implement the Tables

-- Create a Team Table

CREATE TABLE Team(
team_ID INT(3),
team_name VARCHAR(25) NOT NULL,
PRIMARY KEY(team_ID)
);

-- Show the Details of Team table

DESC Team;



-- Create a Matche Table

CREATE TABLE Matche(
match_ID INT(3),
match_name VARCHAR(20) NOT NULL,
PRIMARY KEY(match_ID)
);

-- Show the Details of Matche table

DESC Matche;


-- Create a Player Table

CREATE TABLE Player(
team_ID INT(3),
name VARCHAR(35),
match_ID INT(3),
span VARCHAR(12) NOT NULL,
total_matches INT(4),
CONSTRAINT PK_Player PRIMARY KEY(team_ID,name,match_ID),
FOREIGN KEY(team_ID) REFERENCES Team(team_ID) ON DELETE CASCADE,
FOREIGN KEY(match_ID) REFERENCES Matche(match_ID) ON DELETE CASCADE
);

-- Show the Details of Player table

DESC Player;


-- Create a BattingDetail Table

CREATE TABLE BattingDetail(
team_ID INT(3),
name VARCHAR(35),
match_ID INT(3),
total_run INT(6),
highest_score CHAR(5),
average DECIMAL(5,2),
0s INT(3),
50s INT(3),
100s INT(3),
CONSTRAINT PK_Batting PRIMARY KEY(team_ID,name,match_ID),
FOREIGN KEY(team_ID) REFERENCES Team(team_ID) ON DELETE CASCADE,
FOREIGN KEY(match_ID) REFERENCES Matche(match_ID) ON DELETE CASCADE
);


-- Show the Details of BattingDetail table

DESC BattingDetail;


-- Create a BowlingDetail Table

CREATE TABLE BowlingDetail(
team_ID INT(3),
name VARCHAR(35),
match_ID INT(3),
balls INT(6),
runs INT(6),
total_wickets INT(4),
CONSTRAINT PK_Bowling PRIMARY KEY(team_ID,name,match_ID),
FOREIGN KEY(team_ID) REFERENCES Team(team_ID) ON DELETE CASCADE,
FOREIGN KEY(match_ID) REFERENCES Matche(match_ID) ON DELETE CASCADE
);


-- Show the Details of BowlingDetail table

DESC BowlingDetail;




-- Create a FieldingDetail Table

CREATE TABLE FieldingDetail(
team_ID INT(3),
name VARCHAR(35),
match_ID INT(3),
catches INT(4),
stumps INT(4),
dismissals INT(4),
CONSTRAINT PK_Fielding PRIMARY KEY(team_ID,name,match_ID),
FOREIGN KEY(team_ID) REFERENCES Team(team_ID) ON DELETE CASCADE,
FOREIGN KEY(match_ID) REFERENCES Matche(match_ID) ON DELETE CASCADE
);


-- Show the Details of FieldingDetail table

DESC FieldingDetail;




## b. Insert Sample values to the database






# Insert data from CSV file to the Matche Table

-- Method 1 : Using Mysql LOAD method

LOAD DATA LOCAL
	INFILE 'MatchData.csv'
    INTO TABLE Matche
    FIELDS TERMINATED BY ',' ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    IGNORE 1 ROWS;
    
 -- Got Some Error code When Insert the Data
 
  /* ERROR 3948 (42000): Loading local data is disabled; this must be 
enabled on both the client and server sides */


-- FIX fOR that Error

SET GLOBAL local_infile=1;
 -- Quit the mysql Server

 -- mysql --local-infile=1 -u thasneem -p
 
 -- Then start from use <database name> and the load data local infile query.



-- Method 2 : Using Java code and SQL Insert Statement With While Loop

/*
	pst = conn.prepareStatement("INSERT INTO Matche(match_ID,match_name) "
			+ "values(?,?)");                               
													*/



# Insert data from CSV file to the Team Table

-- Method 1 : Using Mysql LOAD method

LOAD DATA LOCAL
	INFILE 'TeamData.csv'
    INTO TABLE Team
    FIELDS TERMINATED BY ',' ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    IGNORE 1 ROWS;
    
  
  -- Method 2 : Using Java code and SQL Insert Statement With While Loop
  
  /* 
		pst = conn.prepareStatement("INSERT INTO Team(team_ID,team_name) "
										+ "values(?,?)");      
																*/
  
  
  
  # Insert data from CSV file to the Player Table
  
  
  -- Using Java code and SQL Insert Statement With While Loop
  
  /*
		pst = conn.prepareStatement("INSERT INTO Player(team_ID,name,match_ID,span,total_matches) "
										+ "values(?,?,?,?,?)");
								
		pst.setString(1, team_Id);
		pst.setString(2, name);
		pst.setString(3, match_Id);
		pst.setString(4, span);
		pst.setString(5, total_matches);
								
		pst.executeUpdate();
        
								*/
                                
	
  # Insert data from CSV file to BattingDetail Table
  
  
  -- Using Java code and SQL Insert Statement With While Loop
  
  /*
  
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
																*/
                                                                


                                
 # Insert data from CSV file to BowlingDetail Table
  
  
  -- Using Java code and SQL Insert Statement With While Loop  
  
  /*
  
  pst = conn.prepareStatement("INSERT INTO BowlingDetail(team_ID,name,match_ID,balls,runs,total_wickets) "
										+ "values(?,?,?,?,?,?)");
								
								pst.setString(1, team_Id);
								pst.setString(2, name);
								pst.setString(3, match_Id);
								pst.setString(4, balls);
								pst.setString(5, runs);
								pst.setString(6, total_wickets);
							
								pst.executeUpdate();
                                
															*/
                                                            
                                                            
  
  
  # Insert data from CSV file to FieldingDetail Table
  
  
  -- Using Java code and SQL Insert Statement With While Loop
  
  
  /*
  
  pst = conn.prepareStatement("INSERT INTO FieldingDetail(team_ID,name,match_ID,catches,stumps,dismissals) "
										+ "values(?,?,?,?,?,?)");
								
								pst.setString(1, team_Id);
								pst.setString(2, name);
								pst.setString(3, match_Id);
								pst.setString(4, catches);
								pst.setString(5, stumps);
								pst.setString(6, dismissals);
							
								pst.executeUpdate();
                                
															*/
                                                            
                                                            


# Sample Insert Statement to demonstarte the Intergrity Constraints.(Detailed Explanation is provided in the report)

-- sample data 1:

INSERT INTO Player(team_ID,name,match_ID,span,total_matches) 
VALUES
(
	0,'A Dananjaya (SL)',0,'2012-2019',36
);


-- This record already in the Player Table, So when we insert the same data it will give error.
 
		-- ERROR 1062 (23000): Duplicate entry '0-A Dananjaya (SL)-0' for key 'Player.PRIMARY'
        
        

-- sample data 2:

INSERT INTO Player(team_ID,name,match_ID,span,total_matches) 
VALUES
(
	0,'Thasneem (SL)',6,'2012-2022',40
);

-- This is new record but we don't have any match_Id as 6 in the matche_Table so it will gives error

 /*  ERROR 1452 (23000): Cannot add or update a child row: a foreign key constraint fails 
 (`CricketDB_20535155`.`Player`, CONSTRAINT `Player_ibfk_2` FOREIGN KEY (`match_ID`) REFERENCES `Matche` (`match_ID`)) */

