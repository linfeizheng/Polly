package com.polly.program.bean.response;

import java.util.List;

/**
 * Created by linfeizheng on 2017/4/17.
 */

public class MovieDetailResponse {

    /**
     * {
     * "rating": ,
     * "reviews_count": 3227,
     * "wish_count": 59414,
     * "douban_site": "",
     * "year": "1994",
     * "images": ,
     * "alt": "https:\/\/movie.douban.com\/subject\/1295644\/",
     * "id": "1295644",
     * "mobile_url": "https:\/\/movie.douban.com\/subject\/1295644\/mobile",
     * "title": "\u8fd9\u4e2a\u6740\u624b\u4e0d\u592a\u51b7",
     * "do_count": null,
     * "share_url": "http:\/\/m.douban.com\/movie\/subject\/1295644",
     * "seasons_count": null,
     * "schedule_url": "",
     * "episodes_count": null,
     * "countries": ["\u6cd5\u56fd"],
     * "genres": ["\u5267\u60c5", "\u52a8\u4f5c", "\u72af\u7f6a"],
     * "collect_count": 1018091,
     * "casts":,
     * "current_season": null,
     * "original_title": "L\u00e9on",
     * "summary": "\u91cc\u6602\uff08\u8ba9\u00b7\u96f7\u8bfa\u9970\uff09\u662f\u540d\u5b64\u72ec\u7684\u804c\u4e1a\u6740\u624b\uff0c\u53d7\u4eba\u96c7\u4f63\u3002\u4e00\u5929\uff0c\u90bb\u5c45\u5bb6\u5c0f\u59d1\u5a18\u9a6c\u8482\u5c14\u8fbe\uff08\u7eb3\u5854\u4e3d\u00b7\u6ce2\u7279\u66fc\u9970)\u6572\u5f00\u4ed6\u7684\u623f\u95e8\uff0c\u8981\u6c42\u5728\u4ed6\u90a3\u91cc\u6682\u907f\u6740\u8eab\u4e4b\u7978\u3002\u539f\u6765\u90bb\u5c45\u5bb6\u7684\u4e3b\u4eba\u662f\u8b66\u65b9\u7f09\u6bd2\u7ec4\u7684\u773c\u7ebf\uff0c\u53ea\u56e0\u8d2a\u6c61\u4e86\u4e00\u5c0f\u5305\u6bd2\u54c1\u800c\u906d\u6076\u8b66\uff08\u52a0\u91cc\u00b7\u5965\u5fb7\u66fc\u9970\uff09\u6740\u5bb3\u5168\u5bb6\u7684\u60e9\u7f5a\u3002\u9a6c\u8482\u5c14\u8fbe\u5f97\u5230\u91cc\u6602\u7684\u7559\u6551\uff0c\u5e78\u514d\u4e8e\u96be\uff0c\u5e76\u7559\u5728\u91cc\u6602\u90a3\u91cc\u3002\u91cc\u6602\u6559\u5c0f\u5973\u5b69\u4f7f\u67aa\uff0c\u5979\u6559\u91cc\u6602\u6cd5\u6587\uff0c\u4e24\u4eba\u5173\u7cfb\u65e5\u8d8b\u4eb2\u5bc6\uff0c\u76f8\u5904\u878d\u6d3d\u3002\n\u5973\u5b69\u60f3\u7740\u53bb\u62a5\u4ec7\uff0c\u53cd\u5012\u88ab\u6293\uff0c\u91cc\u6602\u53ca\u65f6\u8d76\u5230\uff0c\u5c06\u5973\u5b69\u6551\u56de\u3002\u6df7\u6742\u7740\u54c0\u6028\u60c5\u4ec7\u7684\u6b63\u90aa\u4e4b\u6218\u6e10\u6b21\u5347\u7ea7\uff0c\u66f4\u5927\u7684\u51b2\u7a81\u5728\u6240\u96be\u514d\u2026\u2026\u00a9\u8c46\u74e3",
     * "subtype": "movie",
     * "directors": ,
     * "comments_count": 178278,
     * "ratings_count": 780080,
     * "aka": ["\u6740\u624b\u83b1\u6602", "\u7ec8\u6781\u8ffd\u6740\u4ee4(\u53f0)", "\u6740\u624b\u91cc\u6602", "Leon", "Leon: The Professional"]
     * }
     */

    private RatingResponse rating;
    private int reviews_count;
    private int wish_count;
    private String douban_site;
    private String year;
    private ImagesResponse images;
    private String alt;
    private String id;
    private String mobile_url;
    private String title;
    private int do_count;
    private String share_url;
    private int seasons_count;
    private String schedule_url;
    private int episodes_count;
    private int collect_count;
    private String current_season;
    private String original_title;
    private String summary;
    private String subtype;
    private int comments_count;
    private Integer ratings_count;
    private List<String> countries;
    private List<String> genres;
    private List<PersonResponse> casts;
    private List<PersonResponse> directors;
    private List<String> aka;

    public RatingResponse getRating() {
        return rating;
    }

    public void setRating(RatingResponse rating) {
        this.rating = rating;
    }

    public int getReviews_count() {
        return reviews_count;
    }

    public void setReviews_count(int reviews_count) {
        this.reviews_count = reviews_count;
    }

    public int getWish_count() {
        return wish_count;
    }

    public void setWish_count(int wish_count) {
        this.wish_count = wish_count;
    }

    public String getDouban_site() {
        return douban_site;
    }

    public void setDouban_site(String douban_site) {
        this.douban_site = douban_site;
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

    public String getMobile_url() {
        return mobile_url;
    }

    public void setMobile_url(String mobile_url) {
        this.mobile_url = mobile_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDo_count() {
        return do_count;
    }

    public void setDo_count(int do_count) {
        this.do_count = do_count;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public int getSeasons_count() {
        return seasons_count;
    }

    public void setSeasons_count(int seasons_count) {
        this.seasons_count = seasons_count;
    }

    public String getSchedule_url() {
        return schedule_url;
    }

    public void setSchedule_url(String schedule_url) {
        this.schedule_url = schedule_url;
    }

    public int getEpisodes_count() {
        return episodes_count;
    }

    public void setEpisodes_count(int episodes_count) {
        this.episodes_count = episodes_count;
    }

    public int getCollect_count() {
        return collect_count;
    }

    public void setCollect_count(int collect_count) {
        this.collect_count = collect_count;
    }

    public String getCurrent_season() {
        return current_season;
    }

    public void setCurrent_season(String current_season) {
        this.current_season = current_season;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getComments_count() {
        return comments_count;
    }

    public void setComments_count(int comments_count) {
        this.comments_count = comments_count;
    }

    public Integer getRatings_count() {
        return ratings_count;
    }

    public void setRatings_count(Integer ratings_count) {
        this.ratings_count = ratings_count;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public List<PersonResponse> getCasts() {
        return casts;
    }

    public void setCasts(List<PersonResponse> casts) {
        this.casts = casts;
    }

    public List<PersonResponse> getDirectors() {
        return directors;
    }

    public void setDirectors(List<PersonResponse> directors) {
        this.directors = directors;
    }

    public List<String> getAka() {
        return aka;
    }

    public void setAka(List<String> aka) {
        this.aka = aka;
    }
}
