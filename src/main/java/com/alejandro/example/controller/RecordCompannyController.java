package com.alejandro.example.controller;

import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alejandro.example.dto.RecordCompanyDTO;
import com.alejandro.example.entity.RecordCompanyEntity;
import com.alejandro.example.service.IRecordCompanyService;
import com.alejandro.example.util.JsonUtil;

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
	public ResponseEntity<RecordCompanyEntity> save(@Valid @RequestBody RecordCompanyDTO record) {;
	RecordCompanyEntity toSave = (RecordCompanyEntity) JsonUtil.bodyMapper(record, RecordCompanyEntity.class); 
	return ResponseEntity.ok().body(this.service.save(toSave));
	}

}
