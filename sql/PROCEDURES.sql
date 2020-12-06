--------------------------------------------------------
--  File created - вторник-декабря-01-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure ADD_GRADE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "ADD_GRADE" (
    student_id NUMBER,
    group_id_n NUMBER,
    grade_in DOUBLE PRECISION)
IS
    grade_var NUMBER;
BEGIN
    SELECT COUNT(*) INTO grade_var FROM "RECORD" rec
    WHERE 
        student_id = rec.student_id and
        group_id_n = rec.group_id;

    IF grade_var = 1 THEN
        UPDATE "RECORD" rec
        SET GRADE = grade_in,
            GRADE_DATE = CURRENT_DATE
        WHERE 
            student_id = rec.student_id and
            group_id_n = rec.group_id;
        dbms_output.put_line('Student grade has been added.');
  ELSE
        dbms_output.put_line('Student record does not exist.');
  END IF;
END;
--------------------------------------------------------
--  DDL for Procedure ADD_STUDENT_TO_GROUP
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "ADD_STUDENT_TO_GROUP" (
    student_id_in NUMBER,
    group_in NUMBER
    )
IS
    student_count NUMBER;
    student_limit NUMBER;
BEGIN
    SELECT count(*) INTO student_count FROM "RECORD" rec
    WHERE
        rec.group_id = group_in;

    SELECT grp.student_limit INTO student_limit FROM "GROUP" grp
    WHERE
        grp.group_id = group_in;

    IF student_count < student_limit THEN
        INSERT INTO "RECORD"(student_id, group_id, record_date)
        VALUES (student_id_in, group_in, CURRENT_DATE);
    END IF;
END;
--------------------------------------------------------
--  DDL for Procedure CLOSE_GROUP
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "CLOSE_GROUP" (group_in NUMBER)
IS
BEGIN
    DELETE FROM "RECORD" rec
    WHERE 
        rec.group_id = group_in;

    DELETE FROM "GROUP" grp
    WHERE
        grp.group_id = group_in;
END;
--------------------------------------------------------
--  DDL for Procedure REMOVE_GRADE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "REMOVE_GRADE" (
    student_id NUMBER,
    group_id_n NUMBER)
IS
    grade_var NUMBER;
BEGIN
    SELECT COUNT(*) INTO grade_var FROM "RECORD" rec
    WHERE 
        student_id = rec.student_id and
        group_id_n = rec.group_id;

    IF grade_var = 1 THEN
        UPDATE "RECORD" rec
        SET 
            GRADE = NULL,
            GRADE_DATE = NULL
        WHERE 
            student_id = rec.student_id and
            group_id_n = rec.group_id;
        dbms_output.put_line('Student grade has been removed.');
  ELSE
        dbms_output.put_line('Student record does not exist.');
  END IF;
END;
--------------------------------------------------------
--  DDL for Procedure REMOVE_STUDENT_FROM_GROUP
--------------------------------------------------------
set define off;

  CREATE OR REPLACE NONEDITIONABLE PROCEDURE "REMOVE_STUDENT_FROM_GROUP" 
(
  STUDENT_IN IN NUMBER 
, GROUP_IN IN NUMBER 
) AS 
BEGIN
  DELETE FROM "RECORD" rec
  WHERE
    group_in = rec.group_id and
    student_in = rec.student_id;
    
END REMOVE_STUDENT_FROM_GROUP;
