package com.alejandro.example.dto;

import java.io.Serializable;
import java.util.Set;

import com.alejandro.example.entity.AlbumEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AlbumDTO implements Serializable {

	private Long albumId;
	private String name;
	private String autor;
	private Double price;
	@JsonIgnoreProperties(value = "albums", allowSetters = true)
	private RecordCompanyDTO recordCompany;
	@JsonIgnoreProperties(value = "album", allowSetters = true)
	private Set<AlbumDTO> tracks;
	
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

	public Set<AlbumDTO> getTracks() {
		return tracks;
	}

	public void setTracks(Set<AlbumDTO> tracks) {
		this.tracks = tracks;
	}
	
	public AlbumEntity toEntity() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			System.out.println(mapper.writeValueAsString(this));
			return mapper.readValue(mapper.writeValueAsString(this), AlbumEntity.class);
		} catch (JsonProcessingException e) {
			System.err.println(e.getMessage());
			throw new RuntimeException("Error to parse json");
		}
	}
	private static final long serialVersionUID = 11221L;

}
