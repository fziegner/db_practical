CREATE TABLE person (
	id integer,
	creationDate timestamp,
	firstName varchar(64),
	lastName varchar(64),
	gender varchar(8),
	birthday date,
	browserUsed varchar(64),
	locationIP inet,
	cityName varchar(64),
	PRIMARY KEY (id),
	FOREIGN KEY(cityName) REFERENCES city
	);