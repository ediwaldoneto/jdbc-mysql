package br.com.nt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;

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
		} catch (IOException e) {

			e.printStackTrace();
		}

		log.info("Connecting to the database");
		try {
			Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
			log.info("Database connection test: " + connection.getCatalog());
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
