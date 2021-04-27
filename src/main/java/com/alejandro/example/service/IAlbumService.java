package com.alejandro.example.service;

import com.alejandro.example.entity.AlbumEntity;
import com.alejandro.example.entity.TrackEntity;
import com.alejandro.example.service.common.SimpleService;

public interface IAlbumService extends SimpleService<AlbumEntity, Long> {
	
	public AlbumEntity addTrack(TrackEntity track, Long id);
	
	public AlbumEntity removeTrack(TrackEntity track, Long id);

}