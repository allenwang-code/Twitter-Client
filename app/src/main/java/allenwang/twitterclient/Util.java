package allenwang.twitterclient;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by allenwang on 2017/3/4.
 */

public class Util {
    private static final String DATA = "DATA";
    private static final String USER_ID = "USER_ID";
    private static final String USER_NAME = "USER_NAME";


    public static String readId(Context context){
        SharedPreferences settings = context.getSharedPreferences(DATA, 0);
        return settings.getString(USER_ID, "");
    }

    public static void saveId(Context context, String id){
        SharedPreferences settings = context.getSharedPreferences(DATA, 0);
        settings.edit().putString(USER_ID, id).apply();
    }

    public static String readName(Context context){
        SharedPreferences settings = context.getSharedPreferences(DATA, 0);
        return settings.getString(USER_NAME, "");
    }

    public static void saveName(Context context, String name){
        SharedPreferences settings = context.getSharedPreferences(DATA, 0);
        settings.edit().putString(USER_NAME, name).apply();
    }
}
