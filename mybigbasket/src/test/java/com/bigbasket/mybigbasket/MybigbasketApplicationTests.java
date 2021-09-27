package com.bigbasket.mybigbasket;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MybigbasketApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	public void checkRestTemplate() throws Exception {
		assertThat(testTemplate).isNotNull();
	}

	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		assertThat(this.testTemplate.getForObject("http://localhost:" + port + "/all",
				String.class)).contains("temp");
	}

	@Test
	public void checkUserExistance() throws Exception {
		assertThat(this.testTemplate.getForObject("http://localhost:" + port + "/getUserId?username=temp", int.class)).isEqualTo(3);
	}

	@Test
	public void testUserPermission() throws Exception {
		assertThat(this.testTemplate.getForObject("http://localhost:" + port + "/getUserPermission?username=temp", boolean.class)).isTrue();
	}

	@Test
	public void testUserStatusChange() throws Exception {
		assertThat(this.testTemplate.getForObject("http://localhost:" + port + "/setAdmin/3", String.class)).contains("Admin status set");
	}

	@Test
	public void testUserAdminStatusChange() throws Exception {
		assertThat(this.testTemplate.getForObject("http://localhost:" + port + "/revokeAdmin/3", String.class)).contains("Admin status revoked");
	}

	@Test
	public void authenticate() throws Exception {
		assertThat(this.testTemplate.getForObject("http://localhost:" + port + "/authenticate?username=temp&password=temp", boolean.class)).isTrue();
	}

	@Test
	public void addUser() throws Exception {
		assertThat(this.testTemplate.getForObject("http://localhost:" + port + "/add?name=?mew1&username=new1&password=temp", String.class)).contains("A NEW USER HAS BEEN ADDED TO THE DB");
	}

}
