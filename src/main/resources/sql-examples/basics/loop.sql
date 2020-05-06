CREATE OR REPLACE FUNCTION get_books() RETURNS VOID AS $$
    DECLARE
        rec RECORD;
    BEGIN
        FOR rec IN SELECT name, price FROM book ORDER BY price DESC
        LOOP
            RAISE NOTICE '%, %', rec.name, rec.price;
        END LOOP;
    END;
$$ LANGUAGE PLPGSQL;

-- SELECT get_books();
