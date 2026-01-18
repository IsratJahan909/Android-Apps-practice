package com.israt.apiexample.entity;

import com.israt.apiexample.util.Constants;

public class Category {
    private  Long id;

    private String name;

    private String imageUrl;

    private  Long parentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public String getFullImageUrl() {
        if (imageUrl == null) return  null;
        if (imageUrl.startsWith("http")) return  imageUrl;
        return Constants.BASE_URL + imageUrl;
    }

    public Category(String name, String imageUrl, Long parentId) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.parentId = parentId;
    }

    public Category() {
    }

    public Category(Long id, String name, String imageUrl, Long parentId) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Catagory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
