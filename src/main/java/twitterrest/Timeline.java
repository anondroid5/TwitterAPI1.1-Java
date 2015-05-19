package twitterrest;

import java.util.List;

import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.TwitterFactory;


public class Timeline {
	//OAUTH認証
	final static String CONSUMER_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String CONSUMER_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String ACCESS_TOKEN = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String ACCESS_TOKEN_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	
	public static void main(String[] args) throws TwitterException {
		
		Configuration configuration = new ConfigurationBuilder().setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET)
                .build();
		
    	Twitter tw = new TwitterFactory(configuration).getInstance();
			List<Status> list = tw.getUserTimeline("masason");//ScreenName[anondroid5]
			
			for(Status status: list){
				System.out.print(status.getCreatedAt()+" ");//CreatedDate
				System.out.print(status.getUser().getScreenName()+" ");//ScreenName
				System.out.println(status.getText());//tweet
			}
	}
}
