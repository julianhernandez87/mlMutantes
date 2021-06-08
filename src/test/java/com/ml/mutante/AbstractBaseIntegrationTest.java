package com.ml.mutante;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.MOCK, classes={ MutantApplication.class })
public abstract class AbstractBaseIntegrationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private RestTemplate restTemplate;
	
	protected MockMvc mockMvc;
	protected MockRestServiceServer mockRestServer;
	
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockRestServer = MockRestServiceServer.createServer(restTemplate);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

}
