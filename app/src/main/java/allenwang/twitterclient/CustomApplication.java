package allenwang.twitterclient;

import android.app.Application;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

/**
 * Created by allenwang on 2017/3/2.
 */

public class CustomApplication extends Application {

    private static String TWITTER_KEY;
    private static String TWITTER_SECRET;


    @Override
    public void onCreate() {
        super.onCreate();
        TWITTER_KEY = getString(R.string.twitter_api_key);
        TWITTER_SECRET = getString(R.string.twitter_secret);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
    }
}
