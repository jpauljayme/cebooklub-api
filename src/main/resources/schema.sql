CREATE TABLE IF NOT EXISTS tbl_book (
    id SERIAL PRIMARY KEY,
    fld_title VARCHAR(100) NOT NULL,
    fld_isbn VARCHAR(20) NOT NULL,
    fld_image_url VARCHAR(400) NOT NULL,
    fld_description TEXT NOT NULL,
    fld_original_publication_date DATE NOT NULL,
    fld_average_rating DECIMAL NOT NULL,
    fld_num_of_pages INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS tbl_author (
    id SERIAL PRIMARY KEY,
    tbl_book INTEGER NOT NULL,
    fld_goodreads_id VARCHAR(20) NOT NULL,
    fld_name VARCHAR(50) NOT NULL,
    fld_role VARCHAR(20) NOT NULL,
    fld_image_url VARCHAR(200) NOT NULL,
    CONSTRAINT fk_book FOREIGN KEY(tbl_book) REFERENCES tbl_book(id)
);