CREATE OR REPLACE FUNCTION get_book(pattern VARCHAR)
RETURNS TABLE (book_name VARCHAR, book_price NUMERIC) AS $$
    BEGIN
        RETURN QUERY SELECT name, price FROM book WHERE name ILIKE pattern;
    END;
$$
LANGUAGE PLPGSQL;

-- SELECT * FROM get_book('%MET%');

