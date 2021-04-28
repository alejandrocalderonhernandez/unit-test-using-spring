package com.alejandro.example.controller;

import static com.alejandro.example.util.AlbumData.*;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.alejandro.example.service.IAlbumService;

@WebMvcTest(value = AlbumController.class)
public class AlbumControllerSpec {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	IAlbumService albumService;
	
	private static final Long DEFAULT_ID = 1L;
	private static final Long INVALID_ID = 2L;
	private static final String BASE_URL = "/v1/album/";
	
	@Test
	@DisplayName("call findById happy path")
	void findByIdHP() throws Exception {
		String endpoint = BASE_URL + DEFAULT_ID;
		when(albumService.findById(DEFAULT_ID)).thenReturn(ALBUM);
		mockMvc.perform(get(endpoint).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.name").value(ALBUM.getName()));
	}
	
	@Test
	@DisplayName("call findById unhappy path")
	void findByIdUP() throws Exception {
		String endpoint = BASE_URL + INVALID_ID;
		when(albumService.findById(INVALID_ID)).thenThrow(NoSuchElementException.class);
		mockMvc.perform(get(endpoint).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}

}
