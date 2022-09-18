package org.testrunner.testmanager.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.testrunner.testmanager.model.TestCase;
import org.testrunner.testmanager.repo.TestCaseRepository;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.*;

//@Service
public class TestCaseService
{
//	@Autowired
	TestCaseRepository repo = new TestCaseRepository();

	public List<TestCase> findAll()
	{
		return repo.testCases;
	}

	public List<TestCase> findRange(int start, int size)
	{
		return repo.testCases.subList(start, start+size);
	}

	public List<TestCase> findByName(String containing)
	{
		List<TestCase> testCases = repo.findAll().stream()
				.filter(testCase -> containsIgnoreCase(testCase.name, containing))
				.collect(Collectors.toList());

		return testCases;
	}


	public TestCase findById(long id)
	{
		List<TestCase> testCases = repo.findAll().stream()
				.filter(testCase -> testCase.id == id)
				.collect(Collectors.toList());

		if (testCases.size() == 0)
		{
			throw new TestCaseNotFound();
		}

		if (testCases.size() < 1)
		{
			throw new IllegalStateException("more than one TestCase found with matching id");
		}


		return testCases.get(0);
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "TestCase not found")
	public class TestCaseNotFound extends RuntimeException {}
}
