package pg_java_jdbc.pg_java_jdbc;

import java.util.List;

import org.junit.Test;

import pg_java_jdbc.pg_java_jdbc.dao.UserPgDAO;
import pg_java_jdbc.pg_java_jdbc.model.Telephone;
import pg_java_jdbc.pg_java_jdbc.model.UserPg;
import pg_java_jdbc.pg_java_jdbc.model.UserPhone;

public class TestJdbc {

	@Test
	public void initBase() {
		UserPgDAO dao = new UserPgDAO();
		UserPg user = new UserPg();
		
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
	
	@Test
	public void initDelete() {
		
		try {
			UserPgDAO dao = new UserPgDAO();
			dao.delete(2L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initAssignTelephone() {
		UserPgDAO dao = new UserPgDAO();
		Telephone tel = new Telephone();
		tel.setNumber("(99) 9999-9999");
		tel.setType("Home");
		tel.setUserId(2L);
		
		dao.assignTelephone(tel);
	}
	
	@Test
	public void initListUserPhone() {
		
		UserPgDAO dao = new UserPgDAO();
		
		List<UserPhone> list = dao.listUserPhone(1L);
		
		list.forEach(System.out::println);
	}
	
	@Test
	public void initDeleteTelephone() {
		
		UserPgDAO dao = new UserPgDAO();
		
		dao.deleteTelephone(1L);
	}
}
