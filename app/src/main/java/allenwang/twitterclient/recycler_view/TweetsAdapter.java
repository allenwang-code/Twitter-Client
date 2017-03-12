package allenwang.twitterclient.recycler_view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import allenwang.twitterclient.Constant;
import allenwang.twitterclient.R;
import allenwang.twitterclient.UserDatailActivity;
import allenwang.twitterclient.Util;

/**
 * Created by allenwang on 2017/2/26.
 */

public class TweetsAdapter extends
        RecyclerView.Adapter<TweetsAdapter.ViewHolder> {

    // Store a member variable for the contacts
    private List<Tweet> mTweets;
    // Store the context for easy access
    private Context mContext;

    // Pass in the contact array into the constructor
    public TweetsAdapter(Context context, List<Tweet> tweets) {
        mTweets = tweets;
        mContext = context;
    }

    public void updateData(List<Tweet> ts){
        mTweets.clear();
        mTweets.addAll(ts);
        this.notifyDataSetChanged();
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.cell, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get the data model based on position
        final Tweet tweet = mTweets.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, UserDatailActivity.class);
                i.putExtra(Constant.KEY_USER_ID, tweet.user.getId());
                mContext.startActivity(i);
            }
        });

        Glide.with(getContext()).load(tweet.user.profileImageUrlHttps).into(holder.imageView);
        holder.tvBody.setText(tweet.text);
        holder.tvScreenName.setText("@" + tweet.user.screenName);
        holder.tvUserName.setText(tweet.user.name);
        holder.tvTime.setText(Util.getRelativeTimeAgo(tweet.createdAt));
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final View itemView;
        ImageView imageView;
        TextView tvUserName;
        TextView tvScreenName;
        TextView tvBody;
        TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.iv_headShot);
            tvUserName = (TextView) itemView.findViewById(R.id.tv_userName);
            tvScreenName = (TextView) itemView.findViewById(R.id.tv_userScreenName);
            tvBody = (TextView) itemView.findViewById(R.id.tv_body);
            tvTime = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {


            }
        }
    };
}