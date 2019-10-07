package com.techsnob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MediaController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping(path = "/media")
	public ResponseEntity<byte[]> getImageAsResponseEntity(@RequestParam("fileName") String fileName,
			@RequestParam("moduleName") String moduleName,
			@RequestParam("contentType") String contentType,
			@RequestParam("columnId") String columnId) {
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.valueOf(contentType));
	    byte[] media = jdbcTemplate.queryForObject("SELECT "+fileName+" FROM "+moduleName+" WHERE ?", new Object[] {Long.valueOf(columnId)}, byte[].class);
	    ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
	    return responseEntity;
	}

}
