package com.uob.tokenizer.scheduler;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Path;

import org.springframework.stereotype.Component;

import com.uob.tokenizer.dto.UobTokenizerXmlRootDto;

/**
 * Represents abstract operations to process the data from TokenizerScheduler.
 * 
 * @author Mangesh K
 *
 */
@Component
public interface ITokenizerProcessor {

	/**
	 * Read files from source folder and send for processing one by one.
	 * 
	 * @param sourceDirStream
	 * @throws Exception
	 */
	public void readAndProcessDirectory(DirectoryStream<Path> sourceDirStream) throws Exception;

	/**
	 * Process the input Xml file and return the transformed Xml object for
	 * writing it to file in destination folder.
	 * 
	 * @param sourceXmlFile
	 * @return
	 * @throws Exception
	 */
	public UobTokenizerXmlRootDto processSourceXmlFile(final File sourceXmlFile) throws Exception;

	/**
	 * Write the tokenized Xml object to a file in destination folder.
	 * 
	 * @param responseXmlDto
	 * @param destFile
	 * @return
	 * @throws Exception
	 */
	public boolean writeFileToDestination(final UobTokenizerXmlRootDto responseXmlDto, final File destFile)
			throws Exception;

}
