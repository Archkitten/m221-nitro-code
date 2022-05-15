/*
 * database.java is used for accessing and storing the connection to 
 * local database using java.sql (sqlite3)
 * this file does not handle api 
 */

package com.nitrocode.db;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.io.File;

import com.nitrocode.db.DBExpection;

public class Database {

	private static final String databasePath = "test.db";

	public static Connection conn;

	public static void init() {
		boolean db_exists = new File(databasePath).isFile();

		// register the driver
		try {
			String sDriverName = "org.sqlite.JDBC";
			Class.forName(sDriverName);
		} catch(ClassNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		}

		String sJdbc = "jdbc:sqlite";
		String sDbUrl = sJdbc + ":" + databasePath;

		// create a database connection
		try {
			conn = DriverManager.getConnection(sDbUrl);
		} catch (Exception e) {
			//TODO: handle exception
		}

		// check if database exists
		// if exists then do not initialize new table
		// if not then create new table with unique ID, name, and password
		if (!db_exists) {
			try {
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(
				  "CREATE TABLE user_profiles ("
				+ "		id INTEGER PRIMARY KEY,"
				+ "		username UNIQUE TEXT,"
				+ "		role TEXT,"
				+ "		nickname TEXT,"
				+ "		password TEXT"
				+ ");");
				stmt.close();
			} catch (SQLException e) {
				System.err.println("Error creating table: " + e.getMessage());
			}
		}
	}

		// potentially vunleriable to sql injection attack
	public static String get(String table, String username, String column) throws DBExpection {
		String query = "SELECT " + column + " FROM " + table 
						+ " WHERE username = '" + username + "';";

		String result = "";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
			result = stmt.getResultSet().getString(column);
			stmt.close();
		} catch (SQLException e) {
			throw new DBExpection("Error getting data: " + e.getMessage());
		}

		if(result.isEmpty()) {
			throw new DBExpection("Error getting data: " + username + " not found");
		}

		return result;
	}

	public static void addUser(String username, 
								String password, 
								String role, 
								String nickname) 
								throws DBExpection {
		String query = "INSERT INTO user_profiles" 
							+ "(username, password, role, nickname)"
							+ "VALUES ('" 
								+ username + "', '" 
								+ password + "', '" 
								+ role + "', '" 
								+ nickname 
							+ "');";

		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			System.err.println("Error adding user: " + e.getMessage());
			throw new DBExpection("Error adding user: " + e.getMessage());
		}
	}

	public static void setUserPassword(String username, String password) throws DBExpection {
		String query = "UPDATE user_profiles SET password = '" 
							+ password 
							+ "' WHERE username = '" + username + "';";

		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
		} catch(SQLException e) {
			throw new DBExpection("Error setting password: " + e.getMessage());
		}
	}

	public static void runTests() {
		init();
	}
}
