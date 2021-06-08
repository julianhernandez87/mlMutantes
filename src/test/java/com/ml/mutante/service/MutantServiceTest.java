package com.ml.mutante.service;

import static org.mockito.Mockito.mock;

import org.junit.Test;
import org.mockito.Mockito;

import com.ml.mutante.repository.IDNAVerifiedRepository;
import com.ml.mutante.service.impl.MutantServiceImpl;

public class MutantServiceTest {

	private final IDNAVerifiedRepository repo = mock(IDNAVerifiedRepository.class);
	
	private final MutantServiceImpl sut = new MutantServiceImpl(repo);
	
	@Test
	public void getStatsTest() throws Exception{
		
		//given
		
		//when
		
		//then
		
			
	}
	

}
