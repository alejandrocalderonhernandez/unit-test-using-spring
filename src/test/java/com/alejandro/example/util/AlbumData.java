package com.alejandro.example.util;

import java.util.Arrays;
import java.util.HashSet;

import com.alejandro.example.entity.AlbumEntity;
import com.alejandro.example.entity.RecordCompanyEntity;
import com.alejandro.example.entity.TrackEntity;

public class AlbumData {
	
	public static final RecordCompanyEntity RECORD_COMPANY;
	
	public static final TrackEntity TRACK_1;
	public static final TrackEntity TRACK_2;
	public static final TrackEntity TRACK_3;
	public static final TrackEntity TRACK_4;
	public static final TrackEntity TRACK_5;
	
	public static final AlbumEntity ALBUM;

	static {
		TRACK_1 = new TrackEntity(1L, "test-1", "lycris-1");
		TRACK_2 = new TrackEntity(2L, "test-2", "lycris-2");
		TRACK_3 = new TrackEntity(3L, "test-3", "lycris-3");
		TRACK_4 = new TrackEntity(4L, "test-4", "lycris-4");
		TRACK_5 = new TrackEntity(5L, "test-5", "lycris-5");
		
		
		RECORD_COMPANY = new RecordCompanyEntity("test-comany");
		
		ALBUM = new AlbumEntity(
				1L, 
				"album-test", 
				"actor-test", 
				20.20, 
				RECORD_COMPANY, 
				new HashSet<>(Arrays.asList(TRACK_1, TRACK_2, TRACK_3, TRACK_4)));
	}
}
