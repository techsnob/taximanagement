package com.techsnob.repository;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface MediaRepository {
	byte[] getMediaContent();
}
