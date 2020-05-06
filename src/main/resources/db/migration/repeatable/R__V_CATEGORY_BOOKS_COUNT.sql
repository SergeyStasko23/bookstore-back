CREATE OR REPLACE VIEW V_CATEGORY_BOOKS_COUNT AS
SELECT category.id,
       category.name    AS category_name,
       count(book.name) AS books_count
FROM book_category category
         JOIN book ON book.category_id = category.id AND book.is_active = true
GROUP BY category.id, category.name
ORDER BY books_count DESC;

