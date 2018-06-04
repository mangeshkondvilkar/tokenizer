package com.uob.tokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.uob.tokenizer.scheduler.TokenizerScheduler;

/**
 * @author Mangesh K
 *
 */
@Configuration
@ComponentScan
public class TokenizerSchedulerMainApp implements CommandLineRunner {

	@Autowired
	private TokenizerScheduler scheduler;

	public static void main(String[] args) {
		System.out.println("======================== Scheduler MAIN STARTED =============================");
		ConfigurableApplicationContext context = SpringApplication.run(TokenizerSchedulerMainApp.class, args);

		// close context
		context.close();
	}

	@Override
	public void run(String... args) throws Exception {
		scheduler.startTokenizerPoller();
	}

}
