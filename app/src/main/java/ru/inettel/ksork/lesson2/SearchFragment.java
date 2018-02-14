package ru.inettel.ksork.lesson2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class SearchFragment extends Fragment {

    private TextView mQuery;
    private Button mSearch;

    private SettingsHelper mSettings;

    public static Fragment newInstance(SettingsHelper settingsHelper) {
        SearchFragment instance = new SearchFragment();
        instance.setSettings(settingsHelper);
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mQuery = view.findViewById(R.id.etQuery);
        mSearch = view.findViewById(R.id.btnSearch);

        mSearch.setOnClickListener(v -> search());
    }

    private void search() {
        String url = mSettings.loadEngineUrl() + mQuery.getText();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    private void setSettings(SettingsHelper settingsHelper) {
        mSettings = settingsHelper;
    }
}
