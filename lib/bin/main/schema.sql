CREATE TABLE Users (
	userid SERIAL PRIMARY KEY,
	username TEXT UNIQUE NOT NULL,
	email TEXT NOT NULL,
	email_domain TEXT NOT NULL,
	userpass TEXT NOT NULL,
	CONSTRAINT email_unique UNIQUE(email, email_domain)
);

insert into Users (username,email,email_domain,userpass) values ('Stephen','stephen.morse','gmail.com','password');

CREATE TABLE measures (
    measure_id SERIAL PRIMARY KEY,
    measure_shortname TEXT UNIQUE NOT NULL,
    measure_fullname TEXT NOT NULL
);

INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('ct','Count');          -- 1
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('dl','Deciliter');      -- 2
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('L','Liter');           -- 3
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('cc','Milliliter');     -- 4
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('mL','Milliliter');     -- 5
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('c','Cup');             -- 6
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('cup','Cup');           -- 7
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('dash','Dash');         -- 8
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('fl oz','Fluid ounce'); -- 9
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('gal','Gallon');        -- 10
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('pinch','Pinch');       -- 11
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('fl pt','Pint');        -- 12
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('p','Pint');            -- 13
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('pt','Pint');           -- 14
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('fl qt','Quart');       -- 15
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('q','Quart');           -- 16
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('qt','Quart');          -- 17
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('T','Tablespoon');      -- 18
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('tbl','Tablespoon');    -- 19
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('tbs','Tablespoon');    -- 20
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('tbsp','Tablespoon');   -- 21
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('t','Teaspoon');        -- 22
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('tsp','Teaspoon');      -- 23
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('g','Gram');            -- 24
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('kg','Kilogram');       -- 25
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('mg','Milligram');      -- 26
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('oz','Ounce (Weight)'); -- 27
INSERT INTO measures (measure_shortname, measure_fullname) VALUES ('lb','Pound');          -- 28
