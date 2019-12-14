package com.techsnob.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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
	    String primaryKeyColumnname = null;
	    if(moduleName.equals("drivers")){
			primaryKeyColumnname = "driver_id";
		} else if (moduleName.equals("vehicles")){
			primaryKeyColumnname = "vehicle_id";
		}
	    byte[] media = jdbcTemplate.queryForObject("SELECT "+fileName+" FROM "+moduleName+" WHERE "+primaryKeyColumnname+"=?", new Object[] {Long.valueOf(columnId)}, byte[].class);
	    ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
	    return responseEntity;
	}

	@GetMapping(path = "/delete")
	public ResponseEntity<String> deleteMedia(@RequestParam("fileName") String fileName,
														   @RequestParam("moduleName") String moduleName,
														   @RequestParam("contentType") String contentType,
														   @RequestParam("columnId") String columnId) {
		HttpHeaders headers = new HttpHeaders();
		String primaryKeyColumnname = null;
		if(moduleName.equals("drivers")){
			primaryKeyColumnname = "driver_id";
		} else if (moduleName.equals("vehicles")){
			primaryKeyColumnname = "vehicle_id";
		}
		jdbcTemplate.update("UPDATE "+moduleName+" SET "+fileName+"=null"+" WHERE "+primaryKeyColumnname+"=?", Long.valueOf(columnId));
		return new ResponseEntity<>("Deleted", headers, HttpStatus.OK);
	}

	@PostMapping(path = "/updateMedia", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void updateMedia(@RequestParam("fileName") String fileName,
							@RequestParam("insurance") MultipartFile file,
							@RequestParam("moduleName") String moduleName,
							@RequestParam("columnId") String columnId) throws IOException {
		String primaryKeyColumnname = null;
		if(moduleName.equals("drivers")){
			primaryKeyColumnname = "driver_id";
		} else if (moduleName.equals("vehicles")){
			primaryKeyColumnname = "vehicle_id";
		}
		String sql = "UPDATE "+moduleName+" SET "+fileName+"=?,"+fileName.concat("_type")+"=?"+" WHERE "+primaryKeyColumnname+"=?";
		jdbcTemplate.update(sql, new Object[]{  file.getBytes(),file.getContentType(),  Long.valueOf(columnId)});
	}

}
