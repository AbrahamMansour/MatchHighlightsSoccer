package com.apps.soccerscores.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Calendar;

@Entity(
        tableName = "favourites",
        foreignKeys = {@ForeignKey(entity = Match.class, parentColumns = {"id"}, childColumns = {"match_id"})},
        indices = {@Index("match_id")}
)
public class Favourites {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long favouritesId = 0;

    @ColumnInfo(name = "match_id")
    public String matchId;

    @ColumnInfo(name = "added_date")
    public Calendar addedDate = Calendar.getInstance();

    public Favourites(String matchId) {
        this.matchId = matchId;
    }
}
