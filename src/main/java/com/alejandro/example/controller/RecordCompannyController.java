package com.alejandro.example.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.example.entity.RecordCompanyEntity;
import com.alejandro.example.service.IRecordCompanyService;

@RestController
@RequestMapping(path = "v1/record")
public class RecordCompannyController {
	
	@Autowired
	private IRecordCompanyService service;
	
	@GetMapping
	public ResponseEntity<Set<RecordCompanyEntity>> getAll(){
		return ResponseEntity.ok().body(this.service.getAll());
	}
	
	@PostMapping
	public ResponseEntity<RecordCompanyEntity> save(@RequestBody RecordCompanyEntity record) {;
		return ResponseEntity.ok().body(this.service.save(record));
	}

}
