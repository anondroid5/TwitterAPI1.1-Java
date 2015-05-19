package twitterrest;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/*緯度,経度情報を指定して範囲内の投稿を取得するプログラム*/
public class GeoSearch {
	//OAUTH認証
	final static String CONSUMER_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String CONSUMER_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String ACCESS_TOKEN = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String ACCESS_TOKEN_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	
	public static void main(String[] args) throws TwitterException {
		// 認証キーを設定
    	Configuration configuration = new ConfigurationBuilder()
    			.setOAuthConsumerKey(CONSUMER_KEY)
    			.setOAuthConsumerSecret(CONSUMER_SECRET)
    			.setOAuthAccessToken(ACCESS_TOKEN)
    			.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET)
    			.build();		
		Twitter twitter = new TwitterFactory(configuration).getInstance();
		Query query = new Query();

		// 緯度経度を使って新宿区役所あたりから10km四方を設定（IPアドレスでも指定できるらしい）		
		GeoLocation geo = new GeoLocation(35.69384, 139.703549);
		query.setGeoCode(geo, 10.0, Query.KILOMETERS);

		// 検索実行
		QueryResult result = twitter.search(query);

		// これで新宿のTweetがとれる（placeやgeoLocationはほぼ空だけど）
		for (Status tweet : result.getTweets()) {
			System.out.println(tweet.getText());
			System.out.println(tweet.getPlace() + " : " + tweet.getGeoLocation());
		}
	}
}