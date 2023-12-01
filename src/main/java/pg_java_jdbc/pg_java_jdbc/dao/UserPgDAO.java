package pg_java_jdbc.pg_java_jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import pg_java_jdbc.pg_java_jdbc.connection.SingleConnection;
import pg_java_jdbc.pg_java_jdbc.model.UserPg;

public class UserPgDAO {
	
	private Connection connection;
	
	public UserPgDAO() {
		connection = SingleConnection.getConnection();
	}
	
	public void save(UserPg user) {
		try {
			String sql = "INSERT INTO user_pg (id, name, email) VALUES (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, user.getId());
			statement.setString(2, user.getName());
			statement.setString(3, user.getEmail());
			statement.execute();
			connection.commit(); // salva no banco
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
