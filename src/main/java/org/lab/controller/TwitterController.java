package org.lab.controller;

import java.util.List;

import org.lab.service.ITwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.social.twitter.api.DirectMessage;
import org.springframework.social.twitter.api.Place;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@RestController
@RequestMapping(value = "/twitter", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}) 
public class TwitterController {

	@Autowired
	private ITwitterService twitterService;
	
	/*@Autowired
	private Twitter twitter;
	
	@Autowired
	private ConnectionRepository connectionRepository;
*/
	@GetMapping("/timeline/{twitterUser}")
	public List<Tweet> getUserTimeline(@PathVariable String twitterUser) {
		List<Tweet> tweets = twitterService.getUserTimeline(twitterUser);
		return tweets;
	}
	
	@PostMapping("/timeline/status")
	public Tweet updateStatus(@RequestParam String status) {
		Tweet tweet = twitterService.updateStatus(status);
		return tweet;
	}

	@DeleteMapping("/timeline/status/{tweetId}")   //  "id": 909679272633049088,
	public void deleteStatus(@PathVariable Long tweetId) {
		twitterService.deleteStatus(tweetId);
	}
	
	@GetMapping("/profile/{twitterUser}")
	public TwitterProfile getUserProfile(@PathVariable String twitterUser) {
		TwitterProfile userProfile = twitterService.getUserProfile(twitterUser);
		return userProfile;
	}
	
	@GetMapping("/messages/{twitterUser}")
	public List<DirectMessage> getDirectMessages(@PathVariable String twitterUser) {
		List<DirectMessage> directMessageList = twitterService.getDirectMessages(twitterUser);
		return directMessageList;
	}
	
	@GetMapping("/followers/{twitterUser}")
	public List<TwitterProfile> getFollowers(@PathVariable String twitterUser) {
		List<TwitterProfile> followerList = twitterService.getFollowers(twitterUser);
		return followerList;
	}
	
	@GetMapping("/search")
	public SearchResults search(@RequestParam String text) {
		SearchResults resultList = twitterService.search(text);
		return resultList;
	}
	
	@GetMapping("/streaming")
	public void streaming(@RequestParam String keyword) {
		twitterService.streaming(keyword);
		//return resultList;
	}
	
	@GetMapping("/place/{twitterUser}")
	public Place getPlace(@PathVariable String twitterUser) {
		Place place = twitterService.getPlace(twitterUser);
		return place;
	}

	/*@RequestMapping(method=RequestMethod.GET)
	public String helloTwitter(Model model, @ModelAttribute("tweet") String tweet) {
	    if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
	        return "redirect:/connect/twitter";
	    }
	        
	    twitter.timelineOperations().updateStatus(tweet);
	    model.addAttribute("tweets", twitter.timelineOperations().getHomeTimeline());
	        
	    return "hellotwitter";
	}*/
}
