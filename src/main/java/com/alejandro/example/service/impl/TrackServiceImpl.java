package com.alejandro.example.service.impl;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.example.entity.TrackEntity;
import com.alejandro.example.repository.TrackRepository;
import com.alejandro.example.service.ITrackService;

@Service
@Transactional
public class TrackServiceImpl implements ITrackService {
	
	
	private static final Logger log = LoggerFactory.getLogger(TrackServiceImpl.class);

	@Autowired
	private TrackRepository repository;

	@Override
	public Set<TrackEntity> getAll() {
		Set<TrackEntity> response = new HashSet<>();
		this.repository.findAll().forEach(response::add);
		return response;
	}

	@Override
	public TrackEntity save(TrackEntity entity) {
		log.info("save {}", entity.toString());
		return this.repository.save(entity);
	}

	@Override
	public TrackEntity findById(Long id) {
		return this.repository.findById(id).get();
	}

	@Override
	public void delete(Long entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TrackEntity update(TrackEntity entity, Long id) {
		if(!this.repository.existsById(id)) {
			throw new NoSuchElementException("Id not found");
		}
		TrackEntity toUpdate = this.repository.findById(id).get();
		toUpdate.setLycris(entity.getLycris());
		toUpdate.setName(entity.getName());
		return this.repository.save(toUpdate);
	}

}
