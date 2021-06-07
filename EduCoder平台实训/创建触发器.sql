DROP TRIGGER IF EXISTS state_change1;
DROP TRIGGER IF EXISTS state_change2;
DELIMITER ||
CREATE TRIGGER state_change1 AFTER UPDATE ON diagnose_record FOR EACH ROW
BEGIN
	IF
		NEW.result = 1 
		THEN
			UPDATE isolation_record 
			SET isolation_record.state = 3 
			WHERE
				isolation_record.p_id = NEW.p_id;
	END IF;
END||
CREATE TRIGGER state_change2 AFTER INSERT ON diagnose_record FOR EACH ROW
BEGIN
	IF
		NEW.result = 1 
		THEN
			UPDATE isolation_record 
			SET isolation_record.state = 3 
			WHERE
				isolation_record.p_id = NEW.p_id;
	END IF;
END||
DELIMITER;

UPDATE diagnose_record SET diagnose_record.result=1 WHERE diagnose_record.p_id=7;