package com.test.mybatis.beans;

public class Post {
    private Integer pid;

    private String postName;
    private Integer blogId;
    private Blog blog;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName == null ? null : postName.trim();
    }

    public Blog getBlog() {
        return this.blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}