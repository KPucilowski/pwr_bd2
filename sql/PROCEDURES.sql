--------------------------------------------------------
--  File created - âòîðíèê-äåêàáðÿ-01-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure ADD_GRADE
--------------------------------------------------------

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
--------------------------------------------------------
--  DDL for Procedure ADD_STUDENT
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE "ADD_STUDENT"
(
    FIRST_NAME_IN IN VARCHAR2
, LAST_NAME_IN IN VARCHAR2
, FACULTY_ID_IN IN VARCHAR2
, PESEL_IN IN VARCHAR2
, LOGIN_IN IN VARCHAR2
, PASSWORD_IN IN VARCHAR2
) AS
    TEMP_ID NUMBER;

BEGIN
    TEMP_ID := STUDENT_SEQ.NEXTVAL;

    insert into "USER" (user_id, login, "PASSWORD", "TYPE")
    values (TEMP_ID, LOGIN_IN, PASSWORD_IN, 'STUDENT');

    insert into PERSONAL_DATA (user_id, first_name, last_name, email, faculty_id)
    values (TEMP_ID, FIRST_NAME_IN, LAST_NAME_IN, TEMP_ID || '@edu.meme', FACULTY_ID_IN);

    insert into STUDENT (student_id, pesel, admission_date, year, semester, specialization_id)
    values (TEMP_ID, PESEL_IN, SYSDATE, 1, 1, null);
END ADD_STUDENT;
--------------------------------------------------------
--  DDL for Procedure REMOVE_STUDENT
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE PROCEDURE "REMOVE_STUDENT"
(
    STUDENT_ID_IN IN NUMBER
) AS
BEGIN
    DELETE FROM "RECORD" rec
    WHERE
            STUDENT_ID_IN = rec.student_id;

    DELETE FROM STUDENT st
    WHERE
            STUDENT_ID_IN = st.student_id;

    DELETE FROM "USER" usr
    WHERE
            STUDENT_ID_IN = usr.user_id;

    DELETE FROM PERSONAL_DATA pd
    WHERE
            STUDENT_ID_IN = pd.user_id;
END REMOVE_STUDENT;