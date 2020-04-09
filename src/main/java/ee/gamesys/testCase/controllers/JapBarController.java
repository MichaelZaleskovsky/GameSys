package ee.gamesys.testCase.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ee.gamesys.testCase.TestCaseApplication;
import ee.gamesys.testCase.models.JapBar;
import ee.gamesys.testCase.services.BarService;

@RestController
public class JapBarController {
	
	private static final String smallResult = "Not enough data in database";
	
	private BarService barService;
	
	@Autowired
	public JapBarController(BarService barService) {
		this.barService = barService;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping(path = "/japbars")
	public ResponseEntity<List<JapBar>> getJapBar() {
		
		List<JapBar> resultList = barService.getJapBars();
		
		ResponseEntity result = resultList.size() < TestCaseApplication.resultSize ? 
				new ResponseEntity(smallResult, HttpStatus.NOT_ACCEPTABLE) : new ResponseEntity(resultList, HttpStatus.OK);
		return result;
	}

}
