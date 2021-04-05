package br.com.nt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import br.com.nt.client.DemoClient;
import br.com.nt.model.ModelApplication;
import br.com.nt.utils.Util;

public class DemoApplication {

	public static void main(String[] args) {

		Util.infoLog("Loading application properties");
		Properties properties = new Properties();

		try {
			properties.load(DemoApplication.class.getClassLoader().getResourceAsStream("application.properties"));

			Util.infoLog("Connecting to the database");

			Connection connection = DriverManager.getConnection(properties.getProperty("url"), properties);
			Util.infoLog("Database connection test: " + connection.getCatalog());

			Util.infoLog("Loading database schema");

			Scanner scanner = new Scanner(DemoApplication.class.getClassLoader().getResourceAsStream("schema.sql"));
			Statement statement = connection.createStatement();

			while (scanner.hasNext()) {
				statement.execute(scanner.nextLine());

			}

			Util.infoLog("Create database schema");

			ModelApplication md = new ModelApplication(1L, "configuration",
					"congratulations, you have set up JDBC correctly!", true);

			DemoClient.insertData(md, connection);

			
			DemoClient.readData(connection);
						
			md.setDetails("congratulations, you have updated data!");
			
			DemoClient.updateData(md, connection);
			
			DemoClient.deleteData(md, connection);

			connection.close();
			AbandonedConnectionCleanupThread.uncheckedShutdown();

			Util.infoLog("Closing database connection");

		} catch (IOException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
