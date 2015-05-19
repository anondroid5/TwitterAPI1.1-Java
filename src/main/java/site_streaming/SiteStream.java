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

package site_streaming;

import twitter4j.DirectMessage;
import twitter4j.FilterQuery;
import twitter4j.SiteStreamsListener;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class SiteStream {
	//OAUTH認証を行う
	final static String CONSUMER_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String CONSUMER_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String ACCESS_TOKEN = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String ACCESS_TOKEN_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	
	//Public streams：twitterに流れる全てのtweets
	static class MyStatusListener implements SiteStreamsListener {
        public void onStatus(Status status) {
            System.out.println("tweet: " + status.getText());
            System.out.println("@" + status.getUser().getScreenName() + " | " + status.getText() + " 【 https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId() + " 】");
            // こんな感じでstatusについている名前とかを色々表示させるとさらに欲しい情報にたどり着けると思います
        }

        public void onDeletionNotice(StatusDeletionNotice sdn) {
            System.out.println("onDeletionNotice.");
        }

        public void onTrackLimitationNotice(int i) {
            System.out.println("onTrackLimitationNotice.(" + i + ")");
        }

        public void onScrubGeo(long lat, long lng) {
            System.out.println("onScrubGeo.(" + lat + ", " + lng + ")");
        }

        public void onException(Exception excptn) {
            System.out.println("onException.");
        }

        

		public void onStallWarning(StallWarning arg0) {
			// TODO Auto-generated method stub			
		}

		@Override
		public void onBlock(long arg0, User arg1, User arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onDeletionNotice(long arg0, StatusDeletionNotice arg1) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onDeletionNotice(long arg0, long arg1, long arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onDirectMessage(long arg0, DirectMessage arg1) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onDisconnectionNotice(String arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onFavorite(long arg0, User arg1, User arg2, Status arg3) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onFollow(long arg0, User arg1, User arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onFriendList(long arg0, long[] arg1) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onStatus(long arg0, Status arg1) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onUnblock(long arg0, User arg1, User arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onUnfavorite(long arg0, User arg1, User arg2, Status arg3) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onUnfollow(long arg0, User arg1, User arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onUserListCreation(long arg0, User arg1, UserList arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onUserListDeletion(long arg0, User arg1, UserList arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onUserListMemberAddition(long arg0, User arg1, User arg2, UserList arg3) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onUserListMemberDeletion(long arg0, User arg1, User arg2, UserList arg3) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onUserListSubscription(long arg0, User arg1, User arg2, UserList arg3) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onUserListUnsubscription(long arg0, User arg1, User arg2, UserList arg3) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onUserListUpdate(long arg0, User arg1, UserList arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onUserProfileUpdate(long arg0, User arg1) {
			// TODO Auto-generated method stub
		}
    }

    public static void main(String[] args) throws Exception {
        Configuration configuration = new ConfigurationBuilder().setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET)
                .setSiteStreamBaseURL("https://sitestream.twitter.com/1.1/site.json")
                .build();

        TwitterStream SiteStream = new TwitterStreamFactory(configuration).getInstance();
        SiteStream.addListener(new MyStatusListener());
        
        long[] follow = {1598997848};//anondroid3のid:1598997848
        FilterQuery filter = new FilterQuery(follow);//特定のuserIDをカンマ区切りで指定してフィルターをかける。
        SiteStream.filter(filter);
    }
}
