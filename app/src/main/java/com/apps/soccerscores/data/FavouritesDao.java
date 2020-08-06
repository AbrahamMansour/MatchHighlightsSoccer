package com.apps.soccerscores.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;
@Dao
public interface FavouritesDao {
    @Query("SELECT * FROM favourites")
    LiveData<List<Favourites>> getFavourites();

    @Query("SELECT * FROM favourites WHERE id = :gardenPlantingId")
    LiveData<Favourites> getGardenPlanting(long gardenPlantingId);

    @Query("SELECT * FROM favourites WHERE match_id = :plantId")
    LiveData<Favourites> getGardenPlantingForPlant(String plantId);

    @Transaction
    @Query("SELECT * FROM matches")
    LiveData<List<FavouriteMatches>> getPlantAndGardenPlantings();

    @Insert
    long insertFavourite(Favourites favourites);

    @Query("DELETE from favourites where match_id = :plantId")
    void deleteFavourites(String plantId);

}
