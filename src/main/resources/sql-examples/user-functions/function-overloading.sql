CREATE OR REPLACE FUNCTION get_price_without_tax(book_id INTEGER) RETURNS INTEGER AS $$
    DECLARE
        TAX CONSTANT NUMERIC := 0.1;
        natural_price book.price%TYPE;
    BEGIN
        SELECT INTO natural_price (price - (price * TAX)) FROM book WHERE id = book_id;
        RETURN natural_price;
    END;
$$ LANGUAGE PLPGSQL;

-- SELECT get_price_without_tax(6);
