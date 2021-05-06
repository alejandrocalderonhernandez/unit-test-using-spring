package com.alejandro.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.alejandro.example.entity.TrackEntity;
import com.alejandro.example.repository.TrackRepository;
import com.alejandro.example.service.impl.TrackServiceImpl;

import static com.alejandro.example.util.AlbumData.*;


@SpringBootTest
public class TrackSpec {

	private static final Long DEFAULT_ID = 1L;
	
	@Mock
	TrackRepository repository;
	@InjectMocks
	TrackServiceImpl service;
	
	@Test
	void findById(){
		when(repository.findById(DEFAULT_ID)).thenReturn(Optional.of(TRACK_1));
		TrackEntity response = service.findById(DEFAULT_ID);
		assertEquals(response, TRACK_1);
	}
	
}
