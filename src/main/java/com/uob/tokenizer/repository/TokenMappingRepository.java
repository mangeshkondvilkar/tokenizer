package com.uob.tokenizer.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.uob.tokenizer.entity.TokenMapping;

/**
 * A repository class for TokenMapping entity to have SQL query implementations.
 * 
 * @author Mangesh K
 * @since March 2018
 *
 */
public interface TokenMappingRepository extends CrudRepository<TokenMapping, Long> {

	/**
	 * Return TokenMapping record based on tokenName
	 * 
	 * @param tokenName
	 * @return TokenMapping record
	 */
	public TokenMapping findByTokenName(String tokenName);

	/**
	 * @return
	 */
	@Query("select max(tm.cipherText) from TokenMapping tm")
	public BigInteger getMaxCipherText();

	/**
	 * Return maskText for a token.
	 * 
	 * @param tokenName
	 * @return maskText
	 */
	@Query("select tm.cipherText from TokenMapping tm where tm.tokenName=:token")
	public BigInteger findCipherTextByTokenName(@Param("token") String tokenName);
}
