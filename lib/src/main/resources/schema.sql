CREATE TABLE Users (
	userid SERIAL PRIMARY KEY,
	username TEXT UNIQUE NOT NULL,
	email TEXT NOT NULL,
	email_domain TEXT NOT NULL,
	userpass TEXT NOT NULL,
	CONSTRAINT email_unique UNIQUE(email, email_domain)
);

CREATE TABLE measures (
    measure_id SERIAL PRIMARY KEY,
    measure_shortname TEXT UNIQUE NOT NULL,
    measure_fullname TEXT NOT NULL
);

CREATE TABLE Stephen_Pantry (
	item_id SERIAL PRIMARY KEY,
	amount FLOAT4 NOT NULL,
	measure_index SMALLINT NOT NULL,
	item_name TEXT UNIQUE NOT NULL
);

INSERT INTO Users (username,email,email_domain,userpass) VALUES ('Stephen','stephen.morse','gmail.com','password');

INSERT INTO measures (measure_shortname, measure_fullname) VALUES
	('ct','Count'),          -- 1
	('dl','Deciliter'),      -- 2
	('L','Liter'),           -- 3
	('cc','Milliliter'),     -- 4
	('mL','Milliliter'),     -- 5
	('c','Cup'),             -- 6
	('cup','Cup'),           -- 7
	('dash','Dash'),         -- 8
	('fl oz','Fluid ounce'), -- 9
	('gal','Gallon'),        -- 10
	('pinch','Pinch'),       -- 11
	('fl pt','Pint'),        -- 12
	('p','Pint'),            -- 13
	('pt','Pint'),           -- 14
	('fl qt','Quart'),       -- 15
	('q','Quart'),           -- 16
	('qt','Quart'),          -- 17
	('T','Tablespoon'),      -- 18
	('tbl','Tablespoon'),    -- 19
	('tbs','Tablespoon'),    -- 20
	('tbsp','Tablespoon'),   -- 21
	('t','Teaspoon'),        -- 22
	('tsp','Teaspoon'),      -- 23
	('g','Gram'),            -- 24
	('kg','Kilogram'),       -- 25
	('mg','Milligram'),      -- 26
	('oz','Ounce (Weight)'), -- 27
	('lb','Pound');          -- 28

INSERT INTO Stephen_Pantry (amount,measure_index,item_name) VALUES
	(0.25,28,'Chestnuts'),    -- 1
	(1,1,'Watermelons'),      -- 2
	(25,27,'Marshmallows'),   -- 3
	(26,27,'Salt'),           -- 4
	(0.3,28,'Tortillas'),     -- 5
	(8,1,'Radishes'),         -- 6
	(48,27,'Minced Garlic'),  -- 7
	(20,27,'Cherries'),       -- 8
	(750,5,'Amaretto'),       -- 9
	(0.5,28,'Bok Choy'),      -- 10
	(4,27,'Garlic Powder'),   -- 11
	(0.5,10,'Half-and-Half'), -- 12
	(2.7,27,'Cinnamon'),      -- 13
	(16,9,'Fish Sauce'),      -- 14
	(0.5,28,'Broccoli'),      -- 15
	(6,27,'Ricotta Cheese'),  -- 16
	(1,10,'Milk'),            -- 17
	(4,27,'Black Pepper'),    -- 18
	(2,28,'Ground Beef'),     -- 19
	(7.5,27,'Onion Powder'),  -- 20
	(1,28,'Sesame Seeds'),    -- 21
	(1,28,'Steak'),           -- 22
	(0.5,28,'Walnuts'),       -- 23
	(45,27,'Potato Chips'),   -- 24
	(1,28,'Sunflower Seeds'), -- 25
	(0.25,28,'Soybeans'),     -- 26
	(2,1,'Eggplants'),        -- 27
	(1.2,3,'Wine'),           -- 28
	(5,1,'Peaches'),          -- 29
	(32,9,'Canola Oil'),      -- 30
	(1,28,'Sugar'),           -- 31
	(0.25,28,'Dried Leeks'),  -- 32
	(48,27,'Yogurt'),         -- 33
	(32,9,'Lemon Juice'),     -- 34
	(2,27,'Dill Weed'),       -- 35
	(4,27,'Basil'),           -- 36
	(3,1,'Plums'),            -- 37
	(4,27,'Oregano'),         -- 38
	(1,1,'Cabbage');          -- 39
