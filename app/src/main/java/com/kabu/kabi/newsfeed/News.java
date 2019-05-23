package com.kabu.kabi.newsfeed;

public class News {
    private String mTitle, mSection, mWebUrl;

    public News(String title, String section, String webUrl) {
        mTitle = title;
        mSection = section;
        mWebUrl = webUrl;
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
}
