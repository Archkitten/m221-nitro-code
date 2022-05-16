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
import java.sql.ResultSet;

import java.io.File;

import com.nitrocode.db.DBExpection;
import com.nitrocode.db.Hashing;

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
				// create a table in sqlite database with unique ID, unique username, a role, a nickname, and a password 
				stmt.executeUpdate(
				  "CREATE TABLE user_profiles ("
				+ "		id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ "		username TEXT,"
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

		System.out.println("fuckfukc");
		String result = "";
		try {
			Statement stmt = conn.createStatement();
			System.out.println("created stmt");
			stmt.executeQuery(query);
			System.out.println("executed query");
			ResultSet results = stmt.getResultSet();
			System.out.println("got results");
			if(results.next())
				result = results.getString(column);

			System.out.println(result);

			stmt.close();
		} catch (SQLException e) {
			throw new DBExpection("Error getting data: " + e.getMessage());
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
								+ Hashing.getHash(password) + "', '" 
								+ role + "', '" 
								+ nickname 
							+ "');";

		try {
            // check user already exists
			if(get("user_profiles", username, "username").isEmpty()) {
				Statement stmt = conn.createStatement();
				stmt.executeUpdate(query);
				stmt.close();
			} else {
				throw new DBExpection("Error adding user: " + username + " already exists");
			}

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
		} catch (SQLException e) {
			throw new DBExpection("Error adding user: " + e.getMessage());
		}
	}

	public static void setUserPassword(String username, String password) throws DBExpection {
		String query = "UPDATE user_profiles SET password = '" 
							+ Hashing.getHash(password)
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
