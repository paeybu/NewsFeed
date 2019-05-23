package com.kabu.kabi.newsfeed;

import java.text.SimpleDateFormat;
import java.util.Date;

public class News {
    private String mTitle, mSection, mWebUrl, mDate, mFormattedDate;

    public News(String title, String section, String webUrl, String date) {
        mTitle = title;
        mSection = section;
        mWebUrl = webUrl;
        mDate = date;
        mFormattedDate = mDate.substring(0, mDate.length() - 10);
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSection() {
        return mSection;
    }

    public String getWebUrl() {
        return mWebUrl;
    }

    public String getDate() {
        return mDate;
    }

    public String getFormattedDate() {
        return mFormattedDate;
    }
}
