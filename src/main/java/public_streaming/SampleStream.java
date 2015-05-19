package public_streaming;

import twitter4j.Status;
import twitter4j.StatusAdapter;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

//実践用
public class SampleStream {
	//OAUTH認証
	final static String CONSUMER_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String CONSUMER_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String ACCESS_TOKEN = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String ACCESS_TOKEN_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	
	public static void main(String[] args) {
		// 認証キーを設定
    	Configuration configuration = new ConfigurationBuilder()
			.setOAuthConsumerKey(CONSUMER_KEY)
			.setOAuthConsumerSecret(CONSUMER_SECRET)
			.setOAuthAccessToken(ACCESS_TOKEN)
			.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET)
			.build();
		// TwitterStreamのインスタンス作成
		TwitterStream twitterStream = new TwitterStreamFactory(configuration).getInstance();
		// Listenerを登録
		twitterStream.addListener(new Listener());
		// 実行（この処理はエラーにならない限り終了しない）
		twitterStream.sample();
	}
}

/** Tweetを出力するだけのListener */
class Listener extends StatusAdapter {
	// Tweetを受け取るたびにこのメソッドが呼び出される
	public void onStatus(Status status) {
		System.out.println("tweet:"+status.getText());//ツイート内容
		System.out.println(status.getFavoriteCount());
		System.out.println("User："+status.getUser().getName()+"@"+status.getUser().getScreenName());//ユーザ名,スクリーンネーム(@xx)
		System.out.println("Posted Time:"+status.getCreatedAt());//投稿時間
		System.out.println("Application Name:"+status.getSource());//投稿アプリ
		System.out.println("Time Zone:"+status.getUser().getTimeZone());//タイムゾーン
		System.out.println("Created Date:"+status.getUser().getCreatedAt());//アカウント作成日
		System.out.println("GeoLocation:"+status.getUser().getLocation());//位置情報
		System.out.println("postID:"+status.getId());//投稿ID
		System.out.println("UserID"+status.getUser().getId());//ユーザID
		System.out.println("Language:"+status.getUser().getLang());//使用言語
		System.out.println("Follow:"+status.getUser().getFriendsCount());//フォロー数
		System.out.println("Follower:"+status.getUser().getFollowersCount());//フォロワー数
	}
}
