package com.ml.mutante.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.ml.mutante.dto.StatsDto;
import com.ml.mutante.repository.IDNAVerifiedRepository;
import com.ml.mutante.service.impl.MutantServiceImpl;

public class MutantServiceTest {

	private final IDNAVerifiedRepository repo = mock(IDNAVerifiedRepository.class);
	
	private final MutantServiceImpl sut = new MutantServiceImpl(repo);
	
	@Test
	public void getStatsTest() throws Exception{
		
		//given
		
		//when
		StatsDto dto = sut.getStats();
		
		//then
		assertThat(dto).isNotNull();
			
	}
	
	@Test
	public void isMutantTest() throws Exception{
		
		//given
		String[] dnamock = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
		
		//when
		boolean isMutant = sut.isMutant(dnamock);
		
		//then
		assertThat(isMutant).isTrue();
			
	}
	
	@Test
	public void isNotMutantTest() throws Exception{
		
		//given
		String[] dnamock = {"ATGCCA","CAGTGC","TTATGT","AGAAGG","CACCTA","TCACTG"};
		
		//when
		boolean isMutant = sut.isMutant(dnamock);
		
		//then
		assertThat(isMutant).isFalse();
			
	}
	

}
