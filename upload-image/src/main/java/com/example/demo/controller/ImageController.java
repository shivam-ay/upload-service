package com.example.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.couchbase.client.java.Collection;
import com.couchbase.client.java.codec.RawBinaryTranscoder;
import com.couchbase.client.java.kv.GetOptions;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.kv.UpsertOptions;
import com.example.demo.document.Image;
import com.example.demo.repository.ImageRepository;
import com.fasterxml.jackson.databind.node.BinaryNode;

import reactor.core.publisher.Mono;

@RestController
public class ImageController
{
	@Autowired
	private ImageRepository imageRepo;
	
	@PostMapping("/images")
	public Mono<Image> addImage(@RequestParam("image") MultipartFile image) throws IOException
	{
		System.out.println(image.getName());
		return imageRepo.save(new Image(1,new BinaryNode(image.getBytes())));
	}
	@GetMapping(value = "/images/{id}")//, produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	public Mono<Object> getImage(@PathVariable int id)
	{
//		 Collection collection = null;
		 
		 return imageRepo.findById(id)
		 			.map(img -> {
		 				return img.getImage().binaryValue();
//		 				collection.upsert("1",img.getImage(),UpsertOptions.upsertOptions().transcoder(RawBinaryTranscoder.INSTANCE));
//		 				GetResult result = collection.get("1",GetOptions.getOptions().transcoder(RawBinaryTranscoder.INSTANCE));
//		 				return result.contentAs(byte[].class);
					});
	}
}
