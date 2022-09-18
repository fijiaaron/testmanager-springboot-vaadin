package org.testrunner.testmanager.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.testrunner.testmanager.model.TestCase;
import org.testrunner.testmanager.service.TestCaseService;

import java.util.List;

@RestController
public class TestCaseController
{
//	@Autowired
	TestCaseService service = new TestCaseService();

	@GetMapping("/testcases")
	public ResponseEntity<List<TestCase>> list()
	{
		List<TestCase> testCases =  service.findAll();
		return ResponseEntity.ok(testCases);
	}

	@GetMapping("/testcase") // ?name=caseInsensitiveMatcher
	public ResponseEntity<List<TestCase>> findTestCasesByName(@RequestParam String name)
	{
		List<TestCase> testCases = service.findByName(name);

		if (testCases.size() == 0)
		{
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(testCases);
	}


	@GetMapping("/testcase/{id}")
	public ResponseEntity<TestCase> getTestCase(@PathVariable("id") long id)
	{
		try
		{
			TestCase testCase = service.findById(id);
			return ResponseEntity.ok(testCase);
		}
		catch (TestCaseService.TestCaseNotFound e)
		{
			return ResponseEntity.notFound().build();
		}
		catch (IllegalStateException e)
		{
			return ResponseEntity.unprocessableEntity().build();
		}
	}
}
