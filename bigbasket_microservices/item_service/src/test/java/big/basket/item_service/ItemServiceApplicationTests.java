package big.basket.item_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isNull;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ItemServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	public void checkCatalog() throws Exception {
		assertThat(this.testTemplate.getForObject("http://localhost:" + port + "/showCatalog", String.class)).contains("Milk");
	}

	@Test
	public void additem() throws Exception {
		assertThat(this.testTemplate.getForObject("http://localhost:" + port + "/addItem?productID=888&productName=ToothBrush&productPrice=50", String.class)).contains("Item successfully added with productID");
	}

	@Test
	public void deleteItem() throws Exception {
		this.testTemplate.delete("http://localhost:" + port + "/deleteItem/888");
	}
}
