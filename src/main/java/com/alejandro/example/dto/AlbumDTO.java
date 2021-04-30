package com.alejandro.example.dto;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class AlbumDTO implements Serializable {

	private Long albumId;
	private String name;
	private String autor;
	private Double price;
	@JsonIgnoreProperties(value = "albums", allowSetters = true)
	private RecordCompanyDTO recordCompany;
	@JsonIgnoreProperties(value = "album", allowSetters = true)
	private Set<TrackDTO> tracks;
	
	public AlbumDTO() {}

	public Long getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Long albumId) {
		this.albumId = albumId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public RecordCompanyDTO getRecordCompany() {
		return recordCompany;
	}

	public void setRecordCompany(RecordCompanyDTO recordCompany) {
		this.recordCompany = recordCompany;
	}

	public Set<TrackDTO> getTracks() {
		return tracks;
	}

	public void setTracks(Set<TrackDTO> tracks) {
		this.tracks = tracks;
	}

	private static final long serialVersionUID = 11221L;

}
