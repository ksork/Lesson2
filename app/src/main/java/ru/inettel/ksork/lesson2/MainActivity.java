package ru.inettel.ksork.lesson2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private SettingsHelper mSettings;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_main);
        mFragmentManager = getSupportFragmentManager();
        mSettings = SettingsHelper.getInstance(getApplicationContext());
        mFragmentManager.beginTransaction().add(R.id.fragmentContainer, MainFragment.newInstance()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSettings:
                showMessage(R.string.settings);
                switchFragment(SettingsFragment.newInstance(mSettings));
                break;
            case R.id.menuSearch:
                showMessage(R.string.search);
                switchFragment(SearchFragment.newInstance(mSettings));
                break;
            case R.id.menuExit:
                showMessage(R.string.exit);
                finish();
        }
        return true;
    }

    private void showMessage(@StringRes int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void switchFragment(Fragment fragment) {
        mFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }
}
