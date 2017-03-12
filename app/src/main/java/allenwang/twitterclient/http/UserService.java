package allenwang.twitterclient.http;

import com.twitter.sdk.android.core.models.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by allenwang on 2017/3/12.
 */

public interface UserService {

    @GET("https://api.twitter.com/1.1/users/show.json")
    Call<User> show(@Query("id") long id,
                          @Query("screen_name") String screenName,
                          @Query("include_entities") boolean isIncludeEntities);
}
