package twitterrest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/*キーワードを指定して、投稿を検索するプログラム*/
public class SearchTweet {
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
		try {
			File file = new File("./file/tweets.txt");
				PrintWriter pw;
				//追記で書き込む
				pw = new PrintWriter(new BufferedWriter(new FileWriter(file,true)));
		
				// 検索ワードをセット（試しにバルスを検索）
				query.setQuery("バルス");
				query.setLang("ja");
				// 1度のリクエストで取得するTweetの数（100が最大）
				query.setCount(100);
				//指定期間に限定して取得したい場合
				//query.setSince("2013-06-13");
				//query.setUntil("2013-06-17");
				
				// 1500件（15ページ）最大数
				for (int i = 1; i <= 15; i++) {
					// 検索実行
					QueryResult result = twitter.search(query);
					System.out.println("ヒット数: " + result.getTweets().size());
					System.out.println("ページ数 : " + new Integer(i).toString());
	          
					// 検索結果を見てみる
					for (Status tweet : result.getTweets()) {
						// 本文
						String str = tweet.getText();
						System.out.println(str);
						//ユーザ情報
						System.out.println(tweet.getUser());
						//投稿時間
						System.out.println(tweet.getCreatedAt());
						// ハッシュタグとURLの削除	
						StringTokenizer sta = new StringTokenizer(str, " ");
						//トークンの出力
						while(sta.hasMoreTokens()) {
							String wk = sta.nextToken();
							if(wk.indexOf("#") == -1 && wk.indexOf("http") == -1
									&& wk.indexOf("RT") == -1 && wk.indexOf("@") == -1){
								pw.println(wk);
							}
						}
						String u = tweet.getUser().getName();
						pw.println(u);
					}
					if(result.hasNext()){
						query = result.nextQuery();
					}else{
						break;
					}
				}
	          pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
