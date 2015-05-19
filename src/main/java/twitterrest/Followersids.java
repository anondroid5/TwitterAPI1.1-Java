package twitterrest;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.IDs;
import twitter4j.RateLimitStatus;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

import java.util.List;
import java.util.ArrayList;
/*特定のユーザのフォロワー情報を取得するプログラム*/
public class Followersids {
	//OAUTH認証
	final static String CONSUMER_KEY = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String CONSUMER_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String ACCESS_TOKEN = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	final static String ACCESS_TOKEN_SECRET = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
	
	 //フォロワーIDの一覧を得る
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
                // (メモ) status code も併せてチェックすべきか？！

                RateLimitStatus rateLimit = twitterException.getRateLimitStatus();
                int secondsUntilReset = rateLimit.getSecondsUntilReset();
                System.err.println("please wait for " + secondsUntilReset + " seconds");
                System.err.println("Reset Time : " +  rateLimit.getResetTimeInSeconds());
                
                //(注) 120秒間，水増し・・getSecondsUntilReset() の返す値が負の
                //場合があり，それに対応するため
                long waitTime = (long)(secondsUntilReset + 120) * 1000;
                //long waitTime = (long)(300 * 1000); // 300秒待ち
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

    public static void main(String[] args) throws TwitterException{

        String ScreenName = "anondroid5";//スクリーンネームの設定

    	// 認証キーを設定
    	Configuration configuration = new ConfigurationBuilder()
    			.setOAuthConsumerKey(CONSUMER_KEY)
    			.setOAuthConsumerSecret(CONSUMER_SECRET)
    			.setOAuthAccessToken(ACCESS_TOKEN)
    			.setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET)
    			.build();
        Twitter twitter = new TwitterFactory(configuration).getInstance();
        
        List<Long> followersList = followers(twitter, ScreenName);//フォロワーの一覧を得る
        System.out.println(ScreenName+":FolloweID List");
        for(int i = 0; i < followersList.size(); i++){
            System.out.println("["+(i+1)+"]" + followersList.get(i));
            //ユーザ情報の表示
            User user = twitter.showUser(followersList.get(i));
		System.out.println("User ID : " + user.getId());//UserID
    	    System.out.println("ScreenName : " + user.getScreenName());//ScreenName
    	    System.out.println("User's Name : " + user.getName());//UserName
    	    System.out.println("Number of Followers : " + user.getFollowersCount());//Number of Followers
    	    System.out.println("Number of Friends : " + user.getFriendsCount());//Number of Friends
    	    System.out.println("Language : " + user.getLang());//Language
        }
    }
}
