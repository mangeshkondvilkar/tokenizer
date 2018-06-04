package com.uob.tokenizer.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.uob.tokenizer.dto.ListPortfolioHoldinginfo;
import com.uob.tokenizer.dto.PortfolioHoldinginfo;
import com.uob.tokenizer.dto.PortfolioRequestDetails;
import com.uob.tokenizer.dto.QueryResponseDto;
import com.uob.tokenizer.dto.RequestDetails;
import com.uob.tokenizer.dto.RequestHeader;
import com.uob.tokenizer.dto.RequesterContext;
import com.uob.tokenizer.dto.Ticker;
import com.uob.tokenizer.dto.UobTokenizerXmlRootDto;
import com.uob.tokenizer.repository.TokenMappingRepository;
import com.uob.tokenizer.utils.ResponseStatus;
import com.uob.tokenizer.utils.TokenizerConfig;

/**
 * Represents Junit tests for TokenizerServiceImpl operations.
 *
 * @author Mangesh K
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration
public class TokenizerServiceImplTest {

	@Configuration
	static class TokenizerServiceContextConfiguration {
		@Bean
		public ITokenizerService tokenizerService() {
			return new TokenizerServiceImpl();
		}

		@Bean
		public ITokenizerServiceHelper tokenizerServiceHelper() {
			return Mockito.mock(TokenizerServiceHelperImpl.class);
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
	private ITokenizerService tokenizerService;

	@Autowired
	private ITokenizerServiceHelper tokenizerServiceHelper;

	@Autowired
	private TokenizerConfig tokenizerConfig;

	@SuppressWarnings("unused")
	@Autowired
	private TokenMappingRepository tokenMappingRepository;

	private UobTokenizerXmlRootDto mockUobTokenizerXmlRootDto;

	@Before
	public void setup() {
		this.mockUobTokenizerXmlRootDto = new UobTokenizerXmlRootDto();
		setupDataForTokenizerRequestXmlDto();
	}

	/**
	 * Setup mock input Xml data object
	 */
	private void setupDataForTokenizerRequestXmlDto() {

		RequestHeader header = new RequestHeader();
		RequesterContext requesterContext = new RequesterContext();
		requesterContext.setLoginGroup("dummyLoginGroup");
		requesterContext.setLoginID("dummyLoginID");
		requesterContext.setRequestID("dummyRequestID");
		requesterContext.setSourceSystem("RIMS");
		requesterContext.setTargetSystem("PAT");
		header.setRequesterContext(requesterContext);
		this.mockUobTokenizerXmlRootDto.setRequestHeader(header);

		RequestDetails requestDetails = new RequestDetails();

		PortfolioRequestDetails portfolioRequestDetails = new PortfolioRequestDetails();
		portfolioRequestDetails.setRequestorID("dummyRequestorID");
		portfolioRequestDetails.setCifNumber("dummyCifNumber");
		portfolioRequestDetails.setCustomerEntityNumber("dummyCustomerEntityNumber");
		portfolioRequestDetails.setCustoemrRiskProfileDate("2018-04-22");

		ListPortfolioHoldinginfo listPortfolioHoldinginfo = new ListPortfolioHoldinginfo();
		ArrayList<PortfolioHoldinginfo> portfolioHoldinginfoList = new ArrayList<PortfolioHoldinginfo>();
		PortfolioHoldinginfo portfolioHoldinginfo = new PortfolioHoldinginfo();
		portfolioHoldinginfo.setAccCouponDividendAmountBaseCcy("EUR");
		portfolioHoldinginfo.setAccDividendReinvestUnits("123");
		portfolioHoldinginfo.setCurrentQuantityNominalAmount("12345.22");
		portfolioHoldinginfo.setInitialQuantity("4542");
		portfolioHoldinginfo.setTenor("1222");
		portfolioHoldinginfo.setSourceData("sourceData");
		portfolioHoldinginfo.setValueDate("2018-04-21");

		Ticker ticker = new Ticker();
		ticker.setAssetClass("assetClass");
		ticker.setContractSize("contractSize");
		ticker.setAleternateCurrency("USD");
		ticker.setProductType("FUND");
		ticker.setUniqueReferenceNumber("UNI123");

		portfolioHoldinginfo.setTicker(ticker);
		portfolioHoldinginfoList.add(portfolioHoldinginfo);
		listPortfolioHoldinginfo.setListPortfolioHoldinginfo(portfolioHoldinginfoList);
		portfolioRequestDetails.setListPortfolioHoldinginfo(listPortfolioHoldinginfo);
		requestDetails.setPortfolioRequestDetails(portfolioRequestDetails);

		this.mockUobTokenizerXmlRootDto.setRequestDetails(requestDetails);
	}

	/**
	 * A Junit test for method
	 * {@link TokenizerServiceImpl#tokenizeSourceXmlObject
	 * TokenizerServiceImpl.tokenizeSourceXmlObject}
	 *
	 * @throws Exception
	 */
	@Test
	public void testTokenizeSourceXmlObject() throws Exception {

		// mock cipherText from DB
		String newCipherText = "100002";

		// mock serviceHelper call
		Mockito.when(this.tokenizerServiceHelper.fetchOrInsertCipherTextForToken(
				this.mockUobTokenizerXmlRootDto.getRequestDetails().getPortfolioRequestDetails().getCifNumber()))
				.thenReturn(newCipherText);
		assertEquals("cipherTextfromDB assert failed", "100002", newCipherText);

		List<String> headerFields = Arrays.asList(new String[] { "cifNumber" });
		Mockito.when(tokenizerConfig.getRequestFieldsToBeEncryptedAsList()).thenReturn(headerFields);

		// call service with above mock calls
		boolean isSuccess = this.tokenizerService.tokenizeSourceXmlObject(this.mockUobTokenizerXmlRootDto);
		assertEquals("Service call failed", true, isSuccess);

		assertEquals("Encryption test failed", newCipherText,
				this.mockUobTokenizerXmlRootDto.getRequestDetails().getPortfolioRequestDetails().getCifNumber());
	}

	/**
	 * A Junit test for method
	 * {@link TokenizerServiceImpl#tokenizeSourceXmlObject
	 * TokenizerServiceImpl.tokenizeSourceXmlObject}
	 *
	 * @throws Exception
	 */
	@Test
	public void testTokenizeSourceXmlObjectException() throws Exception {
		Mockito.when(this.tokenizerServiceHelper.fetchOrInsertCipherTextForToken(
				this.mockUobTokenizerXmlRootDto.getRequestDetails().getPortfolioRequestDetails().getCifNumber()))
				.thenThrow(new RuntimeException("Test Exception"));
		tokenizerService.tokenizeSourceXmlObject(this.mockUobTokenizerXmlRootDto);
	}

	/**
	 * A Junit positive test for method
	 * {@link TokenizerServiceImpl#processQueryRequestText
	 * TokenizerServiceImpl.processQueryRequestText}
	 *
	 * @throws Exception
	 */
	@Test
	public void testProcessQueryRequestText() throws Exception {
		String requestToken = "prive";
		BigInteger maskText = new BigInteger("100005");

		// mock inner service helper call
		Mockito.when(tokenizerServiceHelper.fetchCipherTextForToken(requestToken)).thenReturn(maskText);

		QueryResponseDto mockResponseDto = tokenizerService.processQueryRequestText(requestToken);

		assertNotNull("Test failed: null mockResponseDto", mockResponseDto);
		assertEquals("testProcessQueryRequestText failed", ResponseStatus.SUCCESS, mockResponseDto.getStatus());
	}

	/**
	 * A negative test for method
	 * {@link TokenizerServiceImpl#processQueryRequestText
	 * TokenizerServiceImpl.processQueryRequestText}
	 *
	 * @throws Exception
	 */
	@Test
	public void testProcessQueryRequestTextNegative() throws Exception {
		String requestToken = "prive";

		// mock inner service helper call
		Mockito.when(tokenizerServiceHelper.fetchCipherTextForToken(requestToken)).thenReturn(null);

		QueryResponseDto mockResponseDto = tokenizerService.processQueryRequestText(requestToken);

		assertNotNull("Test failed: null mockResponseDto", mockResponseDto);
		assertEquals("testProcessQueryRequestText failed", ResponseStatus.NOT_FOUND, mockResponseDto.getStatus());
	}
}
