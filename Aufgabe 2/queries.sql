-- Query 1
SELECT count (distinct city.cityid)
FROM city JOIN country ON city.country = country.countryid 
   JOIN continent ON country.continent = continent.continentid 
   JOIN University ON city.cityid = city
WHERE continent.continentname = 'Africa';