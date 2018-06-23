package de.unileipzig.dbs.aufgabe2;

public class Constants {

	// Connection properties
	public static final String URL_PREFIX = "jdbc:db2:";
	public static final String HOST = "127.0.0.1";
	public static final String PORT = "50000";
	public static final String USER = "dbs2";
	public static final String PASSWORD = "dbs2";
	public static final String DB = "aufgabe2";

	// Queries
	public static final String Q_ALL_EMPLOYEES = "SELECT * FROM Employee";
	public static final String Q_ALL_PROJECTS = "SELECT * FROM Project";
	public static final String Q_PROJECT_LEAD = "SELECT e.* FROM Employee e, Project p WHERE p.lead = e.empID AND p.projID = ?";
	public static final String Q_PROJECT_MEMBERS = "SELECT * FROM EmployeeProject WHERE projID = ?";
	public static final String Q_EMPLOYEE_PROJECTS = "SELECT p.* FROM Project p JOIN EmployeeProject ep ON p.projID = ep.projID JOIN Employee e ON ep.empID = e.empID WHERE e.empID = ?";
	public static final String Q_INSERT_EMPLOYEE = "INSERT INTO  Employee (name, address, zipcode, city) VALUES (?, ?, ?, ?)";
	public static final String Q_INSERT_PROJECT = "INSERT INTO Project (name, lead) VALUES (?, ?)";
	public static final String Q_INSERT_EMPLOYEE_PROJECT = "INSERT INTO EmployeeProject (empID, projID) VALUES (?, ?)";
	public static final String Q_DELETE_EMPLOYEE = "DELETE FROM Employee WHERE empID = ?";
	public static final String Q_DELETE_PROJECT = "DELETE FROM Project WHERE projID = ?";

}
