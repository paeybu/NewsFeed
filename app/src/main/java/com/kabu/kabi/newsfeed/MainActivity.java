package com.kabu.kabi.newsfeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private NewsAdapter mAdapter;
    private static final String NEWS_URL = "https://content.guardianapis.com/search?q=thailand&api-key=test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = findViewById(R.id.list);

        List<News> newsList = QueryUtils.extractFromJson(QueryUtils.SAMPLE_JSON);

        mAdapter = new NewsAdapter(this, R.layout.list_item, newsList);
        mListView.setAdapter(mAdapter);

    }
}
