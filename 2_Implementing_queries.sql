### Part 3. Designing and implementing queries: ###

## Level1: Baisc SQL Staements

# Question 1: 

/* Retrieve all the information from "Player" table */

-- Answer: 
SELECT * FROM Player;


# Question 2: 

/* Find only the name, span and total matches Played in T20 from "Player" Table. */

-- Answer:

-- match_ID of T20 is : 1

SELECT name,span,total_matches FROM Player WHERE match_ID = 1;


# Question 3: 

/* Retrieve player name, total run, highest score, total centuries where the total run for the player in ODI match is between 4000 and 7000
(including 4000 and 7000 both). */

-- Answer:

-- match_ID of ODI is : 0

SELECT name,total_run,highest_score,100s FROM BattingDetail WHERE match_ID = 0 AND total_run BETWEEN 4000 AND 7000;


# Question 4:

/* Display name,total run, highest score, total half centuries in T20 match that have player names containing the string 
'kara'. */

-- Answer:

-- match_ID of T20 is : 1

SELECT name,total_run, highest_score,50s FROM BattingDetail WHERE match_ID = 1 AND name LIKE '%kara%';


# Question 5:

/* Display name, total run, average score, total duck out in ODI match Of Australian team Players. */

-- Answer:

-- match_ID of ODI is : 0
-- team_ID 0f Austraila : 3

SELECT name, total_run, average AS AverageScore, 0s AS Total_Duck_Out FROM BattingDetail WHERE match_ID = 0 AND team_ID = 3;


# Question 6:

/* Display name And average score of Players who are from Srilanka, India And Pakistan and has average score as null in T20 matches. */

-- Answer:

-- match_ID of T20 is : 1
-- team_ID 0f Srilanka : 0
-- team_ID 0f India : 1
-- team_ID 0f Pakistan : 2


SELECT name, average FROM BattingDetail WHERE match_ID = 1 AND team_ID IN(0,1,2) AND average IS NULL;




## Level2: Use joins and sub-queries, with GROUP BY, ORDER BY, aggregate functions and related clauses.


# Question 7:

/* Use a suitable join Query to show Team_ID, Team_Name, Player_name,span, total matche played in T20. 
Result should be shown in descending Order of total matches. */

-- Answer:

-- match_ID of T20 is : 1

SELECT Team.team_ID,Team.team_name,Player.name,Player.span,Player.total_matches 
FROM Team NATURAL JOIN Player 
WHERE Player.match_ID = 1 
ORDER BY Player.total_matches DESC;



# Question 8:


/* Use a suitable join Query to show Team_ID, Team_Name, match ID, match Name ,Player_name,balls, total wickets. 
Result should be shown in Ascending Order of total wickets. */

-- Answer:

SELECT T.team_ID, T.team_name,M.match_ID,M.match_name,B.name,B.balls,B.total_wickets
FROM Team T NATURAL JOIN BowlingDetail B NATURAL JOIN Matche M
ORDER BY B.total_wickets;



# Question 9:

/* Find the team name, player name, catches, stumps, and dismissals of the 
	player who has the lowest dismissals in ODI. */

-- Answer:

-- match_ID of ODI is : 0

SELECT T.team_Name, F.name, F.catches, F.stumps, F.dismissals
FROM Team T INNER JOIN FieldingDetail F ON
F.match_ID = 0 AND T.team_ID = F.team_ID AND
F.dismissals <= ALL
( 
  SELECT dismissals
  FROM FieldingDetail
  WHERE match_ID = 0
); 	-- SUB QUERY


# Question 10:

/* Show team name, minimum and maximum total run of each team in ODI matches */

-- Answer:

-- match_ID of ODI is : 0

SELECT T.team_ID,team_name,MAX(B.total_run) AS MAX_Score, MIN(B.total_run) AS MIN_Score
FROM Team T NATURAL JOIN BattingDetail B
WHERE B.match_ID = 0
GROUP BY B.team_ID;

-- Got some error when execute this query

/* ERROR 1055 (42000): Expression #2 of SELECT list is not in GROUP BY clause and contains nonaggregated column 
'CricketDB_20535155.B.name' which is not functionally dependent on columns in GROUP BY clause; 
this is incompatible with sql_mode=only_full_group_by */


-- Fix for that error: I changed the sql mode

SET sql_mode = (SELECT REPLACE(@@sql_mode, 'ONLY_FULL_GROUP_BY', ''));

-- After this fix it worked.


-- We can revert back to default setting using this SQL statement

SET sql_mode = 'ONLY_FULL_GROUP_BY';






 