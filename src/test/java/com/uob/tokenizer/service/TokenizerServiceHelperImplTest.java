package com.uob.tokenizer.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigInteger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uob.tokenizer.entity.TokenMapping;
import com.uob.tokenizer.repository.TokenMappingRepository;
import com.uob.tokenizer.utils.TokenizerConfig;

/**
 * Represents Junit tests for TokenizerServiceHelperImpl operations.
 * 
 * @author Mangesh K
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration
public class TokenizerServiceHelperImplTest {

	@Configuration
	static class TokenizerServiceContextConfiguration {

		@Bean
		public ITokenizerServiceHelper tokenizerServiceHelper() {
			return new TokenizerServiceHelperImpl();
		}

		@Bean
		public TokenizerConfig tokenizerConfig() {
			return Mockito.mock(TokenizerConfig.class);
		}

		@Bean
		public TokenMappingRepository tokenMappingRepository() {
			return Mockito.mock(TokenMappingRepository.class);
		}
	}

	@Autowired
	private ITokenizerServiceHelper tokenizerServiceHelper;

	@Autowired
	private TokenizerConfig tokenizerConfig;

	@Autowired
	private TokenMappingRepository tokenMappingRepository;

	/**
	 * Test method for
	 * {@link TokenizerServiceHelperImpl#fetchOrInsertCipherTextForToken}. Test
	 * for existing record in DB.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFetchOrInsertCipherTextForTokenExisting() throws Exception {
		String requestToken = "prive";

		// create mock TokenMapping record
		TokenMapping existingMockRecord = new TokenMapping();
		existingMockRecord.setId(1L);
		existingMockRecord.setTokenName("prive");
		existingMockRecord.setCipherText(new BigInteger("100001"));

		Mockito.when(tokenMappingRepository.findByTokenName(requestToken)).thenReturn(existingMockRecord);

		String maskText = tokenizerServiceHelper.fetchOrInsertCipherTextForToken(requestToken);

		assertNotNull("Test failed: maskText is null", maskText);
		assertEquals("AssertEquals Test passed", String.valueOf(existingMockRecord.getCipherText()), maskText);
	}

	/**
	 * Test method for
	 * {@link TokenizerServiceHelperImpl#fetchOrInsertCipherTextForToken}. Test
	 * for new TokenMapping record where max(cipher) from DB is not null
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFetchOrInsertCipherTextForTokenNew() throws Exception {
		String requestToken = "prive";
		BigInteger mockMaxCipher = new BigInteger("1001");
		// create mock TokenMapping record
		TokenMapping existingMockRecord = null;

		// mock repository calls
		Mockito.when(tokenMappingRepository.findByTokenName(requestToken)).thenReturn(existingMockRecord);
		Mockito.when(tokenMappingRepository.getMaxCipherText()).thenReturn(mockMaxCipher);

		String maskText = tokenizerServiceHelper.fetchOrInsertCipherTextForToken(requestToken);

		assertNotNull("Test failed: maskText is null", maskText);
		assertEquals("AssertEquals Test passed", "1002", maskText);
	}

	/**
	 * Test method for
	 * {@link TokenizerServiceHelperImpl#fetchOrInsertCipherTextForToken}. Test
	 * for new TokenMapping record where max(cipher) from DB is null meaning no
	 * records in DB yet.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFetchOrInsertCipherTextForTokenNew2() throws Exception {
		String requestToken = "prive";
		BigInteger mockMaxCipher = null;
		// create mock TokenMapping record
		TokenMapping existingMockRecord = null;

		// mock base number from config
		Mockito.when(tokenizerConfig.getBaseNumber()).thenReturn("999");

		// mock repository calls
		Mockito.when(tokenMappingRepository.findByTokenName(requestToken)).thenReturn(existingMockRecord);
		Mockito.when(tokenMappingRepository.getMaxCipherText()).thenReturn(mockMaxCipher);

		String maskText = tokenizerServiceHelper.fetchOrInsertCipherTextForToken(requestToken);

		assertNotNull("Test failed: maskText is null", maskText);
		assertEquals("AssertEquals Test passed", "999", maskText);
	}

	/**
	 * Test method for
	 * {@link TokenizerServiceHelperImpl#fetchCipherTextForToken}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testFetchCipherTextForToken() throws Exception {
		String requestToken = "prive";
		BigInteger maskText = new BigInteger("1001");
		Mockito.when(tokenMappingRepository.findCipherTextByTokenName(requestToken)).thenReturn(maskText);

		tokenizerServiceHelper.fetchCipherTextForToken(requestToken);
	}

}
