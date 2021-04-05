package br.com.nt.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.nt.model.ModelApplication;
import br.com.nt.utils.Util;

public class DemoClient {

	public static void insertData(ModelApplication application, Connection connection) throws SQLException {

		Util.infoLog("Insert Data");
		PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO app (id, description, details, done) VALUES (?, ?, ?, ?);");

		preparedStatement.setLong(1, application.getId());
		preparedStatement.setString(2, application.getDescription());
		preparedStatement.setString(3, application.getDetails());
		preparedStatement.setBoolean(4, application.isDone());
		Util.infoLog("Data -> " + application.toString());
		preparedStatement.execute();
	}

	public static ModelApplication readData(Connection connection) throws SQLException {

		Util.infoLog("Read Data");
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM app");
		ResultSet resultSet = preparedStatement.executeQuery();
		if (!resultSet.next()) {
			Util.infoLog("There is no data in the database!");
			return null;
		}

		ModelApplication application = new ModelApplication();
		application.setId(resultSet.getLong("id"));
		application.setDescription(resultSet.getString("description"));
		application.setDetails(resultSet.getString("details"));
		application.setDone(resultSet.getBoolean("done"));
		Util.infoLog("Data read from the database: " + application.toString());
		return application;

	}

	public static void updateData(ModelApplication application, Connection connection) throws SQLException {

		Util.infoLog("Update Data");
		PreparedStatement preparedStatement = connection
				.prepareStatement("UPDATE todo SET description = ?, details = ?, done = ? WHERE id = ?;");
		preparedStatement.setLong(1, application.getId());
		preparedStatement.setString(2, application.getDescription());
		preparedStatement.setString(3, application.getDetails());
		preparedStatement.setBoolean(4, application.isDone());
		Util.infoLog("Data update from the database: " + application.toString());
		preparedStatement.execute();

	}

	public static void deleteData(ModelApplication application, Connection connection) throws SQLException {

		Util.infoLog("Delete Data");
		PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM app WHERE id = ?; ");
		preparedStatement.setLong(1, application.getId());
		preparedStatement.executeUpdate();
		readData(connection);
	}
}
