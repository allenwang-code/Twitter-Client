package allenwang.twitterclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import allenwang.twitterclient.viewpager.ViewPagerActivity;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG  = LoginActivity.class.getSimpleName();

    private TwitterLoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setLoginButton();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // handle cancelled Twitter login (resets TwitterCore.*AuthHandler.AuthState)
        final TwitterAuthClient twitterAuthClient = new TwitterAuthClient();
        if(twitterAuthClient.getRequestCode() == requestCode) {
            boolean twitterLoginWasCanceled = (resultCode == RESULT_CANCELED);
            twitterAuthClient.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void setLoginButton() {
        loginButton = (TwitterLoginButton) findViewById(R.id.login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Log.d(TAG, String.valueOf(result.data.getUserId()));
                Log.d(TAG, String.valueOf(result.data.getUserName()));

                Util.saveId(LoginActivity.this, result.data.getUserId());
                Util.saveName(LoginActivity.this, result.data.getUserName());

                Intent i = new Intent();
                i.setClass(LoginActivity.this, ViewPagerActivity.class);
                startActivity(i);
                finish();
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
            }
        });
    }

}
