package allenwang.twitterclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {
    private static final String TAG  = MainActivity.class.getSimpleName();
    private static String TWITTER_KEY;
    private static String TWITTER_SECRET;

    private TwitterLoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TWITTER_KEY = getString(R.string.twitter_api_key);
        TWITTER_SECRET = getString(R.string.twitter_api_key);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);
        loginButton.setEnabled(true);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Log.d(TAG, String.valueOf(result.data.getUserId()));
                Log.d(TAG, String.valueOf(result.data.getUserName()));
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
            }
        });


    }
}
