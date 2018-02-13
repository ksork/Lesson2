package ru.inettel.ksork.lesson2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class SettingsFragment extends Fragment {

    private static final int GOOGLE = 1;
    private static final int YANDEX = 2;
    private static final int BING = 3;
    private static final String SEARCH_ENGINE_KEY = "SEARCH_ENGINE_KEY";


    private RadioGroup mSearchEngine;
    private RadioButton mGoogle;
    private RadioButton mYandex;
    private RadioButton mBing;

    private SharedPreferences mPref;

    public static Fragment newInstance() {
        return new SettingsFragment();
    }

    private void changeSearchEngine(int checkedId) {
        int searchEngine = 0;
        switch (checkedId) {
            case R.id.rbGoogle:
                searchEngine = GOOGLE;
                break;
            case R.id.rbYandex:
                searchEngine = YANDEX;
                break;
            case R.id.rbBing:
                searchEngine = BING;
        }
        mPref.edit().putInt(SEARCH_ENGINE_KEY, searchEngine).commit();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSearchEngine = view.findViewById(R.id.rgSearchEngine);
        mGoogle = view.findViewById(R.id.rbGoogle);
        mYandex = view.findViewById(R.id.rbYandex);
        mBing = view.findViewById(R.id.rbBing);
        mPref = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        switch (mPref.getInt(SEARCH_ENGINE_KEY, GOOGLE)) {
            case GOOGLE:
                mGoogle.setChecked(true);
                break;
            case YANDEX:
                mYandex.setChecked(true);
                break;
            case BING:
                mBing.setChecked(true);
        }
        mSearchEngine.setOnCheckedChangeListener((group, checkedId) -> changeSearchEngine(checkedId));
    }
}
