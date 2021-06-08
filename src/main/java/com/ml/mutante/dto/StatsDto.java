package com.ml.mutante.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class StatsDto {
	
	private int count_mutant_dna;
	private int count_human_dna;
	private String ratio;


}
