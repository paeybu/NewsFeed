package com.kabu.kabi.newsfeed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        News currentNews = getItem(position);

        String title = currentNews.getTitle();
        String section = currentNews.getSection();

        TextView titleTv = convertView.findViewById(R.id.news_title);
        TextView sectionTv = convertView.findViewById(R.id.news_section);

        return convertView;
    }
}
