package com.polly.program.bean.response;

import java.io.Serializable;

/**
 * Created by linfeizheng on 2017/4/14.
 */

public class PersonResponse implements Serializable {

    /**
     * "alt": "https:\/\/movie.douban.com\/celebrity\/1044707\/",
     * "avatars":,
     * "name": "\u9053\u6069\u00b7\u5f3a\u68ee",
     * "id": "1044707"
     */

    private String alt;
    private ImagesResponse avatars;
    private String name;
    private String id;

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public ImagesResponse getAvatars() {
        return avatars;
    }

    public void setAvatars(ImagesResponse avatars) {
        this.avatars = avatars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
