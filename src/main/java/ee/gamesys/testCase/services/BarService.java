package ee.gamesys.testCase.services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ee.gamesys.testCase.TestCaseApplication;
import ee.gamesys.testCase.models.JapBar;
import ee.gamesys.testCase.repositories.BarRepository;
import ee.gamesys.testCase.requests.RequestRunner;

@Service
public class BarService {
	
	private Logger log = Logger.getLogger(RequestRunner.class.getName());
	
	private BarRepository repository;

	@Autowired
	public BarService(BarRepository repository) {
		this.repository = repository;
	}

	public List<JapBar> getJapBars() {
		List<JapBar> result = new ArrayList<>();
		try {
			ResultSet sqlResult = repository.getJapBars(TestCaseApplication.resultSize);
			JapBar bar;
			while (sqlResult.next()) {
				bar = new JapBar();
				bar.setClosePrice(sqlResult.getBigDecimal("closeprice"));
				bar.setOpenPrice(sqlResult.getBigDecimal("openprice"));
				bar.setMaxPrice(sqlResult.getBigDecimal("maxprice"));
				bar.setMinPrice(sqlResult.getBigDecimal("minprice"));
				bar.setVolume(sqlResult.getBigDecimal("volume"));
				bar.setTime(sqlResult.getLong("time"));
				result.add(bar);
			}
		} catch (SQLException e) {
			log.log(Level.INFO, "SQL error", e);
		}
		
		return result;
	}

}
