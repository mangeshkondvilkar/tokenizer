package com.uob.tokenizer.service;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

/**
 * A helper class to handle service requests.
 * 
 * @author Mangesh K
 *
 */
@Service
public interface ITokenizerServiceHelper {

	/**
	 * A method to process the input token from Xml and return the cipherTex for
	 * it.
	 * 
	 * Return cipherText from DB if the token already exists in mapping table.
	 * Else, generate new cipherText which is max(cipherText) from DB plus one
	 * and return, also store the new mapping in DB.
	 * 
	 * @param token
	 * @return mask-text for a token. Create new if not found in DB and return.
	 * @throws Exception
	 */
	public String fetchOrInsertCipherTextForToken(String token) throws Exception;

	/**
	 * A method to fetch the maskText from DB for an input token. Return null if
	 * token not found in DB.
	 * 
	 * @param token
	 * @return mask-text for a token. null if not found in DB.
	 * @throws Exception
	 */
	public BigInteger fetchCipherTextForToken(String token) throws Exception;
}
