DROP TRIGGER IF EXISTS book_changes_trigger ON book;

CREATE OR REPLACE FUNCTION log_last_book_name() RETURNS TRIGGER AS
    $$
    BEGIN
        IF NEW.name != OLD.name THEN
            INSERT INTO book_audit(book_id, book_name, change_date) VALUES (OLD.id, OLD.name, now());
        END IF;
        RETURN NEW;
    END;
    $$
LANGUAGE PLPGSQL;

CREATE TRIGGER book_changes_trigger
    BEFORE UPDATE ON book
    FOR EACH ROW
        EXECUTE PROCEDURE log_last_book_name();
