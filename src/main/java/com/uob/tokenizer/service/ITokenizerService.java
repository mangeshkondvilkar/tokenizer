package com.uob.tokenizer.service;

import org.springframework.stereotype.Service;

import com.uob.tokenizer.dto.QueryResponseDto;
import com.uob.tokenizer.dto.UobTokenizerXmlRootDto;

/**
 * A service interface to serve the requests from Tokenizer Resource/Controller.
 * 
 * @author Mangesh K
 * @since March 2018
 */
@Service
public interface ITokenizerService {

	/**
	 * Tokenize the in memory Xml object using running number encryption.
	 * 
	 * @return true if tokenized successful, false otherwise
	 * @throws Exception
	 */
	public boolean tokenizeSourceXmlObject(final UobTokenizerXmlRootDto requestXmlDto) throws Exception;

	/**
	 * A method to process the incoming query token and then return the
	 * mask-text from DB. if token not found then return specific error code.
	 * 
	 * @param token
	 * @return QueryResponseDto containing status code and maskText for an input
	 *         token.
	 * @throws Exception
	 */
	public QueryResponseDto processQueryRequestText(String token) throws Exception;
}
