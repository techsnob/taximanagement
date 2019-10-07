package com.techsnob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techsnob.entitiy.MediaFile;

@RestController
public class MediaController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@PostMapping(path = "/media", consumes = "application/json", produces="application/json")
	public ResponseEntity<byte[]> getImageAsResponseEntity(@RequestBody MediaFile mediaFile) {
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.valueOf(mediaFile.getContentType()));
	    byte[] media = jdbcTemplate.queryForObject("SELECT "+mediaFile.getFileName()+" FROM "+mediaFile.getModuleName()+" WHERE ?", new Object[] {Long.valueOf(mediaFile.getColumnId())}, byte[].class);
	    ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
	    return responseEntity;
	}

}
