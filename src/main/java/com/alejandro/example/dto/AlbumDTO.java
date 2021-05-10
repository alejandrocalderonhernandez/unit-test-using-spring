package com.alejandro.example.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.alejandro.example.util.JsonUtil;
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

	public AlbumDTO(Long albumId,
			@NotNull(message = "The name is mandatory") @Size(min = 2, max = 20, message = "The size have to length between 2 and 20 characters") String name,
			@NotNull(message = "The autor is mandatory") String autor,
			@NotNull(message = "The price is mandatory") Double price, @Valid RecordCompanyDTO recordCompany,
			Set<TrackDTO> tracks) {
		super();
		this.albumId = albumId;
		this.name = name;
		this.autor = autor;
		this.price = price;
		this.recordCompany = recordCompany;
		this.tracks = tracks;
	}


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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((albumId == null) ? 0 : albumId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlbumDTO other = (AlbumDTO) obj;
		if (albumId == null) {
			if (other.albumId != null)
				return false;
		} else if (!albumId.equals(other.albumId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return JsonUtil.toStringJson(this);
	}

	private static final long serialVersionUID = 11221L;

}
