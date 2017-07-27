package com.ddbullfrog.repository;

import com.ddbullfrog.domain.ShortURL;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortURLRepository extends CrudRepository<ShortURL, Integer> {
    Optional<ShortURL> findBySlug(String slug);
}
