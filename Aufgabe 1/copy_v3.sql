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