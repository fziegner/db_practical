--Query 1
CREATE TYPE PunktT AS(
	x_koordinate INTEGER,
 	y_koordinate INTEGER,
	punkt_name VARCHAR(1024)
) INSTANTIABLE NOT FINAL MODE DB2SQL;

--Query 2
CREATE TABLE PunktTab OF PunktT
	(REF IS Oid USER GENERATED);

--Query 3	
INSERT INTO PunktTab(Oid, x_koordinate, y_koordinate, punkt_name)
	VALUES(PunktT('1'), 1, 1, 'A');
INSERT INTO PunktTab(Oid, x_koordinate, y_koordinate, punkt_name)
	VALUES(PunktT('2'), 2, 2, 'B');
INSERT INTO PunktTab(Oid, x_koordinate, y_koordinate, punkt_name)
	VALUES(PunktT('3'), 3, 2, 'C');
INSERT INTO PunktTab(Oid, x_koordinate, y_koordinate, punkt_name)
	VALUES(PunktT('4'), 2, 1, 'D');
INSERT INTO PunktTab(Oid, x_koordinate, y_koordinate, punkt_name)
	VALUES(PunktT('5'), 3, 1, 'E');

--Query 6
CREATE TYPE KanteT AS(
	von_punkt REF(PunktT),
	nach_punkt REF(PunktT)
) INSTANTIABLE NOT FINAL MODE DB2SQL;

--Query 7
CREATE TABLE KanteTab OF KanteT
	(REF IS kid SYSTEM GENERATED)