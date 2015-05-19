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
import java.util.List;
import java.util.ArrayList;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.IDs;
import twitter4j.RateLimitStatus;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;


/*Followersidsの実践向け*/
public class UserFollowers {
	//OAUTH認証を行う
	final static String CONSUMER_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String CONSUMER_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String ACCESS_TOKEN = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String ACCESS_TOKEN_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	
	//フォロワーの一覧を得る
    static List<Long> followers(Twitter twitter, String screenName){
        List<Long> m_FollowersList = new ArrayList<Long>();

        long cursor = -1;
        //int count = 0;
        while(true) {
            IDs ids = null;
            try {
                //IDs ids = twitter.getFollowersIDs(user.getId(), cursor);
                ids = twitter.getFollowersIDs(screenName, cursor);
            } catch(TwitterException twitterException){
            	// Rate Limit に引っかかった場合の処理
            	// (memo) status code も併せてチェックすべきか？！

                RateLimitStatus rateLimit = twitterException.getRateLimitStatus();
                int secondsUntilReset = rateLimit.getSecondsUntilReset();
                System.err.println("please wait for " + secondsUntilReset + " seconds");
                System.err.println("Reset Time : " +  rateLimit.getResetTimeInSeconds());
                
                //(注) 120秒間，水増し・getSecondsUntilReset() の返す値が負の場合があり，それに対応するため
                // long waitTime = (long)(secondsUntilReset + 120) * 1000;
                long waitTime = (long)(300 * 1000); // 300秒
                try {
                    Thread.sleep(waitTime);
                } catch(Exception e){
                    e.printStackTrace();
                }

                continue;
            } catch(Exception e){
                e.printStackTrace();
            }
            
            long[] idArray = ids.getIDs();
            for(int i = 0; i < idArray.length; i++){
                //System.out.println("["+(++count)+"]" + idArray[i]);
                m_FollowersList.add(new Long(idArray[i]));
            }
            
            if(ids.hasNext()){
                cursor = ids.getNextCursor();
            } else {
                break;
            }
        }
        return m_FollowersList;
    }

    public static void main(String[] args) throws TwitterException, IOException{
    	//認証キーを設定
    	Configuration configuration = new ConfigurationBuilder()
			.setOAuthConsumerKey(CONSUMER_KEY)
			.setOAuthConsumerSecret(CONSUMER_SECRET)
			.setOAuthAccessToken(ACCESS_TOKEN)
			.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET)
			.build();		
    	Twitter tw = new TwitterFactory(configuration).getInstance();
        //String screenName = "masason";
		String screenName ="";
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("フォロワー情報を調べる対象ユーザのScreenNameを入力してください!! ex)masason : ");
		screenName = br.readLine();

        //フォロワーの一覧を得る
        List<Long> followersList = followers(tw, screenName);
        for(int i = 0; i < followersList.size(); i++){
        	//これでユーザのid→screenName変換実装
        	User user = tw.showUser(followersList.get(i));
            System.out.println("["+(i+1)+"]" + "ScreenName : " + "["+ user.getScreenName() +"]  " + "UserIds : " +followersList.get(i));
        }
    }
}
