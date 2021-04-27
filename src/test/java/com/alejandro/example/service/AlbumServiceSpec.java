package com.alejandro.example.service;

import static com.alejandro.example.util.AlbumData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.alejandro.example.entity.AlbumEntity;
import com.alejandro.example.repository.AlbumRepository;
import com.alejandro.example.repository.TrackRepository;

@SpringBootTest
public class AlbumServiceSpec {

	@MockBean
	AlbumRepository albumRepository;
	@MockBean
	TrackRepository trackRepository;
	
	@Autowired
	IAlbumService service;
	
	private static final Long DEFAULT_ID = 1L;
	private static final Long INVALID_ID = 2L;
	
	@Test
	@DisplayName("call findById method happy path")
	void findByIdHP() {
		when(albumRepository.findById(DEFAULT_ID)).thenReturn(Optional.of(ALBUM));
		AlbumEntity result = this.service.findById(DEFAULT_ID);
		verify(albumRepository).findById(DEFAULT_ID);
		assertEquals(result, ALBUM);
	}
	
	@Test
	@DisplayName("call findById method unhappy path")
	void findByIdUP() {
		when(albumRepository.findById(DEFAULT_ID)).thenReturn(Optional.of(ALBUM));
		assertThrows(NoSuchElementException.class, () -> {
			this.service.findById(INVALID_ID);
		});
	}

}
