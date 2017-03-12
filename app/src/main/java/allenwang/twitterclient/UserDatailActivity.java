package allenwang.twitterclient;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.twitter.sdk.android.core.models.User;

import allenwang.twitterclient.http.MyTwitterApiCllient;
import allenwang.twitterclient.viewpager.TimeLineFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by allenwang on 2017/3/11.
 */

public class UserDatailActivity extends AppCompatActivity {

    long userId;

    private ImageView pictureImageView;
    private TextView taglineTextView;
    private TextView followerTextView;
    private TextView followingTextView;
    private RelativeLayout twitterFragmentContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        pictureImageView = (ImageView) findViewById(R.id.iv_picture);
        taglineTextView = (TextView) findViewById(R.id.tv_tagline);
        followerTextView = (TextView) findViewById(R.id.tv_followers);
        followingTextView = (TextView) findViewById(R.id.tv_following);
        twitterFragmentContainer = (RelativeLayout) findViewById(R.id.tweet_frament_container);

        userId = getIntent().getLongExtra(Constant.KEY_USER_ID, 0);
        Toast.makeText(this, String.valueOf(userId), Toast.LENGTH_SHORT).show();

        getUserInfo();
        getTwiiterFragment();
    }

    private void getTwiiterFragment() {
        TimeLineFragment fragment = TimeLineFragment.newInstance(userId, TimeLineFragment.TWEET);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.tweet_frament_container, fragment, "")
                .commit();
    }

    private void getUserInfo(){
        MyTwitterApiCllient client = new MyTwitterApiCllient();
        Call call = client.getUserService().show(userId, null, false);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if (user == null) return;
                Glide.with(UserDatailActivity.this).load(user.profileImageUrl).into(pictureImageView);
                taglineTextView.setText(user.description);
                followerTextView.setText("#follower : " +user.followersCount);
                followingTextView.setText("#following : " +user.friendsCount);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }


}
