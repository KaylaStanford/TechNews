package com.example.android.technews;

public class News {
    private String webTitle;
    private String sectionName;
    private String webPublicationDate;
    private String webUrl;
    private String author;

    public News(String webTitle, String sectionName,  String webPublicationDate, String webUrl, String author ) {

        this.webTitle = webTitle;
        this.sectionName = sectionName;
        this.webPublicationDate = webPublicationDate;
        this.webUrl = webUrl;
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

