DROP PROCEDURE test_insert;
DELIMITER ;;


CREATE PROCEDURE test_insert ()
BEGIN
	DECLARE
		i INT DEFAULT 0 ;
	WHILE i < 5000 DO
		INSERT INTO test_table (
			
			namee,
			sex,
			age,
			bitthday,
			aihao,
			xuehao,
			mingci
		)
	VALUES
		(
		
			CONCAT('test', i),
			'ÄÐ',
			22,
			'2019-01-01',
			'xuexi',
			CONCAT('test', i),
			i
		) ;
	SET i = i + 1 ;
	END
	WHILE ;
	END;;

CALL test_insert () ;