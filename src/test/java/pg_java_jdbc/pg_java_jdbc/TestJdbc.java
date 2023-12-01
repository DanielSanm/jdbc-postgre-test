package pg_java_jdbc.pg_java_jdbc;

import java.util.List;

import org.junit.Test;

import pg_java_jdbc.pg_java_jdbc.dao.UserPgDAO;
import pg_java_jdbc.pg_java_jdbc.model.UserPg;

public class TestJdbc {

	@Test
	public void initBase() {
		UserPgDAO dao = new UserPgDAO();
		UserPg user = new UserPg();
		
		user.setId(5L);
		user.setName("test");
		user.setEmail("test@email.com");
		
		dao.save(user);
	}
	
	@Test
	public void initList() {
		UserPgDAO dao = new UserPgDAO();
		try {
			List<UserPg> list = dao.list();
			list.forEach(System.out::println);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initSearch() {
		UserPgDAO dao = new UserPgDAO();
		try {
			UserPg user = dao.search(1L);
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initUpdate() {
		try {
			
			UserPgDAO dao = new UserPgDAO();
			UserPg result = dao.search(4L);
			
			result.setName("Nome atualizado com método update");
			dao.update(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
