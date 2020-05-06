CREATE TABLE book_audit (
    id BIGSERIAL PRIMARY KEY NOT NULL,
    book_id BIGINT NOT NULL,
    book_name VARCHAR NOT NULL,
    change_date TIMESTAMP NOT NULL
);
