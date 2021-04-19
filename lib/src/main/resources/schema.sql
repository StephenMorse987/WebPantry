CREATE TABLE Users (
	userid SERIAL PRIMARY KEY,
	username TEXT UNIQUE NOT NULL,
	email TEXT NOT NULL,
	email_domain TEXT NOT NULL,
	userpass TEXT NOT NULL,
	CONSTRAINT email_unique UNIQUE(email, email_domain)
);

insert into Users (username,email,email_domain,userpass) values ('Stephen','stephen.morse','gmail.com','password');
