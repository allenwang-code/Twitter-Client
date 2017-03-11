package allenwang.twitterclient.otto;

import com.squareup.otto.Bus;

/**
 * Created by allenwang on 2017/3/11.
 */

public class OttoProvider extends Bus{

    private static OttoProvider instance;

    private OttoProvider(){}

    public static OttoProvider getInstance() {
        if (instance == null) {
            instance = new OttoProvider();
        }
        return instance;
    }
}
