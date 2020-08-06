package com.apps.soccerscores.data.model;

import java.util.List;

public class SoccerMatch {

    private String title;
    private String embed;
    private String url;
    private String thumbnail;
    private String date;
    Side side1;
    Side side2;
    Competition competition;
    List<MatchVideo> videos;

    // Getter Methods

    public String getTitle() {
        return title;
    }

    public String getEmbed() {
        return embed;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getDate() {
        return date;
    }

    public Side getSide1() {
        return side1;
    }

    public Side getSide2() {
        return side2;
    }

    public Competition getCompetition() {
        return competition;
    }

    public List<MatchVideo> getVideos() {
        return videos;
    }

    public void setVideos(List<MatchVideo> videos) {
        this.videos = videos;
    }

    // Setter Methods

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEmbed(String embed) {
        this.embed = embed;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSide1(Side side1) {
        this.side1 = side1;
    }

    public void setSide2(Side side2) {
        this.side2 = side2;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}

