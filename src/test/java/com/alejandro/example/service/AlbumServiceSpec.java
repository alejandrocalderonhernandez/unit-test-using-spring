package com.alejandro.example.service;

import static com.alejandro.example.util.AlbumData.*;
import static org.junit.jupiter.api.Assertions.assertAll;
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

import com.alejandro.example.dto.AlbumDTO;
import com.alejandro.example.repository.AlbumRepository;
import com.alejandro.example.repository.RecordCompanyRepository;
import com.alejandro.example.repository.TrackRepository;

@SpringBootTest
public class AlbumServiceSpec {

	@MockBean
	AlbumRepository albumRepository;
	@MockBean
	TrackRepository trackRepository;
	@MockBean
	RecordCompanyRepository recordCompanyRepository;
	@Autowired
	IAlbumService service;
	
	private static final Long DEFAULT_ID = 1L;
	private static final Long INVALID_ID = 2L;
	
	@Test
	@DisplayName("call findById method happy path")
	void findByIdHP() {
		when(albumRepository.findById(DEFAULT_ID)).thenReturn(Optional.of(ALBUM));
		AlbumDTO result = this.service.findById(DEFAULT_ID);
		verify(albumRepository).findById(DEFAULT_ID);
		assertAll(
				() -> assertEquals(result, ALBUM_DTO),
				() -> assertEquals(result.getAlbumId(), ALBUM.getAlbumId()),
				() -> assertEquals(result.getName(), ALBUM.getName()),
				() -> assertEquals(result.getAutor(), ALBUM.getAutor()),
				() -> assertEquals(result.getPrice(), ALBUM.getPrice())
		);
		
		
	}
	
	@Test
	@DisplayName("call findById method unhappy path")
	void findByIdUP() {
		when(albumRepository.findById(DEFAULT_ID)).thenReturn(Optional.of(ALBUM));
		assertThrows(NoSuchElementException.class, () -> {
			this.service.findById(INVALID_ID);
			verify(albumRepository).findById(DEFAULT_ID);
		});
	}
	
	@Test
	@DisplayName("call save method happy path")
	void saveHP() {
		String recordCompanyId = ALBUM.getRecordCompany().getTittle();
		when(recordCompanyRepository.findById(recordCompanyId)).thenReturn(Optional.of(RECORD_COMPANY));
		when(albumRepository.save(ALBUM)).thenReturn(ALBUM);
		AlbumDTO result = this.service.save(ALBUM_DTO);
		verify(recordCompanyRepository).findById(recordCompanyId);
		verify(albumRepository).save(ALBUM);
		assertEquals(result, ALBUM_DTO);
	}
	
	@Test
	@DisplayName("call delete method unhappy path")
	void deleteHP() {
		doNothing().when(albumRepository).delete(ALBUM);
		when(albumRepository.findById(DEFAULT_ID)).thenReturn(Optional.of(ALBUM));
		service.delete(DEFAULT_ID);
		verify(albumRepository).delete(ALBUM);
	}
	

	@Test
	@DisplayName("call delete method unhappy path")
	void deleteUP() {
		doNothing().when(albumRepository).delete(ALBUM);
		when(albumRepository.findById(DEFAULT_ID)).thenReturn(Optional.empty());
		assertThrows(NoSuchElementException.class, () -> service.delete(DEFAULT_ID));
	}
}
