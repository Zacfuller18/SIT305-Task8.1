package com.zfuller.task81_zacharyfuller.model;

public class Video {
    private String URL;
    private int videoId;
    private int userId;

    public Video(int userId, String URL) {
        this.userId = userId;
        this.URL = URL;
    }

    public Video(int videoId, int userId, String URL) {
        this.videoId = videoId;
        this.userId = userId;
        this.URL = URL;
    }

    public int getUserId() { return userId; }

    public int getVideoId() { return videoId; }

    public String getURL() { return URL; }

    public void setURL(String URL) { this.URL = URL; }
}
