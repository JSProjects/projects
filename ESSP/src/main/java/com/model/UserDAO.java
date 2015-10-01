package com.model;

import java.util.List;

public interface UserDAO {

	public void insert(User user);
	public List<User> fetch(User user);
	List<User> fetchforMail();
	int countUser(User user);
	public void updateUser(User user);
}
