CREATE OR REPLACE FUNCTION getShortestFriendshipPath (IN person1 bigint, IN person2 bigint, OUT shortestPath TEXT) RETURNS TEXT AS $$
BEGIN 
	WITH RECURSIVE friend(personID, firstName, lastName, grade, path, cycle) AS (
		SELECT p.personID, p.firstName, p.lastName, 1, ARRAY[p.personID], false
		FROM pkp_symmetric f JOIN person p ON f.personOne = p.personID
		WHERE f.personTwo = person1
		UNION 
		SELECT DISTINCT ON (p.personID) p.personID, p.firstName, p.lastName, (friend.grade + 1), friend.path || p.personID, (p.personID = ANY((friend.path)))
		FROM friend, pkp_symmetric f JOIN person p ON f.personOne = p.personID
		WHERE f.personOne = friend.personID AND NOT (p.personID = ANY(friend.path)))
	SELECT * INTO shortestPath
	FROM (
		SELECT path
		FROM friend WHERE personID = person2
		ORDER BY grade ASC
		LIMIT 1) AS s;
END;
$$ LANGUAGE plpgsql;
