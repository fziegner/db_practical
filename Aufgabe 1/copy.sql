/* continent, country, city */
CREATE TEMP TABLE place_0_0 (
	id integer,
	name varchar(128),
	url varchar(128),
	type varchar(32),
	isPartOf integer
);

COPY place_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/csv_data/place_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO continent(continentName)
SELECT name
FROM place_0_0
WHERE type='continent';

INSERT INTO country(countryName, continentName)
SELECT a.name, b.name
FROM place_0_0 a, place_0_0 b
WHERE a.type='country' AND a.isPartOf=b.id;

INSERT INTO city(cityName)
SELECT name
FROM place_0_0
WHERE type='city';

DROP TABLE place_0_0;


/* comment */
/*CREATE TEMP TABLE comment_0_0 (
	creationDate timeStamp,
	locationIP inet,
	browserUsed varchar(64),
	content text,
	length integer
	creator integer,
	place integer,
	replyOfPost integer,
	replyOfComment integer,
);

COPY comment_0_0 FROM 'F:/Git/Repository/DBPraktikum/Aufgabe 1/coment_0_0.csv' DELIMITER '|' CSV HEADER;

INSERT INTO comment(creationDate, locationIP, browserUsed, content, length, creatorID, place, replyOfPost, replyOfComment)
SELECT creationDate, locationIP, browserUsed, content, length, creationID, place, replyOfPost, replyOfComment
FROM comment_0_0;

DROP TABLE coment_0_0;*/