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
	FOREIGN KEY(city) REFERENCES city ON UPDATE CASCADE
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
	companyID integer,
	companyName varchar(128) NOT NULL,
	country integer NOT NULL,
	PRIMARY KEY(companyID),
	FOREIGN KEY(country) REFERENCES country ON UPDATE CASCADE
	);
	
CREATE TABLE work_At(
	personID integer,
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
	personID integer,
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
	forumID integer,
	forumTitle varchar(128),
	creationDate timestamp NOT NULL,
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
	postID integer,
	creationDate timestamp,
	creatorID integer,
	/*languageName varchar(32),*/
	imageFile varchar(128),
	browserUsed varchar(64),
	locationIP inet NOT NULL,
	content text,
	length integer,
	location integer NOT NULL,
	containerForum integer NOT NULL,
	PRIMARY KEY(postID),
	FOREIGN KEY(creatorID) REFERENCES person
			ON DELETE CASCADE,
	FOREIGN KEY(location) REFERENCES country,
	FOREIGN KEY(containerForum) REFERENCES forum
	);

CREATE TABLE comment(
	commentID integer,
	creationDate timestamp,
	creatorID integer,
	browserUsed varchar(64),
	locationIP inet NOT NULL,
	content text,
	length integer,
	replyOfComment integer,
	replyOfPost integer,
	location integer NOT NULL,
	PRIMARY KEY(commentID),
	FOREIGN KEY(creatorID) REFERENCES person
			ON DELETE CASCADE,
	FOREIGN KEY(location) REFERENCES country
	);
	
CREATE TABLE has_Interest(
	tag integer,
	personID integer,
	PRIMARY KEY(personID, tag),
	FOREIGN KEY(tag) REFERENCES tag
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	FOREIGN KEY(personID) REFERENCES person
			ON UPDATE CASCADE
			ON DELETE CASCADE
	);	
		
CREATE TABLE likes_Post(
	personID integer,
	postID integer,
	/*creationDate timestamp,
	creatorID integer,*/
	PRIMARY KEY(personID, /*creationDate, creatorID*/postID),
	FOREIGN KEY(personID) REFERENCES person
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	/*FOREIGN KEY(creatorID, creationDate) REFERENCES post 
			ON UPDATE CASCADE
			ON DELETE CASCADE*/
	FOREIGN KEY(postID) REFERENCES post
			ON UPDATE CASCADE
			ON DELETE CASCADE
	);
	
CREATE TABLE likes_Comment(
	personID integer,
	commentID integer,
	/*creationDate timestamp,
	creatorID integer,*/
	PRIMARY KEY(personID, /*creationDate, creatorID*/ commentID),
	FOREIGN KEY(personID) REFERENCES person,
	/*FOREIGN KEY(creatorID, creationDate) REFERENCES comment */
	FOREIGN KEY(commentID) REFERENCES comment
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
	tag integer,
	forumID integer,
	PRIMARY KEY(forumID, tag),
	FOREIGN KEY(forumID) REFERENCES forum
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	FOREIGN KEY(tag) REFERENCES tag		
	);
	
CREATE TABLE comment_Has_Tag(
	tagID integer,
	commentID integer,
	PRIMARY KEY(tagID, commentID),
	FOREIGN KEY(commentID) REFERENCES comment
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	FOREIGN KEY(tagID) REFERENCES tag	
	);
	
CREATE TABLE post_Has_Tag(
	tagID integer,
	postID integer,
	PRIMARY KEY(tagID),
	FOREIGN KEY(postID) REFERENCES post
			ON UPDATE CASCADE
			ON DELETE CASCADE,
	FOREIGN KEY(tagID) REFERENCES tag	
	);	
	
/* Person knows Person AND TagClass is subclass of TagClass AND Forum has Moderator*/