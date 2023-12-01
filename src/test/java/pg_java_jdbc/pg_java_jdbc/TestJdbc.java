package pg_java_jdbc.pg_java_jdbc;

import org.junit.Test;

import pg_java_jdbc.pg_java_jdbc.connection.SingleConnection;
import pg_java_jdbc.pg_java_jdbc.dao.UserPgDAO;
import pg_java_jdbc.pg_java_jdbc.model.UserPg;

public class TestJdbc {

	@Test
	public void initBase() {
		UserPgDAO userDAO = new UserPgDAO();
		UserPg user = new UserPg();
		
		user.setId(4L);
		user.setName("test");
		user.setEmail("test@email.com");
		
		userDAO.save(user);
	}
}
