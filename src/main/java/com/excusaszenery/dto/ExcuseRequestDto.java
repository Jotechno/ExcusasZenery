package com.excusaszenery.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExcuseRequestDto {
    private String excuseText;
    private Integer likes;
    private Integer dislikes;
    private Boolean isPublic = true;
    private Integer userId;
    private Integer categoryID;

    public String getExcuseText() {
        return excuseText;
    }

    public void setExcuseText(String excuseText) {
        this.excuseText = excuseText;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }
}
