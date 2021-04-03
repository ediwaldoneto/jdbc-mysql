package br.com.nt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

public class DemoApplication {

	private static final Logger log;

	static {
		System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
		log = Logger.getLogger(DemoApplication.class.getName());
	}

	public static void main(String[] args) {

		log.info("Loading application properties");
		Properties properties = new Properties();
		try {
			properties.load(DemoApplication.class.getClassLoader().getResourceAsStream("application.properties"));

			log.info("Connecting to the database");

			Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
			log.info("Database connection test: " + connection.getCatalog());

			log.info("Loading database schema");
			Scanner scanner = new Scanner(DemoApplication.class.getClassLoader().getResourceAsStream("schema.sql"));
			Statement statement = connection.createStatement();
			while (scanner.hasNext()) {
				statement.execute(scanner.nextLine());

			}
			log.info("Create database schema");
			log.info("Closing database connection");
			connection.close();
			AbandonedConnectionCleanupThread.uncheckedShutdown();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
