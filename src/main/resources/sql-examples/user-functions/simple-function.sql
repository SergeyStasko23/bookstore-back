CREATE FUNCTION increment(counter integer) RETURNS integer AS
    $$
    BEGIN
        RETURN counter + 1;
    END;
    $$
LANGUAGE PLPGSQL;

-- SELECT increment(100);
