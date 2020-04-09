package ee.gamesys.testCase;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ee.gamesys.testCase.requests.RequestRunner;

@SpringBootApplication
public class TestCaseApplication implements CommandLineRunner {
	
	public static final long requestDelay = 5;
	public static final String requestUrl = "https://api.exmo.com/v1/trades/?pair=BTC_USD";
	public static final int resultSize = 4;
	public static final String dbUrl = "jdbc:h2:mem:testdb";
	
	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

	private RequestRunner runner;
	
	@Autowired
	public TestCaseApplication(RequestRunner runner) {
		this.runner = runner;
	}

	public static void main(String[] args) {
		SpringApplication.run(TestCaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		scheduler.scheduleAtFixedRate(runner, 0, requestDelay, TimeUnit.SECONDS);
	}

}
