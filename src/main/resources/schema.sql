DROP TABLE IF EXISTS authors CASCADE;
DROP TABLE IF EXISTS books CASCADE;
DROP TABLE IF EXISTS book_authors CASCADE;

CREATE TABLE authors
(
    author_id integer not null,
        first_name varchar(255),
        last_name varchar(255),
        primary key (author_id)
);

CREATE TABLE books
(
    book_id integer not null,
        title varchar(255),
        year integer,
        primary key (book_id)

);

CREATE TABLE book_authors
(
        author_id integer not null,
        book_id integer not null
);

alter table book_authors
       add constraint FKaplrj3g6pw72p4kceta338okj
       foreign key (book_id)
       references books;

alter table book_authors
       add constraint FK6b48wl2lxbg2dynww4yefcb4b
       foreign key (author_id)
       references authors;