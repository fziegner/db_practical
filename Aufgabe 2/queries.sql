-- Query 1
SELECT count (distinct city.cityid)
FROM city JOIN country ON city.country = country.countryid 
   JOIN continent ON country.continent = continent.continentid 
   JOIN University ON city.cityid = city
WHERE continent.continentname = 'Africa'

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
WHERE personOne = (SELECT personID FROM person WHERE firstName = 'Hans' AND lastName = 'Johansson')
   OR personTwo = (SELECT personID FROM person WHERE firstName = 'Hans' AND lastName = 'Johansson')
   
/*Ergebnis:
"Jorge"	"Araujo Castro"
"Bryn"	"Davies"
"Alim"	"Guliyev"
...
"Ali"	"Achiou"
"Hans"	"Johansson"
"Otto"	"Richter"
-- Anzahl Tupel: 13

	