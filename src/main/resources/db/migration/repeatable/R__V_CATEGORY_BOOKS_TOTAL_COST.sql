CREATE OR REPLACE VIEW V_CATEGORY_BOOKS_TOTAL_COST AS
SELECT category.id,
       category.name   AS category_name,
       sum(book.price) AS total_cost
FROM book_category category
         JOIN book ON book.category_id = category.id
GROUP BY category.id, category.name
ORDER BY total_cost DESC;
