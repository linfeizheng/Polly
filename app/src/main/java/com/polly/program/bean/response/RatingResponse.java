package com.polly.program.bean.response;

import java.io.Serializable;

/**
 * Created by linfeizheng on 2017/4/14.
 */

public class RatingResponse implements Serializable {

    /**
     * "max": 10,
     * "average": 7.3,
     * "stars": "40",
     * "min": 0
     */

    private Integer max;
    private Double average;
    private String stars;
    private Integer min;

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }
}
