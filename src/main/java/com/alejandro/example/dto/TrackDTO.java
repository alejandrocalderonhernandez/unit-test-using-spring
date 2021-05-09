package com.alejandro.example.dto;

import java.io.Serializable;

public class TrackDTO implements Serializable{

	private Long trackId;
	private String name;
	private String lycris;
	private AlbumDTO album;
	
	public TrackDTO() {}
	
	public TrackDTO(Long trackId, String name, String lycris) {
		this.trackId = trackId;
		this.name = name;
		this.lycris = lycris;
	}

	public Long getTrackId() {
		return trackId;
	}

	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLycris() {
		return lycris;
	}

	public void setLycris(String lycris) {
		this.lycris = lycris;
	}

	public AlbumDTO getAlbum() {
		return album;
	}

	public void setAlbum(AlbumDTO album) {
		this.album = album;
	}

	private static final long serialVersionUID = 5585894285878319988L;
	
}
