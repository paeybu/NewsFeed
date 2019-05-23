package com.kabu.kabi.newsfeed;

import android.app.LoaderManager;
import android.content.Loader;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        getLoaderManager().initLoader(0, null,this);

    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        String url = buildUrl();
        return new NewsLoader(this, url);
    }

    private String buildUrl() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority(AUTHORITY)
                .appendPath("search")
                .appendQueryParameter("q", "thailand")
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
}
