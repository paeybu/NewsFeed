package com.kabu.kabi.newsfeed;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<News>> {
    private String mNewsUrl;

    public NewsLoader(Context context, String newsUrl) {
        super(context);
        mNewsUrl = newsUrl;
    }

    @Override
    public List<News> loadInBackground() {
        return QueryUtils.fetchData(mNewsUrl);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
