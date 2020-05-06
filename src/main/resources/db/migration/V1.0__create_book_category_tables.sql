CREATE TABLE book_category (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR
);

CREATE TABLE book (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR,
    description VARCHAR,
    price DECIMAL(13, 2),
    is_active BOOLEAN,
    number INT,
    create_date TIMESTAMP,
    category_id BIGINT NOT NULL,
    FOREIGN KEY (category_id) REFERENCES book_category(id)
);

INSERT INTO book_category (name) VALUES ('Программирование'), ('Музыка'), ('Спорт');

INSERT INTO book (name, description, price, is_active, number, create_date, category_id)
VALUES ('Философия Java', 'Эта книга поможет вам разобраться с Java', 1200.00, true, 135, now(), 1),
       ('Всё о спорте', 'Оставайтесь в курсе новостей в мире спорта', 600.00, false, 0, now(), 3),
       ('Биография Metallica', 'Здесь всё, через что мы прошли', 5000.00, true, 666, now(), 2),
       ('Программирвоание С++', 'Эта книга вынесет вам мозг', 2500.00, true, 13, now(), 1),
       ('Баскетбол от Смува>', 'Данки, фристайл-шоу и многое другое', 600.00, true, 12, now(), 3),
       ('Биография Behemoth', 'К концу книги вы сойдете с ума', 3000.00, true, 98, now(), 2);

