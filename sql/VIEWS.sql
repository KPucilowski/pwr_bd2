--------------------------------------------------------
--  File created - �������-�������-01-2020   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for View GROUP_VIEW
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE VIEW "GROUP_VIEW" ("DEGREE", "PROFESSOR_ID", "PARITY", "TIME", "FORM", "SUBJECT_ID", "DAY", "FIRST_NAME", "LAST_NAME", "STUDENT_LIMIT") AS SELECT
	PROFESSOR.DEGREE,
	PROFESSOR.PROFESSOR_ID,
	"GROUP".PARITY,
	"GROUP".TIME,
	"GROUP".FORM,
	"GROUP".SUBJECT_ID,
	"GROUP".DAY,
	PERSONAL_DATA.FIRST_NAME,
	PERSONAL_DATA.LAST_NAME,
	"GROUP".STUDENT_LIMIT
FROM
	"GROUP" INNER JOIN
	PROFESSOR ON "GROUP".PROFESSOR_ID = PROFESSOR.PROFESSOR_ID INNER JOIN
	PERSONAL_DATA ON PROFESSOR.PROFESSOR_ID = PERSONAL_DATA.USER_ID;
--------------------------------------------------------
--  DDL for View PROFESSOR_VIEW
--------------------------------------------------------

  CREATE OR REPLACE NONEDITIONABLE VIEW "PROFESSOR_VIEW" ("PROFESSOR_ID", "DEGREE", "FIRST_NAME", "LAST_NAME", "EMAIL", "NAME") AS SELECT
	PROFESSOR.PROFESSOR_ID,
	PROFESSOR.DEGREE,
	PERSONAL_DATA.FIRST_NAME,
	PERSONAL_DATA.LAST_NAME,
	PERSONAL_DATA.EMAIL,
	FACULTY.NAME
FROM
	PROFESSOR INNER JOIN
	PERSONAL_DATA ON PROFESSOR.PROFESSOR_ID = PERSONAL_DATA.USER_ID INNER JOIN
    FACULTY ON faculty.faculity_id = personal_data.faculty_id;
--------------------------------------------------------
--  DDL for View STUDENT_VIEW
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE VIEW "STUDENT_VIEW" ("AVG_GRADE", "STUDENT_ID", "PESEL", "ADMISSION_DATE", "YEAR", "SEMESTER", "FIRST_NAME", "LAST_NAME", "EMAIL", "SPECIALIZATION", "FACULTY") AS
SELECT
    AVG("RECORD".GRADE) as AVG_GRADE,
    STUDENT.STUDENT_ID,
    STUDENT.PESEL,
    STUDENT.ADMISSION_DATE,
    STUDENT.YEAR,
    STUDENT.SEMESTER,
    PERSONAL_DATA.FIRST_NAME,
    PERSONAL_DATA.LAST_NAME,
    PERSONAL_DATA.EMAIL,
    SPECIALIZATION.NAME AS SPECIALIZATION,
    FACULTY.NAME AS FACULTY
FROM
    STUDENT INNER JOIN
    "RECORD" ON STUDENT.STUDENT_ID = "RECORD".STUDENT_ID INNER JOIN
    PERSONAL_DATA ON STUDENT.STUDENT_ID = PERSONAL_DATA.USER_ID INNER JOIN
    SPECIALIZATION ON STUDENT.SPECIALIZATION_ID = SPECIALIZATION.SPECIALIZATION_ID INNER JOIN
    FACULTY ON PERSONAL_DATA.FACULTY_ID = FACULTY.FACULITY_ID AND SPECIALIZATION.FACULTY_ID = FACULTY.FACULITY_ID
GROUP BY
    "RECORD".GRADE,
    STUDENT.STUDENT_ID,
    STUDENT.PESEL,
    STUDENT.ADMISSION_DATE,
    STUDENT.YEAR,
    STUDENT.SEMESTER,
    PERSONAL_DATA.FIRST_NAME,
    PERSONAL_DATA.LAST_NAME,
    PERSONAL_DATA.EMAIL,
    SPECIALIZATION.NAME,
    FACULTY.NAME;