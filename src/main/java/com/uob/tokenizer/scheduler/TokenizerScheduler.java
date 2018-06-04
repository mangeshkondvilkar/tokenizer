package com.uob.tokenizer.scheduler;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uob.tokenizer.utils.TokenizerConfig;

/**
 * A Scheduler class which represents all scheduled operations for Tokenizer.
 *
 * @author Mangesh K
 * @since March 2018
 */
@Component
public class TokenizerScheduler {

	public TokenizerScheduler() {
		super();
	}

	@Autowired
	private ITokenizerProcessor tokenizerProcessor;

	@Autowired
	private TokenizerConfig tokenizerProperties;

	private static final Logger LOGGER = Logger.getLogger(TokenizerScheduler.class);

	/**
	 * A scheduler to poll the source folder for Xml files and delegate them all
	 * to a Processor class for further processing. Runs based on the frequency
	 * configured under application.properties file
	 *
	 */
	public void startTokenizerPoller() {
		final String sourceDir = tokenizerProperties.getSourceDirectory();
		LOGGER.info("Poller started... scanning folder " + sourceDir + " for files...");

		DirectoryStream<Path> sourceDirStream = null;
		try {
			Path sourceDirPath = FileSystems.getDefault().getPath(sourceDir);
			sourceDirStream = Files.newDirectoryStream(sourceDirPath);

			tokenizerProcessor.readAndProcessDirectory(sourceDirStream);

		} catch (Exception e) {
			LOGGER.error("Exception occured while scanning source folder...");
			e.printStackTrace();
		} finally {
			try {
				if (null != sourceDirStream) {
					sourceDirStream.close();
				}
			} catch (IOException e) {
				LOGGER.error("Error closing sourceDirStream...");
				e.printStackTrace();
			}
		}

		LOGGER.info("Poller ended scanning directory " + sourceDir + " for files...");
	}

}
