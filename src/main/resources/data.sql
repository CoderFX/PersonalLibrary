INSERT INTO BOOKS (book_id, title, year, author_id) VALUES
(1, 'Gita', 1000, 1),
(2, 'Deja', 1052, 2),
(3, 'Caption', 1999, 3);

INSERT INTO AUTHORS (author_id, first_name, last_name) VALUES
(1, 'Lokesh', 'Gupta'),
(2, 'Deja', 'Vu'),
(3, 'Caption', 'America');

INSERT INTO BOOK_AUTHORS (author_id, book_id) VALUES
(1, 1),
(2, 2),
(3, 3);