package pg_java_jdbc.pg_java_jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pg_java_jdbc.pg_java_jdbc.connection.SingleConnection;
import pg_java_jdbc.pg_java_jdbc.model.UserPg;

public class UserPgDAO {

	private Connection connection;

	public UserPgDAO() {
		connection = SingleConnection.getConnection();
	}

	public void save(UserPg user) {
		try {
			String sql = "INSERT INTO user_pg (name, email) VALUES (?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.execute();
			connection.commit(); // salva no banco
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<UserPg> list() throws Exception {
		List<UserPg> list = new ArrayList<>();

		String sql = "SELECT * FROM user_pg";

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		while (result.next()) {
			UserPg user = new UserPg();
			user.setId(result.getLong("id"));
			user.setName(result.getString("name"));
			user.setEmail(result.getString("email"));

			list.add(user);
		}

		return list;
	}

	public UserPg search(Long id) throws Exception {
		UserPg user = new UserPg();

		String sql = "SELECT * FROM user_pg WHERE id = " + id;

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet result = statement.executeQuery();

		while (result.next()) {
			user.setId(result.getLong("id"));
			user.setName(result.getString("name"));
			user.setEmail(result.getString("email"));

		}

		return user;
	}

	public void update(UserPg user) {

		try {
			String sql = "UPDATE user_pg SET name = ? WHERE id = " + user.getId();
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getName());
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}

	}
	
	public void delete(Long id) {
		try {
			String sql = "DELETE FROM user_pg WHERE id = " + id;
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
	}
}
