CREATE OR REPLACE FUNCTION CHECK_LOGIN(
    LOGIN IN VARCHAR2,
    PASS IN VARCHAR2
) 
RETURN VARCHAR2
IS
    user_type VARCHAR2(255);
BEGIN
    SELECT usr."TYPE" INTO user_type  FROM "USER" usr
    WHERE usr.login = login and usr.password = pass;

    RETURN user_type;
END;

CREATE OR REPLACE FUNCTION GET_ID(
    LOGIN IN VARCHAR2,
    PASS IN VARCHAR2
)
    RETURN NUMBER
    IS
    r_user_id NUMBER(6,0);
BEGIN
    SELECT usr.user_id INTO r_user_id  FROM "USER" usr
    WHERE usr.login = login and usr.password = pass;

    RETURN r_user_id;
END;