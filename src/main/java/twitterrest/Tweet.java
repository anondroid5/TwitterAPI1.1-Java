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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.TwitterFactory;


public class Tweet {
	//OAUTH認証
	final static String CONSUMER_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String CONSUMER_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String ACCESS_TOKEN = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String ACCESS_TOKEN_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		
	public static void main(String[] args) throws IOException, TwitterException {		
		//認証キーを設定
    	Configuration configuration = new ConfigurationBuilder()
			.setOAuthConsumerKey(CONSUMER_KEY)
			.setOAuthConsumerSecret(CONSUMER_SECRET)
			.setOAuthAccessToken(ACCESS_TOKEN)
			.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET)
			.build();
    	Twitter tw = new TwitterFactory(configuration).getInstance();
		//dateを取得
		Date date = new Date();
		DateFormat df =new SimpleDateFormat("yyyy-MM-dd (EE) HH:mm:ss");
		
		//tweet Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String tweet = "";
		while(tweet.isEmpty()) {
			System.out.print("今何してる ? : ");
			tweet = br.readLine();
			if(tweet.length() > 140) {
				System.out.println("字数制限を超えています。");
				tweet = "";
				continue;
			}
		}
	    Status status = tw.updateStatus(tweet + df.format(date));//tweet
		System.out.println(status.getUser().getScreenName() + " として投稿しました : " + status.getText());
		br.close();
	}
}
