package com.rit.test;

public class BlogPost {
    private final String title;
    private final String content;


    public BlogPost(final String title, final String content) {
        this.title = title;
        this.content = content;
    }

    public String title() {
        return title;
    }

    public String content() {
        return content;
    }
}
