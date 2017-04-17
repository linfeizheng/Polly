package com.polly.program.bean.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linfeizheng on 2017/4/14.
 */

public class SubjectsResponse implements Serializable {

    /**
     * "rating": ,
     * "genres": ["\u52a8\u4f5c", "\u72af\u7f6a"],
     * "title": "\u901f\u5ea6\u4e0e\u6fc0\u60c58",
     * "casts":,
     * "collect_count": 4837,
     * "original_title": "The Fate of the Furious",
     * "subtype": "movie",
     * "directors": ,
     * "year": "2017",
     * "images":,
     * "alt": "https:\/\/movie.douban.com\/subject\/26260853\/",
     * "id": "26260853"
     */

    private RatingResponse rating;
    private List<String> genres;
    private String title;
    private List<PersonResponse> casts;
    private int collect_count;
    private String original_title;
    private String subtype;
    private List<PersonResponse> directors;
    private String year;
    private ImagesResponse images;
    private String alt;
    private String id;

    public RatingResponse getRating() {
        return rating;
    }

    public void setRating(RatingResponse rating) {
        this.rating = rating;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PersonResponse> getCasts() {
        return casts;
    }

    public void setCasts(List<PersonResponse> casts) {
        this.casts = casts;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public List<PersonResponse> getDirectors() {
        return directors;
    }

    public void setDirectors(List<PersonResponse> directors) {
        this.directors = directors;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ImagesResponse getImages() {
        return images;
    }

    public void setImages(ImagesResponse images) {
        this.images = images;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
