CREATE TABLE continent(
	continentName varchar(32),
	PRIMARY KEY(continentName)
	);

CREATE TABLE country(
	countryName varchar(128),
	continentName varchar(32),
	PRIMARY KEY(countryName),
	FOREIGN KEY(continentName) REFERENCES continent
	);
	
CREATE TABLE city(
	cityName varchar(128),
	countryName varchar(128),
	PRIMARY KEY(cityName),
	FOREIGN KEY(countryName) REFERENCES country
	);	
	
CREATE TABLE university(
	universityName varchar(128),
	cityName varchar(128),
	PRIMARY KEY(universityName),
	FOREIGN KEY(cityName) REFERENCES city
	);
	
CREATE TABLE person(
	id integer,
	creationDate timestamp,
	firstName varchar(64),
	lastName varchar(64),
	gender varchar(8),
	birthday date,
	browserUsed varchar(64),
	locationIP inet,
	cityName varchar(64),
	PRIMARY KEY(id),
	FOREIGN KEY(cityName) REFERENCES city
	);
	
CREATE TABLE language(
	languageName varchar(32),
	PRIMARY KEY(languageName)
	);
	
CREATE TABLE speaks_Language(
	languageName varchar(32),
	personID integer,
	PRIMARY KEY(languageName, personID),
	FOREIGN KEY(languageName) REFERENCES language,
	FOREIGN KEY(personID) REFERENCES person
	);

CREATE TABLE emailAddress(
	email varchar(128),
	PRIMARY KEY(email)
	);
	
CREATE TABLE has_EmailAddress(
	email varchar(128),
	personID integer,
	PRIMARY KEY(email, personID),
	FOREIGN KEY(email) REFERENCES emailAddress,
	FOREIGN KEY(personID) REFERENCES person
	);
	
CREATE TABLE company(
	companyName varchar(128),
	countryName varchar(128),
	PRIMARY KEY(companyName),
	FOREIGN KEY(countryName) REFERENCES country
	);
	
CREATE TABLE work_at(
	personID integer,
	companyName varchar(128),
	workFrom integer,
	PRIMARY KEY(personID, companyName),
	FOREIGN KEY(personID) REFERENCES person,
	FOREIGN KEY(companyName) REFERENCES company
	);
	
CREATE TABLE studyAt(
	personID integer,
	universityName varchar(128),
	classYear integer,
	PRIMARY KEY(personID, universityName),
	FOREIGN KEY(personID) REFERENCES person,
	FOREIGN KEY(universityName) REFERENCES university
	);
	
CREATE TABLE comment(
	creationDate timestamp,
	creatorID integer,
	browserUsed varchar(64),
	locationIP inet,
	content text,
	length integer,
	replyOfComment integer,
	replyOfPost integer,
	locationName varchar(128),
	PRIMARY KEY(creationDate, creatorID),
	FOREIGN KEY(creatorID) REFERENCES person,
	FOREIGN KEY(locationName) REFERENCES country
	);
	
CREATE TABLE forum(
	forumTitle varchar(128),
	creationDate timestamp,
	PRIMARY KEY(forumTitle)
	);
	
CREATE TABLE tag(
	tagName varchar(128)
	PRIMARY KEY(tagName)
	);

CREATE TABLE post(
	creationDate timestamp,
	creatorID integer,
	languageName varchar(32),
	imageFile varchar(128),
	browserUsed varchar(64),
	locationIP inet,
	content text,
	length integer,
	locationName varchar(128),
	containerForum integer,
	PRIMARY KEY(creationDate, creatorID),
	FOREIGN KEY(creatorID) REFERENCES person,
	FOREIGN KEY(locationName) REFERENCES country,
	FOREIGN KEY(containerForum) REFERENCES forum
	);
	