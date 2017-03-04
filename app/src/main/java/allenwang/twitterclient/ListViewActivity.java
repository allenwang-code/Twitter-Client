package allenwang.twitterclient;

import android.app.ListActivity;
import android.os.Bundle;

import com.twitter.sdk.android.tweetui.CollectionTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;

public class ListViewActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        CollectionTimeline timeline = new CollectionTimeline.Builder()
                .id(836537114044985344L)
                .build();

        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(timeline)
                .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                .build();
        setListAdapter(adapter);
    }
}
