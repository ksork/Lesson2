package ru.inettel.ksork.lesson2;

import android.content.Context;
import android.content.SharedPreferences;


class SettingsHelper {

    public static final int GOOGLE_ID = 1;
    public static final int YANDEX_ID = 2;
    public static final int BING_ID = 3;
    private static final int DEFAULT_ID = GOOGLE_ID;

    private static final String GOOGLE_URL = "https://www.google.ru/search?q=";
    private static final String YANDEX_URL = "https://yandex.ru/search/?text=";
    private static final String BING_URL = "http://www.bing.com/search?q=";
    private static final String DEFAULT_URL = GOOGLE_URL;

    private static final String SEARCH_ENGINE_ID = "SEARCH_ENGINE_ID";

    private static SettingsHelper sInstance;
    private SharedPreferences mSharedPref;

    private SettingsHelper(Context context) {
        mSharedPref = context.getApplicationContext().getSharedPreferences(context.getPackageName(),
                Context.MODE_PRIVATE);
    }

    public static SettingsHelper getInstance(Context context) {
        if (sInstance == null)
            sInstance = new SettingsHelper(context);
        return sInstance;
    }

    public void save(int engineId) {
        mSharedPref.edit().putInt(SEARCH_ENGINE_ID, engineId).commit();
    }

    public int loadEngineId() {
        return mSharedPref.getInt(SEARCH_ENGINE_ID, DEFAULT_ID);
    }

    public String loadEngineUrl() {
        switch (mSharedPref.getInt(SEARCH_ENGINE_ID, DEFAULT_ID)) {
            case GOOGLE_ID:
                return GOOGLE_URL;
            case YANDEX_ID:
                return YANDEX_URL;
            case BING_ID:
                return BING_URL;
            default:
                return DEFAULT_URL;
        }
    }
}
