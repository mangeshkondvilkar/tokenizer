package com.uob.tokenizer.service;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uob.tokenizer.dto.PortfolioRequestDetails;
import com.uob.tokenizer.dto.QueryResponseDto;
import com.uob.tokenizer.dto.UobTokenizerXmlRootDto;
import com.uob.tokenizer.utils.ResponseStatus;
import com.uob.tokenizer.utils.TokenizerConfig;

/**
 * A Service class implementation.
 *
 * @author Mangesh K
 * @since March 2018
 */
@Service
public class TokenizerServiceImpl implements ITokenizerService {

	public TokenizerServiceImpl() {
		super();
	}

	@Autowired
	private TokenizerConfig globalProperties;

	@Autowired
	private ITokenizerServiceHelper serviceHelper;

	private static final Logger LOGGER = Logger.getLogger(TokenizerServiceImpl.class);

	@Override
	public boolean tokenizeSourceXmlObject(final UobTokenizerXmlRootDto requestXmlDto) throws Exception {
		try {
			if (null == requestXmlDto || null == requestXmlDto.getRequestHeader()) {
				LOGGER.info("Invalid Xml object...");
				return false;
			}

			// get fields to be encrypted from configuration
			List<String> fieldsToBeEncrypted = globalProperties.getRequestFieldsToBeEncryptedAsList();
			LOGGER.info("headerFields to be encrypted from config => " + fieldsToBeEncrypted);

			// encrypt all configured header fields
			return encryptHeaderFields(requestXmlDto, fieldsToBeEncrypted);

		} catch (Exception e) {
			LOGGER.error("Error while tokenizing input xml object...");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public QueryResponseDto processQueryRequestText(String token) throws Exception {
		QueryResponseDto queryResponseDto = new QueryResponseDto();

		BigInteger maskTextInt = serviceHelper.fetchCipherTextForToken(token);

		if (null != maskTextInt) {
			String maskText = String.valueOf(maskTextInt);
			queryResponseDto.setStatus(ResponseStatus.SUCCESS);
			queryResponseDto.setMaskText(maskText);
			LOGGER.info("Token " + token + " found in DB, returning mask-text => " + maskText);

			return queryResponseDto;
		} else {
			queryResponseDto.setStatus(ResponseStatus.NOT_FOUND);
			LOGGER.info("Token " + token + " not found in DB, returning error code not-found.");

			return queryResponseDto;
		}

	}

	/**
	 * Encrypt header level data based on the fields read from config and
	 * reflection.
	 *
	 * @param requestXmlDto
	 * @param fieldsToBeEncrypted
	 * @return isSuccess
	 * @throws Exception
	 */
	private boolean encryptHeaderFields(final UobTokenizerXmlRootDto requestXmlDto, List<String> fieldsToBeEncrypted)
			throws Exception {

		if (CollectionUtils.isEmpty(fieldsToBeEncrypted)) {
			LOGGER.info("Invalid header fields. fieldsToBeEncrypted are not configured");
			return false;
		}

		final PortfolioRequestDetails requestDetailsTag = requestXmlDto.getRequestDetails() != null
				? requestXmlDto.getRequestDetails().getPortfolioRequestDetails() : null;
				
		if (null == requestDetailsTag){
			LOGGER.info("Invalid request. Null or empty PortfolioRequestDetails");
			return false;
		}
		
		AccessibleObject.setAccessible(requestDetailsTag.getClass().getDeclaredFields(), true);

		for (String fieldName : fieldsToBeEncrypted) {
			Field currentField = requestDetailsTag.getClass().getDeclaredField(fieldName);
			currentField.setAccessible(true);

			String currentVal = String.valueOf(currentField.get(requestDetailsTag));
			LOGGER.info("Encrypting field: " + fieldName + " currentValue: " + currentVal);

			if (StringUtils.isNotBlank(currentVal)) {
				String value = serviceHelper.fetchOrInsertCipherTextForToken(currentVal.trim());
				currentField.set(requestDetailsTag, value);
			}
		}
		return true;
	}

}
