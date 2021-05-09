package com.alejandro.example.controller;

import java.net.URI;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.alejandro.example.service.IAlbumService;

@RestController
@RequestMapping(path = "v1/album")
public class AlbumController {
	
	@Autowired
	private IAlbumService service;
	
	@GetMapping
	public ResponseEntity<Set<AlbumDTO>> getAll() {
		return ResponseEntity.ok().body(this.service.getAll());
	}
	
	@PostMapping
	public ResponseEntity<AlbumDTO> save(@Valid @RequestBody AlbumDTO album) {;
		return ResponseEntity.created(URI.create("v1/album/" + this.service.save(album).getAlbumId())).build();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<AlbumDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(this.service.findById(id));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<AlbumDTO> delete(@PathVariable Long id) {
		this.service.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("{id}")
	public  ResponseEntity<AlbumDTO> update(@RequestBody AlbumDTO album, @PathVariable Long id) {
		return ResponseEntity.ok().body(this.service.update(album, id));
	}
	
	@PatchMapping("add/{id}")
	public  ResponseEntity<AlbumDTO> addTrack(@RequestBody TrackDTO track, @PathVariable Long id) {
		return ResponseEntity.ok().body(this.service.addTrack(track, id));
	}
	
	@PatchMapping("remove/{id}")
	public  ResponseEntity<AlbumDTO> removeTrack(@RequestBody TrackDTO track, @PathVariable Long id) {
		return ResponseEntity.ok().body(this.service.removeTrack(track, id));
	}
	
	@GetMapping("between")
	public ResponseEntity<Set<AlbumDTO>> fondBetweenPrice(@RequestParam Double min, @RequestParam Double max) {
			return ResponseEntity.ok().body(this.service.findBetweenprice(min, max));
	
	}
	
}
