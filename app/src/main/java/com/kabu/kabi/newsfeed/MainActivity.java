package com.kabu.kabi.newsfeed;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private ListView mListView;
    private NewsAdapter mAdapter;
    private static final String AUTHORITY = "content.guardianapis.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.list);
        mAdapter = new NewsAdapter(this, R.layout.list_item, new ArrayList<News>());
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News currentItem = mAdapter.getItem(position);
                Uri uri =  Uri.parse(currentItem.getWebUrl());
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });

        getLoaderManager().initLoader(0, null,this);

    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {

        //Get the sharedPref
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String keywordKey = getString(R.string.pref_keyword_key);
        String keywordDefault = getString(R.string.pref_keyword_default);

        String keyword = sharedPreferences.getString(keywordKey, keywordDefault);

        String url = buildUrl(keyword);
        return new NewsLoader(this, url);
    }

    private String buildUrl(String keyword) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority(AUTHORITY)
                .appendPath("search")
                .appendQueryParameter("q", keyword)
                .appendQueryParameter("api-key", "test");

        return builder.build().toString();
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        mAdapter.clear();
        mAdapter.addAll(data);
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mAdapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_settings) {
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
