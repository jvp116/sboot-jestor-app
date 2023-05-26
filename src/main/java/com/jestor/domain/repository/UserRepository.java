package com.jestor.domain.repository;

import com.jestor.domain.model.User;

public interface UserRepository {
	
	User getUser(Long id);
	User save(User user);
	void remove(User user);

}
