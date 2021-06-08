package com.ml.mutante.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.ml.mutante.AbstractBaseIntegrationTest;

public class MutantControllerTest extends AbstractBaseIntegrationTest {

	@Test
	public void getStatsTest() throws Exception{

		// given

		//when
		mockMvc.perform( MockMvcRequestBuilders
				.get("/ml/stats")
				.accept(MediaType.APPLICATION_JSON))

		// then
		.andExpect(status().isOk());
	}


}
