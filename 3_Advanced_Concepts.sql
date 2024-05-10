### Part 4. Increase the database functionality with advanced concepts ###

-- Category 1 : Stored Procedures

-- 1.

DELIMITER //
CREATE PROCEDURE resetNullValueToZeroBatting()
COMMENT 'Set the total_run ,highest_score, average, 0s,50s,100s to zero If the attribute is NULL In the  BattingDetail Table'

BEGIN

UPDATE BattingDetail SET total_run = 0
WHERE total_run IS NULL;

UPDATE BattingDetail SET highest_score = '0'
WHERE highest_score IS NULL;

UPDATE BattingDetail SET average = 0
WHERE average IS NULL;

UPDATE BattingDetail SET 0s = 0
WHERE 0s IS NULL;

UPDATE BattingDetail SET 50s = 0
WHERE 50s IS NULL;

UPDATE BattingDetail SET 100s = 0
WHERE 100s IS NULL;

END
//

DELIMITER ;



-- Show All the Procedures:

SHOW PROCEDURE STATUS;

-- To Drop the Procedure:

DROP PROCEDURE resetNullValueToZeroBatting;

-- TO Executing the procedure

CALL resetNullValueToZeroBatting();


-- 2.

DELIMITER //
CREATE PROCEDURE resetNullValueToValBowling( IN val INT)
COMMENT 'Set the balls ,runs, total wickets to a value provided by the management for Players who has NULL 
		Values for these Attributes'
        
BEGIN

UPDATE BowlingDetail SET balls = val
WHERE balls IS NULL;

UPDATE BowlingDetail SET runs = val
WHERE runs IS NULL;

UPDATE BowlingDetail SET total_wickets = val
WHERE total_wickets IS NULL;


END
//

DELIMITER ;


-- TO Executing the procedure

CALL resetNullValueToValBowling(0);

        


-- Category 2 : Views

-- 1.

/* Create a View for All ROUNDERS played in ODI matches that contain all the details (Team Detail, Player Detail,
   Match Detail BattingDetail, Bowling Detail, Fielding Detail). 
   And Sort the result based on the total matches in ascending order.*/
   
-- Answer:

-- match_ID of ODI is : 0
   
   -- Create the View
   
   CREATE VIEW All_Rounders_ODI_Details AS
   SELECT T.team_ID, T.team_name, M.match_name,P.name, P.span, P.total_matches,
   B.total_run, B.highest_score, B.average, B.0s,B.50s,B.100s,
   W.balls, W.runs,W.total_wickets,
   F.catches,F.stumps,F.dismissals
   FROM Team T NATURAL JOIN Matche M NATURAL JOIN Player P 
   NATURAL JOIN BattingDetail B NATURAL JOIN BowlingDetail W
   NATURAL JOIN FieldingDetail F
   WHERE M.match_ID = 0
   ORDER BY P.total_matches;
   
   
   -- Display the View
   
   SELECT * FROM All_Rounders_ODI_Details;
   
   
   
   -- 2.

/* Create a View for All ROUNDERS played in T20 matches that contain all the details (Team Detail, Player Detail,
   Match Detail BattingDetail, Bowling Detail, Fielding Detail). 
   And Sort the result based on the total matches in Descending order.*/
   
-- Answer:

-- match_ID of T20 is : 1
   
   -- Create the View
   
   CREATE VIEW All_Rounders_T20_Details AS
   SELECT T.team_ID, T.team_name, M.match_name,P.name, P.span, P.total_matches,
   B.total_run, B.highest_score, B.average, B.0s,B.50s,B.100s,
   W.balls, W.runs,W.total_wickets,
   F.catches,F.stumps,F.dismissals
   FROM Team T NATURAL JOIN Matche M NATURAL JOIN Player P 
   NATURAL JOIN BattingDetail B NATURAL JOIN BowlingDetail W
   NATURAL JOIN FieldingDetail F
   WHERE M.match_ID = 1
   ORDER BY P.total_matches DESC;
   
   
   -- Display the View
   
   SELECT * FROM All_Rounders_T20_Details;
        
             

-- Category 3 : Indexes

/* Use PlyerNameIndex and TeamNameIndex to find the team_ID,team_Name,player_Name,span, and 
	total_matches In ODI_Matches that have a player name that includes the word 'ab' and whose 
    team includes the word 'af'. And sort the result in descending order based on total_matches */
    
    -- Create the Indexes
    
    CREATE INDEX PlayerNameInd ON Player(name);
    CREATE INDEX TeamNameInd ON Team(team_name);
    
    
    -- Using Indexes
    
    -- match_ID of ODI is : 0
    
    SELECT T.team_ID,T.team_name,P.name,P.span,P.total_matches
    FROM Team T NATURAL JOIN Player P
    WHERE P.match_ID = 0 AND
    P.name LIKE '%ab%' AND
    T.team_name LIKE '%af%'
    ORDER BY total_matches DESC;
    