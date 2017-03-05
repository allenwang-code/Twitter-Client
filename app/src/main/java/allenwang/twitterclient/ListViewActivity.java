package allenwang.twitterclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import java.util.ArrayList;
import java.util.List;

import allenwang.twitterclient.recycler_view.EndlessRecyclerViewScrollListener;
import allenwang.twitterclient.recycler_view.TweetsAdapter;
import retrofit2.Call;

public class ListViewActivity extends AppCompatActivity {

    static final int POST_REQUEST_CODE = 100;
    static final String PARCELABLE_NAME = "tweet";

    static List<Tweet> tweets = new ArrayList<Tweet>();
    RecyclerView recyclerView;
    TweetsAdapter adapter;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        loadTweets();
        setToolBarItem();
        setRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == POST_REQUEST_CODE) {
            if (resultCode != Activity.RESULT_OK) { return; }
            adapter.notifyDataSetChanged();
        }
    }

    private void setRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new TweetsAdapter(this, tweets);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        EndlessRecyclerViewScrollListener  scrollListener =
                new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                    @Override
                    public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                        // Triggered only when new data needs to be appended to the list
                        // Add whatever code is needed to append new items to the bottom of the list
                        loadNextDataFromApi(page);
                    }
                };
        // Adds the scroll listener to RecyclerView
        recyclerView.addOnScrollListener(scrollListener);
    }

    void loadTweets() {
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        // Can also use Twitter directly: Twitter.getApiClient()
        StatusesService statusesService = twitterApiClient.getStatusesService();
        Call call = statusesService.homeTimeline(200, null, null, null, null, null, null);
        call.enqueue(new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                tweets = result.data;
                adapter.updateData(tweets);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failure(TwitterException exception) {

            }
        });
    }

    public void loadNextDataFromApi(int offset) {

    }

    private void setToolBarItem() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(onMenuItemClick);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setLogo(R.drawable.tw__ic_logo_default);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.tweet:
                    Intent i = new Intent();
                    i.setClass(ListViewActivity.this, ComposeActivity.class);
                    startActivityForResult(i, POST_REQUEST_CODE);
                    break;
            }
            return true;
        }
    };
}
