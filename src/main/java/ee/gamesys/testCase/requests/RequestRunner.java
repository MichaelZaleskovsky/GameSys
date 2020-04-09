package ee.gamesys.testCase.requests;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ee.gamesys.testCase.TestCaseApplication;
import ee.gamesys.testCase.models.InputResponse;
import ee.gamesys.testCase.models.JapBar;
import ee.gamesys.testCase.repositories.BarRepository;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RequestRunner implements Runnable {

	private Logger log = Logger.getLogger(RequestRunner.class.getName());
	
	private BarRepository repository;
	
	@Autowired
	public RequestRunner(BarRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run() {
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
			      .uri(URI.create(TestCaseApplication.requestUrl))
			      .build();
		
		HttpResponse<String> httpResponse;
		try {
			httpResponse = client.send(request, BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			log.log(Level.INFO, "Connection error", e);
			return;
		}
		
		if (httpResponse.statusCode() > 299) {
			log.log(Level.INFO, httpResponse.body());
			return;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		JapBar japBar = new JapBar();
		try {
			InputResponse inputResponse = mapper.readValue(httpResponse.body(), InputResponse.class);
			List<InputResponse.Input> inputList = inputResponse.getTool();
			
			japBar = inputList.stream()
					.reduce(new JapBar(),
							(bar, input) -> {
								bar.setMinPrice(bar.getMinPrice().min(input.getPrice()));
								bar.setMaxPrice(bar.getMaxPrice().max(input.getPrice()));
								bar.setVolume(bar.getVolume().add(input.getPrice()));
								return bar;
							}, 
							(bar1, bar2) -> bar1);
			
			japBar.setOpenPrice(inputList.get(0).getPrice());
			japBar.setClosePrice(inputList.get(inputList.size()-1).getPrice());
			japBar.setTime(System.currentTimeMillis());
			
		} catch (JsonProcessingException e) {
			log.log(Level.INFO, "Wrong response format", e);
			return;
		}
		
		repository.insertJapBar(japBar);
		
	}

}
