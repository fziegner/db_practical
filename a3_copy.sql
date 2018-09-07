/*
FROM Path Steven: F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/%.csv
Replacement user-specific
*/

/* continent, country, city */
CREATE TEMP TABLE place_0_0 (
	id integer,
	name varchar(128),
	url varchar(128),
	type varchar(32),
	isPartOf integer
);

COPY place_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/place_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO continent(ID, continentName)
SELECT id, name
FROM place_0_0
WHERE type='continent';

INSERT INTO country(ID, countryName, continent)
SELECT id, name, isPartOf
FROM place_0_0
WHERE type='country';

INSERT INTO city(ID, cityName, country)
SELECT id, name, isPartOf
FROM place_0_0
WHERE type='city';

DROP TABLE place_0_0;

/* university, company*/
CREATE TEMP TABLE organisation_0_0 (
		id integer,
		type varchar(32),
		name varchar(128),
		url varchar(256),
		place integer
);

COPY organisation_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/organisation_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO university(ID, universityName, city)
SELECT id, name, place
FROM organisation_0_0
WHERE type='university';

INSERT INTO company(ID, companyName, country)
SELECT id, name, place
FROM organisation_0_0
WHERE type='company';

DROP TABLE organisation_0_0;

/* Person */
CREATE TEMP TABLE person_0_0(
	id bigint,
	firstName varchar(64),
	lastName varchar(64),
	gender varchar(8),
	birthday date,
	creationDate timestamp,
	locationIP inet,
	browserUsed varchar(64),
	place integer
);

COPY person_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/person_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO person(personID, creationDate, firstName, lastName, gender, birthday, browserUsed, locationIP, city)
SELECT id, creationDate, firstName, lastName, gender, birthday, browserUsed, locationIP, place
FROM person_0_0;

DROP TABLE person_0_0;

/* Tag */
CREATE TEMP TABLE tag_0_0(
	id integer,
	name varchar(128),
	url varchar(128)
);

COPY tag_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/tag_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO tag(tagID, tagName)
SELECT id, name
FROM tag_0_0;

DROP TABLE tag_0_0;

/* Tag Class */
CREATE TEMP TABLE tagclass_0_0(
	id integer,
	name varchar(128),
	url varchar(128)
);

COPY tagclass_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/tagclass_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO tag_Class(tagClassID, tagClassName)
SELECT id, name
FROM tagclass_0_0;

DROP TABLE tagclass_0_0;

/* Forum */
CREATE TABLE forum_0_0(
	id bigint,
	title varchar(128),
	creationDate timestamp,
	moderator bigint
);

COPY forum_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/forum_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO forum(forumID, forumTitle, creationDate, moderator)
SELECT id, title, creationDate, moderator
FROM forum_0_0;

DROP TABLE forum_0_0;

/* Post */
CREATE TABLE post_0_0(
	id bigint,
	imageFile varchar(128),
	creationDate timestamp,
	locationIP inet,
	browserUsed varchar(64),
	language varchar(32),
	content text,
	length integer,
	creator bigint,
	forumID bigint,
	place integer
);

COPY post_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/post_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO post(postID, creationDate, creatorID, languageName, imageFile, browserUsed, locationIP, content, length, location, containerForum)
SELECT id, creationDate, creator, language, imageFile, browserUsed, locationIP, content, length, place, forumID
FROM post_0_0;

DROP TABLE post_0_0;

/* Comment */
CREATE TEMP TABLE comment_0_0 (
	id bigint,
	creationDate timeStamp,
	locationIP inet,
	browserUsed varchar(64),
	content text,
	length integer,
	creator bigint,
	place integer,
	replyOfPost bigint,
	replyOfComment bigint
);

COPY comment_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/comment_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO comment(commentID, creationDate, creatorID, browserUsed, locationIP, content, length, replyOfPost, replyOfComment, location)
SELECT id, creationDate, creator, browserUsed, locationIP, content, length, replyOfPost, replyOfComment, place
FROM comment_0_0;

DROP TABLE comment_0_0;

/* Person_workAt */
CREATE TABLE person_workAt_organisation_0_0 (
	personID bigint,
	organisationID integer,
	workFrom integer
);

COPY person_workAt_organisation_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/person_workAt_organisation_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO work_At(personID, company, workFrom)
SELECT personID, organisationID, workFrom
FROM person_workAt_organisation_0_0;

DROP TABLE person_workAt_organisation_0_0;

/* Person_studyAt */
CREATE TABLE person_studyAt_organisation_0_0 (
	personID bigint,
	organisationID integer,
	classYear integer
);

COPY person_studyAt_organisation_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/person_studyAt_organisation_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO study_At(personID, university, classYear)
SELECT personID, organisationID, classYear
FROM person_studyAt_organisation_0_0;

DROP TABLE person_studyAt_organisation_0_0;

/* EMail, has_EMailAddress */
CREATE TEMP TABLE person_email_emailaddress_0_0 (
	personID bigint,
	email varchar(128)
);

COPY person_email_emailaddress_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/person_email_emailaddress_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO emailAddress(email)
SELECT email
FROM person_email_emailaddress_0_0;

INSERT INTO has_EMailAddress(email, personID)
SELECT email, personID
FROM person_email_emailaddress_0_0;

DROP TABLE person_email_emailaddress_0_0;

/* Forum_has_Tag */
CREATE TABLE forum_hasTag_tag_0_0 (
	forumID bigInt,
	tagID integer
);

COPY forum_hasTag_tag_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/forum_hasTag_tag_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO forum_Has_Tag(tagID, forumID)
SELECT tagID, forumID
FROM forum_hasTag_tag_0_0;

DROP TABLE forum_hasTag_tag_0_0;

/* Post_has_Tag */
CREATE TABLE post_hasTag_tag_0_0 (
	postID bigInt,
	tagID integer
);

COPY post_hasTag_tag_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/post_hasTag_tag_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO post_Has_Tag(tagID, postID)
SELECT tagID, postID
FROM post_hasTag_tag_0_0;

DROP TABLE post_hasTag_tag_0_0;

/* Comment_has_Tag */
CREATE TABLE comment_hasTag_tag_0_0 (
	commentID bigInt,
	tagID integer
);

COPY comment_hasTag_tag_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/comment_hasTag_tag_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO comment_Has_Tag(tagID, commentID)
SELECT tagID, commentID
FROM comment_hasTag_tag_0_0;

DROP TABLE comment_hasTag_tag_0_0;

/* language, speaks_Language */
CREATE TEMP TABLE person_speaks_language_0_0 (
	personID bigint,
	language varchar(32)
);

COPY person_speaks_language_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/person_speaks_language_0_0.csv' DELIMITER '|' CSV HEADER;

/*INSERT INTO language(languageName)
SELECT language
FROM person_speaks_language_0_0;*/

INSERT INTO speaks_Language(personID, languageName)
SELECT personID, language
FROM person_speaks_language_0_0;

DROP TABLE person_speaks_language_0_0;

/* tag_hasType_tagClass */
CREATE TEMP TABLE tag_hasType_tagclass_0_0 (
	tagID integer,
	tagClassID integer
);

COPY tag_hasType_tagclass_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/tag_hasType_tagclass_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO has_Type(tagClass, tag)
SELECT tagClassID, tagID
FROM tag_hasType_tagclass_0_0;

DROP TABLE tag_hasType_tagclass_0_0;

/* Forum_has_Member */
CREATE TEMP TABLE forum_hasMember_person_0_0 (
	forumID bigInt,
	personID bigInt,
	joinDate timeStamp
);

COPY forum_hasMember_person_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/forum_hasMember_person_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO has_Member(personID, forumID, joinDate)
SELECT personID, forumID, joinDate
FROM forum_hasMember_person_0_0;

DROP TABLE forum_hasMember_person_0_0;

/* person_likes_post */ 
CREATE TEMP TABLE person_likes_post_0_0 (
	personID bigInt,
	postID bigInt,
	creationDate timestamp
);

COPY person_likes_post_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/person_likes_post_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO likes_Post(personID, postID)
SELECT personID, postID
FROM person_likes_post_0_0;

DROP TABLE person_likes_post_0_0;

/* person_likes_comment */
CREATE TEMP TABLE person_likes_comment_0_0 (
	personID bigInt,
	commentID bigInt,
	creationDate timestamp
);

COPY person_likes_comment_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/person_likes_comment_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO likes_Comment(personID, commentID)
SELECT personID, commentID
FROM person_likes_comment_0_0;

DROP TABLE person_likes_comment_0_0;

/* person_hasInterest */
CREATE TEMP TABLE person_hasInterest_tag_0_0 (
	personID bigInt,
	tagID integer
);

COPY person_hasInterest_tag_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/person_hasInterest_tag_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO has_Interest(tag, personID)
SELECT tagID, personID
FROM person_hasInterest_tag_0_0;

DROP TABLE person_hasInterest_tag_0_0;

/* person_knows_person */
CREATE TEMP TABLE person_knows_person_0_0 (
	personIDOne bigInt,
	personIDTwo bigInt,
	creationDate timestamp
);

COPY person_knows_person_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/person_knows_person_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO person_Knows_Person(personOne, personTwo, creationDate)
SELECT personIDOne, personIDTwo, creationDate
FROM person_knows_person_0_0;;

DROP TABLE person_knows_person_0_0;

/* tagclass_isSubClassOf_tagclass_0_0 */
CREATE TEMP TABLE tagclass_isSubClassOf_tagclass_0_0 (
	tagClassID1 integer,
	tagClassID2 integer
);

COPY tagclass_isSubClassOf_tagclass_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/tagclass_isSubClassOf_tagclass_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO is_Sub_Class(tagClassID1, tagClassID2)
SELECT tagClassID1, tagClassID2
FROM tagclass_isSubClassOf_tagclass_0_0;

DROP TABLE tagclass_isSubClassOf_tagclass_0_0;

