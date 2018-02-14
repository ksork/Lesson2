package ru.inettel.ksork.lesson2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class SettingsFragment extends Fragment {

    private RadioGroup mSearchEngine;
    private RadioButton mGoogle;
    private RadioButton mYandex;
    private RadioButton mBing;

    private SettingsHelper mSettings;

    public static Fragment newInstance(SettingsHelper settingsHelper) {
        SettingsFragment instance = new SettingsFragment();
        instance.setSettings(settingsHelper);
        return instance;
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

        switch (mSettings.loadEngineId()) {
            case SettingsHelper.GOOGLE_ID:
                mGoogle.setChecked(true);
                break;
            case SettingsHelper.YANDEX_ID:
                mYandex.setChecked(true);
                break;
            case SettingsHelper.BING_ID:
                mBing.setChecked(true);
        }

        mSearchEngine.setOnCheckedChangeListener((group, checkedId) -> changeSearchEngine(checkedId));
    }

    private void changeSearchEngine(int checkedId) {
        switch (checkedId) {
            case R.id.rbGoogle:
                mSettings.save(SettingsHelper.GOOGLE_ID);
                break;
            case R.id.rbYandex:
                mSettings.save(SettingsHelper.YANDEX_ID);
                break;
            case R.id.rbBing:
                mSettings.save(SettingsHelper.BING_ID);
        }
    }

    private void setSettings(SettingsHelper settingsHelper) {
        mSettings = settingsHelper;
    }
}
