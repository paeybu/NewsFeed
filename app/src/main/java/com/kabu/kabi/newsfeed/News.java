package com.kabu.kabi.newsfeed;

public class News {
    private String mTitle, mSection;

    public News(String title, String section) {
        mTitle = title;
        mSection = section;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSection() {
        return mSection;
    }
}
