package com.polly.program.bean.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class VideoResponse extends HashMap<String, List<VideoResponse.Video>> {

    /*"mp4Hd_url": "http://flv2.bn.netease.com/videolib3/1501/28/wlncJ2098/HD/wlncJ2098-mobile.mp4",
    "cover": "http://img3.cache.netease.com/3g/2015/1/12/20150112103113e10a2.png",
    "title": "袁腾飞《这个历史挺靠谱》",
    "replyBoard": "videonews_bbs",
    "playCount": 1050521,
    "sectiontitle": "第15集:科举制让屌丝逆袭",
    "replyid": "AG4JHJUR008535RB",
    "mp4_url": "http://flv2.bn.netease.com/videolib3/1501/28/wlncJ2098/SD/wlncJ2098-mobile.mp4",
    "length": 591,
    "m3u8Hd_url": "http://flv2.bn.netease.com/videolib3/1501/28/wlncJ2098/HD/movie_index.m3u8",
    "latest": "",
    "vid": "VAG4JHJUR",
    "ptime": "2015-01-28 15:59:31",
    "m3u8_url": "http://flv2.bn.netease.com/videolib3/1501/28/wlncJ2098/SD/movie_index.m3u8"*/

    public class Video implements Serializable{
        private String topicImg;
        private String topicName;
        private String topicDesc;
        private String mp4Hd_url;
        private String cover;
        private String title;
        private String replyBoard;
        private int playCount;
        private String sectiontitle;
        private String replyid;
        private String mp4_url;
        private int length;
        private String m3u8Hd_url;
        private String vid;
        private String ptime;
        private String m3u8_url;

        public String getTopicImg() {
            return topicImg;
        }

        public void setTopicImg(String topicImg) {
            this.topicImg = topicImg;
        }

        public String getTopicName() {
            return topicName;
        }

        public void setTopicName(String topicName) {
            this.topicName = topicName;
        }

        public String getTopicDesc() {
            return topicDesc;
        }

        public void setTopicDesc(String topicDesc) {
            this.topicDesc = topicDesc;
        }

        public String getMp4Hd_url() {
            return mp4Hd_url;
        }

        public void setMp4Hd_url(String mp4Hd_url) {
            this.mp4Hd_url = mp4Hd_url;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPlayCount() {
            return playCount;
        }

        public void setPlayCount(int playCount) {
            this.playCount = playCount;
        }

        public String getReplyBoard() {
            return replyBoard;
        }

        public void setReplyBoard(String replyBoard) {
            this.replyBoard = replyBoard;
        }

        public String getSectiontitle() {
            return sectiontitle;
        }

        public void setSectiontitle(String sectiontitle) {
            this.sectiontitle = sectiontitle;
        }

        public String getReplyid() {
            return replyid;
        }

        public void setReplyid(String replyid) {
            this.replyid = replyid;
        }

        public String getMp4_url() {
            return mp4_url;
        }

        public void setMp4_url(String mp4_url) {
            this.mp4_url = mp4_url;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }

        public String getM3u8Hd_url() {
            return m3u8Hd_url;
        }

        public void setM3u8Hd_url(String m3u8Hd_url) {
            this.m3u8Hd_url = m3u8Hd_url;
        }

        public String getVid() {
            return vid;
        }

        public void setVid(String vid) {
            this.vid = vid;
        }

        public String getM3u8_url() {
            return m3u8_url;
        }

        public void setM3u8_url(String m3u8_url) {
            this.m3u8_url = m3u8_url;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }
    }

}
