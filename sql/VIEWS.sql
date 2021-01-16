--------------------------------------------------------
--  DDL for View GROUP_VIEW
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE VIEW "GROUP_VIEW" ("GROUP_ID", "PROFESSOR_ID", "DEGREE", "FIRST_NAME", "LAST_NAME", "SUBJECT_ID", "SUBJECT_NAME", "PARITY", "TIME", "FORM", "DAY", "STUDENT_LIMIT", "FACULTY_ID") AS
SELECT
    "GROUP".GROUP_ID,
    PROFESSOR.PROFESSOR_ID,
    PROFESSOR.DEGREE,
    PERSONAL_DATA.FIRST_NAME,
    PERSONAL_DATA.LAST_NAME,
    "GROUP".SUBJECT_ID,
    SUBJECT.SUBJECT_NAME,
    "GROUP".PARITY,
    to_char("GROUP"."TIME", 'HH24:MI') as "TIME",
    "GROUP".FORM,
    "GROUP".DAY,
    "GROUP".STUDENT_LIMIT,
    SUBJECT.FACULTY_ID
FROM
    "GROUP" INNER JOIN
    PROFESSOR ON "GROUP".PROFESSOR_ID = PROFESSOR.PROFESSOR_ID INNER JOIN
    PERSONAL_DATA ON PROFESSOR.PROFESSOR_ID = PERSONAL_DATA.USER_ID INNER JOIN
    SUBJECT ON SUBJECT.SUBJECT_ID = "GROUP".SUBJECT_ID
;
--------------------------------------------------------
--  DDL for View GROUP_STUDENTS_VIEW
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE VIEW "GROUP_STUDENTS_VIEW" ("PROFESSOR_ID", "GROUP_ID", "STUDENT_ID", "STUDENT", "EMAIL", "GRADE", "GRADE_DATE", "RECORD_DATE") AS
SELECT
    grp.professor_id,
    grp."GROUP_ID",
    rec.student_id,
    pd.first_name || ' ' || pd.last_name as "STUDENT",
    pd.email,
    rec.grade,
    rec.grade_date,
    rec.record_date
FROM
    "GROUP" grp inner join
    "RECORD" rec on grp."GROUP_ID" = rec."GROUP_ID" inner join
    PERSONAL_DATA pd on rec.student_id = pd.user_id
;
--------------------------------------------------------
--  DDL for View PROFESSOR_VIEW
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE VIEW "PROFESSOR_VIEW" ("PROFESSOR_ID", "DEGREE", "FIRST_NAME", "LAST_NAME", "EMAIL", "FACULTY", "FACULTY_ID") AS
SELECT
    PROFESSOR.PROFESSOR_ID,
    PROFESSOR.DEGREE,
    PERSONAL_DATA.FIRST_NAME,
    PERSONAL_DATA.LAST_NAME,
    PERSONAL_DATA.EMAIL,
    FACULTY.NAME AS FACULTY,
    FACULTY.FACULTY_ID
FROM
    PROFESSOR INNER JOIN
    PERSONAL_DATA ON PROFESSOR.PROFESSOR_ID = PERSONAL_DATA.USER_ID INNER JOIN
    FACULTY ON faculty.FACULTY_id = personal_data.faculty_id
;
--------------------------------------------------------
--  DDL for View STUDENT_VIEW
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE VIEW "STUDENT_VIEW" ("STUDENT_ID", "PESEL", "ADMISSION_DATE", "YEAR", "SEMESTER", "FIRST_NAME", "LAST_NAME", "EMAIL", "SPECIALIZATION", "FACULTY_ID", "FACULTY", "AVG_GRADE") AS
SELECT
    st.STUDENT_ID,
    st.PESEL,
    st.ADMISSION_DATE,
    st.YEAR,
    st.SEMESTER,
    pd.FIRST_NAME,
    pd.LAST_NAME,
    pd.EMAIL,
    spec.NAME AS SPECIALIZATION,
    fc.FACULTY_ID,
    fc.NAME as FACULTY,
    AVG(rec.GRADE) as AVG_GRADE
FROM
    STUDENT st LEFT JOIN
    "RECORD" rec ON st.STUDENT_ID = rec.STUDENT_ID JOIN
    PERSONAL_DATA pd on pd.user_id = st.student_id JOIN
    SPECIALIZATION spec on spec.specialization_id = st.specialization_id JOIN
    FACULTY fc on pd.faculty_id = fc.faculty_id
GROUP BY
    rec.GRADE,
    st.STUDENT_ID,
    st.PESEL,
    st.ADMISSION_DATE,
    st.YEAR,
    st.SEMESTER,
    pd.FIRST_NAME,
    pd.LAST_NAME,
    pd.EMAIL,
    spec.NAME,
    fc.FACULTY_ID,
    fc.NAME
;
--------------------------------------------------------
--  DDL for View STUDENT_GRADES_VIEW
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE VIEW "STUDENT_GRADES_VIEW" ("STUDENT_ID", "SUBJECT_NAME", "FORM", "GRADE", "TOTAL_MONTHS", "PROFESSOR") AS
select
    rec.student_id,
    sbj.subject_name,
    grp."FORM",
    rec.grade,
    round(MONTHS_BETWEEN(rec.grade_date, st.admission_date)) as TOTAL_MONTHS,
    pd.first_name || ' ' || pd.last_name as PROFESSOR
from
    "RECORD" rec join
    "GROUP" grp on grp."GROUP_ID" = rec."GROUP_ID" join
    subject sbj on grp.subject_id = sbj.subject_id join
    personal_data pd on grp.professor_id = pd.user_id join
    student st on st.student_id = rec.student_id
;
--------------------------------------------------------
--  DDL for View RECORD_VIEW
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE VIEW "RECORD_VIEW" ("STUDENT_ID", "PROFESSOR", "SUBJECT_NAME", "PARITY", "TIME", "FORM", "DAY", "STUDENT_LIMIT", "GRADE") AS
SELECT
    "RECORD".STUDENT_ID,
    PROFESSOR.DEGREE || ' ' ||
    PERSONAL_DATA.FIRST_NAME || ' ' ||
    PERSONAL_DATA.LAST_NAME as PROFESSOR,
    SUBJECT.SUBJECT_NAME,
    "GROUP".PARITY,
    to_char("GROUP"."TIME", 'HH24:MI') as "TIME",
    "GROUP".FORM,
    "GROUP".DAY,
    "GROUP".STUDENT_LIMIT,
    "RECORD".GRADE
FROM
    "GROUP" INNER JOIN
    "RECORD" ON "RECORD"."GROUP_ID" = "GROUP"."GROUP_ID" INNER JOIN
    SUBJECT ON SUBJECT.SUBJECT_ID = "GROUP".SUBJECT_ID INNER JOIN
    PROFESSOR ON "GROUP".PROFESSOR_ID = PROFESSOR.PROFESSOR_ID INNER JOIN
    PERSONAL_DATA ON PROFESSOR.PROFESSOR_ID = PERSONAL_DATA.USER_ID
;
--------------------------------------------------------
--  DDL for View DEANS_WORKER_VIEW
--------------------------------------------------------

CREATE OR REPLACE NONEDITIONABLE VIEW "DEANS_WORKER_VIEW" ("DEANS_WORKER_ID", "USER_ID", "FIRST_NAME", "LAST_NAME", "EMAIL", "FACULTY_ID") AS
SELECT
    "DEANS_WORKER_ID",
    "USER_ID",
    "FIRST_NAME",
    "LAST_NAME",
    "EMAIL",
    "FACULTY_ID"
FROM
    DEANS_WORKER dw JOIN
    PERSONAL_DATA pd ON dw.deans_worker_id = pd.user_id
;