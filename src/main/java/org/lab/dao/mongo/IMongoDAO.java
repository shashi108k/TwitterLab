package org.lab.dao.mongo;

import java.util.Date;
import java.util.List;

import org.lab.model.MongoUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IMongoDAO extends MongoRepository<MongoUser, String> {

	List<MongoUser> findByFirstName(String firstName);
	
	// @Query("{ 'lastName' : ?0 }")
	List<MongoUser> findByLastName(String lastName);
    
    @Query("{ 'emailId' : ?0 }")
    List<MongoUser> findByEmailId(String emailId);
	
    @Query("{ 'age' : { $gt: ?0, $lt: ?1 } }")
    List<MongoUser> findByAgeBetween(int ageGT, int ageLT);
    
    List<MongoUser> deleteByLastName(String lastName);

    @Query("{$match : {_id:{$lt:100}}  }, {	$group : {_id : \"$lastName\", points : { $sum : 1 }} }, {$sort : {points : -1}  }")
    List<MongoUser> findPremiumCustomers();
    
    MongoUser findTopByOrderByCreationDateDesc();
    
    List<MongoUser> findAllByOrderByCreationDateDesc();
}
