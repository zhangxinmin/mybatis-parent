package com.test.mybatis.beans;

import java.util.List;

public class Blog {
    private Integer bid;

    private String name;

    private Integer authorId;
    private List posts;

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public List getPosts() {
        return posts;
    }

    public void setPosts(List posts) {
        this.posts = posts;
    }
}