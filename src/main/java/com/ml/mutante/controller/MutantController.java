package com.ml.mutante.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ml.mutante.dto.DNADto;
import com.ml.mutante.dto.StatsDto;
import com.ml.mutante.service.IMutantService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "/ml")
public class MutantController {

	private final IMutantService service;

	public MutantController(IMutantService service) {
		super();
		this.service = service;
	}

	@ApiOperation(value = "Verify if dna is mutant.", tags = { "DNA - Verification" })
	@RequestMapping(method = RequestMethod.POST, path = "/mutant",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> verifyDNA(@RequestBody DNADto dnaDto){
		
		if (service.isMutant(dnaDto.getDna())) {
			return new ResponseEntity<>(HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
	}
	
	@ApiOperation(value = "Gets all dna verified of sqlserver database.", tags = { "DNA - Stats" })
	@RequestMapping(method = RequestMethod.GET, path = "/stats",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StatsDto> getStats(){
		StatsDto stats =  service.getStats();
		return new ResponseEntity<>(stats, HttpStatus.OK); 
	}

}
