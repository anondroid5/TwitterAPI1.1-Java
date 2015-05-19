package twitterrest;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/*特定のユーザの情報を指定するプログラム*/
public class UserSearch {
	//OAUTH認証
	final static String CONSUMER_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String CONSUMER_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String ACCESS_TOKEN = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String ACCESS_TOKEN_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	
	public static void main(String[] args) throws TwitterException {	
		//認証キーを設定
    	Configuration configuration = new ConfigurationBuilder()
			.setOAuthConsumerKey(CONSUMER_KEY)
			.setOAuthConsumerSecret(CONSUMER_SECRET)
			.setOAuthAccessToken(ACCESS_TOKEN)
			.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET)
			.build();		
		Twitter twitter = new TwitterFactory(configuration).getInstance();
		Query query = new Query();

		// 検索ワードをセット（masasonを含む日本語Tweet）
		//query.setQuery("from:anondroid3 OR to:anondroid3");
		query.setQuery("from:anondroid3");

		// 検索して結果を表示
		QueryResult result = twitter.search(query);
		//ヒット数
		System.out.println("ヒット数:" + result.getTweets().size());
		// 1度のリクエストで取得するTweetの数（100が最大）
		query.setCount(100);
		
		for (Status tweet : result.getTweets()){
			System.out.println("tweet:"+tweet.getText());//ツイート内容
			System.out.println("UserID:"+tweet.getUser().getId());//ユーザID
			System.out.println("Application:"+tweet.getSource());//投稿アプリ
			System.out.println("Created Date:"+tweet.getCreatedAt());//アカウント作成日
			System.out.println("GeoLocation:"+tweet.getGeoLocation());//投稿場所
		}
	}
}
