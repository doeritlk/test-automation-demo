package com.doeritlk.test.blog;

public class BlogPost {
    private final String header;
    private final String content;

    public BlogPost(final String header, final String content) {
        this.header = header;
        this.content = content;
    }

    public String header() {
        return header;
    }

    public String content() {
        return content;
    }
}
