package allenwang.twitterclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    List<Tweet> tweets = new ArrayList<Tweet>();
    RecyclerView recyclerView;
    TweetsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new TweetsAdapter(this, tweets);
        recyclerView.setAdapter(adapter);

        loadTweets();

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
}
