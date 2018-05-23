CREATE VIEW pkp_symmetric(personOne, personTwo, creationDate) AS
	SELECT personOne, personTwo, creationDate
	FROM person_Knows_Person UNION
	SELECT personTwo, personOne, creationDate
	FROM person_Knows_Person