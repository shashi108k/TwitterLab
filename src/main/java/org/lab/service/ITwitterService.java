package org.lab.service;

import java.util.List;

import org.springframework.social.twitter.api.DirectMessage;
import org.springframework.social.twitter.api.Place;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TwitterProfile;

public interface ITwitterService {

	List<Tweet> getUserTimeline(String twitterUser);
	
	Tweet updateStatus(String status);
	
	void deleteStatus(Long tweetId);
	
	TwitterProfile getUserProfile(String twitterUser);
	
	List<DirectMessage> getDirectMessages(String twitterUser);
	
	List<TwitterProfile> getFollowers(String twitterUser);
	
	SearchResults search(String text);
	
	Place getPlace(String twitterUser);
	
	void streaming(String trackKeyword);
}
