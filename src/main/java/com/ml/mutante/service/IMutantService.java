package com.ml.mutante.service;

import com.ml.mutante.dto.StatsDto;


public interface IMutantService {
	
	/**
	 * Gets all dna verified 
	 * @return
	 */
	StatsDto getStats();
	
	/**
	 * Verify if dna is mutant
	 * @param dna
	 * @return
	 */
	boolean isMutant(String[] dna);
}
