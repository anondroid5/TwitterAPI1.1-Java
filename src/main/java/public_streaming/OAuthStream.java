package public_streaming;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class OAuthStream {
	//OAUTH認証
	final static String CONSUMER_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String CONSUMER_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String ACCESS_TOKEN = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String ACCESS_TOKEN_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	
	static class MyStatusListener implements StatusListener {

		public void onStatus(Status status) {
            System.out.println("Status: " + status.getText());
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

        public void onStallWarning1(StallWarning arg0) {
            // TODO Auto-generated method stub
        }

		@Override
		public void onStallWarning(StallWarning arg0) {
            // TODO Auto-generated method stub			
		}
    }

    public static void main(String[] args) throws Exception {
        Configuration configuration = new ConfigurationBuilder().setOAuthConsumerKey(CONSUMER_KEY)
        	.setOAuthConsumerSecret(CONSUMER_SECRET)
        	.setOAuthAccessToken(ACCESS_TOKEN)
        	.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET)
        	.build();

        TwitterStream twStream = new TwitterStreamFactory(configuration).getInstance();
        twStream.addListener(new MyStatusListener());
        twStream.user();//OAuth認証しているユーザをpublic_timelineから取得
    }
}