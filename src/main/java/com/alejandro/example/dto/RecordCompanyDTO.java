package com.alejandro.example.dto;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class RecordCompanyDTO implements Serializable{

	private String tittle;
	@JsonIgnoreProperties(value = "recordCompany", allowSetters = true)
	private Set<AlbumDTO> albums;
	
	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public Set<AlbumDTO> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<AlbumDTO> albums) {
		this.albums = albums;
	}

	private static final long serialVersionUID = -830289242848414364L;

}
