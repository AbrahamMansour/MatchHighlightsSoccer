package com.apps.soccerscores.data;

import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import com.apps.soccerscores.adapters.FavouriteMatch;

import java.util.List;

public class FavouritesRepository {

    static FavouritesRepository instance;
    FavouritesDao favouritesDao;

    public FavouritesRepository(FavouritesDao favouritesDao) {
        this.favouritesDao = favouritesDao;
    }

    public static FavouritesRepository getInstance(FavouritesDao favouritesDao) {
        if (instance == null) {
            synchronized (Favourites.class) {
                if (instance == null) {
                    instance = new FavouritesRepository(favouritesDao);
                }
            }
        }
        return instance;
    }

    public void addToFavourites(String matchTitle) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Favourites favourites = new Favourites(matchTitle);
                favouritesDao.insertFavourite(favourites);
            }
        });

    }

    public void removeFromFavourites(String matchTitle) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                favouritesDao.deleteFavourites(matchTitle);
            }
        });

    }

    public LiveData<Favourites> getGardenPlantingForPlant(String plantId) {
        return favouritesDao.getGardenPlantingForPlant(plantId);
    }

    public LiveData<List<Favourites>> getFavourites() {
        return favouritesDao.getFavourites();
    }

    public LiveData<List<FavouriteMatches>> getPlantAndGardenPlantings(){
        return favouritesDao.getPlantAndGardenPlantings();
    }


//    private ExecutorService IO_EXECUTOR = Executors.newSingleThreadExecutor();
//
//    void runOnIoThread() {
//        IO_EXECUTOR.execute();
//    }



}
