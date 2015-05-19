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

package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import twitter4j.*;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.TwitterFactory;


/*ShowUserの実践向け*/
public class UserInfo {
	//OAUTH認証を行う
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
		String screenName ="";
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("調べたいユーザのScreenNameを入力してください!! ex)masason : ");
		screenName = br.readLine();
		//String screenName = "masason";//masason
		try{
			//ユーザ情報を取得&表示
			User user = tw.showUser(screenName);
			System.out.println("↓ここからユーザ情報を表示");
			System.out.println("User ID : " + user.getId());
			System.out.println("ScreenName : " + user.getScreenName());
			System.out.println("User's Name : " + user.getName());
			System.out.println("Number of Followers : " + user.getFollowersCount());
			System.out.println("Number of Friends : " + user.getFriendsCount());
			System.out.println("Language : " + user.getLang());
			//ユーザステータスの表示
			Status status = user.getStatus();
			System.out.println("↓ここからユーザステータスを表示");
			System.out.println("User Created : " + status.getCreatedAt());
			System.out.println("Status ID : " + status.getId());
			System.out.println(status.getSource());
			System.out.println("Tweet" + status.getText());
				
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
