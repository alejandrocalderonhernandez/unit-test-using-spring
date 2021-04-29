package com.alejandro.example.service.impl;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.example.entity.AlbumEntity;
import com.alejandro.example.entity.RecordCompanyEntity;
import com.alejandro.example.entity.TrackEntity;
import com.alejandro.example.repository.AlbumRepository;
import com.alejandro.example.repository.RecordCompanyRepository;
import com.alejandro.example.repository.TrackRepository;
import com.alejandro.example.service.IAlbumService;

@Service
@Transactional
public class AlbumServiceImpl implements IAlbumService {
	
	
	private static final Logger log = LoggerFactory.getLogger(AlbumServiceImpl.class);

	private AlbumRepository albumAepository;
	private TrackRepository trackRepository;
	private RecordCompanyRepository recordCompanyRepository;

	@Autowired
	public AlbumServiceImpl(
			AlbumRepository albumAepository, 
			TrackRepository trackRepository,
			RecordCompanyRepository recordCompanyRepository
			) {
		this.albumAepository = albumAepository;
		this.trackRepository = trackRepository;
		this.recordCompanyRepository = recordCompanyRepository;
	}

	@Override
	public Set<AlbumEntity> getAll() {
		Set<AlbumEntity> response = new HashSet<>();
		this.albumAepository.findAll().forEach(response::add);
		if(response.isEmpty()) {
			new NoSuchElementException("No data");
		}
		return response;
	}

	@Override
	public AlbumEntity save(AlbumEntity entity) {
		if(entity.getRecordCompany() != null) {
			Optional<RecordCompanyEntity> response = this.recordCompanyRepository.findById(entity.getRecordCompany().getTittle());
			if(response.isPresent()) {
				System.out.println(response.get());
				entity.setRecordCompany(response.get());
			}
		}
		log.info("save {}", entity.toString());
		return this.albumAepository.save(entity);
	}

	@Override
	public AlbumEntity findById(Long id) {
		return this.albumAepository.findById(id).orElseThrow();
	}

	@Override
	public void delete(Long id) {
		if(!this.albumAepository.findById(id).isPresent()) {
			throw new NoSuchElementException("The id dont exist");
		}
		this.albumAepository.delete(this.albumAepository.findById(id).get());
	}

	@Override
	public AlbumEntity update(AlbumEntity entity, Long id) {
		if(!this.albumAepository.findById(id).isPresent()) {
			throw new NoSuchElementException("The id dont exist");
		}
		AlbumEntity toUpdate = this.albumAepository.findById(id).get();
		toUpdate.setAutor(entity.getAutor());
		toUpdate.setName(entity.getName());
		toUpdate.setPrice(entity.getPrice());
		return this.albumAepository.save(toUpdate);
	}

	@Override
	public AlbumEntity addTrack(TrackEntity track, Long id) {
		if(!this.albumAepository.findById(id).isPresent()) {
			throw new NoSuchElementException("The id dont exist");
		}
		AlbumEntity toUpdate = this.albumAepository.findById(id).get();
		toUpdate.addTrack(track);
		return albumAepository.save(toUpdate);
	}

	@Override
	public AlbumEntity removeTrack(TrackEntity track, Long id) {
		if(!this.albumAepository.findById(id).isPresent()) {
			throw new NoSuchElementException("The id dont exist");
		}
		AlbumEntity toUpdate = this.albumAepository.findById(id).get();
		if(!this.trackRepository.existsById(track.getTrackId())) {
			throw new NoSuchElementException("The track dont exist");
		}
		toUpdate.removeTrack(track);
		this.albumAepository.save(toUpdate);
		return toUpdate;
	}

	@Override
	public Set<AlbumEntity> findBetweenprice(Double min, Double max) {
		 Set<AlbumEntity> response = this.albumAepository.findByPriceBetween(min, max);
		 if (response.size() == 0) {
			 throw new NoSuchElementException("Not records");
		 }
		return response;
	}

}
