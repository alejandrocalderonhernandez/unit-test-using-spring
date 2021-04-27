package com.alejandro.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.alejandro.example.entity.RecordCompanyEntity;

public interface RecordCompanyRepository extends CrudRepository<RecordCompanyEntity, String>{

}
