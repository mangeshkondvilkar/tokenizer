package com.uob.tokenizer.controller;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.uob.tokenizer.dto.QueryResponseDto;
import com.uob.tokenizer.service.ITokenizerService;
import com.uob.tokenizer.service.ITokenizerServiceHelper;
import com.uob.tokenizer.service.TokenizerServiceHelperImpl;
import com.uob.tokenizer.service.TokenizerServiceImpl;
import com.uob.tokenizer.utils.ResponseStatus;

/**
 * Represents a Junit class for TokenizerController end-points.
 *
 * @author Mangesh K
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TokenizerController.class)
@ContextConfiguration
public class TokenizerControllerTest {


	@Configuration
	static class TokenizerServiceContextConfiguration {

		@Bean
		public ITokenizerService tokenizerService() {
			return Mockito.mock(TokenizerServiceImpl.class);
		}

		@Bean
		public ITokenizerServiceHelper tokenizerServiceHelper() {
			return Mockito.mock(TokenizerServiceHelperImpl.class);
		}
		
		@Bean
		public TokenizerController tokenizerController() {
			return new TokenizerController();
		}
	}

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TokenizerController tokenizerController;

	@MockBean
	private ITokenizerService tokenizerService;

	@MockBean
	private ITokenizerServiceHelper tokenizerServiceHelper;

	/**
	 * Test query end-point for success. Test for method
	 * {@link TokenizerController#handleQueryRequest }.
	 *
	 * @throws Exception
	 */
	@Test
	public void testHandleQueryRequest() throws Exception {
		String textParamVal = "token";
		QueryResponseDto responseDto = new QueryResponseDto();
		responseDto.setMaskText("100001");
		responseDto.setStatus(ResponseStatus.SUCCESS);

		// mock service call
		when(tokenizerService.processQueryRequestText(textParamVal)).thenReturn(responseDto);

		mockMvc.perform(get("/tokenizer/query?text=" + textParamVal).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("status", is(responseDto.getStatus().getDisplayText())))
				.andDo(print());
	}

	/**
	 * Test query end-point for not-found. Test for method
	 * {@link TokenizerController#handleQueryRequest }.
	 *
	 * @throws Exception
	 */
	@Test
	public void testHandleQueryRequestNotFound() throws Exception {
		String textParamVal = "randomText";
		QueryResponseDto responseDto = new QueryResponseDto();
		responseDto.setStatus(ResponseStatus.NOT_FOUND);

		// mock service call
		when(tokenizerService.processQueryRequestText(textParamVal)).thenReturn(responseDto);

		ResponseEntity<QueryResponseDto> mockResponseEntity = new ResponseEntity<QueryResponseDto>(responseDto,
				HttpStatus.OK);

		mockResponseEntity = tokenizerController.handleQueryRequest(textParamVal);

		assertNotNull(mockResponseEntity.getBody());
		assertEquals("Failed", ResponseStatus.NOT_FOUND, mockResponseEntity.getBody().getStatus());
	}

	/**
	 * Test query end-point with mock service and mock service helper. Test for
	 * method {@link TokenizerController#handleQueryRequest }.
	 *
	 * @throws Exception
	 */
	@Test
	public void testHandleQueryRequestWithMockService() throws Exception {
		String textParamVal = "textInDB";

		// mock service helper call
		BigInteger maskedValInt = new BigInteger("100002");
		when(tokenizerServiceHelper.fetchCipherTextForToken(textParamVal)).thenReturn(maskedValInt);

		QueryResponseDto responseDto = new QueryResponseDto();
		responseDto.setMaskText(String.valueOf(maskedValInt));
		responseDto.setStatus(ResponseStatus.SUCCESS);

		// mock service call
		when(tokenizerService.processQueryRequestText(textParamVal)).thenReturn(responseDto);

		assertEquals("Unexpected status value", responseDto.getStatus(), ResponseStatus.SUCCESS);
		assertEquals("Unexpected mask-text value", responseDto.getMaskText(), "100002");

		// mock controller endpoint
		mockMvc.perform(get("/tokenizer/query?text=" + textParamVal).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("status", is(responseDto.getStatus().getDisplayText())))
				.andExpect(jsonPath("mask-text", is(responseDto.getMaskText()))).andDo(print());

	}

	@Test
	public void testHandleQueryRequestWithExceptions() throws Exception {

		String textParamVal = "token";
		QueryResponseDto responseDto = new QueryResponseDto();
		responseDto.setMaskText("100001");
		responseDto.setStatus(ResponseStatus.SUCCESS);

		@SuppressWarnings("rawtypes")
		Set<? extends ConstraintViolation> cs = new HashSet<ConstraintViolation>();

		@SuppressWarnings("unchecked")
		ConstraintViolationException cse = new ConstraintViolationException("Junit ConstraintViolation exception",
				(Set<? extends ConstraintViolation<?>>) cs);

		// mock service call
		when(tokenizerService.processQueryRequestText(textParamVal)).thenThrow(cse);

		mockMvc.perform(get("/tokenizer/query?text=" + textParamVal).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is(400)).andDo(print());
	}

	@Test
	public void testHandleQueryRequestWithExceptions2() throws Exception {

		String textParamVal = "token";
		QueryResponseDto responseDto = new QueryResponseDto();
		responseDto.setMaskText("100001");
		responseDto.setStatus(ResponseStatus.SUCCESS);

		// mock service call
		when(tokenizerService.processQueryRequestText(textParamVal)).thenThrow(new Exception("Junit Test Exception"));

		mockMvc.perform(get("/tokenizer/query?text=" + textParamVal).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is(500)).andDo(print());
	}
}
