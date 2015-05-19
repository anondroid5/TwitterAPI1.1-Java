/*
 * Copyright 2007 Yusuke Yamamoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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