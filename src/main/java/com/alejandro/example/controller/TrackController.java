package com.alejandro.example.controller;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.example.entity.TrackEntity;
import com.alejandro.example.service.ITrackService;
import com.alejandro.example.util.ResponseModel;

@RestController
@RequestMapping(path = "v1/track")
public class TrackController {
	
	@Autowired
	private ITrackService service;
	
	@GetMapping
	public ResponseEntity<Set<TrackEntity>> getAll(){
		return ResponseEntity.ok().body(this.service.getAll());
	}
	
	@PostMapping
	public ResponseEntity<TrackEntity> save(@RequestBody TrackEntity track) {
		return ResponseEntity.ok().body(this.service.save(track));
	}

	@PutMapping("{id}")
	public  ResponseEntity<?> update(@RequestBody TrackEntity entity, @PathVariable Long id) {

		try {
			return ResponseEntity.ok().body(this.service.update(entity, id));
		} catch (NoSuchElementException e) {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body(new ResponseModel(LocalDateTime.now(), null, e.getMessage()));
		}
	}
	
}
