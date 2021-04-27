package com.alejandro.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.alejandro.example.entity.AlbumEntity;

public interface  AlbumRepository extends CrudRepository<AlbumEntity, Long>{

}
