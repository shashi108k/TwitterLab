package org.lab.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class MongoUser {

	@Id
	private String id;

	private String firstName;

	private String lastName;

	private String emailId;

	private Integer age;

	private String screenName;
	
	private String profileImageUrl;
	
	private String tweet;

	private Integer points;

	private Boolean isDeleted;

	private Date creationDate;

	private Date lastUpdatedDate = new Date();

	private List<MongoProduct> products;

	public MongoUser() {
	}

	public MongoUser(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getProfileImageUrl() {
		return profileImageUrl;
	}

	public void setProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}

	public String getTweet() {
		return tweet;
	}

	public void setTweet(String tweet) {
		this.tweet = tweet;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public List<MongoProduct> getProducts() {
		return products;
	}

	public void setProducts(List<MongoProduct> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "MongoUser [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", age=" + age + ", screenName=" + screenName + ", profileImageUrl=" + profileImageUrl + ", tweet="
				+ tweet + ", points=" + points + ", isDeleted=" + isDeleted + ", creationDate=" + creationDate
				+ ", lastUpdatedDate=" + lastUpdatedDate + ", products=" + products + "]";
	}

}
