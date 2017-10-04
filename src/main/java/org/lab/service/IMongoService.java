package org.lab.service;

import java.util.Date;
import java.util.List;

import org.lab.model.MongoUser;

public interface IMongoService extends ICRUDService<MongoUser>{

	List<MongoUser> getByFirstName(String firstName);
	
    List<MongoUser> getByLastName(String lastName);
    
    List<MongoUser> getByEmailId(String emailId);
    
    List<MongoUser> getByAgeBetween(int ageGT, int ageLT);
    
    List<MongoUser> getPremiumCustomers();
    
    MongoUser getByMaxCreationDate();
    
    List<MongoUser> getAllByCreationDareDesc();
}
