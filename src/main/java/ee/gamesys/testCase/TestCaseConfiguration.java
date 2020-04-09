package ee.gamesys.testCase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TestCaseConfiguration {
	
	@Bean
	@Scope("prototype")
	public Connection getConnectionBean() throws SQLException {
		return DriverManager.getConnection(TestCaseApplication.dbUrl, "sa", "");
	}

}
