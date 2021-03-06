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

package public_streaming;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class HashtagStream {
	private static final String CONSUMER_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    private static final String CONSUMER_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    private static final String ACCESS_TOKEN = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    private static final String ACCESS_TOKEN_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";

    static class MyStatusListener implements StatusListener {

        public void onStatus(Status status) {
            System.out.println("Tweet: " + status.getText());
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
        
        //set filter
        FilterQuery filter = new FilterQuery();
        String[] track = {"#nhk"};//hashtag
        filter.track(track);
        twStream.filter(filter);

    }

}
