package com.polly.program.bean.response.response;

/**
 * @author linfeizheng
 * @date 2017/3/13 13:10
 */

public class JuejinResponse {

    String title;
    String url;
    String imgUrl;
    String date;

    public JuejinResponse(String title, String url, String imgUrl, String date) {
        this.title = title;
        this.url = url;
        this.imgUrl = imgUrl;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
