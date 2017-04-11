package allenwang.twitterclient;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.models.Tweet;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComposeActivity extends AppCompatActivity {

    private Button button;
    private TextView tvBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        button = (Button) findViewById(R.id.button);
        tvBody = (TextView) findViewById(R.id.tv_body);
        button.setOnClickListener(new ClickListener());
    }

    class ClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Call<Tweet> call = Twitter.getApiClient().getStatusesService().update(
                    tvBody.getText().toString(),
                    null, null, null, null, null, null, null, null);

            call.enqueue(new Callback<Tweet>() {
                @Override
                public void onResponse(Call<Tweet> call, Response<Tweet> response) {
                    Tweet tweet = response.body();


                    setResult(Activity.RESULT_OK);
                    finish();
                }

                @Override
                public void onFailure(Call<Tweet> call, Throwable t) {
                    Toast.makeText(ComposeActivity.this, "post fail", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
