package com.example.kaisen.model.Tweet;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class Tweet {
    public Twitter twitter;

    public void doHogeHoge(String tweetKekka) {
        getTwitter();
        try {
            //先頭に#でハッシュタグ
            twitter.updateStatus("#BattleShipGameJava"+"\r\n"+"あなたは"+tweetKekka+"です");
        }catch (TwitterException e){
            e.printStackTrace();
        }
    }

    public void getTwitter () {
        if (twitter == null) {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("dayQHbvKvSd5SpiyD5K6JwKLM")
                    .setOAuthConsumerSecret("zMHk7665aZmimU7SdGVAsOMLyvFJuACoWOVatvuxUj5cdQFxSD")
                    .setOAuthAccessToken("1051757940879765504-p0URTijS6VhCFCf8BO8X3UFQI5z8Ff")
                    .setOAuthAccessTokenSecret("Avu95L2CimMA8Zhg7XJOnoxPbSyyJ1M5hKjYxewMxzjUw");
            TwitterFactory tf = new TwitterFactory(cb.build());
            twitter = tf.getInstance();
        }
    }
}
