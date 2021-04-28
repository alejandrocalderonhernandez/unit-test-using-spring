package com.alejandro.example.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import com.alejandro.example.entity.AlbumEntity;

@DataJpaTest
public class AlbumRepositorySpec {

	@Autowired
	AlbumRepository repository;
	
	private static final Long DEFAULT_ID = 100L;
	private static final Long INVALID_ID = 25L;
	
	@Test
	@DisplayName("call finfById method happy path")
	void findByIdHP() {
		Optional<AlbumEntity> result = repository.findById(DEFAULT_ID);
		assertTrue(result.isPresent());
		assertEquals(result.get().getName(), "fear of the dark");
	}
	
	@Test
	@DisplayName("call finfById method unhappy path")
	void findByIdUP() {
		Optional<AlbumEntity> result = repository.findById(INVALID_ID);
		assertTrue(!result.isPresent());
		assertThrows(NoSuchElementException.class, () -> {
			result.get();
		});
	}
	
	@Test
	@DisplayName("call findByPriceBetween method happy path")
	void findByPriceBetweenHP() {
		Set<AlbumEntity> result = this.repository.findByPriceBetween(10.0, 1000.0);
		assertNotNull(result);
		assertEquals(result.size(), 2);
	}
	
	@Test
	@DisplayName("call findByPriceBetween method unhappy path")
	void findByPriceBetweenUP() {
		Set<AlbumEntity> result = this.repository.findByPriceBetween(1.0, 10.0);
		assertEquals(result.size(), 0);
	}
	
	@Test
	@DisplayName("call save method happy path")
	void saveHP() {
		AlbumEntity toSave = new AlbumEntity();
		toSave.setName("test-name");
		toSave.setAutor("test-autor");
		toSave.setPrice(67.90);
		AlbumEntity result = this.repository.save(toSave);
		assertEquals(toSave, result);
	}
	
	@Test
	@DisplayName("call save method unhappy path")
	void saveHUP() {
		AlbumEntity toSave = null;
		assertThrows(InvalidDataAccessApiUsageException.class, () -> this.repository.save(toSave));

	}
	
	@Test
	@DisplayName("call delete method happy path")
	void deleteHP() {
		int beforeRemove = 
				StreamSupport.stream(this.repository.findAll().spliterator(), false).collect(Collectors.toList()).size();
		assertEquals(beforeRemove, 2);
		AlbumEntity toDelete = this.repository.findById(DEFAULT_ID).get();
		this.repository.delete(toDelete);
		int afterRemove = 
				StreamSupport.stream(this.repository.findAll().spliterator(), false).collect(Collectors.toList()).size();
		assertEquals(afterRemove, 1);
	}
	
	@Test
	@DisplayName("call delete method unhappy path")
	void deleteHUP() {
		AlbumEntity toDelete = null;
		assertThrows(InvalidDataAccessApiUsageException.class, () -> this.repository.delete(toDelete));
	}
}
