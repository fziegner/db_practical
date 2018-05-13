CREATE TABLE continent(
	continentID integer,
	continentName varchar(32) NOT NULL,
	PRIMARY KEY(continentID)
	);

CREATE TABLE country(
	countryID integer,
	countryName varchar(128) NOT NULL,
	continent integer,
	PRIMARY KEY(countryID),
	FOREIGN KEY(continent) REFERENCES continent ON UPDATE CASCADE
	);
	
CREATE TABLE city(
	cityID integer,
	cityName varchar(128) NOT NULL,
	country integer,
	PRIMARY KEY(cityID),
	FOREIGN KEY(country) REFERENCES country ON UPDATE CASCADE
	);	
	
CREATE TABLE university(
	universityID integer,
	universityName varchar(128),
	city integer,
	PRIMARY KEY(universityID),
	FOREIGN KEY(city) REFERENCES city ON UPDATE CASCADE
	);
	
CREATE TABLE person(
	personID bigint,
	creationDate timestamp NOT NULL,
	firstName varchar(64) NOT NULL,
	lastName varchar(64) NOT NULL,
	gender varchar(8),
	birthday date,
	browserUsed varchar(64),
	locationIP inet,
	city integer,
	PRIMARY KEY(personID),
	FOREIGN KEY(city) REFERENCES city ON UPDATE CASCADE,
	CHECK (birthday < current_date)
	);
	
CREATE TABLE speaks_Language(
	languageName varchar(32),
	personID bigint,
	PRIMARY KEY(languageName, personID),
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
	personID bigint,
	PRIMARY KEY(email, personID),
	FOREIGN KEY(email) REFERENCES emailAddress,
	FOREIGN KEY(personID) REFERENCES person
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	CHECK (email LIKE '%_@__%.__%')
	);
	
CREATE TABLE company(
	companyID integer,
	companyName varchar(128) NOT NULL,
	country integer NOT NULL,
	PRIMARY KEY(companyID),
	FOREIGN KEY(country) REFERENCES country ON UPDATE CASCADE
	);
	
CREATE TABLE work_At(
	personID bigint,
	company integer,
	workFrom integer,
	PRIMARY KEY(personID, company),
	FOREIGN KEY(personID) REFERENCES person
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	FOREIGN KEY(company) REFERENCES company
			ON UPDATE CASCADE
			ON DELETE CASCADE
	);
	
CREATE TABLE study_At(
	personID bigint,
	university integer,
	classYear integer,
	PRIMARY KEY(personID, university),
	FOREIGN KEY(personID) REFERENCES person
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	FOREIGN KEY(university) REFERENCES university
			ON UPDATE CASCADE
			ON DELETE CASCADE
	);
	
CREATE TABLE forum(
	forumID bigint,
	forumTitle varchar(128),
	creationDate timestamp NOT NULL,
	moderator bigint,
	PRIMARY KEY(forumID)
	);
	
CREATE TABLE tag(
	tagID integer,
	tagName varchar(128),
	PRIMARY KEY(tagID)
	);	
	
CREATE TABLE tag_Class(
	tagClassID integer,
	tagClassName varchar(128),
	PRIMARY KEY(tagClassID)
	);	
	
CREATE TABLE post(
	postID bigint,
	creationDate timestamp,
	creatorID bigint,
	languageName varchar(32),
	imageFile varchar(128),
	browserUsed varchar(64),
	locationIP inet NOT NULL,
	content text,
	length integer,
	location integer NOT NULL,
	containerForum bigint NOT NULL,
	PRIMARY KEY(postID),
	FOREIGN KEY(creatorID) REFERENCES person
			ON DELETE CASCADE,
	FOREIGN KEY(location) REFERENCES country,
	FOREIGN KEY(containerForum) REFERENCES forum
	);

CREATE TABLE comment(
	commentID bigint,
	creationDate timestamp,
	creatorID bigint,
	browserUsed varchar(64),
	locationIP inet NOT NULL,
	content text,
	length integer,
	replyOfComment bigint,
	replyOfPost bigint,
	location integer,
	PRIMARY KEY(commentID),
	FOREIGN KEY(creatorID) REFERENCES person
			ON DELETE CASCADE,
	FOREIGN KEY(location) REFERENCES country
	);
	
CREATE TABLE has_Interest(
	tag integer,
	personID bigint,
	PRIMARY KEY(personID, tag),
	FOREIGN KEY(tag) REFERENCES tag
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	FOREIGN KEY(personID) REFERENCES person
			ON UPDATE CASCADE
			ON DELETE CASCADE
	);	
		
CREATE TABLE likes_Post(
	personID bigint,
	postID bigint,
	PRIMARY KEY(personID, postID),
	FOREIGN KEY(personID) REFERENCES person
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	FOREIGN KEY(postID) REFERENCES post
			ON UPDATE CASCADE
			ON DELETE CASCADE
	);
	
CREATE TABLE likes_Comment(
	personID bigint,
	commentID bigint,
	PRIMARY KEY(personID, commentID),
	FOREIGN KEY(personID) REFERENCES person,
	FOREIGN KEY(commentID) REFERENCES comment
	);
	
CREATE TABLE has_Member(
	personID bigint,
	forumID bigint,
	joinDate timestamp NOT NULL,
	PRIMARY KEY(personID, forumID),
	FOREIGN KEY(personID) REFERENCES person
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	FOREIGN KEY(forumID) REFERENCES forum
			ON UPDATE CASCADE
			ON DELETE CASCADE
	);	
	
CREATE TABLE has_Type(
	tagClass integer,
	tag integer,
	PRIMARY KEY(tagClass, tag),
	FOREIGN KEY(tagClass) REFERENCES tag_Class
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	FOREIGN KEY(tag) REFERENCES tag
			ON UPDATE CASCADE
			ON DELETE CASCADE
	);	
	
CREATE TABLE forum_Has_Tag(
	tagID integer,
	forumID bigint,
	PRIMARY KEY(forumID, tagID),
	FOREIGN KEY(forumID) REFERENCES forum
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	FOREIGN KEY(tagID) REFERENCES tag		
	);
	
CREATE TABLE comment_Has_Tag(
	tagID integer,
	commentID bigint,
	PRIMARY KEY(tagID, commentID),
	FOREIGN KEY(commentID) REFERENCES comment
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	FOREIGN KEY(tagID) REFERENCES tag	
	);
	
CREATE TABLE post_Has_Tag(
	tagID integer,
	postID bigint,
	PRIMARY KEY(tagID, postID),
	FOREIGN KEY(postID) REFERENCES post
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	FOREIGN KEY(tagID) REFERENCES tag	
	);	
	
	
CREATE TABLE person_Knows_Person(
	personOne bigint,
	personTwo bigint,
	creationDate timestamp,
	PRIMARY KEY(personOne, personTwo),
	FOREIGN KEY(personOne) REFERENCES person
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	FOREIGN KEY(personTwo) REFERENCES person
			ON UPDATE CASCADE
			ON DELETE CASCADE
	);
	
CREATE TABLE is_Sub_Class(
	tagClassID1 integer,
	tagClassID2 integer,
	PRIMARY KEY(tagClassID1, tagClassID2),
	FOREIGN KEY(tagClassID1) REFERENCES tag_Class
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	FOREIGN KEY(tagClassID2) REFERENCES tag_Class
);
