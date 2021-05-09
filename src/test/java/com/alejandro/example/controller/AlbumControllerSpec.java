package com.alejandro.example.controller;

import static com.alejandro.example.util.AlbumData.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.alejandro.example.dto.AlbumDTO;
import com.alejandro.example.entity.AlbumEntity;
import com.alejandro.example.service.IAlbumService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = AlbumController.class)
public class AlbumControllerSpec {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	IAlbumService albumServiceMock;
	
	ObjectMapper mapper;
	
	private static final Long DEFAULT_ID = 1L;
	private static final Long INVALID_ID = 2L;
	private static final String BASE_URL = "/v1/album";
	
	@BeforeEach
	void setUp() {
		this.mapper = new ObjectMapper();
	}
	
	@Test
	@DisplayName("call findById happy path")
	void findByIdHP() throws Exception {
		String endpoint = BASE_URL + "/" + DEFAULT_ID;
		when(albumServiceMock.findById(DEFAULT_ID)).thenReturn(ALBUM_DTO);
		mockMvc.perform(get(endpoint).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.name").value(ALBUM.getName()));
	}
	
	@Test
	@DisplayName("call findById unhappy path")
	void findByIdUP() throws Exception {
		String endpoint = BASE_URL + "/" + INVALID_ID;
		when(albumServiceMock.findById(INVALID_ID)).thenThrow(NoSuchElementException.class);
		mockMvc.perform(get(endpoint).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("call save happy path")
	void saveHP() throws Exception {
		String endpoint = BASE_URL;
		when(albumServiceMock.save(any(AlbumDTO.class))).thenReturn(ALBUM_DTO);
		mockMvc
			.perform(post(endpoint)
			.contentType(MediaType.APPLICATION_JSON)
			.content(this.mapper.writeValueAsString(ALBUM_DTO)))
				.andExpect(status().isCreated());
	}
	
	@Test
	@DisplayName("call save unhappy path")
	void saveUP() throws Exception {
		String endpoint = BASE_URL;
		AlbumEntity toCreate = null;
		mockMvc
			.perform(post(endpoint)
			.contentType(MediaType.APPLICATION_JSON)
			.content(this.mapper.writeValueAsString(toCreate)))
				.andExpect(status().isBadRequest());
	}
}
