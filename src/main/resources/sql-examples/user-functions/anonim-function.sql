DO $$ <<first_block>>
DECLARE
    BOOK_AVG_COST CONSTANT NUMERIC        := 1000.00;
    counter                INTEGER        := 0;
    book_name              book.name%TYPE := 'MIAMI VICE';
BEGIN
    RAISE NOTICE 'The current value of counter %', counter + 10;
    RAISE NOTICE 'The book name is type: %', pg_typeof(book_name);
    RAISE NOTICE 'The avg cost of book "%" = %', book_name, BOOK_AVG_COST;
END first_block $$;
