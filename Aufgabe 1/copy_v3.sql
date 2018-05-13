/* continent, country, city */
CREATE TEMP TABLE place_0_0 (
	id integer,
	name varchar(128),
	url varchar(128),
	type varchar(32),
	isPartOf integer
);

COPY place_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/place_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO continent(continentID, continentName)
SELECT id, name
FROM place_0_0
WHERE type='continent';

INSERT INTO country(countryID, countryName, continent)
SELECT id, name, isPartOf
FROM place_0_0
WHERE type='country';

INSERT INTO city(cityID, cityName, country)
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

INSERT INTO university(universityID, universityName, city)
SELECT id, name, place
FROM organisation_0_0
WHERE type='university';

INSERT INTO company(companyID, companyName, country)
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









