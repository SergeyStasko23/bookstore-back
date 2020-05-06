CREATE OR REPLACE FUNCTION hi_lo(a integer, b integer, c integer, OUT hi integer, OUT lo integer) AS
    $$
    BEGIN
        hi := greatest(a, b, c);
        lo := least(a, b, c);
    END;
    $$
LANGUAGE PLPGSQL;

-- SELECT hi_lo(1, 10, 100);
-- SELECT * FROM hi_lo(1, 10, 100);
