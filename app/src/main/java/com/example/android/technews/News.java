package com.example.android.technews;

public class News {
    private String webTitle;
    private String sectionName;
    private String webPublicationDate;
    private String webUrl;
    private String author;

    public String REQUEST_URL =
            "https://content.guardianapis.com/search?q=tech&show-tags=contributor&api-key=fc97d1fd-2093-4623-86a7-6eb1dfd07a09";

    public News(String webTitle, String sectionName,  String webPublicationDate, String webUrl, String author ) {
        this.webTitle = webTitle;
        this.sectionName = sectionName;
        this.webUrl = webUrl;
        this.webPublicationDate = webPublicationDate;
        this.author = author;
    }

    public String getWebTitle() {
        return webTitle;
    }
    public String getSectionName() {
        return sectionName;
    }
    public String getWebPublicationDate() {
        return webPublicationDate;
    }
    public String getWebUrl() {
        return webUrl;
    }
    public String getAuthor() {
        return author;
    }
}

