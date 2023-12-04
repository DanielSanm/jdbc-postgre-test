package pg_java_jdbc.pg_java_jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pg_java_jdbc.pg_java_jdbc.connection.SingleConnection;
import pg_java_jdbc.pg_java_jdbc.model.UserPhone;
import pg_java_jdbc.pg_java_jdbc.model.Telephone;
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

	public void assignTelephone(Telephone tel) {
		try {
			String sql = "INSERT INTO telephone (number, type, user_id) VALUES (?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, tel.getNumber());
			statement.setString(2, tel.getType());
			statement.setLong(3, tel.getUserId());
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

	public List<UserPhone> listUserPhone(Long idUser) {

		List<UserPhone> list = new ArrayList<>();

		String sql = "SELECT name, email, number FROM telephone AS tel ";
		sql += " INNER JOIN user_pg AS _user ";
		sql += " ON tel.user_id = _user.id ";
		sql += " WHERE _user.id = " + idUser;

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				UserPhone user = new UserPhone();
				user.setName(result.getString("name"));
				user.setEmail(result.getString("email"));
				user.setNumber(result.getString("number"));
				list.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
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
	
	public void deleteTelephone(Long idUser) {
		
		String sqlTel = "DELETE FROM telephone WHERE user_id = " + idUser;
		String sqlUser = "DELETE FROM user_pg WHERE id = " + idUser;
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlTel);
			statement.executeUpdate();
			connection.commit();
			
			statement = connection.prepareStatement(sqlUser);
			statement.executeUpdate();
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
