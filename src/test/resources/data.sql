INSERT INTO BOOKS (book_id, title, year) VALUES
(1, 'Name of the Wind', 2007),
(2, 'Wise Mans Fear', 2011),
(3, 'Gita', 1000),
(4, 'Hello World', 1052),
(5, 'Marvel', 1999);

INSERT INTO AUTHORS (author_id, first_name, last_name) VALUES
(1, 'Patrick', 'Rothfuss'),
(2, 'Hare', 'Krishna'),
(3, 'Some', 'Programmer'),
(4, 'Captain', 'America');

INSERT INTO BOOK_AUTHORS (author_id, book_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 4),
(4, 5);