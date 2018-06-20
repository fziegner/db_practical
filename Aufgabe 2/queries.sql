-- Query 1
SELECT count (distinct city.cityid)
FROM city JOIN country ON city.country = country.countryid 
   JOIN continent ON country.continent = continent.continentid 
   JOIN University ON city.cityid = city
WHERE continent.continentname = 'Africa'

/*Ergebnis:
"100"
-- Anzahl Tupel: 1
*/

-- Query 2
SELECT firstName, lastName, count(creatorID) AS posts
FROM person JOIN post ON person.personID = post.creatorID
WHERE person.birthday = (SELECT MIN(birthday) FROM person JOIN post ON person.personID = post.creatorID)
GROUP BY firstName, lastName

--Ergebnis: Masahiro Sato, 1 Post
--älteste Person: Joakim Larsson, 0 Posts

--Query 3
SELECT countryName, count(commentID) AS comments
FROM country LEFT JOIN comment ON country.countryid = comment.location
GROUP BY countryName
ORDER BY 2 ASC

/*Ergebnis:
"Northern_Ireland"	"0"
"England"	"0"
"Scotland"	"0"
"Wales"	"0"
"Nepal"	"6"
"Singapore"	"8"
...
"Afghanistan"	"27"
"Germany"	"28"
"Mexico"	"32"
"Republic_of_Macedonia"	"32"
"China"	"35"
"United_Kingdom"	"93"
-- Anzahl Tupel: 111
*/

-- Query 4
SELECT cityName, COUNT(personID) AS einwohner
FROM city JOIN person ON city.cityID = person.city
GROUP BY cityName
HAVING COUNT(personID) = (SELECT COUNT(personID)
						  FROM city JOIN person ON city.cityID = person.city
						  GROUP BY cityName
						  ORDER BY 1 DESC
						  LIMIT 1)
ORDER BY 2 DESC

/*Ergebnis:
"Rahim_Yar_Khan"	"2"
"Ludwigsburg"	"2"
-- Anzahl Tupel: 2
*/

-- Query 5
SELECT DISTINCT firstName, lastName
FROM person JOIN pkp_symmetric pkp ON person.personID = pkp.personOne OR person.personID = pkp.personTwo
WHERE (personOne = (SELECT personID FROM person WHERE firstName = 'Hans' AND lastName = 'Johansson')
   OR personTwo = (SELECT personID FROM person WHERE firstName = 'Hans' AND lastName = 'Johansson'))
   AND firstName <> 'Hans' AND lastName <> 'Johansson'
   
SELECT personID
FROM person JOIN pkp_symmetric pkp ON person.personID = pkp.personOne
WHERE (personTwo = (SELECT personID FROM person WHERE firstName = 'Hans' AND lastName = 'Johansson'))
   AND firstName <> 'Hans' AND lastName <> 'Johansson'
   
/*Ergebnis:
"Jorge"	"Araujo Castro"
"Bryn"	"Davies"
"Alim"	"Guliyev"
...
"Paul"	"Becker"
"Ali"	"Achiou"
"Otto"	"Richter"
-- Anzahl Tupel: 12
*/

-- Query 6
SELECT DISTINCT firstName, lastName
FROM person JOIN pkp_symmetric pkp ON person.personID = pkp.personOne
WHERE personTwo IN (SELECT personID
				   FROM person JOIN pkp_symmetric pkp ON person.personID = pkp.personOne
				   WHERE (personTwo = (SELECT personID FROM person WHERE firstName = 'Hans' AND lastName = 'Johansson'))
				   AND firstName <> 'Hans' AND lastName <> 'Johansson')
				AND personOne NOT IN(SELECT personID
							FROM person JOIN pkp_symmetric pkp ON person.personID = pkp.personOne
							WHERE (personTwo = (SELECT personID FROM person WHERE firstName = 'Hans' AND lastName = 'Johansson'))
							AND firstName <> 'Hans' AND lastName <> 'Johansson')
							
/*Ergebnis:
"Yahya Ould Ahmed El"	"Abdallahi"
"Ali"	"Abouba"
"Evangelos"	"Alkaios"
"Oleg"	"Bazayev"
"Pablo"	"Bernal"
"Adrian"	"Bravo"
...
"Chen"	"Yang"
"Jie"	"Yang"
"Djelaludin"	"Zaland"
"Lei"	"Zhang"
"Li"	"Zhang"
"Lin"	"Zhang"
-- Anzahl Tupel: 48
*/

-- Query 7			  
WITH fids AS (SELECT forumID
			  FROM person JOIN has_member ON person.personID = has_member.personID
			  WHERE firstName = 'Mehmet' AND lastName = 'Koksal')
SELECT firstName, lastName
FROM person JOIN has_member ON person.personID = has_member.personID
WHERE has_member.forumID IN (SELECT * FROM fids)
GROUP BY person.personID
HAVING COUNT(has_member.forumID) = (SELECT COUNT(*) FROM fids)
			  
/*Ergebnis:
"Miguel"	"Gonzalez"
"Chen"	"Yang"
"Paul"	"Becker"
"Mehmet"	"Koksal"
-- Anzahl Tupel: 4
*/

-- Query 8
SELECT continentName, ROUND((COUNT(*) * 100.0) / (SELECT COUNT(*) FROM person), 3) AS anteil
FROM person JOIN city ON person.city = city.cityID
			JOIN country ON city.country = country.countryID
			JOIN continent ON country.continent = continent.continentID
GROUP BY continentName

/*Ergebnis:
"North_America"	"9.091"
"South_America"	"4.545"
"Africa"	"11.364"
"Asia"	"50.000"
"Europe"	"25.000"
-- Anzahl Tupel: 5
*/

-- Query 9
SELECT tagClassName, COUNT(*)
FROM tag_class JOIN has_type ON tag_class.tagClassID = has_type.tagClass
			   JOIN tag ON has_type.tag = tag.tagID
			   JOIN post_has_tag ON tag.tagID = post_has_tag.tagID
			   JOIN post ON post_has_tag.postID = post.postID
GROUP BY tagClassName
ORDER BY 2 DESC
LIMIT 10

/*Ergebnis:
"Person"	"110"
"MusicalArtist"	"99"
"OfficeHolder"	"76"
"Writer"	"66"
"TennisPlayer"	"63"
"BritishRoyalty"	"57"
"Saint"	"33"
"Single"	"30"
"Philosopher"	"28"
"Album"	"27"
-- Anzahl Tupel: 10
*/

-- Query 10
SELECT DISTINCT firstName, lastName
FROM person
WHERE personid NOT IN (SELECT creatorid FROM comment c JOIN likes_Comment lc ON c.commentid = lc.commentid
					   UNION
					   SELECT creatorid FROM post p JOIN likes_Post lp ON p.postid = lp.postid)
			   AND personid NOT IN(SELECT personid FROM person WHERE personid NOT IN (SELECT creatorid FROM comment
									UNION
								   SELECT creatorid FROM post))
ORDER BY lastName

/*Ergebnis:
"Ayesha"	"Ahmed"
"Mirza Kalich"	"Ali"
"Pablo"	"Bernal"
"Luigi"	"Colombo"
"Bryn"	"Davies"
...
"Anatoly"	"Shevchenko"
"Cheng"	"Wei"
"Jie"	"Yang"
"Jan"	"Zakrzewski"
"Li"	"Zhang"
"Zhi"	"Zhang"
--Anzahl Tupel: 27
*/

--Query 11
SELECT forumtitle
FROM forum JOIN post ON forumid = containerforum
GROUP BY forumid
HAVING COUNT(*) > (SELECT COUNT(*) FROM post)/(SELECT COUNT(*) FROM forum)
ORDER BY forumtitle;

/*Ergebnis
"Album 0 of Abdul Haris Tobing"
"Album 0 of Alejandro Rodriguez"
"Album 0 of Ali Abouba"
"Album 0 of Amy Chen"
"Album 0 of Celso Oliveira"
"Album 0 of Djelaludin Zaland"
"Album 0 of Eric Mettacara"
...
"Album 9 of Wei Wei"
"Album 9 of Yang Li"
"Group for Muhammad_Ali_of_Egypt in Hefei"
"Wall of Alim Guliyev"
"Wall of Karl Fischer"
"Wall of Ken Yamada"
--Anzahl Tupel: 359
*/

-- Query 12
SELECT firstName, lastName
FROM person JOIN pkp_symmetric pkp ON person.personID = pkp.personOne
WHERE personTwo = (SELECT person.personid
				   FROM person JOIN likes_post plp ON person.personid = plp.personid
							   JOIN post ON plp.personid = post.creatorid
				   GROUP BY person.personid
				   HAVING COUNT(plp.personid) = (SELECT COUNT(plp.personid)
												 FROM person JOIN likes_post plp ON person.personid = plp.personid
															 JOIN post ON plp.personid = post.creatorid
												 GROUP BY person.personid
												 ORDER BY 1 DESC
												 LIMIT 1))
ORDER BY 2

/*Ergebnis:
"Ali"	"Abouba"
"Ali"	"Achiou"
"Cheng"	"Chen"
"Bryn"	"Davies"
"Karl"	"Fischer"
"Hossein"	"Forouhar"
...
"Wei"	"Wei"
"Ken"	"Yamada"
"Akira"	"Yamamoto"
"Li"	"Zhang"
"Lin"	"Zhang"
"Lei"	"Zhang"
-- Anzahl Tupel: 23
*/

-- Query 13
WITH RECURSIVE tree(id, firstName, lastName, distance, max_recursion) AS (
	SELECT personid, firstName, lastName, 0, 0
	FROM person
	WHERE person.personid = 94
	UNION
	SELECT pkp.persontwo, person.firstName, person.lastName, tree.distance + 1, max_recursion + 1
	FROM pkp_symmetric pkp JOIN person ON pkp.persontwo = person.personid, tree
	WHERE pkp.personone = tree.id AND max_recursion < 100
)
SELECT tree.id, tree.firstName, tree.lastName, MIN(tree.distance) AS distance
FROM tree
WHERE id <> 94
GROUP BY tree.id, tree.firstName, tree.lastName
ORDER BY distance ASC

/*Ergebnis:
"8796093022251"	"Chen"	"Li"	1
"2199023255625"	"Cheng"	"Chen"	1
"96"	"Anson"	"Chen"	1
"8796093022217"	"Alim"	"Guliyev"	1
"3298534883365"	"Wei"	"Wei"	1
"10995116277851"	"Chong"	"Liu"	1
...
"6597069766675"	"Jimmy"	"Burak"	3
"13194139533324"	"Jose"	"Alonso"	3
"1099511627861"	"Hao"	"Li"	3
"13194139533350"	"Alfonso"	"Rodriguez"	3
"5497558138966"	"Alejandro"	"Rodriguez"	3
"15393162788910"	"Alejandro"	"Garcia"	4
-- Anzahl Tupel: 72
*/

-- Query 14
WITH RECURSIVE tree(id, firstName, lastName, distance, treepath) AS (
	SELECT personid, firstName, lastName, 0, CAST(firstName AS VARCHAR(1024)) AS treepath
	FROM person
	WHERE person.personid = 94
	UNION
	SELECT pkp.persontwo, person.firstName, person.lastName, tree.distance + 1, 
	CAST (
		CONCAT (
			tree.treepath, CAST(' -> ' AS VARCHAR(1024)), CAST(person.firstName AS VARCHAR(1024))
		) AS VARCHAR(1024)
	) AS treepath
	FROM person_knows_person pkp JOIN person ON pkp.persontwo = person.personid, tree
	WHERE pkp.personone = tree.id
)
SELECT * from tree

/*Ergebnis:
"94"	"Jun"	"Hu"	0	"Jun"
"2199023255625"	"Cheng"	"Chen"	1	"Jun -> Cheng"
"96"	"Anson"	"Chen"	1	"Jun -> Anson"
"8796093022217"	"Alim"	"Guliyev"	1	"Jun -> Alim"
"10995116277851"	"Chong"	"Liu"	1	"Jun -> Chong"
"8796093022251"	"Chen"	"Li"	1	"Jun -> Chen"
...
"16492674416674"	"Roberto"	"Diaz"	7	"Jun -> Anson -> Ali -> Cam -> Bryn -> Jie -> Celso -> Roberto"
"16492674416674"	"Roberto"	"Diaz"	7	"Jun -> Anson -> Amy -> Cam -> Bryn -> Jie -> Celso -> Roberto"
"16492674416674"	"Roberto"	"Diaz"	8	"Jun -> Anson -> Ali -> Alim -> Akira -> Bryn -> Abdoulaye Khouma -> Celso -> Roberto"
"16492674416674"	"Roberto"	"Diaz"	8	"Jun -> Anson -> Ali -> Amy -> Cam -> Bryn -> Abdoulaye Khouma -> Celso -> Roberto"
"16492674416674"	"Roberto"	"Diaz"	8	"Jun -> Anson -> Ali -> Alim -> Akira -> Bryn -> Jie -> Celso -> Roberto"
"16492674416674"	"Roberto"	"Diaz"	8	"Jun -> Anson -> Ali -> Amy -> Cam -> Bryn -> Jie -> Celso -> Roberto"
-- Anzahl Tupel: 553
*/