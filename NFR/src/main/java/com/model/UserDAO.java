package com.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface UserDAO {

	public void insert(User user);
	public List<User> fetch(User user);
	Map<String,List<PartnerHistory>> fetchforMail();
	int countUser(User user);
	public void updateUser(Map<String,List<PartnerHistory>> userlist);
    public void saveDetails(PartnerDAO user, String name);
    public List<PartnerHistory> getHistory(String name);
    public void deactivateKeys(List<String> cancelKeys);
	public ArrayList<String> getSerialNumbers();
	public ArrayList<String> getActivatedSerialNumbers();
}
