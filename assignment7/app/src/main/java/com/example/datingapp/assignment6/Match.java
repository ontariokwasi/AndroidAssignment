package com.example.datingapp.assignment6;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Match implements Serializable {
    private String imageUrl;
    private boolean liked;
    private String name;
    private String uid;
    private String lat;
    private String longitude;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public static Match fromJSonArray(JSONObject jsonObject) throws JSONException {
        Match match = new Match();
        match.setImageUrl(jsonObject.getString("imageUrl"));
        match.setName(jsonObject.getString("name"));
        match.setUid(jsonObject.getString("uid"));
        match.setLat(jsonObject.getString("lat"));
        match.setLongitude(jsonObject.getString("longitude"));
        match.setLiked(jsonObject.getBoolean("liked"));
        return match;
    }
}
