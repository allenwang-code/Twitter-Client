package allenwang.twitterclient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by allenwang on 2017/3/11.
 */

public class UserDatailActivity extends AppCompatActivity {

    long userId;

    private ImageView pictureImageView;
    private TextView taglineTextView;
    private TextView followerTextView;
    private TextView followingTextView;
    private RecyclerView tweetRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        pictureImageView = (ImageView) findViewById(R.id.iv_picture);
        taglineTextView = (TextView) findViewById(R.id.tv_tagline);
        followerTextView = (TextView) findViewById(R.id.tv_followers);
        followingTextView = (TextView) findViewById(R.id.tv_following);
        tweetRecyclerView = (RecyclerView) findViewById(R.id.tweet_recycler_view);

        userId = getIntent().getLongExtra(Constant.KEY_USER_ID, 0);
        Toast.makeText(this, String.valueOf(userId), Toast.LENGTH_SHORT).show();
    }

    private void getUserInfo(){


//        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
//        // Can also use Twitter directly: Twitter.getApiClient()
//        Twitter.getApiClient().
//
//                getAccountService().verifyCredentials()
//        Call call = statusesService.homeTimeline(200, null, null, null, null, null, null);
//        call.enqueue(new Callback<List<Tweet>>() {
//            @Override
//            public void success(Result<List<Tweet>> result) {
//                tweets = result.data;
//                adapter.updateData(tweets);
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void failure(TwitterException exception) {
//
//            }
//        });
    }


}
