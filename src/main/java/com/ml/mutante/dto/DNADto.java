package com.ml.mutante.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DNADto {
	
	@NotNull
	@NotBlank(message = "DNA is mandatory")
	private String[] dna;


}
