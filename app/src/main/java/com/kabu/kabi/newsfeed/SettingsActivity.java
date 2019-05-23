package com.kabu.kabi.newsfeed;

import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public static class NewsPreferenceFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings_main);

            //Bind OnPreferenceChangeListener
            Preference keywordPreference = findPreference(getString(R.string.pref_keyword_key));
            keywordPreference.setOnPreferenceChangeListener(this);

            //Get the value from sharedPref
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
            String keyword = sharedPreferences.getString(getString(R.string.pref_keyword_key), "");
            keywordPreference.setSummary(keyword);
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            preference.setSummary(value.toString());
            return true;
        }
    }
}
