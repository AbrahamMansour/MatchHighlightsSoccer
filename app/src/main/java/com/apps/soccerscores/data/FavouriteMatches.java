package com.apps.soccerscores.data;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;

public class FavouriteMatches {
    @Embedded
    Match match;

    @Relation(parentColumn = "id", entityColumn = "match_id")
    List<Favourites> favourites = new ArrayList<>();

    public Match getMatch() {
        return match;
    }

    public List<Favourites> getFavourites(){
        return favourites;
    }
}
