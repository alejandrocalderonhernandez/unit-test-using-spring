package com.alejandro.example.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class AlbumDTO implements Serializable {

	private Long albumId;
	@NotNull(message = "The name is mandatory")
	@Size(min = 2, max = 20, message = "The size have to length between 2 and 20 characters")
	private String name;
	@NotNull(message = "The autor is mandatory")
	private String autor;
	@NotNull(message = "The price is mandatory")
	private Double price;
	@Valid
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
