package ee.gamesys.testCase.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ee.gamesys.testCase.models.JapBar;

@Repository
public class BarRepository {
	
	private Logger log = Logger.getLogger(BarRepository.class.getName());
	
	private Connection connection;

	@Autowired
	public BarRepository(Connection connection) {
		this.connection = connection;
	}

	public ResultSet getJapBars(int resultSize) throws SQLException {
		String selectSql = "SELECT * FROM japbars ORDER by time DESC LIMIT "+resultSize;
		Statement statement = connection.createStatement();
		
		ResultSet result;
		synchronized (this) {
			result = statement.executeQuery(selectSql);
		}
		
		return result;
	}
	
	public void insertJapBar(JapBar japBar) {
		String insertSql = "INSERT INTO japbars VALUES (?,?,?,?,?,?)";
		
		try (PreparedStatement statement = connection.prepareStatement(insertSql))
		{
			statement.setLong(1, japBar.getTime());
			statement.setBigDecimal(2, japBar.getOpenPrice());
			statement.setBigDecimal(3, japBar.getClosePrice());
			statement.setBigDecimal(4, japBar.getMinPrice());
			statement.setBigDecimal(5, japBar.getMaxPrice());
			statement.setBigDecimal(6, japBar.getVolume());
			
			synchronized (this) {
				statement.executeUpdate();
			}
			
		} catch (SQLException e) {
			log.log(Level.INFO, "SQL problem", e);
		}
	}

	@PostConstruct
	public void createDB() {
		try (Statement statement = connection.createStatement())
		{
			String tableSql = "CREATE TABLE IF NOT EXISTS japbars ("
					  + "time bigint not null PRIMARY KEY,"
					  + "openPrice decimal(14,7) not null,"
					  + "closePrice decimal(14,7) not null,"
					  + "minPrice decimal(14,7) not null,"
					  + "maxPrice decimal(14,7) not null,"
					  + "volume decimal(14,7) not null"
					  + ")";
			statement.execute(tableSql);
			System.out.println("TABLE japbars created successfuly");
		} catch (SQLException e) {
			log.log(Level.INFO, "SQL problem", e);
		}

	}

}
