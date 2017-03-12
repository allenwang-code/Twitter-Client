package allenwang.twitterclient.http;

import com.twitter.sdk.android.core.TwitterApiClient;

/**
 * Created by allenwang on 2017/3/12.
 */

public class MyTwitterApiCllient extends TwitterApiClient {

    public UserService getUserService() {
        return getService(UserService.class);
    }

}
