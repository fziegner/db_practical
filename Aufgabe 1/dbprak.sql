CREATE TABLE continent(
	continentName varchar(32),
	PRIMARY KEY(continentName)
	);

CREATE TABLE country(
	countryName varchar(128),
	continentName varchar(32),
	PRIMARY KEY(countryName),
	FOREIGN KEY(continentName) REFERENCES continent ON UPDATE CASCADE
	);
	
CREATE TABLE city(
	cityName varchar(128),
	countryName varchar(128),
	PRIMARY KEY(cityName),
	FOREIGN KEY(countryName) REFERENCES country ON UPDATE CASCADE
	);	
	
CREATE TABLE university(
	universityName varchar(128),
	cityName varchar(128),
	PRIMARY KEY(universityName),
	FOREIGN KEY(cityName) REFERENCES city ON UPDATE CASCADE
	);
	
CREATE TABLE person(
	personID integer,
	creationDate timestamp NOT NULL,
	firstName varchar(64) NOT NULL,
	lastName varchar(64) NOT NULL,
	gender varchar(8),
	birthday date,
	browserUsed varchar(64),
	locationIP inet,
	cityName varchar(64),
	PRIMARY KEY(personID),
	FOREIGN KEY(cityName) REFERENCES city ON UPDATE CASCADE
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
			ON UPDATE CASCADE
			ON DELETE CASCADE
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
			ON UPDATE CASCADE
			ON DELETE CASCADE
	);
	
CREATE TABLE company(
	companyName varchar(128),
	countryName varchar(128) NOT NULL,
	PRIMARY KEY(companyName),
	FOREIGN KEY(countryName) REFERENCES country ON UPDATE CASCADE
	);
	
CREATE TABLE work_At(
	personID integer,
	companyName varchar(128),
	workFrom integer,
	PRIMARY KEY(personID, companyName),
	FOREIGN KEY(personID) REFERENCES person
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	FOREIGN KEY(companyName) REFERENCES company
			ON UPDATE CASCADE
			ON DELETE CASCADE
	);
	
CREATE TABLE study_At(
	personID integer,
	universityName varchar(128),
	classYear integer,
	PRIMARY KEY(personID, universityName),
	FOREIGN KEY(personID) REFERENCES person
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	FOREIGN KEY(universityName) REFERENCES university
			ON UPDATE CASCADE
			ON DELETE CASCADE
	);
	
CREATE TABLE comment(
	creationDate timestamp,
	creatorID integer,
	browserUsed varchar(64),
	locationIP inet NOT NULL,
	content text,
	length integer,
	replyOfComment integer,
	replyOfPost integer,
	locationName varchar(128) NOT NULL,
	PRIMARY KEY(creatorID, creationDate),
	FOREIGN KEY(creatorID) REFERENCES person
			ON DELETE CASCADE,
	FOREIGN KEY(locationName) REFERENCES country
	);
	
CREATE TABLE forum(
	forumID integer,
	forumTitle varchar(128),
	creationDate timestamp NOT NULL,
	PRIMARY KEY(forumID)
	);
	
CREATE TABLE tag(
	tagName varchar(128),
	PRIMARY KEY(tagName)
	);

CREATE TABLE post(
	creationDate timestamp,
	creatorID integer,
	languageName varchar(32),
	imageFile varchar(128),
	browserUsed varchar(64),
	locationIP inet NOT NULL,
	content text,
	length integer,
	locationName varchar(128) NOT NULL,
	containerForum integer NOT NULL,
	PRIMARY KEY(creatorID, creationDate),
	FOREIGN KEY(creatorID) REFERENCES person
			ON DELETE CASCADE,
	FOREIGN KEY(locationName) REFERENCES country,
	FOREIGN KEY(containerForum) REFERENCES forum
	);
	
CREATE TABLE has_Interest(
	tagName varchar(128),
	personID integer,
	PRIMARY KEY(personID, tagName),
	FOREIGN KEY(tagName) REFERENCES tag
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	FOREIGN KEY(personID) REFERENCES person
			ON UPDATE CASCADE
			ON DELETE CASCADE
	);
	
CREATE TABLE likes_Comment(
	personID integer,
	creationDate timestamp,
	creatorID integer,
	PRIMARY KEY(personID, creationDate, creatorID),
	FOREIGN KEY(personID) REFERENCES person,
	FOREIGN KEY(creatorID, creationDate) REFERENCES comment 
	);
	
CREATE TABLE likes_Post(
	personID integer,
	creationDate timestamp,
	creatorID integer,
	PRIMARY KEY(personID, creationDate, creatorID),
	FOREIGN KEY(personID) REFERENCES person
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	FOREIGN KEY(creatorID, creationDate) REFERENCES post 
			ON UPDATE CASCADE
			ON DELETE CASCADE
	);
	
CREATE TABLE has_Member(
	personID integer,
	forumID integer,
	joinDate timestamp NOT NULL,
	PRIMARY KEY(personID, forumID),
	FOREIGN KEY(personID) REFERENCES person
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	FOREIGN KEY(forumID) REFERENCES forum
			ON UPDATE CASCADE
			ON DELETE CASCADE
	);
	
CREATE TABLE tag_Class(
	tagClassName varchar(128),
	PRIMARY KEY(tagClassName)
	);
	
CREATE TABLE has_Type(
	tagClassName varchar(128),
	tagName varchar(128),
	PRIMARY KEY(tagClassName, tagName),
	FOREIGN KEY(tagClassName) REFERENCES tag_Class
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	FOREIGN KEY(tagName) REFERENCES tag
			ON UPDATE CASCADE
			ON DELETE CASCADE
	);
	
CREATE TABLE forum_Has_Tag(
	tagName varchar(128),
	forumID integer,
	PRIMARY KEY(forumID, tagName),
	FOREIGN KEY(forumID) REFERENCES forum
			ON UPDATE CASCADE
			ON DELETE CASCADE
	);
	
CREATE TABLE comment_Has_Tag(
	tagName varchar(128),
	creationDate timestamp,
	creatorID integer,
	PRIMARY KEY(tagName),
	FOREIGN KEY(creatorID, creationDate) REFERENCES comment
			ON UPDATE CASCADE
			ON DELETE CASCADE
	);
	
CREATE TABLE post_Has_Tag(
	tagName varchar(128),
	creationDate timestamp,
	creatorID integer,
	PRIMARY KEY(tagName),
	FOREIGN KEY(creatorID, creationDate) REFERENCES post
			ON UPDATE CASCADE
			ON DELETE CASCADE
	);