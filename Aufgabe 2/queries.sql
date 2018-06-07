-- Query 1
SELECT count (distinct city.cityid)
FROM city JOIN country ON city.country = country.countryid 
   JOIN continent ON country.continent = continent.continentid 
   JOIN University ON city.cityid = city
WHERE continent.continentname = 'Africa'

SELECT count(distinct city.cityid)
FROM city NATURAL JOIN country 
   NATURAL JOIN continent 
   NATURAL JOIN University
WHERE continent.continentname = 'Africa' 
   AND city.country = country.countryid 
   AND country.continent = continent.continentid 
   AND city.cityid = city;
--Ergebnis: 100 Datensätze

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
*/
-- Anzahl Tupel: 111

-- Query 4
SELECT cityName, count(personID) AS einwohner
FROM city JOIN person ON city.cityID = person.city
GROUP BY cityName
ORDER BY 2 DESC

/*Ergebnis:
"Ludwigsburg"	"2"
"Rahim_Yar_Khan"	"2"
"Toyohashi"	"1"
"Baku"	"1"
"Pudong"	"1"
"Rovaniemi"	"1"
...
*/
-- Anzahl Tupel: 86

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
SELECT DISTINCT firstName, lastName
FROM person JOIN has_member ON person.personID = has_member.personID
WHERE forumID IN (SELECT forumID
				  FROM person JOIN has_member ON person.personID = has_member.personID
				  WHERE firstName = 'Mehmet' AND lastName = 'Koksal')
			  AND firstName <> 'Mehmet' AND lastName <> 'Koksal'
			  
/*Ergebnis:
"Zhi"	"Zhang"
"Jorge"	"Araujo Castro"
"Cheng"	"Chen"
"Baby"	"Yang"
"Bryn"	"Davies"
"John"	"Johnson"
...
"Celso"	"Oliveira"
"Chen"	"Li"
"Miguel"	"Gonzalez"
"Albaye Papa"	"Diouf"
"Chen"	"Zhu"
"Peng"	"Yang"
-- Anzahl Tupel: 49
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