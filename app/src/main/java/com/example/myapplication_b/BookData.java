package com.example.myapplication_b;

import java.io.Serializable;

public class BookData implements Serializable {
    private String title;
    private String urlToimage;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrlToimage() {
        return urlToimage;
    }

    public void setUrlToimage(String urlToimage) {
        this.urlToimage = urlToimage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
