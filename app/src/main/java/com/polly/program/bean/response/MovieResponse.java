package com.polly.program.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linfeizheng on 2017/4/14.
 */

public class MovieResponse implements Serializable {

    private int count;
    private int start;
    private int total;
    private String title;
    private List<SubjectsResponse> subjects;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubjectsResponse> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectsResponse> subjects) {
        this.subjects = subjects;
    }
}
