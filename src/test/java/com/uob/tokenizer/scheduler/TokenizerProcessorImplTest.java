package com.uob.tokenizer.scheduler;

import java.io.File;
import java.util.ArrayList;

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
import com.uob.tokenizer.dto.RequestDetails;
import com.uob.tokenizer.dto.RequestHeader;
import com.uob.tokenizer.dto.RequesterContext;
import com.uob.tokenizer.dto.Ticker;
import com.uob.tokenizer.dto.UobTokenizerXmlRootDto;
import com.uob.tokenizer.repository.TokenMappingRepository;
import com.uob.tokenizer.service.ITokenizerService;
import com.uob.tokenizer.service.ITokenizerServiceHelper;
import com.uob.tokenizer.service.TokenizerServiceHelperImpl;
import com.uob.tokenizer.service.TokenizerServiceImpl;
import com.uob.tokenizer.utils.TokenizerConfig;

/**
 * Represents Junit tests for TokenizerProcessorImpl operations.
 * 
 * @author Mangesh K
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration
public class TokenizerProcessorImplTest {

	@Configuration
	static class TokenizerServiceContextConfiguration {

		@Bean
		public ITokenizerProcessor tokenizerProcessor() {
			return new TokenizerProcessorImpl();
		}

		@Bean
		public ITokenizerService tokenizerService() {
			return Mockito.mock(TokenizerServiceImpl.class);
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
	private ITokenizerProcessor tokenizerProcessor;

	@Autowired
	private ITokenizerService tokenizerService;

	/**
	 * Below beans are autowired as they are used in processor and service
	 * beans.
	 */
	@SuppressWarnings("unused")
	@Autowired
	private ITokenizerServiceHelper tokenizerServiceHelper;

	@SuppressWarnings("unused")
	@Autowired
	private TokenizerConfig tokenizerConfig;

	@SuppressWarnings("unused")
	@Autowired
	private TokenMappingRepository tokenMappingRepository;

	// as per new structure
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

	@Test
	public void testProcessSourceXmlFile() throws Exception {
		final File file = new File(this.getClass().getResource("/samples/UobRequest.xml").toURI());
		// mock service call
		Mockito.when(tokenizerService.tokenizeSourceXmlObject(this.mockUobTokenizerXmlRootDto)).thenReturn(true);

		@SuppressWarnings("unused")
		UobTokenizerXmlRootDto responseDto = tokenizerProcessor.processSourceXmlFile(file);
	}

}
