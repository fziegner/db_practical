package de.unileipzig.dbs.aufgabe2;

import static java.lang.System.out;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Aufgabe2 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("\n");
		Connection con = null;
		int selection = 1;

		try {
			// syntax for a URL for Universal Type 4 connectivity
			// jdbc:db2://host:port/database
			String url = String.format("%s//%s:%s/%s", Constants.URL_PREFIX,
					Constants.HOST, Constants.PORT, Constants.DB);

			// open connection
			con = DBManager.getConnection(url, Constants.USER,
					Constants.PASSWORD);

			while (selection != 0) {
				out.println("Options:");
				out.println("[1] List all employees");
				out.println("[2] List all projects");
				out.println("[3] Show project lead");
				out.println("[4] Show project members");
				out.println("[5] List member's projects");
				out.println("[6] Add new employee");
				out.println("[7] Add new project");
				out.println("[8] Add employee to project");
				out.println("[9] Delete employee");
				out.println("[10] Delete project");
				out.println("[0] Quit");
				selection = scanner.nextInt();

				switch (selection) {
				case 1:
					queryAllEmployees(con);
					break;
				case 2:
					queryAllProjects(con);
					break;
				case 3:
					queryProjectLead(con, scanner);
					break;
				case 4:
					queryProjectMembers(con, scanner);
					break;
				case 5:
					queryEmployeesProjects(con, scanner);
					break;
				case 6:
					insertEmployee(con, scanner);
					break;
				case 7:
					insertProject(con, scanner);
					break;
				case 8:
					insertEmployeeProject(con, scanner);
					break;
				case 9:
					deleteEmployee(con, scanner);
					break;
				case 10:
					deleteProject(con, scanner);
					break;
				}
				con.commit();
			}
		} catch (SQLException sqlEx) {
			if (con != null) {
				try {
					con.rollback();
				} catch (SQLException rbEx) {
					System.err.println(rbEx);
				}
			}
		} catch (Exception ex) {
			System.err.println(ex);
		} finally {
			// cleanup
			if (con != null) {
				try {
					con.close();
					out.println("**** Connection closed ****");
				} catch (SQLException sqlEx) {
					//catchInputException();
					System.err.println(sqlEx);
				}
			}
			scanner.close();
		}
	}

	// query (read) methods

	private static void queryAllEmployees(Connection con) throws SQLException {
		printEmployees(DBManager
				.executeQuery(con, DBManager.STMT_ALL_EMPLOYEES));
	}

	private static void queryAllProjects(Connection con) throws SQLException {
		printProjects(DBManager.executeQuery(con, DBManager.STMT_ALL_PROJECTS));
	}

	private static void queryProjectLead(Connection con, Scanner scanner)
			throws SQLException {
		int projID;
		// get a list of all projects for selection
		queryAllProjects(con);
		out.println("Enter projID:");
		projID = scanner.nextInt();
		// initialize the statement
		DBManager.STMT_PROJECT_LEAD.setInt(1, projID);
		printEmployees(DBManager.executeQuery(con, DBManager.STMT_PROJECT_LEAD));
	}

	private static void queryProjectMembers(Connection con, Scanner scanner)
			throws SQLException {
		throw new UnsupportedOperationException("Implement me!");
	}

	private static void queryEmployeesProjects(Connection con, Scanner scanner)
			throws SQLException {
		int empID;
		// get a list of all employees for selection
		queryAllEmployees(con);
		out.println("Enter empID:");
		empID = scanner.nextInt();
		// initialize the statement
		DBManager.STMT_EMPLOYEE_PROJECTS.setInt(1, empID);
		printProjects(DBManager.executeQuery(con,
				DBManager.STMT_EMPLOYEE_PROJECTS));
	}

	// write methods

	private static void insertEmployee(Connection con, Scanner scanner)
			throws SQLException {
				
		String name;
		String address;
		String zipcode;
		String city;
		
		out.println("Name:");
		name = scanner.next();
		out.println("Address:");
		address = scanner.next();
		out.println("Zipcode:");
		zipcode = scanner.next();
		out.println("City:");
		city = scanner.next();
		
		DBManager.STMT_INSERT_EMPLOYEE.setString(1, name);
		DBManager.STMT_INSERT_EMPLOYEE.setString(2, address);
		DBManager.STMT_INSERT_EMPLOYEE.setString(3, zipcode);
		DBManager.STMT_INSERT_EMPLOYEE.setString(4, city);
		
		DBManager.executeUpdate(con, DBManager.STMT_INSERT_EMPLOYEE);
	}

	private static void insertProject(Connection con, Scanner scanner)
			throws SQLException {
		String name;
		int leadEmpID;

		out.println("Name:");
		name = scanner.next();
		queryAllEmployees(con);
		out.println("Project-Lead (empID):");
		leadEmpID = scanner.nextInt();

		DBManager.STMT_INSERT_PROJECT.setString(1, name);
		DBManager.STMT_INSERT_PROJECT.setInt(2, leadEmpID);

		DBManager.executeUpdate(con, DBManager.STMT_INSERT_PROJECT);
	}

	private static void insertEmployeeProject(Connection con, Scanner scanner)
			throws SQLException {
		
		int empID;
		int projID;
		
		queryAllEmployees(con);
		out.println("EmployeeID:");
		empID = scanner.nextInt();
		queryAllProjects(con);
		out.println("ProjectID:");
		projID = scanner.nextInt();
		
		DBManager.STMT_INSERT_EMPLOYEE_PROJECT.setInt(1, empID);
		DBManager.STMT_INSERT_EMPLOYEE_PROJECT.setInt(2, projID);
		
		DBManager.executeUpdate(con, DBManager.STMT_INSERT_EMPLOYEE_PROJECT);
		
	}

	private static void deleteEmployee(Connection con, Scanner scanner)
			throws SQLException {
		int empID;

		queryAllEmployees(con);
		out.println("Employee-ID (empID):");
		empID = scanner.nextInt();

		DBManager.STMT_DELETE_EMPLOYEE.setInt(1, empID);

		DBManager.executeUpdate(con, DBManager.STMT_DELETE_EMPLOYEE);
	}

	private static void deleteProject(Connection con, Scanner scanner)
			throws SQLException {
		throw new UnsupportedOperationException("Implement me!");
	}

	// printing methods

	private static void printEmployees(ResultSet rs) throws SQLException {
		out.println("(empID), name, address, zipcode, city");
		out.println("-------------------------------------");
		while (rs.next()) {
			out.println(String.format("(%d), %s, %s, %s, %s", rs.getInt(1),
					rs.getString(2), rs.getString(3), rs.getString(4),
					rs.getString(5)));
		}
		rs.close();
	}

	private static void printProjects(ResultSet rs) throws SQLException {
		out.println("(projID), name, lead");
		out.println("--------------------");
		while (rs.next()) {
			out.println(String.format("(%d), %s, %s", rs.getInt(1),
					rs.getString(2), rs.getInt(3)));
		}
		rs.close();
			
	}
	
	/*private static void catchInputException() {
		out.println("Fehlerhafte Eingabe! Falscher Datentyp oder Leeres Eingabefeld!");
	}*/
}