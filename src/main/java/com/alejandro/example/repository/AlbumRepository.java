package com.alejandro.example.repository;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.alejandro.example.entity.AlbumEntity;

public interface  AlbumRepository extends CrudRepository<AlbumEntity, Long>{

	public Set<AlbumEntity> findByPriceBetween(Double min, Double max);
}
