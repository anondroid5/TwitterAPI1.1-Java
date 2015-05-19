package user_streaming;

import twitter4j.DirectMessage;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.UserList;
import twitter4j.UserStreamAdapter;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class UserStream {
	//OAUTH認証を行う
	final static String CONSUMER_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String CONSUMER_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String ACCESS_TOKEN = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String ACCESS_TOKEN_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	
	static class MyStreamAdapter extends UserStreamAdapter {

        public void onStatus(Status status) {
            System.out.println("tweet: " + status.getText());
            System.out.println("@" + status.getUser().getScreenName() + " | " + status.getText() + " 【 https://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId() + " 】");
            // こんな感じでstatusについている名前とかを色々表示させるとさらに欲しい情報にたどり着けると思います
        }

        public void onStallWarning1(StallWarning arg0) {
            // TODO Auto-generated method stub
        }

		@Override
		public void onStallWarning(StallWarning arg0) {
            // TODO Auto-generated method stub
		}

		@Override
		public void onBlock(User arg0, User arg1) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onDeletionNotice(long arg0, long arg1) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onDirectMessage(DirectMessage arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onFavorite(User arg0, User arg1, Status arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onFollow(User arg0, User arg1) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onFriendList(long[] arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onUnblock(User arg0, User arg1) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onUnfavorite(User arg0, User arg1, Status arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onUserListCreation(User arg0, UserList arg1) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onUserListDeletion(User arg0, UserList arg1) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onUserListMemberAddition(User arg0, User arg1, UserList arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onUserListMemberDeletion(User arg0, User arg1, UserList arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onUserListSubscription(User arg0, User arg1, UserList arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onUserListUnsubscription(User arg0, User arg1, UserList arg2) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onUserListUpdate(User arg0, UserList arg1) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onUserProfileUpdate(User arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onDeletionNotice(StatusDeletionNotice arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onScrubGeo(long arg0, long arg1) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onTrackLimitationNotice(int arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onException(Exception arg0) {
			// TODO Auto-generated method stub
		}
    }

    public static void main(String[] args) throws Exception {
        Configuration configuration = new ConfigurationBuilder().setOAuthConsumerKey(CONSUMER_KEY)
        	.setOAuthConsumerSecret(CONSUMER_SECRET)
        	.setOAuthAccessToken(ACCESS_TOKEN)
        	.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET)
        	.setUserStreamBaseURL("https://userstream.twitter.com/2/")
        	.build();

        TwitterStream UserStream = new TwitterStreamFactory(configuration).getInstance();
        UserStream.addListener(new MyStreamAdapter());
        long[] follow = {1038644269};//ex)ユーザの情報をプロテクトアカウントを突破して取得できる。anondroid3のid:1598997848
        FilterQuery filter = new FilterQuery(follow);//特定のuserIDをカンマ区切りで指定してフィルターをかける。
        UserStream.filter(filter);
    }
}