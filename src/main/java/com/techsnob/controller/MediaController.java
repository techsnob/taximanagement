package com.techsnob.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediaController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@PostMapping(path = "/media")
	public ResponseEntity<byte[]> getImageAsResponseEntity(@RequestParam("filename") String filerequested, 
			@RequestParam("module") String module) {
	    HttpHeaders headers = new HttpHeaders();
	    //headers.setCacheControl(CacheControl.noCache().getHeaderValue());
	    headers.setContentType(MediaType.IMAGE_PNG);
	    //Query media = entityManager.createNativeQuery("SELECT "+filerequested+" FROM "+module+" WHERE ?1").setParameter(1, 1000).getResultList();
	    byte[] media = jdbcTemplate.queryForObject("SELECT "+filerequested+" FROM "+module+" WHERE ?", new Object[] {1000}, byte[].class);
	    ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
	    return responseEntity;
	}

}
