package allenwang.twitterclient.viewpager;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.services.StatusesService;

import java.util.ArrayList;
import java.util.List;

import allenwang.twitterclient.R;
import allenwang.twitterclient.recycler_view.EndlessRecyclerViewScrollListener;
import allenwang.twitterclient.recycler_view.TweetsAdapter;
import retrofit2.Call;

public class TimeLineFragment extends Fragment {
    private static final String CATOGORY = "CATOGORY";
    private static final String USER_ID = "ID";
    public static final int TWEET = 0;
    public static final int MENTION = 1;

    private int content = 0;
    private long id = 0;

    private List<Tweet> tweets = new ArrayList<>();
    private RecyclerView recyclerView;
    private TweetsAdapter adapter;

    public TimeLineFragment() {
        // Required empty public constructor
    }

    public static TimeLineFragment newInstance(long id, int content) {
        TimeLineFragment fragment = new TimeLineFragment();
        Bundle args = new Bundle();
        args.putLong(USER_ID, id);
        args.putInt(CATOGORY, content);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            content = getArguments().getInt(CATOGORY);
            id = getArguments().getLong(USER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerView(view);
        loadContents();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void setRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        adapter = new TweetsAdapter(getContext(), tweets);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        EndlessRecyclerViewScrollListener scrollListener =
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

    void loadContents() {
        TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient();
        // Can also use Twitter directly: Twitter.getApiClient()
        StatusesService statusesService = twitterApiClient.getStatusesService();

        Call call = null;
        if (content == TWEET) {
            if (id == -1) {
                call = statusesService.homeTimeline(200, null, null, null, null, null, null);
            } else {
                call = statusesService.userTimeline(id, null, 200, null, null, null, null, null, null);
            }
        } else if (content == MENTION) {
            call = statusesService.mentionsTimeline(200, null, null, null, null, null);
        }

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
