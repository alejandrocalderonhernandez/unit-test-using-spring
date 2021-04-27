package com.alejandro.example.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class TrackEntity implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long trackId;
	private String name;
	private String lycris;
	@JsonIgnoreProperties(value = "tracks")
	@ManyToOne
	@JoinColumn(name = "albumId")
	private AlbumEntity album;
	
	public TrackEntity() { }

	public TrackEntity(Long trackId, String name, String lycris) {
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

	public AlbumEntity getAlbum() {
		return album;
	}

	public void setAlbum(AlbumEntity album) {
		this.album = album;
	}

	@Override
	public String toString() {
		return "TrackEntity [trackId=" + trackId + ", name=" + name + ", lycris=" + lycris + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lycris == null) ? 0 : lycris.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((trackId == null) ? 0 : trackId.hashCode());
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
		TrackEntity other = (TrackEntity) obj;
		if (lycris == null) {
			if (other.lycris != null)
				return false;
		} else if (!lycris.equals(other.lycris))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (trackId == null) {
			if (other.trackId != null)
				return false;
		} else if (!trackId.equals(other.trackId))
			return false;
		return true;
	}

	private static final long serialVersionUID = 1786544567L;

}
