package com.uob.tokenizer.utils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Represents a global Properties configuration class. Binds the custom
 * properties configured under application.properties to object fields for easy
 * use.
 *
 * @author Mangesh K
 * @since March 2018
 */
@ConfigurationProperties
public class TokenizerConfig {

	private String baseNumber;

	private String requestFieldsToBeEncrypted;

	private String sourceDirectory;

	private String destinationDirectory;
	

	public String getBaseNumber() {
		return baseNumber;
	}

	public void setBaseNumber(String baseNumber) {
		this.baseNumber = baseNumber;
	}

	public String getRequestFieldsToBeEncrypted() {
		return requestFieldsToBeEncrypted;
	}

	public void setRequestFieldsToBeEncrypted(String requestFieldsToBeEncrypted) {
		this.requestFieldsToBeEncrypted = requestFieldsToBeEncrypted;
	}

	public List<String> getRequestFieldsToBeEncryptedAsList() {
		return (null != this.getRequestFieldsToBeEncrypted())
				? Arrays.asList(this.getRequestFieldsToBeEncrypted().split(",")) : null;
	}

	public String getSourceDirectory() {
		return sourceDirectory;
	}

	public void setSourceDirectory(String sourceDirectory) {
		this.sourceDirectory = sourceDirectory;
	}

	public String getDestinationDirectory() {
		return destinationDirectory;
	}

	public void setDestinationDirectory(String destinationDirectory) {
		this.destinationDirectory = destinationDirectory;
	}

	private static final int THREAD_COUNT = 3;

	private static final ExecutorService EXECUTOR_SERVICE_INSTANCE = Executors.newFixedThreadPool(THREAD_COUNT);

	public static ExecutorService getFixedThreadPool() {
		return EXECUTOR_SERVICE_INSTANCE;
	}

}
