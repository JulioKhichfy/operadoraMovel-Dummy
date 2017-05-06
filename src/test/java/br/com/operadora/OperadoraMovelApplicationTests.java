package br.com.operadora;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class OperadoraMovelApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int localServerPort;

	private final String url = "http://localhost:8888/api/v1/sms";

	static ObjectMapper mapper = new ObjectMapper();

	@Test
	public void contextLoads() {
	}

	@Test
	public void test_sucesso() throws RestClientException, JsonProcessingException {

		String src = "21896963704";
		String msg = "teste";
		String dst = "21896963705";

		List<String> l = new ArrayList<String>();
		l.add(src);
		l.add(msg);
		l.add(dst);

		ResponseEntity<String> response = this.restTemplate.postForEntity(url, mapper.writeValueAsString(l),
				String.class);
		assertThat(response.getBody()).contains("SMS sent");
	}

	@Test
	public void test_celular_nao_encontrado() throws RestClientException, JsonProcessingException {

		String src = "21996963704";
		String msg = "teste";
		String dst = "21996963705";

		List<String> l = new ArrayList<String>();
		l.add(src);
		l.add(msg);
		l.add(dst);

		ResponseEntity<String> response = this.restTemplate.postForEntity(url, mapper.writeValueAsString(l),
				String.class);
		assertThat(response.getBody()).contains("Mobile user not found");
	}

	@Test
	public void test_celular_msg_vazia() throws RestClientException, JsonProcessingException {

		String src = "21896963704";
		String msg = "";
		String dst = "21896963705";

		List<String> l = new ArrayList<String>();
		l.add(src);
		l.add(msg);
		l.add(dst);

		ResponseEntity<String> response = this.restTemplate.postForEntity(url, mapper.writeValueAsString(l),
				String.class);
		assertThat(response.getBody()).contains("Message is empty");
	}

	@Test
	public void test_celular_src_errado() throws RestClientException, JsonProcessingException {

		String src = "2189696370";
		String msg = "teste";
		String dst = "21869637040";

		List<String> l = new ArrayList<String>();
		l.add(src);
		l.add(msg);
		l.add(dst);

		ResponseEntity<String> response = this.restTemplate.postForEntity(url, mapper.writeValueAsString(l),
				String.class);
		assertThat(response.getBody()).contains("Source phone is invalid");
	}

	@Test
	public void test_celular_dst_errado() throws RestClientException, JsonProcessingException {

		String src = "21896963704";
		String msg = "teste";
		String dst = "2189696370";

		List<String> l = new ArrayList<String>();
		l.add(src);
		l.add(msg);
		l.add(dst);

		ResponseEntity<String> response = this.restTemplate.postForEntity(url, mapper.writeValueAsString(l),
				String.class);
		assertThat(response.getBody()).contains("Destination phone is invalid");
	}
	@Test
	public void test_celular_src_vazio() throws RestClientException, JsonProcessingException {

		String src = "";
		String msg = "teste";
		String dst = "21996963704";

		List<String> l = new ArrayList<String>();
		l.add(src);
		l.add(msg);
		l.add(dst);

		ResponseEntity<String> response = this.restTemplate.postForEntity(url, mapper.writeValueAsString(l),
				String.class);
		assertThat(response.getBody()).contains("Source phone is invalid");
	}
	
	/*TODO
	 * Cobrir mais casos de teste...
	 */
}
