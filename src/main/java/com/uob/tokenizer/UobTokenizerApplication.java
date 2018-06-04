package com.uob.tokenizer;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import com.uob.tokenizer.utils.TokenizerConfig;

/**
 * This class represents a spring boot main application. Responsible to
 * initialize the spring context at the startup.
 * 
 * @author Mangesh K
 * @since March 2018
 */
@SpringBootApplication
@EnableConfigurationProperties(TokenizerConfig.class)
@ComponentScan(basePackages = {
		"com.uob.tokenizer" }, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = TokenizerSchedulerMainApp.class))
public class UobTokenizerApplication extends SpringBootServletInitializer implements ApplicationRunner {

	private static final Logger LOGGER = Logger.getLogger(UobTokenizerApplication.class);


	/**
	 * Main method to bootstrap UOB main application. Will be called by spring
	 * on startup.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(UobTokenizerApplication.class, args);
	}

	/**
	 * Override this method from {@link ApplicationRunner} interface to capture
	 * command line arguments if any.
	 */
	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOGGER.info("Command-line arguments: " + Arrays.toString(args.getSourceArgs()));
		LOGGER.info("Non Option Args: " + args.getNonOptionArgs());
		LOGGER.info("Option Names: " + args.getOptionNames());

		LOGGER.info("================ UOB tokenizer app command line args ===============");
		for (String name : args.getOptionNames()) {
			LOGGER.info("arg-" + name + "=" + args.getOptionValues(name));
		}
	}

	/**
	 * Override this method from {@link SpringBootServletInitializer} abstract
	 * class to capture server runtime parameters.
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(UobTokenizerApplication.class);
	}
}
