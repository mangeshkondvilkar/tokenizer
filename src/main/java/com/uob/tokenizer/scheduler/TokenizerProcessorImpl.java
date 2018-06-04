package com.uob.tokenizer.scheduler;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uob.tokenizer.dto.UobTokenizerXmlRootDto;
import com.uob.tokenizer.service.ITokenizerService;
import com.uob.tokenizer.utils.TokenizerConfig;

/**
 * Represents operations to process the data from TokenizerScheduler.
 *
 * @author Mangesh K
 *
 */
@Component
public class TokenizerProcessorImpl implements ITokenizerProcessor {

	@Autowired
	private ITokenizerService tokenizerService;

	@Autowired
	private TokenizerConfig tokenizerProperties;

	private static final Logger LOGGER = Logger.getLogger(TokenizerScheduler.class);

	@Override
	public void readAndProcessDirectory(DirectoryStream<Path> sourceDirStream) throws Exception {
		LOGGER.info("Started reading and processing all files from source folder...");

		final String destinationDir = tokenizerProperties.getDestinationDirectory();

		File outputDir = new File(destinationDir);
		if (!outputDir.exists()) {
			outputDir.mkdirs();
		}

		// Loop through all files and process them one by one
		for (Path sourceXmlFile : sourceDirStream) {
			boolean isSuccess = false;
			// read current file and process
			try {
				File tempSourceFile = sourceXmlFile.toFile();
				Path originalFilePath = sourceXmlFile.getFileName();

				// convert to xml object and process to get tokenized xml
				// object
				final UobTokenizerXmlRootDto responseXmlDto = processSourceXmlFile(tempSourceFile);

				if (null == responseXmlDto) {
					LOGGER.info("Failed to process file ==> " + sourceXmlFile + ". Skipping it...");
					continue;
				}

				// convert object to xml file and write to output folder
				final File destFile = new File(destinationDir, tempSourceFile.getName());
				isSuccess = writeFileToDestination(responseXmlDto, destFile);

				// delete xmlFile from source directory if processing is
				// successful
				if (isSuccess) {
					Files.deleteIfExists(sourceXmlFile);
					LOGGER.info("Source file " + originalFilePath + " deleted successfully...");
				}

			} catch (Exception ex) {
				LOGGER.info("Failed to process file ==> " + sourceXmlFile + ". Skipping it...");
				ex.printStackTrace();
			}
		}

		LOGGER.info("Ended reading and processing all files from source folder...");
	}

	@Override
	public UobTokenizerXmlRootDto processSourceXmlFile(final File sourceXmlFile) throws Exception {
		boolean isSuccess = false;
		// convert xml file to object
		UobTokenizerXmlRootDto requestXmlDto = new UobTokenizerXmlRootDto();

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(UobTokenizerXmlRootDto.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Object marshalledObj = jaxbUnmarshaller.unmarshal(sourceXmlFile);

			if (marshalledObj instanceof UobTokenizerXmlRootDto) {
				requestXmlDto = (UobTokenizerXmlRootDto) marshalledObj;
			}

			LOGGER.info("---------------Marshalled object ----------------------");
			LOGGER.info(requestXmlDto);
			LOGGER.info("-------------------------------------------------------");

		} catch (JAXBException je) {
			LOGGER.info("Error while unmarshalling file:" + sourceXmlFile + " to object...");
			je.printStackTrace();
		}

		// call service to tokenize xml object
		if (null != requestXmlDto) {
			isSuccess = tokenizerService.tokenizeSourceXmlObject(requestXmlDto);
		}

		return isSuccess ? requestXmlDto : null;
	}

	@Override
	public boolean writeFileToDestination(final UobTokenizerXmlRootDto responseXmlDto, final File destFile)
			throws Exception {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(UobTokenizerXmlRootDto.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty format
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(responseXmlDto, destFile);

			LOGGER.info("----------------------------Transformed Xml---------------------------");
			jaxbMarshaller.marshal(responseXmlDto, System.out);
			LOGGER.info("----------------------------------------------------------------------");

			LOGGER.info("Transformed xml has been written to output file" + destFile.getAbsolutePath()
					+ " successfully...");
			return true;

		} catch (Exception e) {
			LOGGER.error("Error while marshalling responseObject to xml file...");
			e.printStackTrace();
			return false;
		}
	}
}
