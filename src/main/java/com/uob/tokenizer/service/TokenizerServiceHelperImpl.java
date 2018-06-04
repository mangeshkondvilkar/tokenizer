package com.uob.tokenizer.service;

import java.math.BigInteger;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.uob.tokenizer.entity.TokenMapping;
import com.uob.tokenizer.repository.TokenMappingRepository;
import com.uob.tokenizer.utils.TokenizerConfig;

/**
 * A Service Helper class implementation.
 *
 * @author Mangesh K
 * @since March 2018
 */
@Service
public class TokenizerServiceHelperImpl implements ITokenizerServiceHelper {

	@Autowired
	TokenMappingRepository tokenMappingRepository;

	@Autowired
	TokenizerConfig globalProperties;

	private static final Logger LOGGER = Logger.getLogger(TokenizerServiceHelperImpl.class);

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
	public String fetchOrInsertCipherTextForToken(String token) throws Exception {

		if (StringUtils.isBlank(token)) {
			LOGGER.info("Current Token is null or empty");
			return null;
		}

		// get existing record from TokenMapping table for a given token
		TokenMapping tokenMappingRecordExisting = tokenMappingRepository.findByTokenName(token);

		if (null == tokenMappingRecordExisting) {
			// new cipher field
			LOGGER.info("Token " + token + " does not exists in DB...creating new cipherText");

			TokenMapping tokenMappingRecordNew = new TokenMapping();

			// get the max(cipherText) from DB
			BigInteger maxCipherText = tokenMappingRepository.getMaxCipherText();
			LOGGER.info("Max ciphertext in database => " + maxCipherText);

			// if max cipher is not null- add 1, save and return
			tokenMappingRecordNew.setTokenName(token);

			if (maxCipherText != null) {
				BigInteger bigOne = new BigInteger("1");
				BigInteger runningNumber = maxCipherText.add(bigOne);
				tokenMappingRecordNew.setCipherText(runningNumber);
				LOGGER.info("New ciphertext => " + runningNumber + " is returned...");
			} else {
				LOGGER.info("No entries found in DB. Seems to be a first request. Returning base ciphertext => "
						+ globalProperties.getBaseNumber());
				tokenMappingRecordNew.setCipherText(new BigInteger(globalProperties.getBaseNumber()));
			}
			tokenMappingRepository.save(tokenMappingRecordNew);

			return String.valueOf(tokenMappingRecordNew.getCipherText());

		} else {
			// cipher field already exists in DB
			LOGGER.info("Token " + token + " exists in DB returning existing cipherText => "
					+ tokenMappingRecordExisting.getCipherText());
			return String.valueOf(tokenMappingRecordExisting.getCipherText());
		}

	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public BigInteger fetchCipherTextForToken(String token) throws Exception {
		return tokenMappingRepository.findCipherTextByTokenName(token);
	}

}
