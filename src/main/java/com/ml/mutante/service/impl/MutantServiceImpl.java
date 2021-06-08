package com.ml.mutante.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ml.mutante.dto.StatsDto;
import com.ml.mutante.exception.ServiceException;
import com.ml.mutante.model.DNAVerified;
import com.ml.mutante.repository.IDNAVerifiedRepository;
import com.ml.mutante.service.IMutantService;


@Service
public class MutantServiceImpl implements IMutantService {

	private static DecimalFormat df = new DecimalFormat("0.00");

	private final IDNAVerifiedRepository dnaRep;

	public MutantServiceImpl(IDNAVerifiedRepository dnaRep) {
		super();
		this.dnaRep = dnaRep;
	}


	@Override
	public StatsDto getStats() {
		try {
			List<DNAVerified> lstDNAVerified = dnaRep.findAll();
			int mutants = (int) lstDNAVerified
					.stream()
					.filter( x -> x.isMutant())
					.count();

			return	StatsDto
					.builder()
					.count_mutant_dna(mutants)
					.count_human_dna(lstDNAVerified.size() - mutants)
					.ratio(df.format((double)mutants/lstDNAVerified.size()))
					.build();

		} catch (Exception e) {
			throw new ServiceException("Error getting stats", e);
		}
	}


	@Override
	public boolean isMutant(String[] dna) {
		boolean isMutant;

		// transform dna to matrix 
		char [][] matrix = stringToMatrix(dna);

		// get data to process
		List<String> data = getDatatoProcess(matrix);

		// verify if it is mutant
		isMutant = verifydna(data);

		// save record into db
		addDNAVerified(dna, isMutant);		

		return isMutant;
	}

	private List<String> getDatatoProcess(char [][] matrix) {
		try {
			int lenght = matrix.length;
			char[] diagppal = new char [lenght];
			char[] diagsec = new char [lenght];
			char[] vertical = new char [lenght];
			char[] horizontal = new char [lenght];

			List<String> lstData = new ArrayList<String>();

			for (int i = 0; i < lenght; i++) {
				for (int j = 0; j < lenght; j++) {

					vertical[j] = matrix[j][i];
					horizontal[j] = matrix[i][j];

					if (i == j) {
						diagppal[j] = matrix[i][j];
					}

					if(i+j == lenght-1){
						diagsec[i] = matrix[i][j];
					}
				}
				lstData.add(new String(vertical));
				lstData.add(new String(horizontal));
			}
			lstData.add(new String(diagppal));
			lstData.add(new String(diagsec));
			return lstData;
		} catch (Exception e) {
			throw new ServiceException("Error getting the data", e);
		}
	}

	private boolean verifydna(List<String> dna) {
		try {
			int inrow;
			int fourInrow = 0;

			nextdna:
				for (String dnatira : dna) {
					inrow = 0;
					char[] tira = dnatira.toCharArray();
					char x = tira[0];

					for (int i = 1; i < tira.length; i++) {
						// compara
						char xx = tira[i];
						if (x == xx) { 
							inrow ++;
						}else if ((i + 4)>tira.length && inrow==0) { // si no hay posibilidad de obtener 4 iguales salta al sig dna
							continue nextdna;
						}else {
							x = tira[i];
							inrow = 0;
							continue;
						}  
						// si ya tiene 4 en linea salta al siguiente dna
						if (inrow == 3) {
							fourInrow ++;
							continue nextdna;
						}
					}

				}
			return (fourInrow>=2);
		} catch (Exception e) {
			throw new ServiceException("Error veryfing data", e);
		}
	}

	private char [][]  stringToMatrix(String[] dna) {
		try {
			char [][] matrix  = new char [dna.length][dna.length];
			for (int i = 0; i < dna.length; i++) {
				char [] fila = dna[i].toCharArray();
				matrix [i] = fila;

			}
			return matrix;		
		} catch (Exception e) {
			throw new ServiceException("Error getting matrix", e);
		}
	}


	private DNAVerified addDNAVerified(String[] dna, boolean isMutant) {
		try {
			return dnaRep.save(DNAVerified
					.builder()
					.adn(String.join(",", dna))
					.isMutant(isMutant)
					.build());
		} catch (Exception e) {
			throw new ServiceException("Error adding DNAVerified", e);
		}
	}

}
