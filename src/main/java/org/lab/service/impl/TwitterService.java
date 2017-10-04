package org.lab.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.lab.model.MongoUser;
import org.lab.service.IMongoService;
import org.lab.service.ITwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.twitter.api.DirectMessage;
import org.springframework.social.twitter.api.DirectMessageOperations;
import org.springframework.social.twitter.api.FriendOperations;
import org.springframework.social.twitter.api.GeoOperations;
import org.springframework.social.twitter.api.Place;
import org.springframework.social.twitter.api.SearchOperations;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Stream;
import org.springframework.social.twitter.api.StreamDeleteEvent;
import org.springframework.social.twitter.api.StreamListener;
import org.springframework.social.twitter.api.StreamWarningEvent;
import org.springframework.social.twitter.api.StreamingOperations;
import org.springframework.social.twitter.api.TimelineOperations;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.UserOperations;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

@Service
public class TwitterService implements ITwitterService {
	
	//@Autowired
	private TwitterTemplate twitter;
		
	@Value("${spring.social.twitter.appId}")
    private String consumerKey;
 
    @Value("${spring.social.twitter.appSecret}")
    private String consumerSecret;
 
    @Value("${spring.social.twitter.accessToken}")
    private String accessToken;
 
    @Value("${spring.social.twitter.accessTokenSecret}")
    private String accessTokenSecret;

    @Autowired
    private IMongoService mongoService;
    
   /* @Autowired
    TwitterStreamIngester ingester;*/
    
    
    /* 
     * constructor gets called before properties variable initialization. Hence use @PostConstruct
     * 
	public TwitterService(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret) {
		twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}*/

	@PostConstruct
	public void setTwitter() {
		twitter = new TwitterTemplate(consumerKey, consumerSecret, accessToken, accessTokenSecret);
	}
	
	public List<Tweet> getUserTimeline(String twitterUser) {
		TimelineOperations timelineOperations = twitter.timelineOperations();
		List<Tweet> tweetList = timelineOperations.getUserTimeline("@" + twitterUser);
		return tweetList;
	}
	
	public Tweet updateStatus(String status) {
		TimelineOperations timelineOperations = twitter.timelineOperations();
		Tweet tweet = timelineOperations.updateStatus(status);
		
		MongoUser user = new MongoUser();
		user.setTweet(tweet.getText());
		user.setScreenName(tweet.getUser().getScreenName());
		user.setProfileImageUrl(tweet.getProfileImageUrl());
		user.setCreationDate(tweet.getCreatedAt());
		try {
			mongoService.saveOrUpdate(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return tweet;
	}
	
	public void deleteStatus(Long tweetId) {
		TimelineOperations timelineOperations = twitter.timelineOperations();
		timelineOperations.deleteStatus(tweetId);
	}

	public TwitterProfile getUserProfile(String twitterUser) {
		UserOperations userOperations = twitter.userOperations();
		TwitterProfile userProfile = userOperations.getUserProfile(twitterUser);
		return userProfile;
	}
	
	public List<DirectMessage> getDirectMessages(String twitterUser) {
		DirectMessageOperations directMessagesOperations = twitter.directMessageOperations();
		List<DirectMessage> directMessageList = directMessagesOperations.getDirectMessagesReceived();
		return directMessageList;
	}
	
	public List<TwitterProfile> getFollowers(String twitterUser) {
		FriendOperations friendOperations = twitter.friendOperations();
		List<TwitterProfile> followerList = friendOperations.getFollowers();
		return followerList;
	}
	
	public SearchResults search(String text) {
		SearchOperations searchOperations = twitter.searchOperations();
		SearchResults resultList = searchOperations.search(text);  // crawling
		//SearchResults resultList = null;
		if(null != resultList && null != resultList.getTweets() && resultList.getTweets().size() > 0){
			System.out.println(text + " resultList:: " + resultList.getTweets().get(0).getText());
		}
		
		return resultList;
	}

	public void streaming(String trackKeyword) {
		
		// Listener interface for clients consuming data from a Twitter stream.
		StreamListener listener = new StreamListener() {
	        @Override
	        public void onTweet(Tweet tweet) { // On tweet, retweet(RT), reply
	            System.out.println("Tweet :: " + tweet.getFromUser() + " : " + tweet.getCreatedAt() + " : " + tweet.getText() + " : " + tweet.getProfileImageUrl());
	            MongoUser user = new MongoUser();
				user.setTweet(tweet.getText());
				user.setScreenName(tweet.getUser().getScreenName());
				user.setProfileImageUrl(tweet.getProfileImageUrl());
				user.setCreationDate(tweet.getCreatedAt());
				
				try {
					mongoService.saveOrUpdate(user);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }

	        @Override
	        public void onDelete(StreamDeleteEvent deleteEvent) {
	        	System.out.println("delete event called on stream :: " + deleteEvent.getTweetId());
	        }

	        @Override
	        public void onLimit(int numberOfLimitedTweets) {
	        	System.out.println("stream is being track limited");
	        }

	        @Override
	        public void onWarning(StreamWarningEvent warningEvent) {
	        	System.out.println("warning from twitter :: " + warningEvent.getCode() + ":" + warningEvent.getMessage());
	            }
	        };
	    
		StreamingOperations streamOperations = twitter.streamingOperations();
		Stream stream = streamOperations.filter(trackKeyword, Arrays.asList(listener));
	}

	// "Sorry, that page does not exist"
	public Place getPlace(String twitterUser) {
		GeoOperations geoOperations = twitter.geoOperations();
		Place place = geoOperations.getPlace("mumbai");
		return place;
	}
}
