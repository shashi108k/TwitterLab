package org.lab.config;

import org.lab.model.MongoUser;
import org.lab.service.IMongoService;
import org.lab.service.impl.LiveScoreService;
import org.lab.service.impl.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.social.twitter.api.SearchResults;

@Configuration
@EnableScheduling
public class WebSocketScheduler {
        
    @Autowired
    private SimpMessagingTemplate template;
    
    @Autowired
    LiveScoreService service;
    
    @Autowired
    TwitterService twitterService;   
    
    @Autowired
    private IMongoService mongoService;

    @Scheduled(fixedRate = 3000)
    public void publishUpdates(){
        template.convertAndSend("/topic/myscores", service.getScore());
    }
    
    /**
     * 
     */
    @Scheduled(fixedRate = 50000)
    public void publishTweets(){  // sends tweets on subscribing to "/topic/tweets"
//    	template.convertAndSend("/topic/tweets", service.getScore());
    	
    	//SearchResults resultList = twitterService.search("jeckslvakiya");
    	SearchResults resultList = twitterService.search("HashTagName");
    	//SearchResults resultList = null;
	    if(null != resultList && (mongoService.getByMaxCreationDate().getCreationDate().before(resultList.getTweets().get(0).getCreatedAt()))){
	    	MongoUser user = new MongoUser();
			user.setTweet(resultList.getTweets().get(0).getText());
			user.setScreenName(resultList.getTweets().get(0).getUser().getScreenName());
			user.setProfileImageUrl(resultList.getTweets().get(0).getUser().getProfileImageUrl());
			user.setCreationDate(resultList.getTweets().get(0).getCreatedAt());
			
			try {
				mongoService.saveOrUpdate(user);
				template.convertAndSend("/topic/tweets", mongoService.getAllByCreationDareDesc());
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
       // template.convertAndSend("/topic/tweets", twitterService.search("jeckslvakiya"));
    }
    
 
}