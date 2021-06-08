package com.ml.mutante.service;

import com.ml.mutante.dto.StatsDto;


public interface IMutantService {
	
	/**
	 * 
	 * @return
	 */
	StatsDto getStats();
	
	/**
	 * 
	 * @param dna
	 * @return
	 */
	boolean isMutant(String[] dna);
}
