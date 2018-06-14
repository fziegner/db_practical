CREATE TABLE former_Worker (
		personid Bigint,
		companyid int,
		workfrom int,
		deletedOn timestamp,
		PRIMARY KEY (personid, companyid),
		FOREIGN KEY (personid) REFERENCES person
				ON DELETE CASCADE
				ON UPDATE CASCADE,
		FOREIGN KEY (companyid) REFERENCES company
				ON DELETE CASCADE
				ON UPDATE CASCADE
);

CREATE FUNCTION archive_WorkAt() RETURNS TRIGGER AS
$BODY$
BEGIN
INSERT INTO former_Worker(personid, companyid, workfrom, deletion)
VALUES(OLD.personid, OLD.company, OLD.workfrom, NOW());
RETURN OLD;
END;
$BODY$ language plpgsql;

CREATE TRIGGER end_of_work
AFTER DELETE ON work_at
FOR EACH ROW
EXECUTE PROCEDURE archive_WorkAT();