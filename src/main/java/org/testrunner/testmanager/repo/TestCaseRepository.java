package org.testrunner.testmanager.repo;

import org.springframework.stereotype.Repository;
import org.testrunner.testmanager.model.TestCase;

import java.util.Arrays;
import java.util.List;

//@Repository
public class TestCaseRepository
{
	public static List<TestCase> testCases = Arrays.asList(
		new TestCase("first test") {{ id = 1L; description = "a simple test";}},
		new TestCase("another test") {{ id = 2L; description = "a better test"; categories.add("foo"); }},
		new TestCase("yet another test") {{ id = 3L; description = "an even better test"; categories = Arrays.asList("foo", "bar", "baz"); }}
	);

	public  List<TestCase> findAll()
	{
		return testCases;
	}
}
