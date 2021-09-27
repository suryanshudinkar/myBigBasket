package big.basket.order_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class OrderServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	public void testGetAll() throws Exception {
		assertThat(this.testTemplate.getForObject("http://localhost:" + port + "/getAll", String.class)).contains("1,2");
	}

	@Test
	public void testAddOrder() throws Exception {
		assertThat(this.testTemplate.getForObject("http://localhost:" + port + "/addOrder?uid=3&order=1,2", String.class)).contains("YOUR ORDER HAS BEEN PLACED");	
	}

	@Test
	public void testUserOrders() throws Exception {
		assertThat(this.testTemplate.getForObject("http://localhost:" + port + "/showOrders/3", String.class)).contains("1,2");	
	}
	
	@Test
	public void testBillCalculation() throws Exception {
		assertThat(this.testTemplate.getForObject("http://localhost:" + port + "/totalBill/2", int.class)).isEqualTo(95);	
	}

}
