package com.example.dfoor.Models;

public class ItemModels {

    public String textItem, descItem;
    public int imgItem;
    public int ratingItem;

    public ItemModels(String textItem, String descItem, int imgItem, int ratingItem) {
        this.textItem = textItem;
        this.descItem = descItem;
        this.imgItem = imgItem;
        this.ratingItem = ratingItem;
    }

    public ItemModels() {

    }

    public String getTextItem() {
        return textItem;
    }

    public void setTextItem(String textItem) {
        this.textItem = textItem;
    }

    public String getDescItem() {
        return descItem;
    }

    public void setDescItem(String descItem) {
        this.descItem = descItem;
    }

    public int getImgItem() {
        return imgItem;
    }

    public void setImgItem(int imgItem) {
        this.imgItem = imgItem;
    }

    public int getRatingItem() {
        return ratingItem;
    }

    public void setRatingItem(int ratingItem) {
        this.ratingItem = ratingItem;
    }
}
