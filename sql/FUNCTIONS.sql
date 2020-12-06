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