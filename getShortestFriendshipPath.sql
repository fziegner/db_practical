CREATE OR REPLACE FUNCTION getShortestFriendshipPath(IN person1 bigint, IN person2 bigint, OUT shortestFriendshipPath TEXT) RETURNS TEXT AS $$
BEGIN
	WITH RECURSIVE friend(personID, grade, path) AS (
		SELECT p.personid, 1, ARRAY[p.personID]
		FROM pkp_symmetric pkp JOIN person p ON pkp.personOne = p.personID
		WHERE pkp.personTwo = person1
		UNION 
		SELECT DISTINCT ON (p.personID) p.personID, friend.grade+1, friend.path||p.personID
		FROM friend, pkp_symmetric pkp JOIN person p ON pkp.personOne = p.personID
		WHERE pkp.personTwo = friend.personID AND NOT p.personID = ANY(friend.path)
	)
SELECT * INTO shortestFriendshipPath
FROM (  SELECT path
		FROM friend f
		WHERE f.personid = person2
		ORDER BY grade ASC) AS f;
END; $$
LANGUAGE plpgsql;