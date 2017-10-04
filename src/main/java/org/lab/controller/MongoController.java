package org.lab.controller;

import java.util.List;

import org.lab.model.MongoUser;
import org.lab.service.IMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
@RequestMapping(value = "/mongo", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}) 
public class MongoController {

	@Autowired
	private IMongoService mongoService;
	
	// Welcome page, non-rest
	@RequestMapping("/")
	public String welcome() {
		return "Welcome to Mongo Laboratory !!";
	}

	@GetMapping
	public List<MongoUser> getDocumentsList() throws Exception {
		return mongoService.getAll();	// by default it returns Http status 200.
	}

	/*
	/*  @RequestMapping("/{id}")
	 *  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	 *  <?> : To send any type of response to Postman or UI or browser. Java classes will call service layer method which have proper return type.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getDocumentById(@PathVariable("id") String id) throws Exception {

		// logger.info("In getDocumentById");
		MongoUser document = null;
		try {
			document = mongoService.getById(id);
			if (null == document) {
				//return (ResponseEntity<?>) ResponseEntity.notFound(); // 404, it says url not found because mentioned id in url is not present
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			//logger.error("Exception occurred: "+ e.getMessage(), e);  // print thrown/occurred exception stack-trace (not error)  
			throw e;												// throws error and handled by GlobalExceptionHandler
		}
		// logger.debug("Exit getDocumentById : " + document);

		return ResponseEntity.ok(document); // 200
		// return new ResponseEntity<>(document, HttpStatus.OK);
	}
	
	/*
	 *  @RequestMapping // It can work for POST, PUT, PATCH etc.
	 *  @RequestMapping(method = RequestMethod.POST)
	 */
	@PostMapping
	public ResponseEntity<MongoUser> saveOrUpdateDocument(@RequestBody MongoUser document) throws Exception {
		MongoUser response = mongoService.saveOrUpdate(document);
		if (null == response) {
			//return (ResponseEntity<MongoUser>) ResponseEntity.noContent(); // 204
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(response, HttpStatus.CREATED); // 201
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteDocumentById(@PathVariable String id) throws Exception {
		if (null == mongoService.getById(id)) {
			//return (ResponseEntity<String>) ResponseEntity.notFound(); // 404, it says url not found because mentioned id in url is not present
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		mongoService.deleteById(id);
		return ResponseEntity.ok("Document has been deleted successfully.");
	}
	
	@GetMapping("/firstname")
	public List<MongoUser> getCustomerByFirstName(@RequestParam String firstName) throws Exception {
		return mongoService.getByFirstName(firstName);	
	}
	
	@GetMapping("/lastname")
	public List<MongoUser> getCustomerByLastName(@RequestParam String lastName) throws Exception {
		return mongoService.getByLastName(lastName);	
	}
	
	@GetMapping("/emailId")
	public List<MongoUser> getCustomerByEmailId(@RequestParam String emailId) throws Exception {
		return mongoService.getByEmailId(emailId);	
	}

	@GetMapping("/age")
	public List<MongoUser> getCustomerByAge(@RequestParam int minAge, 
												@RequestParam int maxAge) throws Exception {
		return mongoService.getByAgeBetween(minAge, maxAge);
	}
	
	@GetMapping("/premium")
	public List<MongoUser> getPremiumCustomers() throws Exception {
		return mongoService.getPremiumCustomers();	
	}
	
}
