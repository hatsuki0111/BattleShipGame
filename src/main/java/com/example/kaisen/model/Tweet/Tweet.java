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
            twitter.updateStatus("#BattleShipGameJava"+"\r\n"+"あなたは"+tweetKekka+"です"+"\r\n"+(new java.util.Date()));
        }catch (TwitterException e){
            e.printStackTrace();
        }
    }

    public void getTwitter () {
        if (twitter == null) {
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey("fjkaomowaioaLLLLLLLmaLLLLLL")
                    .setOAuthConsumerSecret("okokokokkkkokokoklololo2795837597927759832rljwnwewo")
                    .setOAuthAccessToken("1707492874907848-joajvmvproiOIIOOOOIOOIJKjjtkrlaa")
                    .setOAuthAccessTokenSecret("vanivupaAAAAAAAAAAAflakajj905782ajvjavjvjpavvv");
            TwitterFactory tf = new TwitterFactory(cb.build());
            twitter = tf.getInstance();
        }
    }
}
