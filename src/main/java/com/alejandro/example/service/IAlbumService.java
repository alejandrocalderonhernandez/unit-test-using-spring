package com.alejandro.example.service;

import java.util.Set;

import com.alejandro.example.dto.AlbumDTO;
import com.alejandro.example.dto.TrackDTO;
import com.alejandro.example.service.common.SimpleService;

public interface IAlbumService extends SimpleService<AlbumDTO, Long> {
	
	public AlbumDTO addTrack(TrackDTO track, Long id);
	
	public AlbumDTO removeTrack(TrackDTO track, Long id);
	
	public Set<AlbumDTO> findBetweenprice(Double min, Double max);

}
