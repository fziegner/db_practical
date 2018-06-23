package de.unileipzig.dbs.aufgabe2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {

	public static PreparedStatement STMT_ALL_EMPLOYEES;
	public static PreparedStatement STMT_ALL_PROJECTS;
	public static PreparedStatement STMT_PROJECT_LEAD;
	public static PreparedStatement STMT_PROJECT_MEMBERS;
	public static PreparedStatement STMT_EMPLOYEE_PROJECTS;
	public static PreparedStatement STMT_INSERT_EMPLOYEE;
	public static PreparedStatement STMT_INSERT_PROJECT;
	public static PreparedStatement STMT_INSERT_EMPLOYEE_PROJECT;
	public static PreparedStatement STMT_DELETE_EMPLOYEE;
	public static PreparedStatement STMT_DELETE_PROJECT;

	public static Connection getConnection(String url, String user,
			String password) throws ClassNotFoundException, SQLException {
		// load the driver
		Class.forName("com.ibm.db2.jcc.DB2Driver");
		System.out.println("**** JDBC Driver loaded *****");

		// open connection
		System.out.println("**** Trying to connect to " + url + " ****");
		Connection con = DriverManager.getConnection(url, user, password);
		con.setAutoCommit(false);
		System.out.println("**** Connection established ****");

		// prepare statements
		prepareStatements(con);
		System.out.println("**** Statements prepared ****");

		return con;
	}

	private static void prepareStatements(Connection con) throws SQLException {
		STMT_ALL_EMPLOYEES = con.prepareStatement(Constants.Q_ALL_EMPLOYEES);
		STMT_ALL_PROJECTS = con.prepareStatement(Constants.Q_ALL_PROJECTS);
		STMT_PROJECT_LEAD = con.prepareStatement(Constants.Q_PROJECT_LEAD);
		STMT_PROJECT_MEMBERS = con
				.prepareStatement(Constants.Q_PROJECT_MEMBERS);
		STMT_EMPLOYEE_PROJECTS = con
				.prepareStatement(Constants.Q_EMPLOYEE_PROJECTS);
		STMT_INSERT_EMPLOYEE = con
				.prepareStatement(Constants.Q_INSERT_EMPLOYEE);
		STMT_INSERT_PROJECT = con.prepareStatement(Constants.Q_INSERT_PROJECT);
		STMT_INSERT_EMPLOYEE_PROJECT = con
				.prepareStatement(Constants.Q_INSERT_EMPLOYEE_PROJECT);
		STMT_DELETE_EMPLOYEE = con
				.prepareStatement(Constants.Q_DELETE_EMPLOYEE);
		STMT_DELETE_PROJECT = con.prepareStatement(Constants.Q_DELETE_PROJECT);
	}

	public static ResultSet executeQuery(Connection con, PreparedStatement stmt)
			throws SQLException {
		// execute query and get a ResultSet
		ResultSet rs = stmt.executeQuery();
		System.out.println("**** Executed query ****");
		return rs;
	}

	public static void executeUpdate(Connection con, PreparedStatement stmt)
			throws SQLException {
		// execute update query
		System.out.println(String.format("**** Added %d row(s) ****",
				stmt.executeUpdate()));
	}
}
