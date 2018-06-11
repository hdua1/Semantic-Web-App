package com.recipewelldone;

/**
 * Created by Yathartha on 11/12/17.
 */

public class card1 {

    private String imgURL;
    private String title;
    private String uri;

    private int isDish;

    public card1(String title) {
        this.title = title;
    }

    public card1(String imgURL, String title) {
        this.imgURL = imgURL;
        this.title = title;
    }

    public card1(String imgURL, String title, String uri) {
        this.imgURL = imgURL;
        this.title = title;
        this.uri=uri;

    }
    public card1(String imgURL, String title, String uri, int isDish) {
        this.imgURL = imgURL;
        this.title = title;
        this.uri=uri;
        this.isDish = isDish;

    }

    public int getIsDish() {
        return isDish;
    }

    public void setDish(int dish) {
        isDish = dish;
    }

    public String getImgURL() {
        return imgURL;
    }

    public String getUri(){
        return uri;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
