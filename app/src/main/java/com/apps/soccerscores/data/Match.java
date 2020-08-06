package com.apps.soccerscores.data;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.databinding.BindingAdapter;
import androidx.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

@Entity(tableName = "matches")
public class Match {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private String title;
    private String side1;
    private String side2;
    private int growZoneNumber;
    private int wateringInterval = 7;
    private String thumbnail = "";
    private String date;
    private String highlights;
    private String competition;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSide1() {
        return side1;
    }

    public void setSide1(String side1) {
        this.side1 = side1;
    }

    public String getSide2() {
        return side2;
    }

    public void setSide2(String side2) {
        this.side2 = side2;
    }

    public int getGrowZoneNumber() {
        return growZoneNumber;
    }

    public void setGrowZoneNumber(int growZoneNumber) {
        this.growZoneNumber = growZoneNumber;
    }

    public int getWateringInterval() {
        return wateringInterval;
    }

    public void setWateringInterval(int wateringInterval) {
        this.wateringInterval = wateringInterval;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


    @BindingAdapter("imageFromUrl")
    public static void imageFromUrl(ImageView view, String imageUrl) {
        if (imageUrl !=  null && !imageUrl.isEmpty())
            Glide.with(view.getContext()).load(imageUrl).into(view);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }
}
