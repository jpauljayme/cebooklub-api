CREATE TABLE IF NOT EXISTS tbl_book (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    fld_title VARCHAR(100) NOT NULL,
    fld_isbn VARCHAR(20) NOT NULL,
    fld_image_url VARCHAR(400) NOT NULL,
    fld_description TEXT NOT NULL,
    fld_original_publication_date DATE NOT NULL,
    fld_average_rating DECIMAL NOT NULL,
    fld_num_of_pages INTEGER NOT NULL,
    fld_completion_date DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS tbl_author (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
--    tbl_book INTEGER,
    fld_goodreads_id VARCHAR(20),
    fld_name VARCHAR(50) NOT NULL,
    fld_role VARCHAR(20) NOT NULL,
    fld_image_url VARCHAR(200) NOT NULL
--    CONSTRAINT fk_book FOREIGN KEY(tbl_book) REFERENCES tbl_book(id)
);

CREATE TABLE IF NOT EXISTS tbl_book_author (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    book_id INTEGER NOT NULL,
    author_id INTEGER NOT NULL,
    UNIQUE (book_id, author_id),
    FOREIGN KEY (book_id) REFERENCES tbl_book(id),
    FOREIGN KEY (author_id) REFERENCES tbl_author(id)
);