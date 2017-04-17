package com.polly.program.bean.response;

import java.io.Serializable;

/**
 * Created by linfeizheng on 2017/4/14.
 */

public class ImagesResponse implements Serializable {

    /**
     * "small": "http://img7.doubanio.com\/view\/movie_poster_cover\/ipst\/public\/p2444256500.webp",
     * "large": "http://img7.doubanio.com\/view\/movie_poster_cover\/lpst\/public\/p2444256500.webp",
     * "medium": "http://img7.doubanio.com\/view\/movie_poster_cover\/spst\/public\/p2444256500.webp"
     */

    private String small;
    private String large;
    private String medium;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }
}
