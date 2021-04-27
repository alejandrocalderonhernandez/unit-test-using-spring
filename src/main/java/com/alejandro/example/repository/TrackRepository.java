package com.alejandro.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.alejandro.example.entity.TrackEntity;

public interface TrackRepository extends CrudRepository<TrackEntity, Long>{

}
