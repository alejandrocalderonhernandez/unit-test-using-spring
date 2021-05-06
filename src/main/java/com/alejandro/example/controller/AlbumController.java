package com.alejandro.example.controller;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.example.dto.AlbumDTO;
import com.alejandro.example.dto.TrackDTO;
import com.alejandro.example.entity.AlbumEntity;
import com.alejandro.example.entity.TrackEntity;
import com.alejandro.example.service.IAlbumService;
import com.alejandro.example.util.JsonUtil;
import com.alejandro.example.util.ResponseModel;
import com.alejandro.example.util.excepcion.IllegalBodyException;

@RestController
@RequestMapping(path = "v1/album")
public class AlbumController {
	
	@Autowired
	private IAlbumService service;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		try {
			Set<AlbumDTO> response = this.service.getAll().stream()
				.map(a -> (AlbumDTO) JsonUtil.bodyMapper(a, AlbumDTO.class))
				.collect(Collectors.toSet());
			return ResponseEntity.ok().body(response);
		} catch (NoSuchElementException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ResponseModel(LocalDateTime.now(), null, e.getMessage()));
		}
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody AlbumDTO album) {;
		try {
			AlbumEntity toSave = (AlbumEntity) JsonUtil.bodyMapper(album, AlbumEntity.class);
			return ResponseEntity.created(URI.create(
					"v1/album/" + this.service.save(toSave).getAlbumId())).build();
		} catch (IllegalBodyException e) {
			return new ResponseEntity<ResponseModel>(
					new ResponseModel(LocalDateTime.now(), null, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR );
		}

	}
	
	@GetMapping("{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			AlbumDTO toResponse = (AlbumDTO) JsonUtil.bodyMapper(this.service.findById(id), AlbumDTO.class);
			return ResponseEntity.ok().body(toResponse);
		} catch (NoSuchElementException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ResponseModel(LocalDateTime.now(), null, e.getMessage()));
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		try {
			this.service.delete(id);
			return ResponseEntity.ok().build();
		} catch (NoSuchElementException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ResponseModel(LocalDateTime.now(), null, e.getMessage()));
		}
	}
	
		@PutMapping("{id}")
	public  ResponseEntity<?> update(@RequestBody AlbumDTO album, @PathVariable Long id) {
		try {
			AlbumEntity toUpdate = (AlbumEntity) JsonUtil.bodyMapper(album, AlbumEntity.class);
			return ResponseEntity.ok().body(this.service.update(toUpdate, id));
		} catch (NoSuchElementException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ResponseModel(LocalDateTime.now(), null, e.getMessage()));
		}
	}
	
	@PatchMapping("add/{id}")
	public  ResponseEntity<?> addTrack(@RequestBody TrackDTO track, @PathVariable Long id) {
		try {
			TrackEntity toAdd = (TrackEntity) JsonUtil.bodyMapper(track, TrackEntity.class);
			return ResponseEntity.ok().body(this.service.addTrack(toAdd, id));
		} catch (NoSuchElementException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ResponseModel(LocalDateTime.now(), null, e.getMessage()));
		}
	}
	
	@PatchMapping("remove/{id}")
	public  ResponseEntity<?> removeTrack(@RequestBody TrackEntity track, @PathVariable Long id) {
		try {
			TrackEntity toRemove = (TrackEntity) JsonUtil.bodyMapper(track, TrackEntity.class);
			return ResponseEntity.ok().body(this.service.removeTrack(toRemove, id));
		} catch (NoSuchElementException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ResponseModel(LocalDateTime.now(), null, e.getMessage()));
		}
	}
	
	@GetMapping("between")
	public  ResponseEntity<?> fondBetweenPrice(@RequestParam Double min, @RequestParam Double max) {
		try {
			Set<AlbumDTO> response = this.service.findBetweenprice(min, max).stream()
					.map(a -> (AlbumDTO) JsonUtil.bodyMapper(a, AlbumDTO.class))
					.collect(Collectors.toSet());
			return ResponseEntity.ok().body(response);
		} catch (NoSuchElementException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ResponseModel(LocalDateTime.now(), null, e.getMessage()));
		}
	}
	
}
