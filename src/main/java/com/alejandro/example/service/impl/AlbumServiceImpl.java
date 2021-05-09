package com.alejandro.example.service.impl;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.example.dto.AlbumDTO;
import com.alejandro.example.dto.TrackDTO;
import com.alejandro.example.entity.AlbumEntity;
import com.alejandro.example.entity.RecordCompanyEntity;
import com.alejandro.example.entity.TrackEntity;
import com.alejandro.example.repository.AlbumRepository;
import com.alejandro.example.repository.RecordCompanyRepository;
import com.alejandro.example.repository.TrackRepository;
import com.alejandro.example.service.IAlbumService;
import com.alejandro.example.util.JsonUtil;

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
	public Set<AlbumDTO> getAll() {
		Set<AlbumEntity> response = new HashSet<>();
		this.albumAepository.findAll().forEach(response::add);
		if(response.isEmpty()) {
			new NoSuchElementException("No data");
		}
		return response.stream()
				.map(a -> (AlbumDTO) JsonUtil.bodyMapper(a, AlbumDTO.class))
				.collect(Collectors.toSet());
	}

	@Override
	public AlbumDTO save(AlbumDTO album) {
		AlbumEntity toSave = (AlbumEntity) JsonUtil.bodyMapper(album, AlbumEntity.class);
		if(toSave.getRecordCompany() != null) {
			Optional<RecordCompanyEntity> response = this.recordCompanyRepository.findById(toSave.getRecordCompany().getTittle());
			if(response.isPresent()) {
				toSave.setRecordCompany(response.get());
			}
		}
		log.info("save {}", toSave.toString());
		return (AlbumDTO) JsonUtil.bodyMapper(this.albumAepository.save(toSave), AlbumDTO.class);
	}

	@Override
	public AlbumDTO findById(Long id) {
		return (AlbumDTO) JsonUtil.bodyMapper(this.albumAepository.findById(id).orElseThrow(), AlbumDTO.class);
	}

	@Override
	public void delete(Long id) {
		if(!this.albumAepository.findById(id).isPresent()) {
			throw new NoSuchElementException("The id dont exist");
		}
		this.albumAepository.delete(this.albumAepository.findById(id).get());
	}

	@Override
	public AlbumDTO update(AlbumDTO album, Long id) {
		if(!this.albumAepository.findById(id).isPresent()) {
			throw new NoSuchElementException("The id dont exist");
		}
		AlbumEntity toUpdate = this.albumAepository.findById(id).get();
		toUpdate.setAutor(album.getAutor());
		toUpdate.setName(album.getName());
		toUpdate.setPrice(album.getPrice());
		return (AlbumDTO) JsonUtil.bodyMapper(this.albumAepository.save(toUpdate), AlbumDTO.class);
	}

	@Override
	public AlbumDTO addTrack(TrackDTO track, Long id) {
		if(!this.albumAepository.findById(id).isPresent()) {
			throw new NoSuchElementException("The id dont exist");
		}
		AlbumEntity toUpdate = this.albumAepository.findById(id).get();
		toUpdate.addTrack((TrackEntity) JsonUtil.bodyMapper(track, TrackEntity.class));
		return (AlbumDTO) JsonUtil.bodyMapper(this.albumAepository.save(toUpdate), AlbumDTO.class);
	}

	@Override
	public AlbumDTO removeTrack(TrackDTO track, Long id) {
		if(!this.albumAepository.findById(id).isPresent()) {
			throw new NoSuchElementException("The id dont exist");
		}
		AlbumEntity toUpdate = this.albumAepository.findById(id).get();
		
		if(!this.trackRepository.existsById(track.getTrackId())) {
			throw new NoSuchElementException("The track dont exist");
		}
		toUpdate.removeTrack((TrackEntity) JsonUtil.bodyMapper(track, TrackEntity.class));
		this.albumAepository.save(toUpdate);
		return (AlbumDTO) JsonUtil.bodyMapper(this.albumAepository.save(toUpdate), AlbumDTO.class);
	}

	@Override
	public Set<AlbumDTO> findBetweenprice(Double min, Double max) {
		 Set<AlbumEntity> response = this.albumAepository.findByPriceBetween(min, max);
		 if (response.size() == 0) {
			 throw new NoSuchElementException("Not records");
		 }
		 return response.stream()
					.map(a -> (AlbumDTO) JsonUtil.bodyMapper(a, AlbumDTO.class))
					.collect(Collectors.toSet());
	}

}
