package ee.gamesys.testCase;

import static org.junit.Assert.*;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ee.gamesys.testCase.models.JapBar;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestCaseApplicationTests {
	
	@LocalServerPort
	private int port;
	
	private static String domen;
	private static String endPoint;
	private static HttpClient client;
	private static HttpRequest request;
	
	@Before 
	public void setUp() { 
		domen = "http://localhost:"+port; 
		endPoint = "/japbars";
		
		client = HttpClient.newHttpClient();
		request = HttpRequest.newBuilder()
			      .uri(URI.create(domen + endPoint))
			      .build();
		
	}

		@Test
		public void fixedSizeListAndLastFromBase() {
			
			try {
				Thread.sleep(TestCaseApplication.requestDelay * (TestCaseApplication.resultSize+2) * 1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			HttpResponse<String> httpResponse = null;
			try {
				httpResponse = client.send(request, BodyHandlers.ofString());
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
				fail();
			}
			
			assertEquals(HttpStatus.OK.value(), httpResponse.statusCode());
			
			ObjectMapper mapper = new ObjectMapper();
			JapBar[] list1 = new JapBar[0];
			try {
				list1 = mapper.readValue(httpResponse.body(), new TypeReference<JapBar[]>() {});
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				fail();
			}
			
			assertEquals(TestCaseApplication.resultSize, list1.length);

			try {
				Thread.sleep(TestCaseApplication.requestDelay * 2 * 1000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			try {
				httpResponse = client.send(request, BodyHandlers.ofString());
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
				fail();
			}
			
			JapBar[] list2 = new JapBar[0];
			try {
				list2 = mapper.readValue(httpResponse.body(), new TypeReference<JapBar[]>() {});
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				fail();
			}
			
			assertEquals(TestCaseApplication.resultSize, list2.length);
			
			assertTrue(!list2[0].equals(list1[0]));						//  list2 Not equals list1
			
			assertTrue(list2[0].getTime() > list1[0].getTime());		//  time of list 2 more then list1, list2 contains last information
		}

}
