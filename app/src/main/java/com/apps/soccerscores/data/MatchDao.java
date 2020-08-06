package com.apps.soccerscores.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MatchDao {
    @Insert(onConflict = REPLACE)
    void insertAll(List<Match> matches);

    @Query("SELECT * FROM matches")
    LiveData<List<Match>> getMatches();

    @Query("SELECT * FROM matches WHERE id = :title")
    LiveData<Match> getMatches(String title);
}
