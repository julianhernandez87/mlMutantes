package com.ml.mutante.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@AllArgsConstructor
@Builder
@Table(name="ADN_VERIFIED")
public class DNAVerified {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private int id;
	
	@Column(name="ADN")
	private String adn;

	@Column(name="ISMUTANT")
	private boolean isMutant;
	
	public DNAVerified() {
		super();
	}
	

}
