package com.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources", 
		glue = { "com.test.stepdefinition", "classpath:io.jdev.cucumber.variables.java.en" },
		plugin = { "pretty",
				"html:target/cucumber-html-report", "json:target/cucumber-json-report.json" }, 
		monochrome = true,
		tags = "@GuestCheckout" )

public class RunnerTest { }

