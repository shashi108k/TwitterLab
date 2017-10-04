package org.lab.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.lab.dao.mongo.IMongoDAO;
import org.lab.model.MongoUser;
import org.lab.service.IMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Description:	Field mongoService in org.lab.controller.MongoController required a bean of type 'org.lab.service.IMongoService' that could not be found.
 * Action:	Consider defining a bean of type 'org.lab.service.IMongoService' in your configuration.
 */
@Service
public class MongoService implements IMongoService {

	@Autowired
	private IMongoDAO mongoDAO;

	@Override
	public MongoUser saveOrUpdate(MongoUser customer) throws Exception {
		return mongoDAO.save(customer);
	}

	@Override
	public MongoUser getById(Serializable id) throws Exception {
		return mongoDAO.findOne((String) id);
	}

	@Override
	public List<MongoUser> getAll() throws Exception {
		return mongoDAO.findAll();
	}

	@Override
	public void deleteById(Serializable id) throws Exception {
		mongoDAO.delete((String) id);
	}

	@Override
	public void delete(MongoUser customer) throws Exception {
		mongoDAO.delete(customer);;
	}

	@Override
	public List<MongoUser> getByFirstName(String firstName) {
		return mongoDAO.findByFirstName(firstName);
	}

	@Override
	public List<MongoUser> getByLastName(String lastName) {
		return mongoDAO.findByLastName(lastName);
	}

	@Override
	public List<MongoUser> getByEmailId(String emailId) {
		return mongoDAO.findByEmailId(emailId);
	}

	@Override
	public List<MongoUser> getByAgeBetween(int ageGT, int ageLT) {
		return mongoDAO.findByAgeBetween(ageGT, ageLT);
	}

	@Override
	public List<MongoUser> getPremiumCustomers() {
		return mongoDAO.findPremiumCustomers();
	}

	@Override
	public MongoUser getByMaxCreationDate() {
		return mongoDAO.findTopByOrderByCreationDateDesc();
	}

	@Override
	public List<MongoUser> getAllByCreationDareDesc() {
		return mongoDAO.findAllByOrderByCreationDateDesc();
	}
	
}
