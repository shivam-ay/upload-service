package com.example.demo.repository;

import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.document.Image;

@Repository(value = "imageRepo")
public interface ImageRepository extends ReactiveCouchbaseRepository<Image, Integer>
{
	
}
