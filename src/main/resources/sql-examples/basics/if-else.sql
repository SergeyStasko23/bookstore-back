DO $$
    DECLARE
        a integer := 10;
        b integer := 10;
    BEGIN
        IF a > b THEN
            RAISE NOTICE 'a is less than b';
        ELSIF a < b THEN
            RAISE NOTICE 'a is less than b';
        ELSE
            RAISE NOTICE 'a is equal to b';
        END IF;
    END;
$$
