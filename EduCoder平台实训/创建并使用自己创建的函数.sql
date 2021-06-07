SET GLOBAL log_bin_trust_function_creators=1;
DROP FUNCTION IF EXISTS Count_Records;
DELIMITER||
CREATE FUNCTION Count_Records(person_id int)
	RETURNS int
BEGIN
	#Routine body goes here...
	RETURN(
		SELECT
			COUNT(itinerary.id)
		FROM
			itinerary
		WHERE
			itinerary.p_id=person_id);
END||
DELIMITER;

SELECT
	* 
FROM
	person 
WHERE
	Count_Records ( person.id )>= 3 
ORDER BY
	person.id;